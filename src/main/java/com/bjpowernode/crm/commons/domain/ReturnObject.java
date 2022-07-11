package com.bjpowernode.crm.commons.domain;

public class ReturnObject {
    private String code;//处理成功或失败的信息
    private String massage;//提示信息
    private Object retDate;//返回其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Object getRetDate() {
        return retDate;
    }

    public void setRetDate(Object retDate) {
        this.retDate = retDate;
    }
}
