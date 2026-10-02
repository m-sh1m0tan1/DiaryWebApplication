package com.example.diarywebapplication.mapper;

import com.example.diarywebapplication.entity.Memo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MemoMapper {
    @Select("select * from memo where user_id = #{userId}")
    List<Memo> getMemosByUserId(long userId);

    @Select("select * from memo where id = #{id}")
    Memo getMemoById(long id);

    @Insert("insert into memo (user_id, content, week_start_date) values (#{userId}, #{content}, #{weekStartDate})")
    int insertMemo(Memo memo);

    @Update("update memo set content = #{content}, updated_at = current_timestamp where id = #{id}")
    int updateMemo(Memo memo);
}
