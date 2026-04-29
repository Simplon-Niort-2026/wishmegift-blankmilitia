package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.ShareDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;
import co.simplon.wishmegift.service.ShareService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShareController.class)
class ShareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ShareService shareService;

    @Test
    void share_shouldReturn201_whenValid() throws Exception {
        WishlistShareRequestDTO requestDTO = new WishlistShareRequestDTO();
        requestDTO.setTargetUserEmail("ami@example.com");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(2L);
        userDTO.setEmail("ami@example.com");

        ShareDTO responseDTO = new ShareDTO();
        responseDTO.setId(1L);
        responseDTO.setWishlistId(1L);
        responseDTO.setWishlistTitle("Ma liste de Noël");
        responseDTO.setUser(userDTO);

        when(shareService.share(eq(1L), eq(1L), any(WishlistShareRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/wishlists/1/shares")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("UTILISATEUR-CONNECTE", "1")
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.wishlistTitle").value("Ma liste de Noël"))
                .andExpect(jsonPath("$.user.email").value("ami@example.com"));
    }

    @Test
    void share_shouldReturn400_whenEmailBlank() throws Exception {
        WishlistShareRequestDTO requestDTO = new WishlistShareRequestDTO();
        requestDTO.setTargetUserEmail("");

        mockMvc.perform(post("/wishlists/1/shares")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("UTILISATEUR-CONNECTE", "1")
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void share_shouldReturn400_whenEmailInvalid() throws Exception {
        WishlistShareRequestDTO requestDTO = new WishlistShareRequestDTO();
        requestDTO.setTargetUserEmail("pasunemail");

        mockMvc.perform(post("/wishlists/1/shares")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("UTILISATEUR-CONNECTE", "1")
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void share_shouldReturn500_whenAlreadyShared() throws Exception {
        WishlistShareRequestDTO requestDTO = new WishlistShareRequestDTO();
        requestDTO.setTargetUserEmail("ami@example.com");

        when(shareService.share(eq(1L), eq(1L), any(WishlistShareRequestDTO.class)))
                .thenThrow(new RuntimeException("Cette liste est déjà partagée avec cet utilisateur."));

        mockMvc.perform(post("/wishlists/1/shares")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("UTILISATEUR-CONNECTE", "1")
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Cette liste est déjà partagée avec cet utilisateur."));
    }

    @Test
    void getByWishlist_shouldReturn200_withShares() throws Exception {
        ShareDTO shareDTO = new ShareDTO();
        shareDTO.setId(1L);
        shareDTO.setWishlistId(1L);

        when(shareService.getByWishlist(1L)).thenReturn(List.of(shareDTO));

        mockMvc.perform(get("/wishlists/1/shares"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void getByWishlist_shouldReturn200_withEmptyList() throws Exception {
        when(shareService.getByWishlist(99L)).thenReturn(List.of());

        mockMvc.perform(get("/wishlists/99/shares"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}