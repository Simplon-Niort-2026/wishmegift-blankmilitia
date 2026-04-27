package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.repository.WishlistRepository;
import co.simplon.wishmegift.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;

    }

    @Override
    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public List<Wishlist> getAll() {
        return wishlistRepository.findAll();
    }

    @Override
    public Wishlist getById(Long id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liste introuvable"));
    }

    @Override
    public void delete(Long id) {
        wishlistRepository.deleteById(id);
    }
}
