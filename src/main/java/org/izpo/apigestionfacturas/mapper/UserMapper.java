package org.izpo.apigestionfacturas.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.izpo.apigestionfacturas.model.dto.UserResponseDTO;
import org.izpo.apigestionfacturas.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public User convertToEntity(UserResponseDTO userResponseDTO){
        return modelMapper.map(userResponseDTO, User.class);
    }
    public UserResponseDTO convertToDTO(User user){
        return modelMapper.map(user, UserResponseDTO.class);
    }
    public List<UserResponseDTO> convertToListDTO(List<User> users){
        return users.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
