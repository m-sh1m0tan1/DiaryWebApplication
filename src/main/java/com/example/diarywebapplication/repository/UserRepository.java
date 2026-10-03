package com.example.diarywebapplication.repository;

import com.example.diarywebapplication.entity.User;
import com.example.diarywebapplication.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {
    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(long id) {
        return userMapper.getUserById(id);
    }

    public User getUserByMail(String mail) {
        return userMapper.getUserByMail(mail);
    }


    public int insertUser(User user) {
         return userMapper.insertUser(user);
    }

    @Transactional
    public User updateUser(User user) {
        int sqlResult = userMapper.updateUser(user);
        if (sqlResult < 1) {
            return null;
        }
        return userMapper.getUserById(user.getId());
    }
}
