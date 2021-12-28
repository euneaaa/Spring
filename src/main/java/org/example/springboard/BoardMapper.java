package org.example.springboard;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    /*public abstract*/ int insBoard(BoardEntity entity);
    List<BoardEntity> selBoardList();
}
