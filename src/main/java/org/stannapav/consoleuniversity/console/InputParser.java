package org.stannapav.consoleuniversity.console;

public class InputParser {
    private static final String departmentRegex = "(?i)(who is|the head|head|show|statistics|stat|average|salary|pay|for|the department|count|number|employees|employee|staff|the average|department|dept|avg|of)";
    private static final String lectorNameRegex = "(?i)(global|search by|find|search|lectors by|lectors|lector by|lector)";
    
    public static String extractDepartment(String input){
        return input.replaceAll(departmentRegex, "").trim();
    }
    
    public static String extractLectorName(String input){
        return input.replaceAll(lectorNameRegex, "").trim();
    }
}
