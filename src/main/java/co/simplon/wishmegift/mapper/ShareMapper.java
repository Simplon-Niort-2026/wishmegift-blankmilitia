package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.ShareDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.entity.Share;
import org.springframework.stereotype.Component;

@Component
public class ShareMapper {

    public ShareDTO toDTO(Share share) {
        ShareDTO dto = new ShareDTO();
        dto.setId(share.getId());
        dto.setWishlistId(share.getWishlist().getId());
        dto.setWishlistTitle(share.getWishlist().getTitle());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(share.getUser().getId());
        userDTO.setEmail(share.getUser().getEmail());
        dto.setUser(userDTO);

        return dto;
    }
}