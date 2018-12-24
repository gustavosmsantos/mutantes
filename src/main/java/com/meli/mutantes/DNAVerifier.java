package com.meli.mutantes;

import java.util.*;

public class DNAVerifier {

    private static final Integer MUTANT_SEQUENCE_LENGTH = 4;

    boolean isMutant(String[] dna) {

        List<String> combinedPossibilities = new ArrayList<>();

        return combinedPossibilities.stream()
                .mapToInt(this::countSequences)
                .sum() > 1;
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

    private String[] allPossibilities(String[] dna) {

        String[] verticals = new String[dna.length];
        String[] diagonalLTR = new String[dna.length * 2 + 1];

        Map<Integer, String> mainDiagonal = new HashMap<>();
        Map<Integer, String> secondaryDiagonal = new HashMap<>();

        for (int i = 0; i < dna.length; i++) {

            char[] verticalSequence = new char[dna.length];

            for (int j = 0; j < dna.length; j++) {
                //vertical
                verticalSequence[j] = dna[j].charAt(i);

                //main diagonal
                mainDiagonal.put(j-i, mainDiagonal.getOrDefault(j-i, "")
                        .concat(Character.toString(dna[i].charAt(j))));

                //secondary diagonal
                if (j == i) {
                    int position = i;
                    if (i % 2 != 0) {
                        position = i + 1;
                    }
                    //TODO should put in the middle
                    secondaryDiagonal.put(position, Character.toString(dna[i].charAt(j)).concat(
                            secondaryDiagonal.getOrDefault(position, "")));
                } else if (j > i) {
                    secondaryDiagonal.put(j-i, Character.toString(dna[i].charAt(j)).concat(
                            secondaryDiagonal.getOrDefault(j-i, "")));
                } else {
                    secondaryDiagonal.put(i-j, Character.toString(dna[i].charAt(j)).concat(
                            secondaryDiagonal.getOrDefault(i-j, "")));
                }

            }
            verticals[i] = Arrays.toString(verticalSequence);
        }

        return secondaryDiagonal.values().toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        System.out.println(Arrays.toString(new DNAVerifier().allPossibilities(dna)));
    }

}
