package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.Share;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.repository.ShareRepository;
import co.simplon.wishmegift.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ShareRepository shareRepository;





    public WishlistService(WishlistRepository wishlistRepository, ShareRepository shareRepository) {
        this.wishlistRepository = wishlistRepository;
        this.shareRepository = shareRepository;

    }

    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public List<Wishlist> getSharedWithUserWishlists(Long userId) {
        return shareRepository.findByUserId(userId).stream().map(Share::getWishlist).toList();
    }

    public List<Wishlist> getAll() {
        return wishlistRepository.findAll();
    }

    public Wishlist getById(Long id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liste introuvable"));
    }

    public void delete(Long id) {
        wishlistRepository.deleteById(id);
    }
}
