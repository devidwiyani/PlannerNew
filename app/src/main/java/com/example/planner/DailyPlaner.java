package com.example.planner;

public class DailyPlaner {

    // variables for our coursename,
    // description, tracks and duration, id.
    private String tambahdailyplaner;
    private String tambahstarttime;
    private String tambahendtime;

    private int id;


    // constructor
    public DailyPlaner(Integer id, String inputDailyPlan, String inputStartTime, String inputEndTime) {
        this.id = id;
        this.tambahdailyplaner = inputDailyPlan;
        this.tambahstarttime = inputStartTime;
        this.tambahendtime = inputEndTime;

    }

    // creating getter and setter methods
    public String getTambahdailyplaner() {
        return tambahdailyplaner;
    }

    public void setTambahdailyplaner(String tambahdailyplaner ) {
        this.tambahdailyplaner = tambahdailyplaner;
    }

    public String getTambahstarttime() {
        return tambahstarttime;
    }

    public void setTambahstarttime(String tambahstarttime) {
        this.tambahstarttime = tambahstarttime;
    }

    public String getTambahendtime() {
        return tambahendtime;
    }

    public void setTambahendtime(String tambahendtime) {
        this.tambahendtime = tambahendtime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}