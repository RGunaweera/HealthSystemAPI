/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Randima
 */

import java.util.List;

public class MedicalRecord {
    private int id;
    private Patient patient;
    private List<String> diagnoses;
    private List<String> treatments;

    //constructors
    public MedicalRecord() {
    }

    public MedicalRecord(int id, Patient patient, List<String> diagnoses, List<String> treatments) {
        this.id = id;
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
    }

    //getters and setters
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

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }
    
    @Override
    public String toString() {
        return 
                "Medical Record id: " + id +
                "/n," + patient.toString() + 
                "/n,Diagnoses: " + diagnoses +
                "/nTreatments: " + treatments;
    }    
}

