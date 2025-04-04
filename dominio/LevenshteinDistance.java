package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevenshteinDistance {
    private int[][] distanceMatrix;
    private char[] processedA;
    private char[] processedB;

    public LevenshteinDistance (String a, String b) {
        this.distanceMatrix = new int[a.length() + 1][b.length() + 1];
        this.processedA = new char[a.length() + 1];
        this.processedA[0] = '\0';
        for (int i = 0; i < a.length(); i++) {
            this.processedA[i + 1] = a.charAt(i);
        }
        this.processedB = new char[b.length() + 1];
        this.processedB[0] = '\0';
        for (int i = 0; i < b.length(); i++) {
            this.processedB[i + 1] = b.charAt(i);
        }
        initializeDistanceMatrix();
    }

    private void initializeDistanceMatrix() {
        for (int i = 0; i < distanceMatrix.length; i++) {
            distanceMatrix[i][0] = i;
        }
        for (int j = 0; j < distanceMatrix[0].length; j++) {
            distanceMatrix[0][j] = j;
        }
    }

    public int calculate () {
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                distanceMatrix[i][j] = step(i, j);
            }
        }
        return (distanceMatrix[processedA.length - 1][processedB.length - 1]);
    }

    public int step (int i, int j) {
        List<Integer> calculate = new ArrayList<>();
        if (i == 0 && j == 0) {
            return 0;
        } else if (i == 0) {
            return j;
        } else if (j == 0) {
            return i;
        } else {
            //erase (Deletion)
            calculate.add(this.distanceMatrix[i - 1][j] + 1);

            //add (Insertion)
            calculate.add(this.distanceMatrix[i][j - 1] + 1);

            //let or substitute
            if (processedA[i] == processedB[j]) {
                calculate.add(this.distanceMatrix[i - 1][j - 1]);
            } else {
                calculate.add(this.distanceMatrix[i - 1][j - 1] + 2);
            }
        }
        return Collections.min(calculate);
    }
}