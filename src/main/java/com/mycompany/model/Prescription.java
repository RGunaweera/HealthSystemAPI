/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Randima
 */

public class Prescription {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String medicationName;
    private String duration;

    // Constructors
    public Prescription() {
    }

    public Prescription(int id, Patient patient, Doctor doctor, String medicationName, String duration) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medicationName = medicationName;        
        this.duration = duration;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }
    
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", patient=" + patient.toString() +
                ", doctor=" + doctor.toString() +
                ", medicationName='" + medicationName + '\'' +               
                ", duration='" + duration + '\'' +
                '}';
    }
}

