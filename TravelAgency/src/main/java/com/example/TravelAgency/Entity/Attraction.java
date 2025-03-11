package com.example.TravelAgency.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attraction")
@Data
@NoArgsConstructor
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id", nullable = false)
    private Town town;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_attraction_id", nullable = false)
    private TypeAttraction typeAttraction;
}
