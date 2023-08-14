package com.example.demo.security;

import com.example.demo.models.DTOs.UserDTO;
import com.example.demo.services.ServantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServantServices servantServices;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void addUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.username)
                .password(passwordEncoder.encode(userDTO.password))
                .person(servantServices.getServantById(userDTO.personId))
                .roles(userDTO.roles)
                .build();
//        user.setPerson();
        System.out.println(user);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        return user;
    }
}
