package com.application;

import com.controller.UserController;

public class MainApp {

    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.start();
    }
}
