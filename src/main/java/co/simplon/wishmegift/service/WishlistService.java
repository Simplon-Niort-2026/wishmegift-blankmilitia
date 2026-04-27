package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.Wishlist;

import java.util.List;

public interface WishlistService {
    Wishlist create(Wishlist wishlist);

    List<Wishlist> getAll();

    Wishlist getById(Long id);

    void delete(Long id);
}
