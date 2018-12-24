package com.meli.mutantes

import org.junit.Test
import spock.lang.Specification

class DnaVerifierTest extends Specification {

    def dnaVerifier = new DNAVerifier()

    @Test
    void "Example mutant DNA"() {
        given:
        def dna = [ ['A', 'T', 'G', 'C', 'G', 'A'],
                    ['C', 'A', 'G', 'T', 'G', 'C'],
                    ['T', 'T', 'A', 'T', 'G', 'T'],
                    ['A', 'G', 'A', 'A', 'G', 'G'],
                    ['C', 'C', 'C', 'C', 'T', 'A'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        dnaVerifier.isMutant(getDnaArray(dna))
    }

    @Test
    void "Example non mutant DNA"() {
        given:
        def dna = [ ['A', 'T', 'G', 'C', 'G', 'A'],
                    ['C', 'A', 'G', 'T', 'G', 'C'],
                    ['T', 'T', 'A', 'T', 'T', 'T'],
                    ['A', 'G', 'A', 'C', 'G', 'G'],
                    ['G', 'C', 'G', 'T', 'C', 'A'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        !dnaVerifier.isMutant(getDnaArray(dna))
    }

    static String[] getDnaArray(List<List<String>> dna) {
        dna.collect { it.join("") }.toArray(new String[0])
    }

}
