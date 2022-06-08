package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.UserLoginDTO;
import com.example.mobilelele.model.dto.UserRegisterDTO;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.repository.UserRepository;

import com.example.mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        User newUser = new User()
                .setActive(true)
                .setEmail(userRegisterDTO.getEmail())
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(newUser);

        login(newUser);
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> userOpt = this.userRepository
                .findByEmail(userLoginDTO.getUsername());

        if (userOpt.isEmpty()) {
            LOGGER.info("User with name [{}] not found", userLoginDTO.getUsername());
            return false;
        }
        var rawPassword = userLoginDTO.getPassword();
        var encodedPassword = userOpt.get().getPassword();

        boolean success = passwordEncoder
                .matches(rawPassword, encodedPassword);

        if (success) {
            login(userOpt.get());
        } else {
            logout();
        }

        return success;
    }

    private void login(User user) {
        currentUser
                .setLoggedIn(true);
        currentUser
                .setName(user.getFirstName() + " " + user.getLastName());
    }

    public void logout() {
        currentUser.clear();
    }
}
