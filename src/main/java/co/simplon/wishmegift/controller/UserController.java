package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.mapper.UserMapper;
import co.simplon.wishmegift.repository.UserRepository;
import co.simplon.wishmegift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserService userServiceInjected, UserRepository userRepositoryInjected, UserMapper userMapperInjected) {
        this.userService = userServiceInjected;
        this.userRepository = userRepositoryInjected;
        this.userMapper = userMapperInjected;
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::mapToUserDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDto) {
        UserDTO created = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
