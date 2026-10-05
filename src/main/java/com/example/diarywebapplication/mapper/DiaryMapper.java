package com.example.diarywebapplication.mapper;

import com.example.diarywebapplication.entity.Diary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DiaryMapper {

    @Select("select * from diary where user_id = #{userId} order by diary_date desc")
    List<Diary> getDiariesByUserId(long userId);

    @Select("select * from diary where user_id = #{userId} and diary_date >= #{startDate} and diary_date < #{endDate} order by diary_date")
    List<Diary> getThisWeekDiariesByUserId(long userId, LocalDate startDate, LocalDate endDate);

    @Select("select * from diary where id = #{id}")
    Diary getDiaryById(long id);

    @Insert("insert into diary (user_id, title, current_mood, content, good_things, tomorrow_note, diary_date) values (#{userId}, #{title}, cast(#{currentMood, jdbcType=VARCHAR} as mood), #{content}, #{goodThings}, #{tomorrowNote}, #{diaryDate}) on conflict (user_id, diary_date) do nothing")
    int insertDiary(Diary diary);

    @Update("update diary set title = #{title}, current_mood = cast(#{currentMood, jdbcType=VARCHAR} as mood), content = #{content}, good_things = #{goodThings}, tomorrow_note = #{tomorrowNote}, updated_at = current_timestamp where id = #{id} and user_id = #{userId}")
    int updateDiary(Diary diary);
}
