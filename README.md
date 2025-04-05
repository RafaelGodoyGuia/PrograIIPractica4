# README.md

## Proyecto: Cálculo de la Distancia de Levenshtein

### Descripción del Programa

Este programa en Java calcula la **distancia de Levenshtein** entre dos cadenas de texto. La distancia de Levenshtein es una métrica que mide la similitud entre dos cadenas, contando el número mínimo de operaciones de edición (inserciones, eliminaciones o sustituciones) necesarias para transformar una cadena en la otra.

El programa principal (`principal.Main`) crea una instancia de la clase `LevenshteinDistance` (ubicada en el paquete `dominio`) con dos cadenas de ejemplo ("holaHHHH" y "holabuenas") y luego imprime en la consola la distancia de Levenshtein calculada entre ellas.

### Estructura del Proyecto

El proyecto se organiza en los siguientes paquetes:

* **`principal`**: Contiene la clase principal `Main`, que es el punto de entrada del programa.
* **`dominio`**: Contiene la clase `LevenshteinDistance`, que implementa el algoritmo para calcular la distancia de Levenshtein.

La estructura de directorios del proyecto es la siguiente:
├── dominio/
│ └── LevenshteinDistance.java
├── principal/
│ └── Main.java
├── Makefile
├── LICENSE
└── README.md

Después de compilar, las clases `.class` se almacenarán en un directorio `bin` que se creará en la raíz del proyecto:
├── bin/
│ ├── dominio/
│ │ └── LevenshteinDistance.class
│ └── principal/
│ └── Main.class
├── dominio/
│ └── LevenshteinDistance.java
├── principal/
│ └── Main.java
├── Makefile
├── LICENSE
└── README.md


### Requisitos

Para compilar y ejecutar este programa, necesitas tener instalado:

* **Java Development Kit (JDK)**:  Asegúrate de tener una versión de JDK instalada y configurada en tu sistema. Puedes descargar la última versión desde la página de Oracle o utilizar una distribución OpenJDK. Verifica que las variables de entorno `JAVA_HOME` y `PATH` estén correctamente configuradas para que el sistema pueda encontrar los comandos `javac` y `java`.

### Compilación y Ejecución

El proyecto incluye un `Makefile` para simplificar la compilación y ejecución del programa. Sigue estos pasos:

1. **Abre una terminal** en el directorio raíz del proyecto (donde se encuentra el `Makefile`).

2. **Para compilar el programa**, ejecuta el siguiente comando:
   ```bash
   make compile
   
### Autores
- Rafael Godoy
- Alejandro Santamaria
