package com.beamm.flightbooking.service;

public interface NotificationService {
    public void sendNotification(String from,String to,String body,String subject);
}
