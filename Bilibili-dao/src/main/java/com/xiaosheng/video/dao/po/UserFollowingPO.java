package com.xiaosheng.video.dao.po;

import java.util.Date;

public class UserFollowingPO {
    private Long id;

    private Long userid;

    private Integer followingid;

    private Integer groupid;

    private Date createtime;

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

    public Integer getFollowingid() {
        return followingid;
    }

    public void setFollowingid(Integer followingid) {
        this.followingid = followingid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}