package main;

import entity.Student;
import java.util.ArrayList;
import manage.Manager;
import validation.Validation;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.create();
        while (true) {
            manager.menu();
            int choice = Validation.checkInputIntLimit(1, 5);
            switch (choice) {
                case 1:
                    manager.createStudent();
                    break;
                case 2:
                    manager.findAndSort();
                    break;
                case 3:
                    manager.UpdateOrDelete();
                    break;
                case 4:
                    manager.report();
                    break;
                case 5:
                    return;
            }
        }
    }

}
