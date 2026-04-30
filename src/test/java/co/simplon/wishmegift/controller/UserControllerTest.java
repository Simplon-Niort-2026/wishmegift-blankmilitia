package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.mapper.UserMapper;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private UserMapper userMapper;

    @Test
    void getUserById_shouldReturn200_whenUserExists() throws Exception {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setEmail("alice@example.com");

        when(userService.getUserById(1L)).thenReturn(dto);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test
    void getUserById_shouldReturn500_whenUserNotFound() throws Exception {
        when(userService.getUserById(99L)).thenThrow(new RuntimeException("Utilisateur introuvable."));

        mockMvc.perform(get("/users/99"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Utilisateur introuvable."));
    }

    @Test
    void createUser_shouldReturn201_whenValid() throws Exception {
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setFirstname("Alice");
        createDTO.setLastname("Martin");
        createDTO.setEmail("alice@example.com");
        createDTO.setPassword("1234@!azerTY");
        createDTO.setConfirmPassword("1234@!azerTY");

        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(1L);
        responseDTO.setEmail("alice@example.com");

        when(userService.createUser(any(UserCreateDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test
    void createUser_shouldReturn400_whenPasswordsDoNotMatch() throws Exception {
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setFirstname("Alice");
        createDTO.setLastname("Martin");
        createDTO.setEmail("alice@example.com");
        createDTO.setPassword("1234@!azerTY");
        createDTO.setConfirmPassword("azerTY@!1234");

        when(userService.createUser(any(UserCreateDTO.class)))
                .thenThrow(new IllegalArgumentException("Les mots de passe ne correspondent pas."));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Les mots de passe ne correspondent pas."));
    }
}
