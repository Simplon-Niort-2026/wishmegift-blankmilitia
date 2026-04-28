package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.entity.Wishlist;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper {

    public WishlistDTO toDTO(Wishlist wishlist) {
        WishlistDTO dto = new WishlistDTO();
        dto.setId(wishlist.getId());
        dto.setTitle(wishlist.getTitle());
        return dto;
    }
}
