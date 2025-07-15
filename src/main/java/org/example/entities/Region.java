package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.enums.ClimatEnum;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private double surface;
    @Enumerated(EnumType.STRING)
    private ClimatEnum climat;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "region_specie",
            joinColumns = @JoinColumn(name = "region_id"),
            inverseJoinColumns = @JoinColumn(name = "specie_id")
    )
    private List<Specie> species;

}
