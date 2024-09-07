package com.edu.ncst.model;

import java.util.Date;

public class Mark {
    private Integer markid;
    private Integer userid;
    private Integer bookid;
    private Date marktime;
    private String marktimestr;

    public Integer getMarkid() {
        return markid;
    }

    public void setMarkid(Integer markid) {
        this.markid = markid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Date getMarktime() {
        return marktime;
    }

    public void setMarktime(Date marktime) {
        this.marktime = marktime;
    }

    public String getMarktimestr() {
        return marktimestr;
    }

    public void setMarktimestr(String marktimestr) {
        this.marktimestr = marktimestr;
    }
}
