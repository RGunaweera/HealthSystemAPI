/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Randima
 */

import com.mycompany.exception.UserNotFoundException;
import com.mycompany.model.Doctor;
import com.mycompany.model.Patient;
import com.mycompany.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements PersonDAO {

    private static List<Patient> patients = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Person getById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null; // Return null if patient with the given ID is not found
    }
    
    @Override
    public List<Person> getAll() {
        return new ArrayList<>(patients); // Return a copy of the list to prevent external modification
    }

    @Override
    public void save(Person person) {
        if (person instanceof Patient) {
            Patient patient = (Patient) person;
            // Check if any of the mandatory fields are null or empty
            if (patient.getName() == null || patient.getName().isEmpty() ||
                patient.getContactInfo() == null || patient.getContactInfo().isEmpty() ||
                patient.getAddress() == null || patient.getAddress().isEmpty()) {
                throw new IllegalArgumentException("Name, contactInfo, and address fields are required");
            }
            // Set ID using getNextPatientId method
            patient.setId(getNextPatientId());
            patients.add(patient);
        } else {
            throw new IllegalArgumentException("Person object is not an instance of Patient");
        }
    }


    @Override
    public void update(Person person) {
        Patient patient = (Patient) person;
        // Check if any of the mandatory fields are null or empty
            if (patient.getName() == null || patient.getName().isEmpty() ||
                patient.getContactInfo() == null || patient.getContactInfo().isEmpty() ||
                patient.getAddress() == null || patient.getAddress().isEmpty() ||
                patient.getCurrentHealthStatus() == null || patient.getCurrentHealthStatus().isEmpty()) {
                throw new IllegalArgumentException("Name, contactInfo, address, and currentHealthStatus fields are required");
            }
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == patient.getId()) {
                patients.set(i, patient);
                return;
            }
        }
        throw new UserNotFoundException("Patient with ID " + person.getId() + " not found");
    }

    @Override
    public void delete(int id) {
        patients.removeIf(patient -> patient.getId() == id);
    }

    private int getNextPatientId() {
        // Initialize maxId with 0 if list is empty
        int maxId = 0;
        if (!patients.isEmpty()) {
            maxId = patients.get(patients.size() - 1).getId();
        }        // Increment maxId to get the next available ID
        return maxId + 1;
    }
}
