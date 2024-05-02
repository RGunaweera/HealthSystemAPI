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
    private double dosage;
    private String instructions;
    private String duration;

    // Constructors
    public Prescription() {
    }

    public Prescription(int id, Patient patient, Doctor doctor, String medicationName, double dosage, String instructions, String duration) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.instructions = instructions;
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

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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
                ", dosage=" + dosage +
                ", instructions='" + instructions + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}

