package com.aine.offline_upi_finalproject;

import java.util.Map;

public class Gson {
    public String toJson(Map<String, String> questionAnswerMap) {
        com.google.gson.Gson gson = new com.google.gson.Gson();
        return gson.toJson(questionAnswerMap);
    }
}
