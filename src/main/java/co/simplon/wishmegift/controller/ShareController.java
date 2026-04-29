package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.ShareDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;
import co.simplon.wishmegift.service.ShareService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists/{wishlistId}/shares")
public class ShareController {

    private final ShareService shareService;

    public ShareController(ShareService shareServiceInjected) {
        this.shareService = shareServiceInjected;
    }

    @PostMapping
    public ResponseEntity<ShareDTO> share(
            @PathVariable Long wishlistId,
            @RequestHeader("UTILISATEUR-CONNECTE") Long currentUserId,
            @RequestBody @Valid WishlistShareRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shareService.share(wishlistId, currentUserId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ShareDTO>> getByWishlist(@PathVariable Long wishlistId) {
        return ResponseEntity.ok(shareService.getByWishlist(wishlistId));
    }
}