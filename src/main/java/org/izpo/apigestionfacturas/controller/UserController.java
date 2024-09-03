package org.izpo.apigestionfacturas.controller;

import org.izpo.apigestionfacturas.model.dto.LoginRequestDTO;
import org.izpo.apigestionfacturas.model.dto.LoginResponseDTO;
import org.izpo.apigestionfacturas.model.dto.UserRequestDTO;
import org.izpo.apigestionfacturas.model.dto.UserResponseDTO;
import org.izpo.apigestionfacturas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Validated UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.registerUser(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Validated LoginRequestDTO loginRequestDTO){
        LoginResponseDTO token = userService.loginUser(loginRequestDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
