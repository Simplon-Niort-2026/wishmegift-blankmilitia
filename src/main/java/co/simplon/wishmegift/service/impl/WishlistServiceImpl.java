package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.mapper.WishlistMapper;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.repository.WishlistRepository;
import co.simplon.wishmegift.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               UserRepository userRepository,
                               WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public WishlistDTO create(Wishlist wishlist) {
        return wishlistMapper.toDTO(wishlistRepository.save(wishlist));
    }

    @Override
    public List<WishlistDTO> getAll() {
        return wishlistRepository.findAll().stream()
                .map(wishlistMapper::toDTO)
                .toList();
    }

    @Override
    public WishlistDTO getById(Long id) {
        return wishlistMapper.toDTO(
                wishlistRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Liste introuvable"))
        );
    }

    @Override
    public void shareWishlist(Long wishlistId, Long currentUserId, WishlistShareRequestDTO dto) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Liste de souhait introuvable."));

        User targetUser = userRepository.findByEmail(dto.getTargetUserEmail())
                .orElseThrow(() -> new RuntimeException("Aucun utilisateur associé à cet email."));

        if (wishlist.getSharedWith().contains(targetUser)) {
            throw new RuntimeException("Cette liste est déjà partagée avec cet utilisateur.");
        }

        wishlist.getSharedWith().add(targetUser);
        wishlistRepository.save(wishlist);
    }

    @Override
    public void delete(Long id) {
        wishlistRepository.deleteById(id);
    }
}
