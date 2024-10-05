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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{email}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable("email") String username){
        UserResponseDTO user = userService.getUserByEmail(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
