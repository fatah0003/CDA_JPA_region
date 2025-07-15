package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.enums.CategoryEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "specie")
public class Specie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String commonName;
    private String scientificName;
    private CategoryEnum category;
    @ManyToMany(mappedBy = "species")
    private List<Region> regions;
    @OneToOne(mappedBy = "specie")
    private Observation observation;
}
