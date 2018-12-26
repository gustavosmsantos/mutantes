package com.meli.mutantes.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "count_mutant_dna", "count_human_dna", "ratio" })
public class StatsResponse {

    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;

    @JsonProperty("count_human_dna")
    private Long countHumanDna;

    @JsonProperty
    public Double ratio() {

        double countMutantDna = this.countMutantDna.doubleValue();

        if (countHumanDna == 0L) {
            return countMutantDna;
        } else if (countMutantDna == 0L) {
            return 0d;
        }

        return countMutantDna / countHumanDna;
    }

    public void setCountMutantDna(Long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public void setCountHumanDna(Long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

}
