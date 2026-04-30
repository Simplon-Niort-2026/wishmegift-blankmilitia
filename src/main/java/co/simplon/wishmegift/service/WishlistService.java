package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.entity.Wishlist;

import java.util.List;

public interface WishlistService {

    WishlistDTO create(Wishlist wishlist);

    List<WishlistDTO> getAll();

    WishlistDTO getById(Long id);

    void delete(Long id);
}
