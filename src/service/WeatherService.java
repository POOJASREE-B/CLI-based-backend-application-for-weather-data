package service;

import model.Weather;
import repository.WeatherRepository;

import java.time.LocalDateTime;
import java.util.Random;

public class WeatherService {

    private WeatherRepository repository = new WeatherRepository();
    private Random random = new Random();
    private int nextWeatherId = 4;

    public Weather fetchAndSaveWeather(String city) {

        double temp = 10 + random.nextInt(25);
        String remark = "";

        if (temp > 30) {
            remark = "Sunny";
        }
        else if (temp <= 30 && temp > 25) {
            remark = "Clear";
        }
        else if (temp <= 25 && temp > 20) {
            remark = "Cloudy";
        }
        else if (temp <= 20) {
            remark = "Raining";
        }

        Weather newWeather = new Weather(nextWeatherId++, city, temp, remark, LocalDateTime.now());

        boolean isDuplicate = false;
        for (Weather w : repository.findAll()) {
            if (w.getCity().equalsIgnoreCase(city) && w.getTemperature() == temp) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            repository.save(newWeather);
        } else {
            System.out.println("Duplicate weather data skipped saving.");
        }

        return newWeather;
    }

    public void printHistory() {
        if (repository.findAll().isEmpty()) {
            System.out.println("No historical data available.");
            return;
        }
        for (Weather w : repository.findAll()) {
            System.out.println("ID: " + w.getWeatherId() + " | City: " + w.getCity() + " | Temp: " + w.getTemperature() + "°C | Remarks: " + w.getRemarks() + " | Date: " + w.getDate());
        }
    }

    public void filterByRangeTemperature(int minTemp,int maxTemp) {
        boolean found = false;
        for (Weather w : repository.findAll()) {
            if (w.getTemperature() >= minTemp && w.getTemperature() <= maxTemp) {
                System.out.println("City: " + w.getCity() + " | Temp: " + w.getTemperature() + "C" + " | Date: " + w.getDate());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No records found with temperature in range of " + minTemp+ "C and "+ maxTemp +"C");
        }
    }

    public void filterByRangeDate(int minDayOfMonth, int maxDayOfMonth) {
        boolean found = false;
        for (Weather w : repository.findAll()) {
            int recordDay = w.getDate().getDayOfMonth();

            if (recordDay >= minDayOfMonth && recordDay <= maxDayOfMonth) {
                System.out.println("City: " + w.getCity() + " | Temp: " + w.getTemperature() + "C" + " | Date: " + w.getDate());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No records found with dates between the " + minDayOfMonth + "th and " + maxDayOfMonth + "th.");
        }
    }
}