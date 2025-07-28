// Luis Eduardo Gutierrez Oliva
// POO Sec. 10
// Ejercicio 1, Sistema Eras Tour

import java.util.Scanner;

public class DriverProgram {
    private SistemaVentas sistemaVentas;
    private Comprador compradorActual;
    private Scanner scanner;
    
    public DriverProgram() {
        sistemaVentas = new SistemaVentas();
        scanner = new Scanner(System.in);
        compradorActual = null;
    }
    
    public static void main(String[] args) {
        DriverProgram programa = new DriverProgram();
        programa.ejecutar();
    }
    
    private void ejecutar() {
        System.out.println("Bienvenido al sistema de ventas de The Eras Tour");
        
        int opcion = 0;
        while (opcion != 6) {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            if (opcion == 1) {
                crearNuevoComprador();
            } else if (opcion == 2) {
                procesarNuevaSolicitud();
            } else if (opcion == 3) {
                mostrarDisponibilidadTotal();
            } else if (opcion == 4) {
                mostrarDisponibilidadIndividual();
            } else if (opcion == 5) {
                mostrarReporteCaja();
            } else if (opcion == 6) {
                System.out.println("Gracias por usar el sistema.");
            } else {
                System.out.println("Opcion no valida.");
            }
            
            if (opcion != 6) {
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private void mostrarMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Nuevo comprador");
        System.out.println("2. Nueva solicitud de boletos");
        System.out.println("3. Consultar disponibilidad total");
        System.out.println("4. Consultar disponibilidad individual");
        System.out.println("5. Reporte de caja");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    private void crearNuevoComprador() {
        System.out.println("\nCrear nuevo comprador:");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Cantidad de boletos: ");
        int cantidad = scanner.nextInt();
        
        System.out.print("Presupuesto maximo: ");
        double presupuesto = scanner.nextDouble();
        scanner.nextLine();
        
        compradorActual = new Comprador(nombre, email, cantidad, presupuesto);
        
        System.out.println("Comprador creado:");
        System.out.println(compradorActual.toString());
    }
    
    private void procesarNuevaSolicitud() {
        if (compradorActual == null) {
            System.out.println("Debe crear un comprador primero.");
            return;
        }
        
        String resultado = sistemaVentas.procesarSolicitud(compradorActual);
        System.out.println(resultado);
    }
    
    private void mostrarDisponibilidadTotal() {
        String disponibilidad = sistemaVentas.getDisponibilidadTotal();
        System.out.println(disponibilidad);
    }
    
    private void mostrarDisponibilidadIndividual() {
        System.out.print("Ingrese numero de localidad (1, 5, o 10): ");
        int numero = scanner.nextInt();
        
        String disponibilidad = sistemaVentas.getDisponibilidadIndividual(numero);
        System.out.println(disponibilidad);
    }
    
    private void mostrarReporteCaja() {
        String reporte = sistemaVentas.getReporteCaja();
        System.out.println(reporte);
    }
}
