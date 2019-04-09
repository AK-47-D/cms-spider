package com.ak47.cms.cms.result;

public class Result<T> {
    private T result;
    private String message;
    private String title;
    private boolean success;

    public Result() {
    }

    public Result(T result, String message, String title, boolean success) {
        this.result = result;
        this.message = message;
        this.title = title;
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
