/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Randima
 */

import com.mycompany.model.Appointment;
import com.mycompany.model.Doctor;
import com.mycompany.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    private static List<Appointment> appointmentList = new ArrayList<>();

    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO(new AppointmentDAO());

    // Method to retrieve a patient by ID
    public Patient getPatientById(int patientId) {
        return (Patient) patientDAO.getById(patientId);
    }

    // Method to retrieve a doctor by ID
    public Doctor getDoctorById(int doctorId) {
        return (Doctor) doctorDAO.getById(doctorId);
    }

    // Method to get all appointments
    public List<Appointment> getAll() {
        return new ArrayList<>(appointmentList);
    }

    // Method to get an appointment by ID
    public Appointment getById(int id) {
        for (Appointment appointment : appointmentList) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null; // Return null if appointment with the given ID is not found
    }    
    
    public void save(Appointment appointment) throws Exception {
        
        // Check if patient and doctor exist
        int patientId = appointment.getPatient().getId();
        int doctorId = appointment.getDoctor().getId();
        
        Patient patient = (Patient) patientDAO.getById(patientId);
        Doctor doctor = (Doctor) doctorDAO.getById(doctorId);
        
        int count = doctorDAO.calculateAvailableAppointments(doctorId);
        appointment.getDoctor().setavailableAppointments(count);
        
        if(count == 0){
            throw new Exception("Maximum appointment count reached.");
        }
        
         // Check if date and time are provided
        if (appointment.getDate() == null || appointment.getTime() == null) {
            throw new IllegalArgumentException("Date and time are mandatory fields");
        }            

        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found");
        }       
        
        appointment.setId(getNextAppointmentId());    

        // Add the appointment to the list
        appointmentList.add(appointment);
    }

    

    // Method to update an existing appointment
    public void update(Appointment appointment) throws Exception {
        
        // Check if patient and doctor exist
        int patientId = appointment.getPatient().getId();
        int doctorId = appointment.getDoctor().getId();
        
        Patient patient = (Patient) patientDAO.getById(patientId);
        Doctor doctor = (Doctor) doctorDAO.getById(doctorId);
        
        int availableCount = doctorDAO.calculateAvailableAppointments(doctorId);

        if(availableCount == 0){
            throw new Exception("Maximum appointment count reached.");
        }
        
         // Check if date and time are provided
        if (appointment.getDate() == null || appointment.getTime() == null) {
            throw new IllegalArgumentException("Date and time are mandatory fields");
        }     

       

        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found");
        }
        
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getId() == appointment.getId()) {
                appointmentList.set(i, appointment);
                return;
            }
        }
        throw new IllegalArgumentException("Appointment with ID " + appointment.getId() + " not found");
    }

    // Method to delete an appointment by ID
    public void delete(int id) {
        appointmentList.removeIf(appointment -> appointment.getId() == id);
    }

    // Method to get the next available appointment ID
    private static int getNextAppointmentId() {
        int maxId = 0;
        if (!appointmentList.isEmpty()) {
            maxId = appointmentList.get(appointmentList.size() - 1).getId();
        }
        return maxId + 1;
    }
}
