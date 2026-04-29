package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.dto.ShareDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;
import co.simplon.wishmegift.entity.Share;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.mapper.ShareMapper;
import co.simplon.wishmegift.repository.ShareRepository;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.repository.WishlistRepository;
import co.simplon.wishmegift.service.ShareService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ShareMapper shareMapper;

    public ShareServiceImpl(ShareRepository shareRepository,
                            WishlistRepository wishlistRepository,
                            UserRepository userRepository,
                            ShareMapper shareMapper) {
        this.shareRepository = shareRepository;
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.shareMapper = shareMapper;
    }

    @Override
    public ShareDTO share(Long wishlistId, Long currentUserId, WishlistShareRequestDTO dto) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Liste introuvable."));

        User targetUser = userRepository.findByEmail(dto.getTargetUserEmail())
                .orElseThrow(() -> new RuntimeException("Aucun utilisateur associé à cet email."));

        if (shareRepository.existsByWishlistIdAndUserId(wishlistId, targetUser.getId())) {
            throw new RuntimeException("Cette liste est déjà partagée avec cet utilisateur.");
        }

        Share share = new Share();
        share.setWishlist(wishlist);
        share.setUser(targetUser);

        return shareMapper.toDTO(shareRepository.save(share));
    }

    @Override
    public List<ShareDTO> getByWishlist(Long wishlistId) {
        return shareRepository.findByWishlistId(wishlistId).stream()
                .map(shareMapper::toDTO)
                .toList();
    }
}
