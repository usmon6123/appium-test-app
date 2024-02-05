package org.example.modul;


import lombok.*;


public class Test {

    private Integer id;
    private String title;
    private double sms;
    private String status;

    public Test() {
    }


    public Test(String title, double sms) {
        this.title = title;
        this.sms = sms;
    }

    public Test(String title, double sms, String status) {
        this.title = title;
        this.sms = sms;
        this.status = status;
    }

    public Test( Integer id, String title, double sms) {
        this.title = title;
        this.sms = sms;
        this.id = id;
    }

    public String toString(){
        return title + " " + sms + " " + status;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getSms() {
        return this.sms;
    }
    public double setSms(double sms) {
        this.sms = sms;
        return this.sms;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
