package com.edu.ncst.web;

import com.edu.ncst.model.AjaxResult;
import com.edu.ncst.model.SysNotice;
import com.edu.ncst.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 公告栏
 */
@RestController
@RequestMapping("/notice")
public class SysNoticeController {

    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @GetMapping("/list")
    public AjaxResult list(SysNotice notice) {
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return AjaxResult.success(list != null && list.size() > 0 ?  list : new ArrayList<>());
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId) {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNotice notice) {
        noticeService.insertNotice(notice);
        return AjaxResult.success();
    }

    /**
     * 修改通知公告
     */
    @PostMapping(value = "/edit")
    public AjaxResult edit(@Validated @RequestBody SysNotice notice) {
        noticeService.updateNotice(notice);
        return AjaxResult.success();
    }

    /**
     * 删除通知公告
     */
    @PostMapping("/remove/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds) {
        noticeService.deleteNoticeByIds(noticeIds);
        return AjaxResult.success();
    }
}
