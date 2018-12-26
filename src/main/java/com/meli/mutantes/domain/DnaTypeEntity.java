package com.meli.mutantes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class DnaTypeEntity {

    @Id
    private Integer hashedDna;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private DnaType dnaType;

    public Integer getHashedDna() {
        return hashedDna;
    }

    public void setHashedDna(Integer hashedDna) {
        this.hashedDna = hashedDna;
    }

    public DnaType getDnaType() {
        return dnaType;
    }

    public void setDnaType(DnaType dnaType) {
        this.dnaType = dnaType;
    }

}