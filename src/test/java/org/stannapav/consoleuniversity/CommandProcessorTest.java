package org.stannapav.consoleuniversity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stannapav.consoleuniversity.console.CommandProcessor;
import org.stannapav.consoleuniversity.service.DepartmentService;
import org.stannapav.consoleuniversity.service.LectorService;

import static org.mockito.Mockito.*;

public class CommandProcessorTest {
    private DepartmentService departmentService;
    private LectorService lectorService;
    private CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        departmentService = mock(DepartmentService.class);
        lectorService = mock(LectorService.class);
        commandProcessor = new CommandProcessor(lectorService, departmentService);
    }

    @Test
    void testHeadCommand() {
        when(departmentService.getHeadOfDepartment("Physics")).thenReturn("Head of Physics department is Ivan Petrenko");

        commandProcessor.processInput("Who is head of department Physics");

        verify(departmentService).getHeadOfDepartment("Physics");
    }

    @Test
    void testShortenedHeadCommand() {
        when(departmentService.getHeadOfDepartment("Physics")).thenReturn("Head of Physics department is Ivan Petrenko");

        commandProcessor.processInput("head of dept Physics");

        verify(departmentService).getHeadOfDepartment("Physics");
    }

    @Test
    void testStatisticsCommand() {
        when(departmentService.getDepartmentStatistics("Mathematics")).thenReturn("assistants - 1\nassociate professors - 2\nprofessors - 0");

        commandProcessor.processInput("Show Mathematics statistics");

        verify(departmentService).getDepartmentStatistics("Mathematics");
    }

    @Test
    void testShortenedStatisticsCommand() {
        when(departmentService.getDepartmentStatistics("Mathematics")).thenReturn("assistants - 1\nassociate professors - 2\nprofessors - 0");

        commandProcessor.processInput("Mathematics stat");

        verify(departmentService).getDepartmentStatistics("Mathematics");
    }

    @Test
    void testAverageSalaryCommand() {
        when(departmentService.getAverageSalary("Chemistry")).thenReturn("The average salary of Chemistry is 3800.00");

        commandProcessor.processInput("Show the average salary for the department Chemistry");

        verify(departmentService).getAverageSalary("Chemistry");
    }

    @Test
    void testShortenedAverageSalaryCommand() {
        when(departmentService.getAverageSalary("Chemistry")).thenReturn("The average salary of Chemistry is 3800.00");

        commandProcessor.processInput("avg pay of Chemistry dept");

        verify(departmentService).getAverageSalary("Chemistry");
    }

    @Test
    void testEmployeeCountCommand() {
        when(departmentService.getEmployeeCount("Physics")).thenReturn("4");

        commandProcessor.processInput("Show count of employee for Physics");

        verify(departmentService).getEmployeeCount("Physics");
    }

    @Test
    void testShortenedEmployeeCountCommand() {
        when(departmentService.getEmployeeCount("Physics")).thenReturn("4");

        commandProcessor.processInput("number of staff for Physics");

        verify(departmentService).getEmployeeCount("Physics");
    }

    @Test
    void testGlobalSearchCommand() {
        when(lectorService.globalSearch("Ivan")).thenReturn("Ivan Petrenko, Petro Ivanov");

        commandProcessor.processInput("Global search by Ivan");

        verify(lectorService).globalSearch("Ivan");
    }

    @Test
    void testShortenedGlobalSearchCommand() {
        when(lectorService.globalSearch("Ivan")).thenReturn("Ivan Petrenko, Petro Ivanov");

        commandProcessor.processInput("find lector Ivan");

        verify(lectorService).globalSearch("Ivan");
    }

    @Test
    void testUnknownCommand() {
        commandProcessor.processInput("is this a cat university");
        
        verifyNoInteractions(departmentService);
        verifyNoInteractions(lectorService);
    }
}
