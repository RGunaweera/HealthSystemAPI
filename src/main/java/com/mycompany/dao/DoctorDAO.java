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
import com.mycompany.model.Appointment;
import com.mycompany.model.Doctor;
import com.mycompany.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements PersonDAO {

    private static List<Doctor> doctors = new ArrayList<>();
    private int nextId = 1;
    
    private AppointmentDAO appointmentDAO; // Declare an instance of AppointmentDAO

    // Constructor to initialize AppointmentDAO
    public DoctorDAO(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }


    // Method to get appointments from AppointmentDAO
    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAll();
    }

    @Override
    public Person getById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        // Return null if doctor with the given ID is not found
        return null; 
    }
    
    @Override
    public List<Person> getAll() {
        return new ArrayList<>(doctors); 
    }

    @Override
    public void save(Person person) {
        Doctor doctor = (Doctor) person;
        if (person instanceof Doctor) {            
            // Check if any of the mandatory fields are null or empty
            if (doctor.getName() == null || doctor.getName().isEmpty() ||
                doctor.getSpecialization() == null || doctor.getSpecialization().isEmpty() ||
                doctor.gettotalAppointments()<= 0 ||
                doctor.getStartTime() == null || doctor.getStartTime().isEmpty()||
                doctor.getEndTime() == null || doctor.getEndTime().isEmpty() ) {
                throw new IllegalArgumentException("Name, specialization, start time, end time and total appointments fields are required");
            }
            // Set ID using getNextDoctorId method
            int docId = getNextDoctorId();
            doctor.setId(docId);
            //int avaCount = calculateAvailableAppointments(docId);
//            doctor.setavailableAppointments(avaCount);
            doctors.add(doctor);
        } else {
            throw new IllegalArgumentException("Person object is not an instance of Doctor");
        }
    }

    @Override
    public void update(Person person) {
        Doctor updatedDoctor = (Doctor) person;
         // Check if any of the mandatory fields are null or empty
            if (updatedDoctor.getName() == null || updatedDoctor.getName().isEmpty() ||
                updatedDoctor.getSpecialization() == null || updatedDoctor.getSpecialization().isEmpty() ||
                updatedDoctor.gettotalAppointments()<= 0 ||
                updatedDoctor.getStartTime() == null || updatedDoctor.getStartTime().isEmpty()||
                updatedDoctor.getEndTime() == null || updatedDoctor.getEndTime().isEmpty() ) {
                throw new IllegalArgumentException("Name, specialization, start time, end time and total appointments fields are required");
            }
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == person.getId()) {
                doctors.set(i, (Doctor) person);
                return;
            }
        }
        throw new UserNotFoundException("Doctor with ID " + person.getId() + " not found");
    }

    @Override
    public void delete(int id) {
        doctors.removeIf(doctor -> doctor.getId() == id);
    }
    
    
    
    public int getNumberOfScheduledAppointments(int doctorId) {
        List<Appointment> allAppointments = appointmentDAO.getAll();
        int scheduledAppointmentsCount = 0;

        if (allAppointments != null) {
            for (Appointment appointment : allAppointments) {
                if (appointment.getDoctor() != null && appointment.getDoctor().getId() == doctorId) {
                    scheduledAppointmentsCount++;
                }
            }
        }

        return scheduledAppointmentsCount;
    }

    public int calculateAvailableAppointments(int doctorId) {
        Doctor doctor = (Doctor) getById(doctorId);

        if (doctor != null) {
            int totalAppointments = doctor.gettotalAppointments();
            int scheduledAppointments = getNumberOfScheduledAppointments(doctorId);

            int availableAppointments = totalAppointments - scheduledAppointments;

            if (availableAppointments >= 0) {
                return availableAppointments;
            } else {
                // Negative available appointments don't make sense, return 0 instead
                return 0;
            }
        } else {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " not found");
        }
    }


    private int getNextDoctorId() {
        // Initialize maxId with 0 if list is empty
        int maxId = 0;
        if (!doctors.isEmpty()) {
            maxId = doctors.get(doctors.size() - 1).getId();
        }        // Increment maxId to get the next available ID
        return maxId + 1;
    }
}