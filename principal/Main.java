package principal;

import dominio.LevenshteinDistance;

import java.util.Scanner;

/**
 * <p>
 * La clase {@code Main} es la clase principal de la aplicación.
 * Su función principal es servir como punto de entrada (main method)
 * para ejecutar el programa.
 * En este ejemplo particular, la clase {@code Main} se utiliza para
 * demostrar el uso de la clase {@link LevenshteinDistance} que se encarga
 * de calcular la distancia de Levenshtein entre dos cadenas de texto.
 * </p>
 *
 * <p>
 * La distancia de Levenshtein es una métrica que mide la similitud entre dos
 * cadenas de texto. Se define como el número mínimo de operaciones de edición
 * de un solo carácter (inserciones, eliminaciones o sustituciones) requeridas
 * para transformar una cadena en la otra. Es una medida útil en diversas
 * aplicaciones como la corrección ortográfica, la comparación de ADN y la
 * búsqueda aproximada de cadenas.
 * </p>
 *
 * <p>
 * En resumen, esta clase {@code Main} actúa como un cliente o consumidor
 * de la funcionalidad proporcionada por la clase {@link LevenshteinDistance},
 * permitiendo ejecutar y probar el cálculo de la distancia de Levenshtein
 * con cadenas de ejemplo.
 * </p>
 *
 * @author [Rafael Godoy y Alejandro Santamaria]
 * @version 1.0
 * @since 2025-04-05
 */
public class Main {

    /**
     * El método {@code main} es el punto de entrada de la aplicación Java.
     * Es el primer método que se ejecuta cuando se inicia el programa.
     * <p>
     * En este caso, el método {@code main} realiza las siguientes acciones:
     * </p>
     * <ol>
     *     <li><b>Crea una instancia de la clase {@link LevenshteinDistance}:</b>
     *         Se crea un objeto llamado {@code distance} de la clase
     *         {@link LevenshteinDistance}. Al crear este objeto, se le pasan
     *         dos cadenas de texto como argumentos al constructor: p1 y
     *         p2. Estas dos cadenas son las que se utilizarán para
     *         calcular la distancia de Levenshtein.</li>
     *     <li><b>Llama al método {@code calculate()} del objeto {@code distance}:</b>
     *         Se invoca el método {@code calculate()} sobre el objeto
     *         {@code distance}. Se asume que el método {@code calculate()}
     *         dentro de la clase {@link LevenshteinDistance} es el responsable
     *         de realizar el algoritmo para calcular la distancia de Levenshtein
     *         entre las dos cadenas que se proporcionaron al constructor.
     *         Este método probablemente retornará un valor entero que representa
     *         la distancia calculada.</li>
     *     <li><b>Imprime el resultado en la consola:</b>
     *         Se utiliza {@code System.out.println()} para imprimir en la consola
     *         el valor retornado por el método {@code calculate()}. Este valor
     *         será la distancia de Levenshtein entre las cadenas p1 y
     *         p2.</li>
     * </ol>
     *
     * @see LevenshteinDistance#calculate()
     * @see LevenshteinDistance
     */
    public static void main (String[] argv) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca la primera palabra a comparar: ");
        String p1 = scanner.nextLine();
        System.out.println("Introduzca la segunda palabra a ser comparada: ");
        String p2 = scanner.nextLine();
        LevenshteinDistance distance = new LevenshteinDistance(p1, p2);
        System.out.println("La distancia de Levenshtein  entre la cadena [" + p1 + "] y la cadena [" + p2 + "] es :" + distance.calculate());
        scanner.close();
    }
}