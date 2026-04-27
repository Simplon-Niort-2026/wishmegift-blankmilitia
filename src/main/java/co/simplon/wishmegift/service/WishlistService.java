package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;

    }

    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
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
