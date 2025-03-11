package com.example.TravelAgency.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "type_attraction")
@Data
@NoArgsConstructor
public class TypeAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @OneToMany(mappedBy = "typeAttraction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attraction> attractions;
}
