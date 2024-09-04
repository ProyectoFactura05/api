package org.izpo.apigestionfacturas.service;

import lombok.AllArgsConstructor;
import org.izpo.apigestionfacturas.exception.BadRequestException;
import org.izpo.apigestionfacturas.mapper.UserMapper;
import org.izpo.apigestionfacturas.model.dto.LoginResponseDTO;
import org.izpo.apigestionfacturas.model.dto.LoginRequestDTO;
import org.izpo.apigestionfacturas.model.dto.UserRequestDTO;
import org.izpo.apigestionfacturas.model.dto.UserResponseDTO;
import org.izpo.apigestionfacturas.model.entity.User;
import org.izpo.apigestionfacturas.repository.UserRepository;
import org.izpo.apigestionfacturas.security.jwt.CustomerDetailsService;
import org.izpo.apigestionfacturas.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerDetailsService customerDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

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
    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

            if (authentication.isAuthenticated()) {
                if (customerDetailsService.getUserDetails().getEstado().equalsIgnoreCase("true")) {
                    String token = jwtUtil.generateToken(customerDetailsService.getUserDetails().getEmail(),
                            customerDetailsService.getUserDetails().getRol());
                    LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
                    loginResponseDTO.setToken(token);
                    return loginResponseDTO;
                }
            }
            throw new BadRequestException("Las credenciales son incorrectas o el usuario no está activo.");
        } catch (Exception e) {
            throw new BadRequestException("Error durante la autenticación: " + e.getMessage());
        }
    }
    public UserResponseDTO getUserByEmail(String email){
        User user = userRepository.findByUsername(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email:" + email));
        return userMapper.convertToDTO(user);
    }
}
