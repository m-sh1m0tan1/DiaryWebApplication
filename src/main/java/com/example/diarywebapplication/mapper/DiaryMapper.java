package com.example.diarywebapplication.mapper;

import com.example.diarywebapplication.entity.Diary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DiaryMapper {

    @Select("select * from diary where user_id = #{userId} order by diary_date desc")
    List<Diary> getDiariesByUserId(long userId);

    @Select("select * from diary where id = #{id}")
    Diary getDiaryById(long id);

    @Insert("insert into diary (user_id, content, diary_date) values (#{userId}, #{content}, #{diaryDate})")
    int insertDiary(Diary diary);

    @Update("update diary set content = #{content}, updated_at = current_timestamp where id = #{id}")
    int updateDiary(Diary diary);

    @Update("update diary set is_completed = true where id = #{id}")
    int completeDiary(Diary diary);
}
