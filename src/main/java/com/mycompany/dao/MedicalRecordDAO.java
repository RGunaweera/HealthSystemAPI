/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author HP
 */

import com.mycompany.model.MedicalRecord;
import com.mycompany.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    private static List<MedicalRecord> medicalRecordList = new ArrayList<>();
    private static PatientDAO patientDAO = new PatientDAO();


    public MedicalRecord getById(int id) {
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getId() == id) {
                return medicalRecord;
            }
        }
        return null;
    }

    public List<MedicalRecord> getAll() {
        return medicalRecordList;
    }
    
    public List<MedicalRecord> getByPatientId(int patientId) {
        List<MedicalRecord> recordsByPatient = new ArrayList<>();
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getPatient().getId() == patientId) {
                recordsByPatient.add(medicalRecord);
            }
        }
        return recordsByPatient;
    }

    public void save(MedicalRecord medicalRecord) {
        
        if (medicalRecord.getPatient()== null) {
            throw new IllegalArgumentException("Patient field is mandatory.");
        }
        
        int patientId = medicalRecord.getPatient().getId();
        Patient patient = (Patient) patientDAO.getById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }
        
        if (medicalRecord == null) {
            throw new IllegalArgumentException("Medical record object cannot be null");
        }
        
        medicalRecord.setId(getNextMRId());
        medicalRecordList.add(medicalRecord);
    }

    public void update(MedicalRecord medicalRecord) {
        int id = medicalRecord.getId();
        MedicalRecord existingRecord = getById(id);
        if (existingRecord == null) {
            throw new IllegalArgumentException("Medical record with ID " + id + " not found");
        }
        if (medicalRecord.getPatient()== null) {
            throw new IllegalArgumentException("Patient field is mandatory.");
        }
        for (int i = 0; i < medicalRecordList.size(); i++) {
            if (medicalRecordList.get(i).getId() == id) {
                medicalRecordList.set(i, medicalRecord);
                return;
            }
        }
    }

    public void delete(int id) {
        MedicalRecord existingRecord = getById(id);
        if (existingRecord == null) {
            throw new IllegalArgumentException("Medical record with ID " + id + " not found");
        }
        medicalRecordList.removeIf(medicalRecord -> medicalRecord.getId() == id);
    }
    
    // Method to get the next available appointment ID
    private static int getNextMRId() {
        int maxId = 0;
        if (!medicalRecordList.isEmpty()) {
            maxId = medicalRecordList.get(medicalRecordList.size() - 1).getId();
        }
        return maxId + 1;
    }
}
