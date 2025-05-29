package org.stannapav.consoleuniversity.console;

import org.stannapav.consoleuniversity.service.DepartmentService;
import org.stannapav.consoleuniversity.service.LectorService;

import java.util.List;

public class CommandProcessor {
    final private LectorService lectorService;
    final private DepartmentService departmentService;

    public CommandProcessor(LectorService lectorService, DepartmentService departmentService) {
        this.lectorService = lectorService;
        this.departmentService = departmentService;
    }
    
    private final List<List<String>> HEAD_COMMAND = List.of(
            List.of("head"), List.of("department", "dept")
    );
    
    private final List<List<String>> STATISTICS_COMMAND = List.of(
            List.of("statistics", "stat")
    );
    
    private final List<List<String>> AVERAGE_SALARY_COMMAND = List.of(
            List.of("average", "avg"), List.of("salary", "pay")
    );
    
    private final List<List<String>> EMPLOYEE_COUNT_COMMAND = List.of(
            List.of("count", "number"), List.of("employee", "employees", "staff")
    );
    
    private final List<List<String>> SEARCH_COMMAND = List.of(
            List.of("search", "find")
    );

    private boolean matchesCommand(String input, List<List<String>> keywordGroups) {
        String lowerInput = input.toLowerCase();
        return keywordGroups.stream()
                .allMatch(group -> group.stream().anyMatch(lowerInput::contains));
    }

    public void processInput(String input) {
        if (matchesCommand(input, HEAD_COMMAND)) {
            String department = InputParser.extractDepartment(input);
            System.out.println(departmentService.getHeadOfDepartment(department));
        } else if (matchesCommand(input, STATISTICS_COMMAND)) {
            String department = InputParser.extractDepartment(input);
            System.out.println(departmentService.getDepartmentStatistics(department));
        } else if (matchesCommand(input, AVERAGE_SALARY_COMMAND)) {
            String department = InputParser.extractDepartment(input);
            System.out.println(departmentService.getAverageSalary(department));
        } else if (matchesCommand(input, EMPLOYEE_COUNT_COMMAND)) {
            String department = InputParser.extractDepartment(input);
            System.out.println(departmentService.getEmployeeCount(department));
        } else if (matchesCommand(input, SEARCH_COMMAND)) {
            String keyword = InputParser.extractLectorName(input);
            System.out.println(lectorService.globalSearch(keyword));
        } else {
            System.out.println("Unrecognized command. Try again.");
        }
    }
}
