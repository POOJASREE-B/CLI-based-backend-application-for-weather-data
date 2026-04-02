package service;

import model.Subscription;
import repository.SubscriptionRepository;

public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository = new SubscriptionRepository();

    public Subscription getSubscriptionForUser(int userId) {
        for (Subscription sub : subscriptionRepository.findAll()) {
            if (sub.getSubscriptionId() == userId) {
                return sub;
            }
        }
        return null;
    }

    public boolean canFetchWeather(int userId) {
        Subscription sub = getSubscriptionForUser(userId);
        if (sub != null && sub.getRemainingBenefits() > 0) {
            sub.setRemainingBenefits(sub.getRemainingBenefits() - 1);
            sub.setBenefitsUsed(sub.getBenefitsUsed() + 1);
            return true;
        }
        return false;
    }

    public void assignDefaultSubscription(int userId) {
        Subscription newSub = new Subscription(userId, "Basic Plan", "3 Searches per day", 0, 3);
        subscriptionRepository.save(newSub);
    }
}