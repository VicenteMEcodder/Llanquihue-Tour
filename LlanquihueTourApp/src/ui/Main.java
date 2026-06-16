package ui;

import data.GestorDatos;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Tour;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try (scanner) {
            System.out.println("=== LLANQUIHUE TOUR - SISTEMA DE GESTIÓN ===\n");
            
            GestorDatos gestor = new GestorDatos();
            
            String rutaArchivo = "src/resources/tours.txt";
            
            System.out.println("Cargando datos desde archivo...");
            gestor.cargarToursDesdeArchivo(rutaArchivo);
            System.out.println();
            
            if (gestor.getListaTours().isEmpty()) {
                System.out.println("ADVERTENCIA: No se cargó ningún tour.");
                System.out.println("Verifica que:");
                System.out.println("1. El archivo tours.txt existe en: " + rutaArchivo);
                System.out.println("2. El archivo tiene al menos una línea con el formato correcto");
                System.out.println("3. El formato es: nombre;tipo;duracionHoras;precio;cuposDisponibles\n");
            }
            
            boolean continuar = true;
            
            while (continuar) {
                mostrarMenuPrincipal();
                int opcion = leerOpcion();
                
                switch(opcion) {
                    case 1 -> mostrarTodosLosTours(gestor);
                    case 2 -> mostrarMenuFiltros(gestor);
                    case 3 -> {
                        if (gestor.getListaTours().isEmpty()) {
                            System.out.println("\nNo hay tours cargados para agregar.");
                            System.out.println("Primero verifica que el archivo se cargue correctamente.\n");
                        } else {
                            agregarNuevoTour(gestor, rutaArchivo);
                        }
                    }
                    case 4 -> {
                        continuar = false;
                        System.out.println("\n=== ¡GRACIAS POR USAR LLANQUIHUE TOUR! ===\n");
                    }
                    default -> System.out.println("\nOpción no válida. Intente nuevamente.\n");
                }
            }
        }
        }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("=".repeat(50));
        System.out.println("MENÚ PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Mostrar todos los tours");
        System.out.println("2. Filtrar tours");
        System.out.println("3. Agregar nuevo tour");
        System.out.println("4. Salir");
        System.out.print("\nSeleccione una opción: ");
    }
    
    private static void mostrarMenuFiltros(GestorDatos gestor) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("FILTROS DISPONIBLES");
        System.out.println("=".repeat(50));
        System.out.println("1. Filtrar por tipo (ej: gastronomico, lacustre, aventura)");
        System.out.println("2. Filtrar por precio mínimo");
        System.out.println("3. Filtrar tours disponibles (cupos > 0)");
        System.out.println("4. Volver al menú principal");
        System.out.print("\nSeleccione una opción: ");
        
        int opcion = leerOpcion();
        
        switch(opcion) {
            case 1 -> filtrarPorTipo(gestor);
            case 2 -> filtrarPorPrecio(gestor);
            case 3 -> filtrarDisponibles(gestor);
            case 4 -> System.out.println("\nVolviendo al menú principal...\n");
            default -> System.out.println("\nOpción no válida.\n");
        }
    }
    
    private static void mostrarTodosLosTours(GestorDatos gestor) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LISTA COMPLETA DE TOURS");
        System.out.println("=".repeat(50));
        
        ArrayList<Tour> todosLosTours = gestor.getListaTours();
        
        if (todosLosTours.isEmpty()) {
            System.out.println("No hay tours cargados en el sistema.");
            System.out.println("Verifica que el archivo tours.txt exista y tenga datos válidos.\n");
        } else {
            for (int i = 0; i < todosLosTours.size(); i++) {
                System.out.println((i + 1) + ". " + todosLosTours.get(i));
            }
            System.out.println("\nTotal de tours: " + todosLosTours.size());
        }
        System.out.println();
    }
    
    private static void filtrarPorTipo(GestorDatos gestor) {
        System.out.print("\nIngrese el tipo de tour (gastronomico, lacustre, aventura, cultural, relax): ");
        String tipo = scanner.nextLine().trim();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TOURS DE TIPO: " + tipo.toUpperCase());
        System.out.println("=".repeat(50));
        
        ArrayList<Tour> toursFiltrados = gestor.filtrarPorTipo(tipo);
        
        if (toursFiltrados.isEmpty()) {
            System.out.println("No se encontraron tours de tipo '" + tipo + "'\n");
        } else {
            for (Tour tour : toursFiltrados) {
                System.out.println("• " + tour);
            }
            System.out.println("\nTotal encontrados: " + toursFiltrados.size() + "\n");
        }
    }
    
    private static void filtrarPorPrecio(GestorDatos gestor) {
        double precioMin = 0;
        
        while (true) {
            System.out.print("\nIngrese el precio mínimo: $");
            try {
                precioMin = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine();
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TOURS CON PRECIO >= $" + String.format("%,.0f", precioMin));
        System.out.println("=".repeat(50));
        
        ArrayList<Tour> toursFiltrados = gestor.filtrarPorPrecioMinimo(precioMin);
        
        if (toursFiltrados.isEmpty()) {
            System.out.println("No se encontraron tours con precio >= $" + String.format("%,.0f", precioMin) + "\n");
        } else {
            for (Tour tour : toursFiltrados) {
                System.out.println("• " + tour);
            }
            System.out.println("\nTotal encontrados: " + toursFiltrados.size() + "\n");
        }
    }
    
    private static void filtrarDisponibles(GestorDatos gestor) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TOURS DISPONIBLES (CUPOS > 0)");
        System.out.println("=".repeat(50));
        
        ArrayList<Tour> toursDisponibles = gestor.filtrarToursDisponibles();
        
        if (toursDisponibles.isEmpty()) {
            System.out.println("No hay tours con cupos disponibles.\n");
        } else {
            for (Tour tour : toursDisponibles) {
                System.out.println("• " + tour);
            }
            System.out.println("\nTotal disponibles: " + toursDisponibles.size() + "\n");
        }
    }
    
    private static void agregarNuevoTour(GestorDatos gestor, String rutaArchivo) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("AGREGAR NUEVO TOUR");
        System.out.println("=".repeat(50));
        
        System.out.println("\nIngrese los datos del nuevo tour:");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        
        System.out.print("Tipo (gastronomico, lacustre, aventura, cultural, relax): ");
        String tipo = scanner.nextLine().trim();
        
        int duracionHoras = 0;
        while (true) {
            System.out.print("Duración (horas): ");
            try {
                duracionHoras = scanner.nextInt();
                scanner.nextLine();
                if (duracionHoras > 0) break;
                System.out.println("La duración debe ser mayor a 0.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine();
            }
        }
        
        double precio = 0;
        while (true) {
            System.out.print("Precio: $");
            try {
                precio = scanner.nextDouble();
                scanner.nextLine();
                if (precio > 0) break;
                System.out.println("El precio debe ser mayor a 0.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine();
            }
        }
        
        int cupos = 0;
        while (true) {
            System.out.print("Cupos disponibles: ");
            try {
                cupos = scanner.nextInt();
                scanner.nextLine();
                if (cupos >= 0) break;
                System.out.println("Los cupos no pueden ser negativos.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine();
            }
        }
        
        Tour nuevoTour = new Tour(nombre, tipo, duracionHoras, precio, cupos);
        
        if (gestor.agregarTour(nuevoTour)) {
            System.out.println("\nTour agregado exitosamente en memoria!");
            System.out.println("Nota: El tour no se guardó en el archivo permanente.");
            System.out.println("Para guardarlo permanentemente, debes agregarlo manualmente al archivo tours.txt\n");
        } else {
            System.out.println("\n Error al agregar el tour.\n");
        }
    }
     
    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return opcion;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar buffer
            return -1;
        }
    }
}