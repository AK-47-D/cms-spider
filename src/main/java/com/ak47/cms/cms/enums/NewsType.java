package com.ak47.cms.cms.enums;

/**
 * Created by wb-cmx239369 on 2017/11/3.
 */
public enum NewsType {

    CENTRAL_BANK(0,"新闻");
//    FOCUS(1,"焦点");

    NewsType(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    private int code;
    private String detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
