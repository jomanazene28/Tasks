package com.jomana.zene.jomanatasksmanger.data;

import android.widget.CheckBox;

import java.util.Date;

/**
 * Created by user on 9/8/2016.
 */
public class MyTask
{
    /**
     * rakam almhama
     */
    private String id;
    /**
     * enwan
     */
    private String title;
    private int prioroty;
    private Date when;
    private String address;
    private String phone;
    private boolean isCompleted;

    public MyTask(String id, int prioroty, String title, Date when, String address, String phone,boolean isCompleted)
    {
        this.id = id;
        this.prioroty = prioroty;
        this.title = title;
        this.when = when;
        this.address = address;
        this.phone = phone;
        this.isCompleted=isCompleted;
    }

    public MyTask()
    {
    }
    public boolean getIsCompleted(){return isCompleted;}
    public void setIsCompleted(boolean isCompleted){this.isCompleted=isCompleted;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrioroty() {
        return prioroty;
    }

    public void setPrioroty(int prioroty) {
        this.prioroty = prioroty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", prioroty=" + prioroty +
                ", when=" + when +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ",isCompleted" + isCompleted +'\'' +
                '}';
    }


    public boolean setIsCompleted() {
        if (isCompleted)
            return true;
        else
            return false;
    }
}
