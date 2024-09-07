package com.edu.ncst.web;

import com.edu.ncst.exception.OperationFailureException;
import com.edu.ncst.model.Mark;
import com.edu.ncst.service.BookInfoService;
import com.edu.ncst.service.MarkService;
import com.edu.ncst.utils.MyResult;
import com.edu.ncst.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mark")
public class MarkController {

    @Autowired
    MarkService markService;
    @Autowired
    BookInfoService bookInfoService;

    // 分页查询收藏 params: {page, limit, userid, bookid}
    @RequestMapping(value = "/queryMarksByPage")
    public Map<String, Object> queryMarksByPage(@RequestParam Map<String, Object> params){
        MyUtils.parsePageParams(params);
        int count = markService.getSearchCount(params);
        List<Mark> marks = markService.searchMarksByPage(params);
        return MyResult.getListResultMap(0, "success", count, marks);
    }

    // 添加收藏
    @RequestMapping(value = "/addMark")
    public Integer addMark(@RequestBody Mark mark){
        return markService.addMark(mark);
    }

    // 获得数量
    @RequestMapping(value = "/getCount")
    public Integer getCount(){
        return markService.getCount();
    }

    // 删除收藏
    @RequestMapping(value = "/deleteMark")
    public Integer deleteMark(@RequestBody Mark mark){
        return markService.deleteMark(mark);
    }

    // 删除一些收藏
    @RequestMapping(value = "/deleteMarks")
    public Integer deleteMarks(@RequestBody List<Mark> marks){
        return markService.deleteMarks(marks);
    }

    // 更新收藏
    @RequestMapping(value = "/updateMark")
    public Integer updateMark(@RequestBody Mark mark){
        return markService.updateMark(mark);
    }

    // 收藏书
    @RequestMapping(value = {"/markBook", "/reader/markBook"})
    @Transactional
    public Integer markBook(Integer userid, Integer bookid){
        try{
            // 添加一条记录到 mark 表
            Mark mark = new Mark();
            mark.setUserid(userid);
            mark.setBookid(bookid);
            mark.setMarktime(new Date(System.currentTimeMillis()));
            Integer res1 = markService.addMark2(mark);
            if(res1 == 0) throw new OperationFailureException("图书" + bookid + "添加收藏记录失败");

        } catch (Exception e) {
            System.out.println("发生异常，进行手动回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
