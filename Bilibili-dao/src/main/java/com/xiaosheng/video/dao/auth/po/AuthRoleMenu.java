package com.xiaosheng.video.dao.auth.po;

import java.util.Date;

public class AuthRoleMenu {
    private Long id;

    private Long roleid;

    private Long menuid;

    private Date createtime;

    // 冗余字段，联表查询
    private AuthMenu authMenu;

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

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}