package com.meli.mutantes.controller.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class DnaVerificationRequest {

    @NotNull
    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

}
