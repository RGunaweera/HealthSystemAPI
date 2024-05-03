/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.List;

/**
 *
 * @author Randima
 */

public class Prescription {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private List<String> medications;
    private String duration;

    // Constructors
    public Prescription() {
    }

    public Prescription(int id, Patient patient, Doctor doctor, List<String> medications, String duration) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medications = medications;        
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

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
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
                ", medications='" + medications + '\'' +               
                ", duration='" + duration + '\'' +
                '}';
    }
}

