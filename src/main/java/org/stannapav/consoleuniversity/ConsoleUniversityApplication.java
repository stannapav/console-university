package org.stannapav.consoleuniversity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.stannapav.consoleuniversity.console.CommandProcessor;
import org.stannapav.consoleuniversity.service.DepartmentService;
import org.stannapav.consoleuniversity.service.LectorService;

import java.util.Scanner;

@SpringBootApplication
public class ConsoleUniversityApplication implements CommandLineRunner {
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    public ConsoleUniversityApplication(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        CommandProcessor processor = new CommandProcessor(lectorService,departmentService);

        System.out.println("Welcome to university console! Please ask me questions and I will answer.\n" +
                "If need help write 'help' and to exit program 'exit'");
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if(input.trim().equalsIgnoreCase("help")){
                System.out.println("""
                        You can ask me questions like this:
                        1) Who is head of department {department_name}
                        2) Show {department_name} statistics
                        3) Show the average salary for the department {department_name}
                        4) Show count of employee for {department_name}
                        5) Global search by {template}
                        
                        Or if you are lazy
                        1) head of dept {department_name}
                        2) {department_name} stat
                        3) avg pay of {department_name} dept
                        4) number of staff for {department_name}
                        5) find lector {template}
                        """);
                continue;
            }
            
            if (input.trim().equalsIgnoreCase("exit")) break;
            processor.processInput(input);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleUniversityApplication.class, args);
    }
}
