package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    UserDTO getUserById(Long id);

    UserDTO createUser(UserCreateDTO dto);
}
