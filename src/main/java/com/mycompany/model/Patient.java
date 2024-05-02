/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Randima
 */

public class Patient extends Person {
    private String medicalHistory;
    private String currentHealthStatus;

    //constructors
    public Patient() {
    }

    public Patient(int id, String name, String contactInfo, String address, String medicalHistory, String currentHealthStatus) {
        super(id, name, contactInfo, address); 
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }
    
    @Override
    public String getType() {
        return "Patient";
    }

    //getters and setters
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
    
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", contactInfo='" + getContactInfo() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", medical history='" + medicalHistory + '\'' +
                ", current health status='" + currentHealthStatus + '\'' +
                '}';
    }
}

