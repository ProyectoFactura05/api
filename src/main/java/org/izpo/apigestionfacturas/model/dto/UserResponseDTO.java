package org.izpo.apigestionfacturas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String nombre;
    private String numeroDeContacto;
    private String email;
    private String password;
    private String rol;
}
