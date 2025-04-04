package principal;

import dominio.LevenshteinDistance;

public class Main {
    public static void main (String[] argv) {
        LevenshteinDistance distance = new LevenshteinDistance("holaHHHH", "holabuenas");
        System.out.println(distance.calculate());
    }
}