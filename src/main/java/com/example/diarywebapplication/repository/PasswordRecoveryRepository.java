package com.example.diarywebapplication.repository;

import com.example.diarywebapplication.entity.PasswordRecovery;
import com.example.diarywebapplication.mapper.PasswordRecoveryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PasswordRecoveryRepository {
    private final PasswordRecoveryMapper passwordRecoveryMapper;

    public PasswordRecoveryRepository(PasswordRecoveryMapper passwordRecoveryMapper) {
        this.passwordRecoveryMapper = passwordRecoveryMapper;
    }

    public PasswordRecovery getPasswordRecoveryByUserId(long userId) {
        return passwordRecoveryMapper.getPasswordRecoveryByUserId(userId);
    }

    public int insertPasswordRecovery(PasswordRecovery passwordRecovery) {
        return passwordRecoveryMapper.insertPasswordRecovery(passwordRecovery);
    }

    @Transactional
    public PasswordRecovery updatePasswordRecovery(PasswordRecovery passwordRecovery) {
        int sqlResult = passwordRecoveryMapper.updatePasswordRecovery(passwordRecovery);
        if (sqlResult < 1) {
            return null;
        }
        return passwordRecoveryMapper.getPasswordRecoveryByUserId(passwordRecovery.getUserId());
    }

    @Transactional
    public PasswordRecovery markPasswordRecoveryAsUsed(PasswordRecovery passwordRecovery) {
        int sqlResult = passwordRecoveryMapper.markPasswordRecoveryAsUsed(passwordRecovery);
        if (sqlResult < 1) {
            return null;
        }
        return passwordRecoveryMapper.getPasswordRecoveryByUserId(passwordRecovery.getUserId());
    }
}
