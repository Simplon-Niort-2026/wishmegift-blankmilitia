package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistController.class)
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private WishlistService wishlistService;

    @Test
    void getAll_shouldReturn200_withListOfWishlists() throws Exception {
        WishlistDTO dto = new WishlistDTO();
        dto.setId(1L);
        dto.setTitle("Ma liste de Noël");

        when(wishlistService.getAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/wishlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Ma liste de Noël"));
    }

    @Test
    void getAll_shouldReturn200_withEmptyList() throws Exception {
        when(wishlistService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/wishlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getById_shouldReturn200_whenFound() throws Exception {
        WishlistDTO dto = new WishlistDTO();
        dto.setId(1L);
        dto.setTitle("Ma liste de Noël");

        when(wishlistService.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/wishlists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Ma liste de Noël"));
    }

    @Test
    void getById_shouldReturn500_whenNotFound() throws Exception {
        when(wishlistService.getById(99L)).thenThrow(new RuntimeException("Liste introuvable"));

        mockMvc.perform(get("/wishlists/99"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Liste introuvable"));
    }

    @Test
    void create_shouldReturn201_whenValid() throws Exception {
        Wishlist wishlist = new Wishlist();
        wishlist.setTitle("Ma liste de Noël");

        WishlistDTO responseDTO = new WishlistDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Ma liste de Noël");

        when(wishlistService.create(any(Wishlist.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/wishlists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wishlist)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Ma liste de Noël"));
    }

    @Test
    void delete_shouldReturn204_whenDeleted() throws Exception {
        doNothing().when(wishlistService).delete(1L);

        mockMvc.perform(delete("/wishlists/1"))
                .andExpect(status().isNoContent());
    }
}