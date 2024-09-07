package com.edu.ncst.mapper;

import com.edu.ncst.model.Mark;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MarkMapper {
    int deleteByPrimaryKey(Integer markid);

    int insert(Mark record);

    int insertSelective(Mark record);

    Mark selectByPrimaryKey(Integer markid);

    int updateByPrimaryKeySelective(Mark record);

    int updateByPrimaryKey(Mark record);

    List<Mark> selectAllByLimit(@Param("begin") Integer begin, @Param("size") Integer size);

    Integer selectCount();

    int selectCountBySearch(Map<String, Object> searchParam);

    List<Mark> selectBySearch(Map<String, Object> searchParam);

    Integer selectCountByReader(Integer userid);

    List<Mark> selectAllByLimitByReader(@Param("begin") Integer begin, @Param("size") Integer size, @Param("userid") Integer userid);
}
