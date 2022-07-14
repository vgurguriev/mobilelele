package com.example.mobilelele.service;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.user.MobileleUserDetails;
import com.example.mobilelele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.
                findByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(User user) {

        return new MobileleUserDetails(
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.
                        getUserRoles().
                        stream().
                        map(this::map).
                        toList()
        );
    }
    private GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.
                        getUserRole().name());
    }
}
