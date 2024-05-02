/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author HP
 */

import com.mycompany.model.Prescription;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    private List<Prescription> prescriptionList = new ArrayList<>();

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
        prescriptionList.add(prescription);
    }

    public void update(Prescription prescription) {
        for (int i = 0; i < prescriptionList.size(); i++) {
            if (prescriptionList.get(i).getId() == prescription.getId()) {
                prescriptionList.set(i, prescription);
                return;
            }
        }
    }

    public void delete(int id) {
        prescriptionList.removeIf(prescription -> prescription.getId() == id);
    }
}
