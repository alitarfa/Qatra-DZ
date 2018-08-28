package com.example.magictouch.my_application;

/**
 * Created by tarfa on 6/9/18.
 */

public class ModelPayment {

    private int id;
    private String coast;
    private String dataArrive;
    private String deadLineDate;
    private String title;


    public ModelPayment(int id, String coast, String dataArrive, String deadLineDate) {
        this.id = id;
        this.coast = coast;
        this.dataArrive = dataArrive;
        this.deadLineDate = deadLineDate;
    }

    public ModelPayment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }

    public String getDataArrive() {
        return dataArrive;
    }

    public void setDataArrive(String dataArrive) {
        this.dataArrive = dataArrive;
    }

    public String getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(String deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
