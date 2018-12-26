package com.meli.mutantes.controller.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DnaVerificationRequest {

    public DnaVerificationRequest() {
        this(new ArrayList<>());
    }

    public DnaVerificationRequest(@NotNull List<String> dna) {
        this.dna = dna;
    }

    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

}
