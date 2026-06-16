Llanquihue Tour

Es una aplicación basada en java, creada exclusivamente para una agencia de tours ubicada en Llanquihue con el propósito de poder gestionar los tours que suele realizar la empresa a lo largo de la comuna y su región.

La aplicación permite:
- Leer datos desde un archivo .txt .
- Crear Tours con sus atributos; nombre, tipo, duración, precio y cupos.
- Almacenar los tours en ArrayList.
- Realizar búsquedas y filtrados por:
   Tipo de tour (gastronómico, lacustre, aventura, cultural, relax).
   Precio mínimo.
   Disponibilidad de cupos.
   Agregar nuevos tours en memoria.

Estructura de Llanquihue Tour:
LlanquihueTourApp/
----|src/
------| model/
| | --- Tour.java 
| |-- data/
| | --- GestorDatos.java 
| |-- ui/
| | --- Main.java
| --- resources/
| --- tours.txt
|-- README.md
|-- .gitignore


   Instrucciones para ejecutar clase Main:

   1) Ejecutar Intellij IDEA.
   2) Seeccionar File -> Open.
   3) Buscar carpeta Llanquihue-Tour y seleccionar.
   4) Navegar hasta src/ui/Main.Java
   5) Hacer clic derecho en el archivo.
   6) Seleccionar Run "Main.main()"
