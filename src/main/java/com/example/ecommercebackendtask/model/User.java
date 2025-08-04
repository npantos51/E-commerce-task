package com.example.ecommercebackendtask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users", schema = "shop")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "username field cannot be NULL")
    @Size(max = 50, message = "The input is too long")
    private String username;

    @NotNull(message = "email field cannot be NULL")
    @Size(max = 50, message = "The input is too long")
    @Email
    private String email;

    @NotNull(message = "password field cannot be NULL")
    @Size(max = 50, message = "The input is too long")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private Cart cart;


}
