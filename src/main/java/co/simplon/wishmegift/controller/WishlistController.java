package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistServiceInjected) {
        this.wishlistService = wishlistServiceInjected;
    }

    @GetMapping
    public ResponseEntity<List<WishlistDTO>> getAll() {
        return ResponseEntity.ok(wishlistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getById(id));
    }

    @PostMapping
    public ResponseEntity<WishlistDTO> create(@RequestBody Wishlist wishlist) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(wishlistService.create(wishlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        wishlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}