package data;

import java.io.*;
import java.util.ArrayList;
import model.Tour;

public class GestorDatos {
    private final ArrayList<Tour> listaTours;

    public GestorDatos() {
        listaTours = new ArrayList<>();
    }

    public void cargarToursDesdeArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.err.println("ERROR: No se encontró el archivo en: " + rutaArchivo);
            System.err.println("Directorio actual: " + System.getProperty("user.dir"));
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int contadorLineas = 0;
            int toursCargados = 0;

            while ((linea = br.readLine()) != null) {
                contadorLineas++;

                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Separar los datos por punto y coma
                String[] datos = linea.split(";");

                if (datos.length != 5) {
                    System.err.println("Línea " + contadorLineas + " tiene formato incorrecto: " + linea);
                    continue;
                }

                try {
                    String nombre = datos[0].trim();
                    String tipo = datos[1].trim();
                    int duracionHoras = Integer.parseInt(datos[2].trim());
                    double precio = Double.parseDouble(datos[3].trim());
                    int cuposDisponibles = Integer.parseInt(datos[4].trim());

                    Tour tour = new Tour(nombre, tipo, duracionHoras, precio, cuposDisponibles);
                    listaTours.add(tour);
                    toursCargados++;

                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear números en línea " + contadorLineas + ": " + linea);
                }
            }

            System.out.println("Se cargaron " + toursCargados + " tours desde el archivo.");

            if (toursCargados == 0) {
                System.out.println("No se cargó ningún tour. Verifica el formato del archivo.");
                System.out.println("Formato esperado: nombre;tipo;duracionHoras;precio;cuposDisponibles");
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void cargarToursDesdeResources() {
        String nombreArchivo = "tours.txt";

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nombreArchivo)) {

            if (inputStream == null) {
                System.err.println("No se encontró el archivo: " + nombreArchivo);
                System.err.println("Verifica que:");
                System.err.println("1. El archivo tours.txt esté en la carpeta resources");
                System.err.println("2. La carpeta resources esté marcada como Resources Root");
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String linea;
                int contadorLineas = 0;
                int toursCargados = 0;

                while ((linea = br.readLine()) != null) {
                    contadorLineas++;

                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    String[] datos = linea.split(";");

                    if (datos.length != 5) {
                        System.err.println("Línea " + contadorLineas + " tiene formato incorrecto: " + linea);
                        continue;
                    }

                    try {
                        String nombre = datos[0].trim();
                        String tipo = datos[1].trim();
                        int duracionHoras = Integer.parseInt(datos[2].trim());
                        double precio = Double.parseDouble(datos[3].trim());
                        int cuposDisponibles = Integer.parseInt(datos[4].trim());

                        Tour tour = new Tour(nombre, tipo, duracionHoras, precio, cuposDisponibles);
                        listaTours.add(tour);
                        toursCargados++;

                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear números en línea " + contadorLineas + ": " + linea);
                    }
                }

                System.out.println("Se cargaron " + toursCargados + " tours desde el archivo.");
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public ArrayList<Tour> getListaTours() {
        return listaTours;
    }

    public ArrayList<Tour> filtrarPorTipo(String tipo) {
        ArrayList<Tour> filtrados = new ArrayList<>();
        for (Tour tour : listaTours) {
            if (tour.getTipo().equalsIgnoreCase(tipo)) {
                filtrados.add(tour);
            }
        }
        return filtrados;
    }

    public ArrayList<Tour> filtrarPorPrecioMinimo(double precioMin) {
        ArrayList<Tour> filtrados = new ArrayList<>();
        for (Tour tour : listaTours) {
            if (tour.getPrecio() >= precioMin) {
                filtrados.add(tour);
            }
        }
        return filtrados;
    }

    public ArrayList<Tour> filtrarToursDisponibles() {
        ArrayList<Tour> filtrados = new ArrayList<>();
        for (Tour tour : listaTours) {
            if (tour.getCuposDisponibles() > 0) {
                filtrados.add(tour);
            }
        }
        return filtrados;
    }

    public boolean agregarTour(Tour tour) {
        if (tour != null) {
            listaTours.add(tour);
            return true;
        }
        return false;
    }
}