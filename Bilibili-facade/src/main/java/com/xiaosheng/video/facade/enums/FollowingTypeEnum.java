package com.xiaosheng.video.facade.enums;

public enum FollowingTypeEnum {
    TYPE_SPECIAL_ATTENTION("0", "特别关注"),
    TYPE_FOLLOWING_QUIETLY("1", "悄悄关注"),
    TYPE_DEFAULT_FOLLOWING("2", "默认分组"),
    TYPE_USER_BUILD("3", "用户自建分组"),
    TYPE_FOLLOW_ALL("4", "全部关注"),

    ;
    private String code;
    private String name;

    FollowingTypeEnum(String index, String name) {
        this.code = index;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public static String getName(String code) {
        FollowingTypeEnum[] typeEnums = values();
        for (FollowingTypeEnum carTypeEnum : typeEnums) {
            if (carTypeEnum.getCode().equals(code)) {
                return carTypeEnum.name;
            }
        }
        return null;
    }
}
