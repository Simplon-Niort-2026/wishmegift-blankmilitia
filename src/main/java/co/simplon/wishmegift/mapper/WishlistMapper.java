package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.entity.Theme;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.Wishlist;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class WishlistMapper {

    public WishlistDTO toDTO(Wishlist wishlist) {
        WishlistDTO dto = new WishlistDTO();
        dto.setId(wishlist.getId());
        dto.setTitle(wishlist.getTitle());
        dto.setDescription(wishlist.getDescription());
        dto.setEventDate(wishlist.getEventDate());
        dto.setTheme(wishlist.getTheme());
        dto.setUserFirstname(wishlist.getUser().getFirstname());
        dto.setUserLastname(wishlist.getUser().getLastname());
        dto.setGiftsNames(wishlist.getGifts().stream()
                .map(Gift::getName).toList());

        return dto;
    }
}
