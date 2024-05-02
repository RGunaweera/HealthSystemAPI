/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exception;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HP
 */

public class JsonObject {

    private Map<String, Object> properties;

    public JsonObject() {
        properties = new HashMap<>();
    }

    public void addProperty(String key, Object value) {
        properties.put(key, value);
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean isFirst = true;
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (!isFirst) {
                sb.append(", ");
            }
            sb.append("\"").append(entry.getKey()).append("\": ");
            Object value = entry.getValue();
            if (value instanceof String) {
                sb.append("\"").append(value).append("\"");
            } else {
                sb.append(value);
            }
            isFirst = false;
        }
        sb.append("}");
        return sb.toString();
    }
}

