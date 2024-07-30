package org.izpo.apigestionfacturas.service;

import lombok.AllArgsConstructor;
import org.izpo.apigestionfacturas.exception.BadRequestException;
import org.izpo.apigestionfacturas.mapper.UserMapper;
import org.izpo.apigestionfacturas.model.dto.UserRequestDTO;
import org.izpo.apigestionfacturas.model.dto.UserResponseDTO;
import org.izpo.apigestionfacturas.model.entity.User;
import org.izpo.apigestionfacturas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        User newUser = new User();
        newUser.setEmail(userRequestDTO.getEmail());
        newUser.setPassword(userRequestDTO.getPassword());
        newUser.setNombre(userRequestDTO.getNombre());
        newUser.setNumeroDeContacto(userRequestDTO.getNumeroDeContacto());

        if(userRepository.existByEmail(userRequestDTO.getEmail())){
            throw new BadRequestException("El email ya existe");
        }
        newUser.setRol("Usuario Comun");
        newUser.setEstado("false");
        return userMapper.convertToDTO(userRepository.save(newUser));
    }
}
