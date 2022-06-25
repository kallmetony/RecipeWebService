package com.aaronr92.recipewebservice.repository;

import com.aaronr92.recipewebservice.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query
    User findUserByEmail(String email);
}
