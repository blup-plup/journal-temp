package com.TheJournal.Login.Controllers.Response;


import lombok.Data;

@Data
public class SimpleResponse {
    private Integer status;
    private String message;
    private Object obj;

    public SimpleResponse(String message,Integer status, Object obj) {
        this.message = message;
        this.status = status;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

