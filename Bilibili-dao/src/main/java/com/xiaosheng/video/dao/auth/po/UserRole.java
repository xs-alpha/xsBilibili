package com.xiaosheng.video.dao.auth.po;

import java.util.Date;

public class UserRole {
    private Long id;

    private Long userid;

    private Long roleid;

    private Date createtime;

    // 冗余字段
    private String roleName;

    // 冗余字段
    private String roleCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}