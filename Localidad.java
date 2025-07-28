public class Localidad {
    private int numero;
    private double precio;
    private int capacidadMaxima;
    private int boletosVendidos;
    private double ingresosTotales;
    
    public Localidad(int numero, double precio) {
        this.numero = numero;
        this.precio = precio;
        this.capacidadMaxima = 20;
        this.boletosVendidos = 0;
        this.ingresosTotales = 0.0;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public int getBoletosDisponibles() {
        return capacidadMaxima - boletosVendidos;
    }
    
    public int getBoletosVendidos() {
        return boletosVendidos;
    }
    
    public double getIngresosTotales() {
        return ingresosTotales;
    }
    
    public boolean hayEspacio() {
        return boletosVendidos < capacidadMaxima;
    }
    
    public boolean puedeVender(int cantidad) {
        return getBoletosDisponibles() >= cantidad;
    }
    
    public void venderBoletos(int cantidad) {
        boletosVendidos += cantidad;
        ingresosTotales += cantidad * precio;
    }
    
    public String toString() {
        return "Localidad " + numero + " - Precio: $" + precio + " - Vendidos: " + boletosVendidos + " - Disponibles: " + getBoletosDisponibles();
    }
}