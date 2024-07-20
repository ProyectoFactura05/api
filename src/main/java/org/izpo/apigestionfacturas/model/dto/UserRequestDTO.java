package org.izpo.apigestionfacturas.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El nombre no puede ser nulo")
    private String nombre;
    @NotBlank(message = "El numero de contaxcto no peude ser nulo")
    @Size(min = 9, max = 15, message = "El numero tiene que estar entre 9 y 15 digitos")
    @Pattern(regexp = "\\d+", message = "El numero de contacto solo puede contener dígitos")
    private String numeroDeContacto;
    @Email(message = "El email tioene que tener la estructura de ***@example.com")
    @NotBlank(message = "El email no puede ser nulo")
    private String email;
    @NotBlank(message = "El password no puede ser nulo")
    private String password;
}
