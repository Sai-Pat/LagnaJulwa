package com.controller;

import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;
import com.util.LanguageUtil;
import com.validation.UserValidator;

import java.util.Scanner;

public class UserController {

    private final UserService userService = new UserServiceImpl();
    private final Scanner sc = new Scanner(System.in);

    private void userMenu(User user) {

    while (true) {
        System.out.println("\n👤 Welcome " + user.getName());
        System.out.println("1. View Available Matches");
        System.out.println("2. Logout");
        System.out.print("Choose: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("\n❤️ Available Matches:");
                userService.findMatches(user).forEach(m ->
                        System.out.println(
                                "Name: " + m.getName() +
                                " | Profession: " + m.getProfession()
                        )
                );
                break;

            case 2:
                System.out.println("👋 Logged out successfully.");
                return; // exit user menu → back to main menu

            default:
                System.out.println("⚠️ Invalid choice.");
        }
    }
}


    public void start() {

        System.out.println(LanguageUtil.get("welcome"));

        while (true) {
            try {
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        register();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        System.out.println("👋 Thank you for using Jodi.com. May your match be perfect!");
                        System.exit(0);
                    default:
                        System.out.println("⚠️ Invalid choice. Try again.");
                }

            } catch (Exception e) {
                System.out.println("❌ Invalid input detected. Please enter numbers only.");
                sc.nextLine();
            }
        }
    }

    private void register() {
        try {
            User u = new User();

            System.out.print("Name: ");
            u.setName(sc.nextLine());

            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            if (!UserValidator.isAgeAllowed(age)) {
                System.out.println("🚫 Registration terminated.");
                return;
            }
            u.setAge(age);

            System.out.print("Gender: ");
            u.setGender(sc.nextLine());

            System.out.print("Email: ");
            u.setEmail(sc.nextLine());

            System.out.print("Password: ");
            u.setPassword(sc.nextLine());

            System.out.print("City: ");
            u.setCity(sc.nextLine());

            System.out.print("Profession: ");
            u.setProfession(sc.nextLine());

            System.out.print("Looking For (Male/Female): ");
            u.setLookingFor(sc.nextLine());

            boolean success = userService.registerUser(u);

            if (success)
                System.out.println("🎉 Registration successful!");
            else
                System.out.println("❌ Registration failed.");

        } catch (Exception e) {
            System.out.println("❌ Error during registration. Please try again.");
        }
    }

    private void login() {
    try {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        User user = userService.login(email, pass);

        if (user != null) {
            userMenu(user);
        } else {
            System.out.println("❌ Invalid login credentials.");
        }

    } catch (Exception e) {
        System.out.println("❌ Login error occurred.");
    }
}

}
