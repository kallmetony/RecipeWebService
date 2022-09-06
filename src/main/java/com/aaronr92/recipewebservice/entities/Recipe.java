package com.aaronr92.recipewebservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "RECIPES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @JsonIgnore
    @UpdateTimestamp
    public LocalDateTime date;

    @NotBlank
    private String description;

    @NotEmpty
    @ElementCollection
    private List<String> ingredients = new ArrayList<>();

    @NotEmpty
    @ElementCollection
    private List<String> directions = new ArrayList<>();

    @JsonIgnore
    @Column(name = "user_email", updatable = false)
    private String email;

    @JsonProperty
    public LocalDateTime getDate() {
        return date;
    }
}
