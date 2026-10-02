package com.example.diarywebapplication.repository;

import com.example.diarywebapplication.entity.Memo;
import com.example.diarywebapplication.mapper.MemoMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemoRepository {
    private final MemoMapper memoMapper;

    public MemoRepository(MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }

    public List<Memo> getMemosByUserId(long userId) {
        return memoMapper.getMemosByUserId(userId);
    }

    public Memo getMemoById(long id) {
        return memoMapper.getMemoById(id);
    }

    public int insertMemo(Memo memo) {
        return memoMapper.insertMemo(memo);
    }

    @Transactional
    public Memo updateMemo(Memo memo) {
        int sqlResult = memoMapper.updateMemo(memo);
        if (sqlResult < 1) {
            return null;
        }
        return memoMapper.getMemoById(memo.getId());
    }
}
