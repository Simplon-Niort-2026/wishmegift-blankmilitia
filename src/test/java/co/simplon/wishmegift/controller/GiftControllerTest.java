package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.service.GiftService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GiftController.class)
class GiftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private GiftService giftService;

    private Gift buildGift(Long id) {
        Gift gift = new Gift();
        gift.setId(id);
        gift.setName("LEGO Star Wars");
        gift.setDescription("Super cadeau");
        gift.setLink("https://lego.com");
        gift.setWishLevel(5);
        gift.setPrice(59.99);
        gift.setBook(false);
        return gift;
    }

    @Test
    void getAllGifts_shouldReturn200_withListOfGifts() throws Exception {
        when(giftService.getAllGifts()).thenReturn(List.of(buildGift(1L)));

        mockMvc.perform(get("/gifts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("LEGO Star Wars"));
    }

    @Test
    void getAllGifts_shouldReturn200_withEmptyList() throws Exception {
        when(giftService.getAllGifts()).thenReturn(List.of());

        mockMvc.perform(get("/gifts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getGiftById_shouldReturn200_whenFound() throws Exception {
        when(giftService.getGiftById(1L)).thenReturn(buildGift(1L));

        mockMvc.perform(get("/gifts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("LEGO Star Wars"));
    }

    @Test
    void getGiftById_shouldReturn200_withNull_whenNotFound() throws Exception {
        when(giftService.getGiftById(99L)).thenReturn(null);

        mockMvc.perform(get("/gifts/99"))
                .andExpect(status().isOk());
    }

    @Test
    void addGift_shouldReturn201_whenValid() throws Exception {
        Gift gift = buildGift(null);
        Gift saved = buildGift(1L);

        when(giftService.save(any(Gift.class))).thenReturn(saved);

        mockMvc.perform(post("/gifts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gift)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("LEGO Star Wars"));
    }

    @Test
    void updateGift_shouldReturn200_whenValid() throws Exception {
        Gift existing = buildGift(1L);
        Gift updated = buildGift(1L);
        updated.setName("LEGO Technic");

        when(giftService.getGiftById(1L)).thenReturn(existing);
        when(giftService.updateGift(any(Gift.class), any(Gift.class))).thenReturn(updated);

        mockMvc.perform(put("/gifts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("LEGO Technic"));
    }

    @Test
    void deleteGift_shouldReturn200_whenDeleted() throws Exception {
        doNothing().when(giftService).deleteGift(1L);

        mockMvc.perform(delete("/gifts/1"))
                .andExpect(status().isOk());
    }
}