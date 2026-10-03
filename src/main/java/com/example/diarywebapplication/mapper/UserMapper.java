package com.example.diarywebapplication.mapper;

import com.example.diarywebapplication.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from users where id = #{id}")
    User getUserById(long id);

    @Select("select * from users where mail = #{mail}")
    User getUserByMail(String mail);

    @Insert("insert into users (mail, name, hashed_pw) values (#{mail}, #{name}, #{hashedPw})")
    int insertUser(User user);

    @Update("update users set mail = #{mail}, name = #{name}, hashed_pw = #{hashedPw} where id = #{id}")
    int updateUser(User user);
}
