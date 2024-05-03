/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


/**
 *
 * @author Randima
 */

public class Doctor extends Person {
    private String specialization;
    private String startTime;
    private String endTime;
    private int totalAppointments;
    private int availableAppointments;

    //constructors 
    public Doctor() {
    }

    public Doctor(int id, String name, String contactInfo, String address, String specialization, String startTime, String endTime, int totalAppointments) {
        super(id, name, contactInfo, address); 
        this.specialization = specialization;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalAppointments = totalAppointments;
    }
    
    @Override
    public String getType() {
        return "Doctor";
    }

    //getters and setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter and Setter for startTime
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // Getter and Setter for endTime
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
   
    public int gettotalAppointments(){
        return totalAppointments;
    }
    
    public void settotalAppointments(int totalAppointments){
        this.totalAppointments = totalAppointments;
    }
    
    public int getavailableAppointments(){
        return availableAppointments;
    }
    
    public void setavailableAppointments(int availableAppointments){
        this.availableAppointments = availableAppointments;
    }
    
    
    
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", contactInfo='" + getContactInfo() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", Sessions start time='" + startTime + '\'' +
                ", Sessions end time='" + endTime + '\'' +
                ", Total Appointments='" + totalAppointments + '\'' +
                ", AvailableAppointments'" + availableAppointments + '\''+
                '}';
    }
}
