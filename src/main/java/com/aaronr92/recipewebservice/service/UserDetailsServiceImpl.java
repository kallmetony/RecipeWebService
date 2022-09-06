package com.aaronr92.recipewebservice.service;

import com.aaronr92.recipewebservice.entities.User;
import com.aaronr92.recipewebservice.repository.UserRepository;
import com.aaronr92.recipewebservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found " + email);
        }
        return new UserDetailsImpl(user);
    }

    public void register(User user) {
        if (repository.findUserByEmail(user.getEmail()) == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            repository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
