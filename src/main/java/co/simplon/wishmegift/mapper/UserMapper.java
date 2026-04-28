package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserDTO;
import co.simplon.wishmegift.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toEntity(UserCreateDTO userCreateDto) {
        User user = new User();
        user.setFirstname(userCreateDto.getFirstname());
        user.setLastname(userCreateDto.getLastname());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(BCrypt.hashpw(userCreateDto.getPassword(), BCrypt.gensalt(12)));
        return user;
    }

    public UserDTO mapToUserDto(User user) {
        return new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }
}
