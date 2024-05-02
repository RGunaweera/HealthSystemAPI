/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Randima
 */

import com.mycompany.model.Person;

import java.util.List;

public interface PersonDAO {
    
    Person getById(int id);
    List<Person> getAll();
    void save(Person person);
    void update(Person person);
    void delete(int id);    

}