package repository;

import model.Subscription;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRepository {
    private List<Subscription> subscriptionDatabase = new ArrayList<>();

    public SubscriptionRepository() {
        subscriptionDatabase.add(new Subscription(1, "Unlimited Plan", "Unlimited access", 0, 999));
        subscriptionDatabase.add(new Subscription(2, "Basic Plan", "3 Searches per day", 0, 3));
    }

    public void save(Subscription subscription) {
        subscriptionDatabase.add(subscription);
    }

    public List<Subscription> findAll() {
        return subscriptionDatabase;
    }
}