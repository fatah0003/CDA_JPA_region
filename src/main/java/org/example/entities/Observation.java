package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "id_specie")
    private Specie specie;
    private String location;
    private Double latitude;
    private Double longitude;
    private LocalDate observationDate;
    private String comment;
    @OneToOne(mappedBy = "observation", cascade = CascadeType.ALL)
    private Deplacement deplacement;

}
