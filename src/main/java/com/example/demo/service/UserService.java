package com.example.demo.service;



import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByUserId(userDTO.getUserId()) != null) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        user.setUserNickname(userDTO.getUserNickname());
        user.setUserEmail(userDTO.getUserEmail());

        return userRepository.save(user);
    }
}
