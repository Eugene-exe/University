package com.botscrew.university;

import com.botscrew.university.exception.CustomException;
import com.botscrew.university.exception.CustomFn;
import com.botscrew.university.services.UniversityService;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class UniversityApplication implements CommandLineRunner {

    private final UniversityService universityService;

    public static void main(String[] args) {

        SpringApplication.run(UniversityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        int choice = 0;
        while (!input.equals("exit")) {

            if (choice == 0) {

                System.out.println("Type number to select a command  or exit to close an app");
                System.out.println("1.Who is head of Department");
                System.out.println("2.Show department statistic");
                System.out.println("3.Show average salary for department");
                System.out.println("4.Show count of employee for department");
                System.out.println("5.Global search for template");
                input = scanner.next();

                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("ERROR");
                }

            }

            switch (choice) {

                case 1:
                    input = askForDepartmentName(scanner);
                    System.out.println("Answer :Head of " + input + " department is " + tryExec(universityService::takeHeadOfDepartment, input));
                    choice = 0;
                    break;
                case 2:
                    input = askForDepartmentName(scanner);
                    System.out.println(tryExec(universityService::takeDepartmentStatistics, input));
                    choice = 0;
                    break;
                case 3:
                    input = askForDepartmentName(scanner);
                    System.out.println("Answer: The average salary of " + input + " is " + tryExec(universityService::takeAverageSalary, input));
                    choice = 0;
                    break;
                case 4:
                    input = askForDepartmentName(scanner);
                    System.out.println("Answer: "+tryExec(universityService::takeEmployeeCount, input));
                    choice = 0;
                    break;
                case 5:
                    System.out.println("Type a keyword");
                    input = scanner.next();
                    System.out.println("Answer: "+ tryExec(universityService::globalSearch, input));
                    choice = 0;
                    break;

                default:
                    System.out.println("ERROR");
                    choice = 0;
                    break;

            }


        }

    }
    public String tryExec(CustomFn<String, String> f, String arg) {
        try {
            return (f.apply(arg));
        } catch (CustomException e) {
            return "ERROR: " + e.getMessage();
        } catch (Exception ex) {
            return "ERROR";
        }
    }

    private String askForDepartmentName(Scanner scanner) {
        System.out.println("Type department name");
        return scanner.next();
    }
}
