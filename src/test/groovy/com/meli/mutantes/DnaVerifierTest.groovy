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

    @Test
    void "Mutant DNA in verticals"() {
        given:
        def dna = [ ['A', 'T', 'G', 'C', 'G', 'A'],
                    ['A', 'A', 'G', 'T', 'G', 'C'],
                    ['A', 'T', 'C', 'T', 'G', 'T'],
                    ['A', 'G', 'A', 'A', 'G', 'G'],
                    ['C', 'C', 'T', 'C', 'T', 'A'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        dnaVerifier.isMutant(getDnaArray(dna))
    }

    @Test
    void "Single sequence in vertical dont characterize a mutant"() {
        given:
        def dna = [ ['A', 'T', 'G', 'C', 'G', 'A'],
                    ['A', 'A', 'G', 'T', 'T', 'C'],
                    ['A', 'T', 'C', 'T', 'G', 'T'],
                    ['A', 'G', 'A', 'A', 'G', 'G'],
                    ['C', 'C', 'T', 'C', 'T', 'A'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        !dnaVerifier.isMutant(getDnaArray(dna))
    }

    @Test
    void "Mutant DNA in main diagonal"() {
        given:
        def dna = [ ['A', 'C', 'G', 'C', 'G', 'A'],
                    ['C', 'A', 'T', 'T', 'G', 'C'],
                    ['T', 'T', 'A', 'T', 'C', 'T'],
                    ['A', 'G', 'A', 'A', 'T', 'G'],
                    ['C', 'A', 'T', 'C', 'T', 'T'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        dnaVerifier.isMutant(getDnaArray(dna))
    }

    @Test
    void "Single sequence in main diagonal dont characterize a mutant"() {
        given:
        def dna = [ ['A', 'C', 'G', 'C', 'G', 'A'],
                    ['C', 'A', 'T', 'T', 'G', 'C'],
                    ['T', 'T', 'C', 'T', 'C', 'T'],
                    ['A', 'G', 'A', 'A', 'T', 'G'],
                    ['C', 'A', 'T', 'C', 'T', 'T'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        !dnaVerifier.isMutant(getDnaArray(dna))
    }

    @Test
    void "Mutant DNA in secondary diagonal"() {
        given:
        def dna = [ ['A', 'C', 'G', 'T', 'G', 'A'],
                    ['C', 'G', 'T', 'G', 'G', 'C'],
                    ['T', 'A', 'G', 'T', 'C', 'T'],
                    ['T', 'G', 'A', 'A', 'T', 'G'],
                    ['C', 'A', 'T', 'C', 'T', 'T'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        dnaVerifier.isMutant(getDnaArray(dna))
    }

    @Test
    void "Single sequence in secondary diagonal dont characterize a mutant"() {
        given:
        def dna = [ ['A', 'C', 'G', 'T', 'G', 'A'],
                    ['C', 'G', 'T', 'G', 'G', 'C'],
                    ['T', 'A', 'C', 'T', 'C', 'T'],
                    ['T', 'G', 'A', 'A', 'T', 'G'],
                    ['C', 'A', 'T', 'C', 'T', 'T'],
                    ['T', 'C', 'A', 'C', 'T', 'G'] ]

        expect:
        !dnaVerifier.isMutant(getDnaArray(dna))
    }

    static String[] getDnaArray(List<List<String>> dna) {
        dna.collect { it.join("") }.toArray(new String[0])
    }

}
