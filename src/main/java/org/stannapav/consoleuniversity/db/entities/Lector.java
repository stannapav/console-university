package org.stannapav.consoleuniversity.db.entities;

import jakarta.persistence.*;
import lombok.*;
import org.stannapav.consoleuniversity.db.enums.Degree;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Enumerated(EnumType.STRING)
    private Degree degree;
    
    private double salary;

    @ManyToMany(mappedBy = "lectors", fetch = FetchType.LAZY)
    private List<Department> departments;
}
