package com.example.AuthService.service;

import com.example.AuthService.Entity.User;
import com.example.AuthService.kafka.AuthKafkaProducer;
import com.example.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthKafkaProducer kafkaProducer;

    public String register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already registered!";
        }
        user.setRole("USER");
        User saved = userRepository.save(user);
        kafkaProducer.publishUserRegistered(saved.getId(), saved.getEmail());
        return "User registered successfully!";
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return "User not found!";
        if (!user.getPassword().equals(password)) return "Invalid password!";
        return "Login successful! Welcome " + user.getUsername();
    }
}
