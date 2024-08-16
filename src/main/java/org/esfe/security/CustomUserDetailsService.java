package org.esfe.security;

import org.esfe.models.user;
import org.esfe.repository.iuserrepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private iuserrepository userRepository;

    public CustomUserDetailsService(iuserrepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        user user = userRepository.findByEmail(email);

        if (user != null) {
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().getName()) // Asegúrate de que el rol es un String o un valor compatible
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
