package com.TheJournal.Login.Dtos;

public class UserSessionDto {

    Integer id;
    Integer parentId;
    Long start;
    Long end;
    String sugarId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getSugarId() {
        return sugarId;
    }

    public void setSugarId(String sugarId) {
        this.sugarId = sugarId;
    }
}
