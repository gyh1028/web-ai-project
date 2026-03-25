package com.ahu.util;

import java.util.HashMap;
import java.util.Map;

public class CurrentHolder {

    private static final ThreadLocal<Map<String, Object>> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrentId(Integer empId){
        Map<String, Object> map = CURRENT_LOCAL.get();
        if(map == null){
            map = new HashMap<>();
            CURRENT_LOCAL.set(map);
        }
        map.put("id", empId);
    }

    public static void setCurrentUsername(String empUsername){
        Map<String, Object> map = CURRENT_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            CURRENT_LOCAL.set(map);
        }
        map.put("username", empUsername);
    }

    public static Integer getCurrentId(){
        Map<String, Object> map = CURRENT_LOCAL.get();
        return map != null ? (Integer) map.get("id") : null;
    }

    public static String getCurrentUsername(){
        Map<String, Object> map = CURRENT_LOCAL.get();
        return map != null ? (String) map.get("username") : null;
    }

    public static void remove(){
        CURRENT_LOCAL.remove();
    }
}
