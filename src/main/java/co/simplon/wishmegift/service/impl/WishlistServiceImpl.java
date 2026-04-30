package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.mapper.WishlistMapper;
import co.simplon.wishmegift.repository.WishlistRepository;
import co.simplon.wishmegift.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
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
                wishlistRepository.findByIdWithUser(id)
                        .orElseThrow(() -> new RuntimeException("Liste introuvable"))
        );
    }

    @Override
    public void delete(Long id) {
        wishlistRepository.deleteById(id);
    }
}
