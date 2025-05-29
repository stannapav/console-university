package org.stannapav.consoleuniversity.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.stannapav.consoleuniversity.db.entities.Department;
import org.stannapav.consoleuniversity.db.entities.Lector;
import org.stannapav.consoleuniversity.db.enums.Degree;
import org.stannapav.consoleuniversity.db.repositories.DepartmentRepository;
import org.stannapav.consoleuniversity.db.repositories.LectorRepository;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public DataSeeder(DepartmentRepository departmentRepository, LectorRepository lectorRepository) {
        this.departmentRepository = departmentRepository;
        this.lectorRepository = lectorRepository;
    }

    @Override
    public void run(String... args) {
        if (departmentRepository.count() > 0 || lectorRepository.count() > 0) {
            System.out.println("Database already seeded. Skipping...");
            return;
        }
        
        Lector ivan = new Lector();
        ivan.setName("Ivan Petrenko");
        ivan.setDegree(Degree.PROFESSOR);
        ivan.setSalary(5000);
        
        Lector petro = new Lector();
        petro.setName("Petro Ivanov");
        petro.setDegree(Degree.ASSOCIATE_PROFESSOR);
        petro.setSalary(3500);
        
        Lector oksana = new Lector();
        oksana.setName("Oksana Moroz");
        oksana.setDegree(Degree.ASSISTANT);
        oksana.setSalary(2500);
        
        Lector nina = new Lector();
        nina.setName("Nina Shevchenko");
        nina.setDegree(Degree.ASSOCIATE_PROFESSOR);
        nina.setSalary(3600);

        Lector andriy = new Lector();
        andriy.setName("Andriy Bondarenko");
        andriy.setDegree(Degree.PROFESSOR);
        andriy.setSalary(5200);

        Lector iryna = new Lector();
        iryna.setName("Iryna Kovalchuk");
        iryna.setDegree(Degree.ASSISTANT);
        iryna.setSalary(2400);

        Lector oleksiy = new Lector();
        oleksiy.setName("Oleksiy Hnatyuk");
        oleksiy.setDegree(Degree.ASSOCIATE_PROFESSOR);
        oleksiy.setSalary(3700);

        Lector maria = new Lector();
        maria.setName("Maria Dobrovolska");
        maria.setDegree(Degree.PROFESSOR);
        maria.setSalary(5400);

        lectorRepository.saveAll(List.of(ivan, petro, oksana, nina, andriy, iryna, oleksiy, maria));

        Department physics = new Department();
        physics.setName("Physics");
        physics.setHead(ivan);
        physics.setLectors(List.of(ivan, petro, andriy, maria)); 

        Department mathematics = new Department();
        mathematics.setName("Mathematics");
        mathematics.setHead(petro);
        mathematics.setLectors(List.of(petro, oksana, oleksiy));  

        Department chemistry = new Department();
        chemistry.setName("Chemistry");
        chemistry.setHead(maria);
        chemistry.setLectors(List.of(maria, nina, iryna));

        departmentRepository.saveAll(List.of(physics, mathematics, chemistry));

        System.out.println("Database seeded successfully.");
    }
}