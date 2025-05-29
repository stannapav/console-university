package org.stannapav.consoleuniversity.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.stannapav.consoleuniversity.db.entities.Department;
import org.stannapav.consoleuniversity.db.entities.Lector;
import org.stannapav.consoleuniversity.db.enums.Degree;
import org.stannapav.consoleuniversity.db.repositories.DepartmentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentService {
    final private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String getHeadOfDepartment(String department) {
        Optional<Department> optionalDepartment = departmentRepository.findByNameIgnoreCase(department);
        
        if(optionalDepartment.isEmpty()){
            return "Department not found";
        }

        Department foundDepartment = optionalDepartment.get();

        return String.format("Head of %s department is %s", foundDepartment.getName(), foundDepartment.getHead().getName());
    }

    public String getDepartmentStatistics(String department) {
        Optional<Department> optionalDepartment = departmentRepository.findByNameIgnoreCase(department);

        if(optionalDepartment.isEmpty()){
            return "Department not found";
        }

        Map<Degree, Long> stats = optionalDepartment.get().getLectors().stream()
                .collect(Collectors.groupingBy(Lector::getDegree, Collectors.counting()));

        return String.format(
                """
                assistants - %d
                associate professors - %d
                professors - %d""",
                stats.getOrDefault(Degree.ASSISTANT, 0L),
                stats.getOrDefault(Degree.ASSOCIATE_PROFESSOR, 0L),
                stats.getOrDefault(Degree.PROFESSOR, 0L));
    }

    public String getAverageSalary(String department) {
        Optional<Department> optionalDepartment = departmentRepository.findByNameIgnoreCase(department);

        if(optionalDepartment.isEmpty()){
            return "Department not found";
        }

        List<Lector> lectors = optionalDepartment.get().getLectors();
        double avg = lectors.stream().mapToDouble(Lector::getSalary).average().orElse(0);
        
        return String.format("The average salary of %s is %.2f", optionalDepartment.get().getName(), avg);
    }
    
    public String getEmployeeCount(String department) {
        Optional<Department> optionalDepartment = departmentRepository.findByNameIgnoreCase(department);

        if(optionalDepartment.isEmpty()){
            return "Department not found";
        }
        
        List<Lector> lectors = optionalDepartment.get().getLectors();
        
        return String.valueOf(lectors.size());
    }
}
