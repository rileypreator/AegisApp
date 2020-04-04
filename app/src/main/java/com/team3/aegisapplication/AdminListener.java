package com.team3.aegisapplication;

import java.sql.Time;

public class AdminListener {


    private int totalClicks;
    private Time lastTimeClicked;
    private String link;
    private int type;

    public AdminListener() {
        totalClicks = 0;
        lastTimeClicked = null;
        type = 0;
    }

    public AdminListener(String link, long type) {
        this.link = link;
        totalClicks = 0;
        lastTimeClicked = new Time(type);
    }

    public int getTotalClicks() {
        return totalClicks;
    }

    public void addClick() {
        totalClicks++;
    }

    public String getLastTimeClicked() {
        return lastTimeClicked.toString();
    }


    public void setTotalClicks(int totalClicks) {
        this.totalClicks = totalClicks;
    }

    public void setLastTimeClicked(Time lastTimeClicked) {
        this.lastTimeClicked = lastTimeClicked;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        switch (type) {
            case 0:
                return "Physical";
            case 1:
                return "Sexual";
            case 2:
                return "Verbal";
            case 3:
                return "Mental";
            default:
                return "Physical";
        }
    }
}
