import java.util.Random;

public class SistemaVentas {
    private Localidad[] localidades;
    private Random random;
    
    public SistemaVentas() {
        random = new Random();
        localidades = new Localidad[3];
        localidades[0] = new Localidad(1, 100.0);
        localidades[1] = new Localidad(5, 500.0);
        localidades[2] = new Localidad(10, 1000.0);
    }
    
    public String procesarSolicitud(Comprador comprador) {
        String resultado = "Procesando solicitud de: " + comprador.getNombre() + "\n";
        
        int ticket = generarTicketAleatorio();
        resultado += "Ticket generado: " + ticket + "\n";
        
        if (!validarTicket(ticket)) {
            resultado += "El ticket no es valido. No puede comprar boletos.\n";
            return resultado;
        }
        
        resultado += "El ticket es valido!\n";
        
        Localidad localidadSeleccionada = seleccionarLocalidadAleatoria();
        resultado += "Localidad asignada: " + localidadSeleccionada.getNumero() + " (Precio: $" + localidadSeleccionada.getPrecio() + ")\n";
        
        if (!localidadSeleccionada.hayEspacio()) {
            resultado += "No hay espacio en esta localidad.\n";
            return resultado;
        }
        
        if (localidadSeleccionada.getPrecio() > comprador.getPresupuestoMaximo()) {
            resultado += "El precio ($" + localidadSeleccionada.getPrecio() + ") es mayor a su presupuesto ($" + comprador.getPresupuestoMaximo() + ").\n";
            return resultado;
        }
        
        int cantidadAVender = comprador.getCantidadDeseada();
        if (!localidadSeleccionada.puedeVender(cantidadAVender)) {
            cantidadAVender = localidadSeleccionada.getBoletosDisponibles();
            resultado += "Solo hay " + cantidadAVender + " boletos disponibles.\n";
        }
        
        double total = cantidadAVender * localidadSeleccionada.getPrecio();
        localidadSeleccionada.venderBoletos(cantidadAVender);
        
        resultado += "Venta exitosa!\n";
        resultado += "Boletos vendidos: " + cantidadAVender + "\n";
        resultado += "Total pagado: $" + total + "\n";
        
        return resultado;
    }
    
    private int generarTicketAleatorio() {
        return random.nextInt(15000) + 1;
    }
    
    private boolean validarTicket(int ticket) {
        int a = random.nextInt(15000) + 1;
        int b = random.nextInt(15000) + 1;
        
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        
        System.out.println("Rango: [" + min + ", " + max + "]");
        
        return ticket >= min && ticket <= max;
    }
    
    private Localidad seleccionarLocalidadAleatoria() {
        int indice = random.nextInt(3);
        return localidades[indice];
    }
    
    public String getDisponibilidadTotal() {
        String reporte = "DISPONIBILIDAD TOTAL:\n";
        
        for (int i = 0; i < localidades.length; i++) {
            reporte += localidades[i].toString() + "\n";
        }
        
        return reporte;
    }
    
    public String getDisponibilidadIndividual(int numeroLocalidad) {
        for (int i = 0; i < localidades.length; i++) {
            if (localidades[i].getNumero() == numeroLocalidad) {
                return "DISPONIBILIDAD LOCALIDAD " + numeroLocalidad + ":\n" + localidades[i].toString() + "\n";
            }
        }
        return "Localidad no encontrada.\n";
    }
    
    public String getReporteCaja() {
        String reporte = "REPORTE DE CAJA:\n";
        double totalGeneral = 0.0;
        
        for (int i = 0; i < localidades.length; i++) {
            reporte += "Localidad " + localidades[i].getNumero() + ": $" + localidades[i].getIngresosTotales() + "\n";
            totalGeneral += localidades[i].getIngresosTotales();
        }
        
        reporte += "TOTAL: $" + totalGeneral + "\n";
        
        return reporte;
    }
}