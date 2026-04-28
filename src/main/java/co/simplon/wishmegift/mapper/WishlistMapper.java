package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.entity.Wishlist;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WishlistMapper {

    public WishlistDTO toDTO(Wishlist wishlist) {
        WishlistDTO dto = new WishlistDTO();
        dto.setId(wishlist.getId());
        dto.setTitle(wishlist.getTitle());

        List<UserDTO> sharedWith = wishlist.getSharedWith().stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setEmail(user.getEmail());
                    return userDTO;
                })
                .toList();

        dto.setSharedWith(sharedWith);
        return dto;
    }
}
