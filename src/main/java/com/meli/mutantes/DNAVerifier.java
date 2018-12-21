package com.meli.mutantes;

import java.util.stream.Stream;

public class DNAVerifier {

    private static final Integer MUTANT_SEQUENCE_LENGTH = 4;

    boolean isMutant(String[] dna) {

        String[] horizontalSequences = dna.clone();
        String[] verticalSequences = getVerticalSequences(dna);

        return Stream.concat(Stream.of(horizontalSequences), Stream.of(verticalSequences))
                .map(this::countSequences)
                .reduce(0, (p, a) -> p + a) > 1;
    }

    private String[] getVerticalSequences(String[] dna) {
        String[] sequences = new String[dna.length];
        for (int i = 0;  i < dna.length; i++) {
            char[] chars = new char[dna.length];
            for (int j = 0; j < dna.length; j++) {
                chars[j] = dna[j].charAt(i);
            }
            sequences[i] = String.valueOf(chars);
        }
        return sequences;
    }

    private int countSequences(String line) {
        char[] chars = line.toCharArray();
        int sequencesFound = 0;
        char computedChar = chars[0];
        int computedAmount = 1;
        for(int i = 1; i < chars.length; i++) {
            if (chars[i] == computedChar) {
                if (computedAmount == MUTANT_SEQUENCE_LENGTH - 1) {
                    sequencesFound++;
                    computedAmount = 1;
                } else {
                    computedAmount++;
                }
            } else {
                computedChar = chars[i];
                computedAmount = 1;
            }
        }
        return sequencesFound;
    }

}
