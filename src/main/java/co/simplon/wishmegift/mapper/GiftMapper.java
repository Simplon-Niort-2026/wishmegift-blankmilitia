package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.GiftDTO;
import co.simplon.wishmegift.entity.Gift;
import org.springframework.stereotype.Component;

@Component
public class GiftMapper {

    public GiftDTO toDTO(Gift gift) {
        GiftDTO dto = new GiftDTO();
        dto.setId(gift.getId());
        dto.setName(gift.getName());
        dto.setWishLevel(gift.getWishLevel());
        dto.setPrice(gift.getPrice());
        dto.setBook(gift.getBook());
        dto.setWishlistName(gift.getWishlist().getTitle());

        return dto;
    }
}
