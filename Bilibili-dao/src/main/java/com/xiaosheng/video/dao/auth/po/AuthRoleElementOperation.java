package com.xiaosheng.video.dao.auth.po;

import java.util.Date;

public class AuthRoleElementOperation {
    private Long id;

    private Long roleid;

    private Long elementoperationid;

    private Date createtime;

    // 冗余字段
    private AuthElementOperation authElementOperation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getElementoperationid() {
        return elementoperationid;
    }

    public void setElementoperationid(Long elementoperationid) {
        this.elementoperationid = elementoperationid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}