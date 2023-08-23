package com.example.demo.services;

import com.example.demo.models.DTOs.UserDTO;
import com.example.demo.models.User;
import com.example.demo.models.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServantServices servantServices;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    public void addUser(UserDTO userDTO) {
        User user = new User();
        userRepository.save(userMapper.userDtoToUser(userDTO, user,servantServices));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
