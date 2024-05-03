/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Randima
 * 
 */

import com.mycompany.exception.UserNotFoundException;
import com.mycompany.model.Doctor;
import com.mycompany.model.Patient;
import com.mycompany.model.Prescription;

import java.util.ArrayList;
import java.util.List;


public class PrescriptionDAO {

    private static List<Prescription> prescriptionList = new ArrayList<>();
    
    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO(new AppointmentDAO());

    public Prescription getById(int id) {
        for (Prescription prescription : prescriptionList) {
            if (prescription.getId() == id) {
                return prescription;
            }
        }
        return null;
    }

    public List<Prescription> getAll() {
        return prescriptionList;
    }

    public void save(Prescription prescription) {
        if (prescription == null) {
            throw new IllegalArgumentException("Prescription information is missing.");
        }
        // Check if patient and doctor exist
        int patientId = prescription.getPatient().getId();
        int doctorId = prescription.getDoctor().getId();
        
        Patient patient = (Patient) patientDAO.getById(patientId);
        Doctor doctor = (Doctor) doctorDAO.getById(doctorId);
        
        if (patient == null) {
            throw new UserNotFoundException("Patient not found");
        }
        if (doctor == null) {
            throw new UserNotFoundException("Doctor not found");
        }   
        
        prescription.setId(getNextPrescriptionId());
        prescriptionList.add(prescription);
    }

    public void update(Prescription prescription) {
        if (prescription == null) {
            throw new IllegalArgumentException("Prescription information is missing.");
        }
        // Check if patient and doctor exist
        int patientId = prescription.getPatient().getId();
        int doctorId = prescription.getDoctor().getId();
        
        Patient patient = (Patient) patientDAO.getById(patientId);
        Doctor doctor = (Doctor) doctorDAO.getById(doctorId);
        
        if (patient == null) {
            throw new UserNotFoundException("Patient not found");
        }
        if (doctor == null) {
            throw new UserNotFoundException("Doctor not found");
        }   

        int prescriptionId = prescription.getId();
        boolean found = false;
        for (int i = 0; i < prescriptionList.size(); i++) {
            if (prescriptionList.get(i).getId() == prescriptionId) {
                prescriptionList.set(i, prescription);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Prescription with ID " + prescriptionId + " not found for update.");
        }
    }

    public void delete(int id) {
        prescriptionList.removeIf(prescription -> prescription.getId() == id);
    }
    
     // Method to get the next available prescription ID
    private static int getNextPrescriptionId() {
        int maxId = 0;
        if (!prescriptionList.isEmpty()) {
            maxId = prescriptionList.get(prescriptionList.size() - 1).getId();
        }
        return maxId + 1;
    }
}

