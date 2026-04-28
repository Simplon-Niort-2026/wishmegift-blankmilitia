package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.ShareDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;

import java.util.List;

public interface ShareService {
    ShareDTO share(Long wishlistId, Long currentUserId, WishlistShareRequestDTO dto);

    List<ShareDTO> getByWishlist(Long wishlistId);
}
