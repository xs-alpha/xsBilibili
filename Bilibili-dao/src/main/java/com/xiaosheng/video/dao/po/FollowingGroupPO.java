package com.xiaosheng.video.dao.po;

import java.util.Date;
import java.util.List;

public class FollowingGroupPO {
    private Long id;

    private Long userid;

    private String name;

    private String type;

    private Date createtime;

    private Date updatetime;

    private List<UserInfoPO> followingUserInfoList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public List<UserInfoPO> getFollowingUserInfoList() {
        return followingUserInfoList;
    }

    public void setFollowingUserInfoList(List<UserInfoPO> followingUserInfoList) {
        this.followingUserInfoList = followingUserInfoList;
    }
}