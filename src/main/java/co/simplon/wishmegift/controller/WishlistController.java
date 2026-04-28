package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.WishlistDTO;
import co.simplon.wishmegift.dto.WishlistShareDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;
import co.simplon.wishmegift.entity.Wishlist;

import co.simplon.wishmegift.service.WishlistService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<WishlistDTO> create(@RequestBody Wishlist wishlist) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(wishlistService.create(wishlist));
    }

    @PostMapping("/{wishlistId}/share")
    public ResponseEntity<WishlistDTO> share(
            @PathVariable Long id,
            @RequestHeader("UTILISATEUR-CONNECTE") Long currentUserId,
            @RequestBody @Valid WishlistShareRequestDTO dto
    ) {
        wishlistService.shareWishlist(id, currentUserId, dto);
        return ResponseEntity.ok(wishlistService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<WishlistDTO>> getAll() {
        return ResponseEntity.ok(wishlistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        wishlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}