package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * La clase {@code LevenshteinDistance} proporciona la funcionalidad para calcular la
 * distancia de Levenshtein entre dos cadenas de texto. La distancia de Levenshtein
 * es una métrica que cuantifica la similitud entre dos secuencias, en este caso,
 * cadenas de caracteres. Se define como el número mínimo de ediciones de un solo
 * carácter (inserciones, eliminaciones o sustituciones) requeridas para transformar
 * una cadena en la otra.
 * </p>
 *
 * <p>
 * Esta implementación utiliza un enfoque de programación dinámica con una matriz
 * para almacenar las distancias de Levenshtein parciales y calcular eficientemente
 * la distancia final. La clase ofrece un constructor para inicializar las cadenas
 * a comparar y un método {@link #calculate()} para realizar el cálculo de la distancia.
 * </p>
 */
public class LevenshteinDistance {
    private int[][] distanceMatrix;
    private char[] processedA;
    private char[] processedB;

    /**
     * Inicializa una nueva instancia de la clase {@code LevenshteinDistance}
     * con las dos cadenas de texto para las que se calculará la distancia.
     * <p>
     * El constructor realiza los siguientes pasos:
     * </p>
     * <ol>
     *     <li><b>Crea la matriz de distancias:</b>
     *         Inicializa {@link #distanceMatrix} como una matriz bidimensional de enteros.
     *         Las dimensiones de la matriz son {@code (a.length() + 1) x (b.length() + 1)}
     *         para poder almacenar las distancias parciales, incluyendo los casos base
     *         de cadenas vacías.</li>
     *     <li><b>Procesa la primera cadena ({@code a}):</b>
     *         Crea el array {@link #processedA} de caracteres con una longitud de
     *         {@code a.length() + 1}.  El primer elemento (índice 0) se establece
     *         como el carácter nulo ('\0'). Luego, copia los caracteres de la cadena
     *         {@code a} al array {@link #processedA} a partir del índice 1. El uso del
     *         carácter nulo al inicio y el indexado desde 1 simplifica la lógica del
     *         algoritmo de distancia de Levenshtein.
     *         <p>
     *         <b>Bucle for:</b> Se utiliza un bucle {@code for} para iterar sobre
     *         cada carácter de la cadena de entrada {@code a} y copiarlo al array
     *         {@link #processedA}. El bucle itera desde {@code i = 0} hasta
     *         {@code i < a.length()}.
     *         </p>
     *     </li>
     *     <li><b>Procesa la segunda cadena ({@code b}):</b>
     *         Realiza el mismo proceso que para la primera cadena, pero con la cadena
     *         {@code b} y el array {@link #processedB}. Crea el array {@link #processedB}
     *         de caracteres con longitud {@code b.length() + 1}, establece el primer
     *         elemento como '\0', y copia los caracteres de {@code b} a partir del índice 1.
     *         <p>
     *         <b>Bucle for:</b> Similar al procesamiento de la primera cadena, se utiliza
     *         un bucle {@code for} para iterar sobre la cadena {@code b} y llenar
     *         el array {@link #processedB}. El bucle itera desde {@code i = 0} hasta
     *         {@code i < b.length()}.
     *         </p>
     *     </li>
     *     <li><b>Inicializa la matriz de distancias:</b>
     *         Llama al método {@link #initializeDistanceMatrix()} para establecer
     *         los valores iniciales de la primera fila y la primera columna de la
     *         {@link #distanceMatrix}. Estos valores representan los casos base del
     *         algoritmo de distancia de Levenshtein (distancia de una cadena vacía
     *         a un prefijo de la otra cadena).</li>
     * </ol>
     *
     * @param a La primera cadena de texto para calcular la distancia de Levenshtein.
     * @param b La segunda cadena de texto para calcular la distancia de Levenshtein.
     */
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

    /**
     * Inicializa la primera fila y la primera columna de la {@link #distanceMatrix}
     * para representar los casos base del algoritmo de distancia de Levenshtein.
     * <p>
     * Este método establece los siguientes valores en la matriz:
     * </p>
     * <ul>
     *     <li><b>Primera columna ({@code j = 0}):</b> {@code distanceMatrix[i][0] = i}
     *         para todo {@code i} desde 0 hasta {@code distanceMatrix.length - 1}.
     *         Esto representa la distancia de transformar una cadena de longitud {@code i}
     *         a una cadena vacía, lo cual requiere {@code i} eliminaciones.
     *         <p>
     *         <b>Bucle for:</b> Se utiliza un bucle {@code for} para iterar sobre las
     *         filas de la matriz (índice {@code i}) y asignar el valor de {@code i}
     *         a cada elemento de la primera columna. El bucle itera desde {@code i = 0}
     *         hasta {@code i < distanceMatrix.length}.
     *         </p>
     *     </li>
     *     <li><b>Primera fila ({@code i = 0}):</b> {@code distanceMatrix[0][j] = j}
     *         para todo {@code j} desde 0 hasta {@code distanceMatrix[0].length - 1}.
     *         Esto representa la distancia de transformar una cadena vacía a una cadena
     *         de longitud {@code j}, lo cual requiere {@code j} inserciones.
     *         <p>
     *         <b>Bucle for:</b> Se utiliza un bucle {@code for} para iterar sobre las
     *         columnas de la matriz (índice {@code j}) y asignar el valor de {@code j}
     *         a cada elemento de la primera fila. El bucle itera desde {@code j = 0}
     *         hasta {@code j < distanceMatrix[0].length}.
     *         </p>
     *     </li>
     * </ul>
     *
     * <p>
     * Estos valores iniciales son cruciales para el algoritmo de programación dinámica
     * utilizado en el cálculo de la distancia de Levenshtein.
     * </p>
     */
    private void initializeDistanceMatrix() {
        for (int i = 0; i < distanceMatrix.length; i++) {
            distanceMatrix[i][0] = i;
        }
        for (int j = 0; j < distanceMatrix[0].length; j++) {
            distanceMatrix[0][j] = j;
        }
    }

    /**
     * Calcula la distancia de Levenshtein entre las dos cadenas proporcionadas
     * en el constructor.
     * <p>
     * Este método implementa el algoritmo de programación dinámica para calcular la
     * distancia de Levenshtein.  Utiliza la matriz {@link #distanceMatrix} previamente
     * inicializada y el método auxiliar {@link #step(int, int)} para determinar el
     * valor de cada celda en la matriz.
     * </p>
     * <p>
     * El cálculo se realiza llenando la matriz {@link #distanceMatrix} de forma iterativa,
     * desde la segunda fila y columna en adelante (índices 1 hasta el final). Para cada
     * celda {@code distanceMatrix[i][j]}, se llama al método {@link #step(int, int)}
     * para determinar el valor, que se basa en los valores de las celdas adyacentes
     * ya calculadas (arriba, izquierda y diagonal superior izquierda).
     * </p>
     * <p>
     * <b>Bucles for anidados:</b> Se utilizan dos bucles {@code for} anidados para
     * iterar sobre todas las celdas de la {@link #distanceMatrix} (excepto la primera
     * fila y columna, que ya están inicializadas).
     * </p>
     * <ol>
     *     <li>El bucle exterior itera sobre las filas (índice {@code i}) desde 0 hasta
     *         {@code distanceMatrix.length}.</li>
     *     <li>El bucle interior itera sobre las columnas (índice {@code j}) desde 0 hasta
     *         {@code distanceMatrix[0].length}.</li>
     *     <li>Dentro de los bucles anidados, se llama a {@link #step(int, int)} con
     *         los índices {@code i} y {@code j} para calcular y asignar el valor de
     *         {@code distanceMatrix[i][j]}.</li>
     * </ol>
     * <p>
     * Una vez que se ha llenado toda la matriz, la distancia de Levenshtein final
     * entre las dos cadenas completas se encuentra en la celda inferior derecha de la
     * matriz, que es {@code distanceMatrix[processedA.length - 1][processedB.length - 1]}.
     * Este valor es el que se retorna.
     * </p>
     *
     * @return La distancia de Levenshtein entre las dos cadenas de texto.
     */
    public int calculate () {
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                distanceMatrix[i][j] = step(i, j);
            }
        }
        return (distanceMatrix[processedA.length - 1][processedB.length - 1]);
    }

    /**
     * Calcula el valor de una celda específica en la {@link #distanceMatrix}
     * en función de las celdas adyacentes y de la comparación de los caracteres
     * correspondientes de las cadenas de texto.
     * <p>
     * Este método implementa la lógica recursiva (o, más precisamente, iterativa dentro
     * del contexto de la programación dinámica) para determinar la distancia de Levenshtein
     * en la posición {@code (i, j)}. Considera tres operaciones posibles:
     * </p>
     * <ol>
     *     <li><b>Eliminación (Deletion):</b> Eliminar un carácter de la primera cadena.
     *         El costo es 1 más la distancia de Levenshtein de las subcadenas
     *         anteriores, que se encuentra en {@code distanceMatrix[i - 1][j]}.</li>
     *     <li><b>Inserción (Insertion):</b> Insertar un carácter en la primera cadena
     *         (o equivalentemente, eliminar un carácter de la segunda cadena para
     *         alinearlas). El costo es 1 más la distancia de Levenshtein de las
     *         subcadenas anteriores, que se encuentra en {@code distanceMatrix[i][j - 1]}.</li>
     *     <li><b>Sustitución o Coincidencia (Substitution/Match):</b>
     *         <ul>
     *             <li>Si los caracteres {@code processedA[i]} y {@code processedB[j]} son iguales,
     *                 no se requiere operación. El costo es la distancia de Levenshtein
     *                 de las subcadenas anteriores, que se encuentra en {@code distanceMatrix[i - 1][j - 1]}.</li>
     *             <li>Si los caracteres son diferentes, se requiere una sustitución. El costo
     *                 es 2 (en este ejemplo, se ha definido un costo de 2 para la sustitución)
     *                 más la distancia de Levenshtein de las subcadenas anteriores,
     *                 {@code distanceMatrix[i - 1][j - 1]}.</li>
     *         </ul>
     *     </li>
     * </ol>
     * <p>
     * El método calcula las distancias correspondientes a estas tres operaciones (o menos
     * en los casos base) y selecciona el mínimo de estos valores como el valor de
     * {@code distanceMatrix[i][j]}.
     * </p>
     * <p>
     * <b>Casos base:</b>
     * </p>
     * <ul>
     *     <li>Si {@code i == 0} y {@code j == 0}, la distancia es 0 (ambas cadenas vacías).</li>
     *     <li>Si {@code i == 0}, la distancia es {@code j} (transformar una cadena vacía
     *         en una cadena de longitud {@code j} requiere {@code j} inserciones).</li>
     *     <li>Si {@code j == 0}, la distancia es {@code i} (transformar una cadena de
     *         longitud {@code i} en una cadena vacía requiere {@code i} eliminaciones).</li>
     * </ul>
     * <p>
     * <b>Uso de ArrayList y Collections.min:</b> Se utiliza un {@link ArrayList} llamado
     * {@code calculate} para almacenar las distancias calculadas para las operaciones
     * de eliminación, inserción y sustitución/coincidencia. Luego, se utiliza
     * {@link Collections#min(java.util.Collection)} para encontrar el mínimo de estos
     * valores, que representa la distancia de Levenshtein óptima en la posición {@code (i, j)}.
     * </p>
     *
     * @param i El índice de fila de la celda en {@link #distanceMatrix} para la que se
     *          está calculando el valor (corresponde al índice en {@link #processedA}).
     * @param j El índice de columna de la celda en {@link #distanceMatrix} para la que
     *          se está calculando el valor (corresponde al índice en {@link #processedB}).
     * @return El valor calculado para la celda {@code distanceMatrix[i][j]}, que representa
     *         la distancia de Levenshtein parcial.
     */
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