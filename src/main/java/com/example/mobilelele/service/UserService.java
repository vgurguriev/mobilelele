package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.UserRegisterDTO;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.mapper.UserMapper;
import com.example.mobilelele.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        User newUser = userMapper.userDtoToUserEntity(userRegisterDTO)
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(newUser);

        login(newUser);
    }
    private void login(User user) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }
}
