package com.example.diarywebapplication.mapper;

import com.example.diarywebapplication.entity.PasswordRecovery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PasswordRecoveryMapper {
    @Select("select * from password_recovery where user_id = #{userId}")
    PasswordRecovery getPasswordRecoveryByUserId(long userId);

    @Insert("insert into password_recovery (user_id, token_hash, expires_at) values (#{userId}, #{tokenHash}, #{expiresAt})")
    int insertPasswordRecovery(PasswordRecovery passwordRecovery);

    @Update("update password_recovery set token_hash = #{tokenHash}, expires_at = #{expiresAt}, used_at = null where user_id = #{userId}")
    int updatePasswordRecovery(PasswordRecovery passwordRecovery);

    @Update("update password_recovery set used_at = current_timestamp where user_id = #{userId} and token_hash = #{tokenHash} and used_at is null and expires_at > current_timestamp")
    int markPasswordRecoveryAsUsed(PasswordRecovery passwordRecovery);
}
