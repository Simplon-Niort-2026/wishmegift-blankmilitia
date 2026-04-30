package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.ShareDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.service.ShareService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists/{wishlistId}/shares")
public class ShareController {

    private final ShareService shareService;
    private final UserRepository userRepository;

    public ShareController(ShareService shareServiceInjected, UserRepository userRepositoryInjected) {
        this.shareService = shareServiceInjected;
        this.userRepository = userRepositoryInjected;
    }

    @PostMapping
    public ResponseEntity<ShareDTO> share(
            @PathVariable Long wishlistId,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid WishlistShareRequestDTO dto
    ) {
        Long currentUserId = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"))
                .getId();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shareService.share(wishlistId, currentUserId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ShareDTO>> getByWishlist(@PathVariable Long wishlistId) {
        return ResponseEntity.ok(shareService.getByWishlist(wishlistId));
    }
}