# Makefile para el proyecto de Distancia de Levenshtein

# Variables
JAVAC = javac
JAVA = java
# Asegúrate de que la variable CLASSPATH incluye el directorio actual (.) para encontrar las clases compiladas
CLASSPATH = .
MAIN_CLASS = principal.Main
SOURCE_DIR = . # Directorio raíz donde están los paquetes 'principal' y 'dominio'
CLASS_DIR = bin # Directorio para las clases compiladas

# Define los archivos Java que se van a compilar.
# Asume que LevenshteinDistance.java está en dominio/ y Main.java en principal/
JAVA_FILES = $(shell find $(SOURCE_DIR) -name "*.java")
CLASS_FILES = $(patsubst %.java,$(CLASS_DIR)/%.class,$(JAVA_FILES))
# Asegúrate de que los directorios de clase se creen si no existen
DIRS = $(CLASS_DIR)/principal $(CLASS_DIR)/dominio

# Targets

all: compile run

compile: create_dirs
	$(JAVAC) -d $(CLASS_DIR) -classpath $(CLASSPATH) $(JAVA_FILES)

run: compile
	$(JAVA) -classpath $(CLASSPATH):$(CLASS_DIR) $(MAIN_CLASS)

create_dirs:
	mkdir -p $(DIRS)

clean:
	rm -rf $(CLASS_DIR)

.PHONY: all compile run clean create_dirs