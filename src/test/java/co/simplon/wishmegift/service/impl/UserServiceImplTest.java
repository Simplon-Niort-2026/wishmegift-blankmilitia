package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.mapper.UserMapper;
import co.simplon.wishmegift.repository.UserRepository;
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
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;
    private UserCreateDTO createDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstname("Alice");
        user.setLastname("Martin");
        user.setEmail("alice@example.com");
        user.setPassword("hashed");

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("alice@example.com");

        createDTO = new UserCreateDTO();
        createDTO.setFirstname("Alice");
        createDTO.setLastname("Martin");
        createDTO.setEmail("alice@example.com");
        createDTO.setPassword("motdepasse123");
        createDTO.setConfirmPassword("motdepasse123");
    }

    // --- findAll() ---

    @Test
    void findAll_shouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> result = userService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    void findAll_shouldReturnEmptyList_whenNoUsers() {
        when(userRepository.findAll()).thenReturn(List.of());

        List<User> result = userService.findAll();

        assertThat(result).isEmpty();
    }

    // --- getUserById() ---

    @Test
    void getUserById_shouldReturnUserDTO_whenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    void getUserById_shouldThrow_whenUserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Utilisateur introuvable.");
    }

    // --- createUser() ---

    @Test
    void createUser_shouldReturnUserDTO_whenValid() {
        when(userRepository.existsByEmail("alice@example.com")).thenReturn(false);
        when(userMapper.toEntity(createDTO)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDTO);

        UserDTO result = userService.createUser(createDTO);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("alice@example.com");
        verify(userRepository).save(user);
    }

    @Test
    void createUser_shouldThrow_whenPasswordsDoNotMatch() {
        createDTO.setConfirmPassword("autremotdepasse");

        assertThatThrownBy(() -> userService.createUser(createDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Les mots de passe ne correspondent pas.");

        verify(userRepository, never()).save(any());
    }

    @Test
    void createUser_shouldThrow_whenEmailAlreadyExists() {
        when(userRepository.existsByEmail("alice@example.com")).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(createDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("L'email que vous avez choisi existe déjà.");

        verify(userRepository, never()).save(any());
    }
}