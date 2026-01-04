package com.validation;

public class UserValidator {

    public static boolean isAgeAllowed(int age) {

        if (age < 18) {
            System.out.println("😅 Oops! Shaadi ke liye thoda aur bada hona padega.");
            System.out.println("📌 Rule: Minimum age is 18.");
            return false;
        }

        if (age > 60) {
            System.out.println("😄 Respectfully speaking, Jodi.com is taking a retirement from matchmaking at 60+.");
            System.out.println("📌 Rule: Maximum age is 60.");
            return false;
        }

        return true; // Age is valid
    }
}
