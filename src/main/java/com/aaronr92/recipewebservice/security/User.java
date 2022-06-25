package com.aaronr92.recipewebservice.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @Email
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @Column
    @NotBlank
    @Size(min = 8)
    private String password;

    @JsonIgnore
    @Column
    private String role;
}
