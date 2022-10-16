package ru.bspl.pet.tradingmarket.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class BusinessUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "businessUnit", cascade = CascadeType.ALL)
    private List<Store> stores;

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

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public BusinessUnit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BusinessUnit() {
    }


}
