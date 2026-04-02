package model;

import java.time.LocalDateTime;

public class Weather {
    private int weatherId;
    private String city;
    private double temperature;
    private String remarks;
    private LocalDateTime date;

    public Weather(int weatherId, String city, double temperature, String remarks, LocalDateTime date) {
        this.weatherId = weatherId;
        this.city = city;
        this.temperature = temperature;
        this.remarks = remarks;
        this.date = date;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public String getCity() {
        return city;
    }
    public double getTemperature() {
        return temperature;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getRemarks() {
        return remarks;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Weather in %s: %.2f°C, %s (Recorded: %s)", city, temperature, remarks, date);
    }


}