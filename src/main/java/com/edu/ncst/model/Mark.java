package com.edu.ncst.model;

import lombok.Data;

import java.util.Date;

@Data
public class Mark {

    private Integer markid;

    private Integer userid;

    private Integer bookid;

    private Date marktime;

    private String marktimestr;

    private String userName;

    private String bookName;

}
