package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name="ORGANIZATION", sequenceName="ORGANIZATION_GENERATOR")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ORGANIZATION")
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "counterparty", cascade = CascadeType.ALL)
    private List<Agreement> agreements;

    @OneToMany(mappedBy = "organization", cascade =  CascadeType.ALL)
    private List<Store> stores;

    public Organization(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Organization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
