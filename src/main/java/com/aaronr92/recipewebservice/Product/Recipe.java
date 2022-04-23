package com.aaronr92.recipewebservice.Product;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String category;

    @JsonIgnore
    @Column
    @UpdateTimestamp
    public LocalDateTime date;

    @NotBlank
    @Column
    private String description;

    @NotEmpty
    @ElementCollection
    private List<String> ingredients = new ArrayList<>();

    @NotEmpty
    @ElementCollection
    private List<String> directions = new ArrayList<>();

    @JsonIgnore
    @Column(name = "USER_ID", updatable = false)
    private String email;

    @JsonProperty
    public LocalDateTime getDate() {
        return date;
    }

    @JsonIgnore
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
