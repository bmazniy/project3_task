package com.mazniy.project3client;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Client {
	public static void main(String[] args) {
        
		final String sensorName = "new sensor 2";

        registerSensor(sensorName);

        Random random = new Random();

        double minTemperature = -30;
        double maxTemperature = 45.0;
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            sendMeasurement(randomTemperature(minTemperature, maxTemperature),
                    random.nextBoolean(), sensorName);
        }
    }
	
	public static double randomTemperature(double min, double max){
		max -= min;
		return (double) (Math.random() * ++max) + min;
	}

    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:7070/sensors/registration";

        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("name", sensorName);

        makePostRequest(url, jsonData);
    }

    private static void sendMeasurement(double value, boolean raining, String sensorName) {
        final String url = "http://localhost:7070/measurements/add";

        Map<String, String> sensorMap = new HashMap<String, String>();
        sensorMap.put("name", sensorName);
        
        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", sensorMap);

        makePostRequest(url, jsonData);
    }

    private static void makePostRequest(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<Object>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("OK");
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
    }
}
