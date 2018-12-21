package com.meli.mutantes;

public class DNAVerifier {

    boolean isMutant(String[] dna) {
        //TODO
        return true;
    }

    private int countSequences(String line, int amount) {
        char[] chars = line.toCharArray();
        int sequencesFound = 0;
        char computedChar = chars[0];
        int computedAmount = 1;
        for(int i = 1; i < chars.length; i++) {
            if (chars[i] == computedChar) {
                if (computedAmount == amount - 1) {
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
