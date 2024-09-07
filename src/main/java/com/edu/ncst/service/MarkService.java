package com.edu.ncst.service;

import com.edu.ncst.model.Mark;

import java.util.List;
import java.util.Map;

public interface MarkService {
    Integer getCount();

    Integer getSearchCount(Map<String, Object> params);

    List<Mark> searchMarksByPage(Map<String, Object> params);

    Integer addMark(Mark mark);

    Integer addMark2(Mark mark);

    Integer deleteMark(Mark mark);

    Integer deleteMarks(List<Mark> marks);

    Integer updateMark(Mark mark);

    Integer updateMark2(Mark mark);

    Mark queryMarksById(Integer markid);
}
