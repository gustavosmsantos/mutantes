package com.meli.mutantes;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DNAVerifierTest {

    private static final DNAVerifier verifier = new DNAVerifier();

    @Test
    public void baseMutantHuman() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertTrue("DNA comes from a mutant", verifier.isMutant(dna));
    }

    @Test
    public void baseNonMutantHuman() {
        String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        assertFalse("DNA comes from a non-mutant", verifier.isMutant(dna));
    }

}
