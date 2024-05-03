/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Randima
 */


import com.mycompany.model.Billing;
import com.mycompany.model.Patient;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {

    private static List<Billing> billingList = new ArrayList<>();
    private static PatientDAO patientDAO = new PatientDAO();

   
    public Billing getById(int id) {
        for (Billing billing : billingList) {
            if (billing.getId() == id) {
                return billing;
            }
        }
        return null;
    }

    public List<Billing> getAll() {
        // Return the bil list
        return billingList;
    
    }

    public void save(Billing billing) {
        if (billing == null || billing.getPatient() == null) {
            throw new IllegalArgumentException("Billing or patient information is missing.");
        }

        int patientId = billing.getPatient().getId();
        Patient patient = (Patient) patientDAO.getById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found for billing.");
        }

        billing.setId(getNextBillingId());
        billingList.add(billing);
    }

    public void update(Billing billing) {
        if (billing == null) {
            throw new IllegalArgumentException("Billing information is missing.");
        }
        
        int patientId = billing.getPatient().getId();
        Patient patient = (Patient) patientDAO.getById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found for billing.");
        }

        int billingId = billing.getId();
        boolean found = false;
        for (int i = 0; i < billingList.size(); i++) {
            if (billingList.get(i).getId() == billingId) {
                billingList.set(i, billing);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Billing with ID " + billingId + " not found for update.");
        }
    }
    
    public double calculateTotal(Billing billing) {
        if (billing == null) {
            throw new IllegalArgumentException("Billing information is missing for total calculation.");
        }

        double total = billing.getHospitalCharges() + billing.getDoctorCharges();
        billing.setTotalAmount(total);
        return total;
    }

    public void delete(int id) {
        billingList.removeIf(billing -> billing.getId() == id);
    }
    
    // Method to get the next available appointment ID
    private static int getNextBillingId() {
        int maxId = 0;
        if (!billingList.isEmpty()) {
            maxId = billingList.get(billingList.size() - 1).getId();
        }
        return maxId + 1;
    }
}
