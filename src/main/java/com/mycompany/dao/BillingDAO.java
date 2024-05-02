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
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {

    private List<Billing> billingList = new ArrayList<>();

    public Billing getById(int id) {
        for (Billing billing : billingList) {
            if (billing.getId() == id) {
                return billing;
            }
        }
        return null;
    }

    public List<Billing> getAll() {
        return billingList;
    }

    public void save(Billing billing) {
        billingList.add(billing);
    }

    public void update(Billing billing) {
        for (int i = 0; i < billingList.size(); i++) {
            if (billingList.get(i).getId() == billing.getId()) {
                billingList.set(i, billing);
                return;
            }
        }
    }

    public void delete(int id) {
        billingList.removeIf(billing -> billing.getId() == id);
    }
}
