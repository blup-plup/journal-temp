package com.TheJournal.Login.Entities;


import javax.persistence.*;


@Entity
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(unique = true)
    Integer parentId;

    Long start;

    Long end;

    String sugarId;


    public String getSugarId() {
        return sugarId;
    }

    public void setSugarId(String sugarId) {
        this.sugarId = sugarId;
    }

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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
