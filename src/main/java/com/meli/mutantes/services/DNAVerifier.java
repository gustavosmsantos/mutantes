package com.meli.mutantes.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DNAVerifier {

    private static final Integer MUTANT_SEQUENCE_LENGTH = 4;

    public boolean isMutant(String[] dna) {
        List<String> possibilities = this.allPossibilities(dna);
        return possibilities.stream()
                .filter(s -> s.length() >= MUTANT_SEQUENCE_LENGTH)
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

    private List<String> allPossibilities(String[] dna) {
        List<String> possibilities = new ArrayList<>(Arrays.asList(dna));

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
                secondaryDiagonal.put(j+i, secondaryDiagonal.getOrDefault(j+i, "")
                        .concat(Character.toString(dna[i].charAt(j))));
            }
            possibilities.add(new String(verticalSequence));
        }

        possibilities.addAll(mainDiagonal.values());
        possibilities.addAll(secondaryDiagonal.values());

        return possibilities;
    }

}
