package com.edu.ncst.service.impl;

import com.edu.ncst.mapper.MarkMapper;
import com.edu.ncst.model.Mark;
import com.edu.ncst.service.MarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class MarkServiceImpl implements MarkService {
    @Resource
    private MarkMapper markMapper;

    @Override
    public Integer getCount() {
        return markMapper.selectCount();
    }

    @Override
    public Integer getSearchCount(Map<String, Object> params) {
        return markMapper.selectCountBySearch(params);
    }

    @Override
    public List<Mark> searchMarksByPage(Map<String, Object> params) {
        List<Mark> marks = markMapper.selectBySearch(params);
        // 添加string类型的时间显示
        for(Mark mark : marks) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(mark.getMarktime() != null) mark.setMarktimestr(simpleDateFormat.format(mark.getMarktime()));
        }
        return marks;
    }

    @Override
    public Integer addMark(Mark mark) {
        // 将string类型的时间重新调整
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            mark.setMarktime(simpleDateFormat.parse(mark.getMarktimestr()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return markMapper.insertSelective(mark);
    }

    // 不会调整时间格式的add
    @Override
    public Integer addMark2(Mark mark) {
        return markMapper.insertSelective(mark);
    }

    @Override
    public Integer deleteMark(Mark mark) {
        // 先查询有没有还书
        Mark mark1 = markMapper.selectByPrimaryKey(mark.getMarkid());
        return markMapper.deleteByPrimaryKey(mark.getMarkid());
    }

    @Override
    public Integer deleteMarks(List<Mark> marks) {
        int count = 0;
        for(Mark mark : marks) {
            count += deleteMark(mark);
        }
        return count;
    }

    @Override
    public Integer updateMark(Mark mark) {
        // 将string类型的时间重新调整
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            mark.setMarktime(simpleDateFormat.parse(mark.getMarktimestr()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return markMapper.updateByPrimaryKeySelective(mark);
    }

    // 不调整时间格式的更新
    @Override
    public Integer updateMark2(Mark mark) {
        return markMapper.updateByPrimaryKeySelective(mark);
    }

    @Override
    public Mark queryMarksById(Integer markid) {
        return markMapper.selectByPrimaryKey(markid);
    }

}
