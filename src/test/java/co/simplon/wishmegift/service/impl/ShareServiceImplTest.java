package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.dto.ShareDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.dto.WishlistShareRequestDTO;
import co.simplon.wishmegift.entity.Share;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.mapper.ShareMapper;
import co.simplon.wishmegift.repository.ShareRepository;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShareServiceImplTest {

    @Mock
    private ShareRepository shareRepository;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ShareMapper shareMapper;

    @InjectMocks
    private ShareServiceImpl shareService;

    private User owner;
    private User targetUser;
    private Wishlist wishlist;
    private WishlistShareRequestDTO dto;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setId(1L);
        owner.setEmail("owner@example.com");
        owner.setFirstname("Alice");
        owner.setLastname("Martin");
        owner.setPassword("hashed");

        targetUser = new User();
        targetUser.setId(2L);
        targetUser.setEmail("chloe.dubois@example.com");
        targetUser.setFirstname("Chloé");
        targetUser.setLastname("Dubois");
        targetUser.setPassword("hashed");

        wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setTitle("Ma liste de Noël");
        wishlist.setUser(owner);

        dto = new WishlistShareRequestDTO();
        dto.setTargetUserEmail("chloe.dubois@example.com");
    }

    // --- share() ---

    @Test
    void share_shouldReturnShareDTO_whenValid() {
        Share savedShare = new Share();
        savedShare.setId(1L);
        savedShare.setWishlist(wishlist);
        savedShare.setUser(targetUser);

        ShareDTO expectedDTO = new ShareDTO();
        expectedDTO.setId(1L);
        expectedDTO.setWishlistId(1L);
        expectedDTO.setWishlistTitle("Ma liste de Noël");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(2L);
        userDTO.setEmail("chloe.dubois@example.com");
        expectedDTO.setUser(userDTO);

        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));
        when(userRepository.findByEmail("chloe.dubois@example.com")).thenReturn(Optional.of(targetUser));
        when(shareRepository.existsByWishlistIdAndUserId(1L, 2L)).thenReturn(false);
        when(shareRepository.save(any(Share.class))).thenReturn(savedShare);
        when(shareMapper.toDTO(savedShare)).thenReturn(expectedDTO);

        ShareDTO result = shareService.share(1L, 1L, dto);

        assertThat(result).isNotNull();
        assertThat(result.getWishlistId()).isEqualTo(1L);
        assertThat(result.getUser().getEmail()).isEqualTo("chloe.dubois@example.com");
        verify(shareRepository).save(any(Share.class));
    }

    @Test
    void share_shouldThrow_whenWishlistNotFound() {
        when(wishlistRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> shareService.share(99L, 1L, dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Liste introuvable.");

        verify(shareRepository, never()).save(any());
    }

    @Test
    void share_shouldThrow_whenTargetUserEmailNotFound() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));
        when(userRepository.findByEmail("chloe.dubois@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> shareService.share(1L, 1L, dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Aucun utilisateur associé à cet email.");

        verify(shareRepository, never()).save(any());
    }

    @Test
    void share_shouldThrow_whenAlreadyShared() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));
        when(userRepository.findByEmail("chloe.dubois@example.com")).thenReturn(Optional.of(targetUser));
        when(shareRepository.existsByWishlistIdAndUserId(1L, 2L)).thenReturn(true);

        assertThatThrownBy(() -> shareService.share(1L, 1L, dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cette liste est déjà partagée avec cet utilisateur.");

        verify(shareRepository, never()).save(any());
    }

    // --- getByWishlist() ---

    @Test
    void getByWishlist_shouldReturnListOfShareDTO() {
        Share share = new Share();
        share.setId(1L);
        share.setWishlist(wishlist);
        share.setUser(targetUser);

        ShareDTO shareDTO = new ShareDTO();
        shareDTO.setId(1L);

        when(shareRepository.findByWishlistId(1L)).thenReturn(List.of(share));
        when(shareMapper.toDTO(share)).thenReturn(shareDTO);

        List<ShareDTO> result = shareService.getByWishlist(1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }
}