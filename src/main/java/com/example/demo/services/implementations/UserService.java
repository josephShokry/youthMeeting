package com.example.demo.services.implementations;

import com.example.demo.models.dtos.UserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.models.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServantService servantService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public Boolean addUser(UserDTO userDTO) {
        User user = new User();
        //TODO: is there a better way
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userMapper.mapToUser(userDTO, user, servantService));
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
