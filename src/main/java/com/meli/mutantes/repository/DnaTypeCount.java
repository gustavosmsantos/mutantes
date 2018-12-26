package com.meli.mutantes.repository;

import com.meli.mutantes.domain.DnaType;

public class DnaTypeCount {

    private DnaType type;

    private Long count;

    public DnaTypeCount(DnaType type, Long count) {
        this.type = type;
        this.count = count;
    }

    public DnaType getType() {
        return type;
    }

    public Long getCount() {
        return count;
    }

}
