import model.Subscription;
import model.User;
import model.Weather;
import service.SubscriptionService;
import service.UserService;
import service.WeatherService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        SubscriptionService subscriptionService = new SubscriptionService();
        WeatherService weatherService = new WeatherService();

        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n--- Welcome ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose an option: ");
            String startChoice = scanner.nextLine();

            if (startChoice.equals("1")) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                loggedInUser = userService.login(username, password);

                if (loggedInUser == null) {
                    System.out.println("Invalid credentials. Try again.");
                } else {
                    System.out.println("Welcome back, " + loggedInUser.getUserName() + "!");
                }

            } else if (startChoice.equals("2")) {
                System.out.print("Choose a Username: ");
                String newUsername = scanner.nextLine();
                System.out.print("Enter your City: ");
                String newCity = scanner.nextLine();
                System.out.print("Choose a Password: ");
                String newPassword = scanner.nextLine();

                User newlyRegisteredUser = userService.register(newUsername, newCity, newPassword);

                if (newlyRegisteredUser != null) {
                    subscriptionService.assignDefaultSubscription(newlyRegisteredUser.getUserId());
                    loggedInUser = newlyRegisteredUser;
                    System.out.println("Welcome! You have been given 3 free searches.");
                }
            } else {
                System.out.println("Invalid choice. Please press 1 or 2.");
            }
        }

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Fetch current weather for a city");
            System.out.println("2. View historical weather data");
            System.out.println("3. Filter history by Temperature Range");
            System.out.println("4. Filter history by Date Range (Day of Month)");
            System.out.println("5. View my subscription status");
            System.out.println("6. Exit");

            if (loggedInUser.isAdmin()) {
                System.out.println("7. Admin: View all registered users");
            }

            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (subscriptionService.canFetchWeather(loggedInUser.getUserId())) {
                        System.out.print("Enter city name: ");
                        String city = scanner.nextLine();

                        Weather w = weatherService.fetchAndSaveWeather(city);
                        System.out.println("\nCurrent Weather in " + w.getCity() + ": " +
                                w.getTemperature() + "C, " + w.getRemarks());
                    } else {
                        System.out.println("Subscription limit reached! You have 0 searches left.");
                    }
                    break;

                case "2":
                    System.out.println("\n--- Weather History ---");
                    weatherService.printHistory();
                    break;

                case "3":
                    try {
                        System.out.print("Enter MINIMUM temperature: ");
                        int minTemp = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter MAXIMUM temperature: ");
                        int maxTemp = Integer.parseInt(scanner.nextLine());

                        System.out.println("\n--- Filtered Results ---");
                        weatherService.filterByRangeTemperature(minTemp, maxTemp);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    }
                    break;

                case "4":
                    try {
                        System.out.print("Enter START date (1-31): ");
                        int minDate = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter END date (1-31): ");
                        int maxDate = Integer.parseInt(scanner.nextLine());

                        System.out.println("\n--- Filtered Results ---");
                        weatherService.filterByRangeDate(minDate, maxDate);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    }
                    break;

                case "5":
                    Subscription sub = subscriptionService.getSubscriptionForUser(loggedInUser.getUserId());
                    if (sub != null) {
                        System.out.println("\n--- My Subscription ---");
                        System.out.println("Plan: " + sub.getSubscriptionName());
                        System.out.println("Used Searches: " + sub.getBenefitsUsed());
                        System.out.println("Remaining Searches: " + sub.getRemainingBenefits());
                    } else {
                        System.out.println("No subscription found for this account.");
                    }
                    break;

                case "6":
                    System.out.println("Exiting application");
                    isRunning = false;
                    break;

                case "7":
                    if (loggedInUser.isAdmin()) {
                        System.out.println("\n--- All Registered Users ---");
                        for (User u : userService.getAllUsers()) {
                            System.out.println("ID: " + u.getUserId() + " | Name: " + u.getUserName() + " | City: " + u.getLivingCity() + " | Admin: " + u.isAdmin());
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid number.");
            }
        }

        scanner.close();
    }
}