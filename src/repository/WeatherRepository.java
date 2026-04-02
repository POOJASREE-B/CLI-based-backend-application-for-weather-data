package repository;

import model.Weather;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherRepository {
    private List<Weather> historyDatabase = new ArrayList<>();

    public WeatherRepository() {
        historyDatabase.add(new Weather(1, "Chennai", 20, "Cloudy", LocalDateTime.now().minusDays(2)));
        historyDatabase.add(new Weather(2, "Kanchipuram", 25, "Raining", LocalDateTime.now().minusDays(1)));
        historyDatabase.add(new Weather(3, "Vellore", 31, "Sunny", LocalDateTime.now()));
    }

    public void save(Weather weather) {
        historyDatabase.add(weather);
    }

    public List<Weather> findAll() {
        return historyDatabase;
    }
}