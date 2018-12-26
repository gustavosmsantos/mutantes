package com.meli.mutantes

class TestUtils {

    static String[] convertMutantInput(List<List<String>> dna) {
        dna.collect { it.join("") }.toArray(new String[0])
    }

}
