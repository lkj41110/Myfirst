package com.rj.lk.domain;

import java.util.Date;


public class Order {
    private int id;
    private int userId;
    private int bicycleId;
    private Date startTime;
    private Date endTime;
    private double cost;
    private String start_X;
    private String start_Y;
    private String end_X;
    private String end_Y;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStart_X() {
        return start_X;
    }

    public void setStart_X(String start_X) {
        this.start_X = start_X;
    }

    public String getStart_Y() {
        return start_Y;
    }

    public void setStart_Y(String start_Y) {
        this.start_Y = start_Y;
    }

    public String getEnd_X() {
        return end_X;
    }

    public void setEnd_X(String end_X) {
        this.end_X = end_X;
    }

    public String getEnd_Y() {
        return end_Y;
    }

    public void setEnd_Y(String end_Y) {
        this.end_Y = end_Y;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", userId=" + userId + ", bicycleId=" + bicycleId + ", status=" + status + "]";
    }



}
