package com.ironhack.TheDataLayerHomework3.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ironhack.TheDataLayerHomework3.utils.Input.promptIntWithValidation;

@Component
public class MainMenu {

    @Autowired
    SalesRepMenu salesRepMenu;

    public void menu() {
        int input = 0;

        while (input != 99) {

            input = promptIntWithValidation("(1) Sales Rep Menu \n(99) Exit", 99);

            if (input == 1) salesRepMenu.menu();
        }

        System.exit(0);
    }



}
