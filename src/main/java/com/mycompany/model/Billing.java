/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Randima
 */

public class Billing {
    private int id;
    private Patient patient;
    private double hospitalCharges;
    private double doctorCharges;
    private double totalAmount;
    private boolean paid;

    // Constructors
    public Billing() {
    }

    public Billing(int id, Patient patient, double hospitalCharges, double doctorCharges, boolean paid) {
        this.id = id;
        this.patient = patient;
        this.hospitalCharges = hospitalCharges;
        this.doctorCharges = doctorCharges;
        this.paid = paid;
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
    
    public double getHospitalCharges() {
        return hospitalCharges;
    }

    public void setHospitalCharges(double hospitalCharges) {
        this.hospitalCharges = hospitalCharges;
    }

    public double getDoctorCharges() {
        return doctorCharges;
    }

    public void setDoctorCharges(double doctorCharges) {
        this.doctorCharges = doctorCharges;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    @Override
    public String toString() {
        return "Billing{" +
                "id=" + id +
                ", patient=" + patient.toString() +
                ", totalAmount=" + totalAmount +
                ", paid=" + paid +
                '}';
    }
}
