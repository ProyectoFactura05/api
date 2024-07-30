package org.izpo.apigestionfacturas.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "numero_de_contacto", nullable = false)
    private String numeroDeContacto;
    @Column(name="email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "estado")
    private String estado;
    @Column(name = "rol")
    private String rol;
}
