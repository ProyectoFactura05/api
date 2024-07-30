package org.izpo.apigestionfacturas.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginResquestDTO {
    @Email(message = "tiene que tener la estructura de un ")
    private String email;
    @NotBlank(message = "El password no puede ser nulo")
    private String password;
}
