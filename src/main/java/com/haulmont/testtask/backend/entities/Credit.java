package com.haulmont.testtask.backend.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Credit {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid")
    private String id;

    private String creditName;

    private long creditLimit;
    private Integer percent;
    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<CreditOffer> creditOffers;

    public Credit(){
        id = UUID.randomUUID().toString();
    }

    public Credit(String creditName, Long creditLimit, Integer percent) {
        id = UUID.randomUUID().toString();
        this.creditName = creditName;
        this.creditLimit = creditLimit;
        this.percent = percent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Set<CreditOffer> getCreditOffers() {
        return creditOffers;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        creditOffers.add(creditOffer);
    }
}
