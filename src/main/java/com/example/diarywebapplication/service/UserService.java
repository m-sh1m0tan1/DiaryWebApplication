package com.example.diarywebapplication.service;

import com.example.diarywebapplication.Form.UserForm;
import com.example.diarywebapplication.entity.User;
import com.example.diarywebapplication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public int createUser(UserForm userForm) {
        User user = new User();
        user.setMail(userForm.getMail());
        user.setName(userForm.getName());
        String hashedPassword = passwordEncoder.encode(userForm.getPlainPassword());
        user.setHashedPw(hashedPassword);

        return userRepository.insertUser(user);
    }
}
