public class Jugador {

    private String nombre;
    private int fila;
    private int columna;
    private int salud;
    private int puntaje;
    private int velocidad;
    private boolean poderActivo;

    public Jugador(String nombre, int fila, int columna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.salud = 3;
        this.puntaje = 0;
        this.velocidad = 1;
        this.poderActivo = false;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public void setFila(int fila) { this.fila = fila; }
    public void setColumna(int columna) { this.columna = columna; }


    public void mover(String direccion) {
        switch (direccion.toUpperCase()) {
            case "W":
                this.fila--;
                break;
            case "S":
                this.fila++;
                break;
            case "A":
                this.columna--;
                break;
            case "D":
                this.columna++;
                break;
        }
    }
    public void recogerPunto(Punto p) {
        if (p != null && !p.fueRecolectado()) {
            this.puntaje += p.obtenerValor();
            p.consumir();
        }
    }
    public int getPuntaje() { return puntaje; }
    public int getSalud() { return salud; }

    public void recibirDaño(int cantidad) {

    }
    public void usarPoder(Poder p) {

    }
    public boolean estaVivo() {
        return this.salud > 0;
    }

    public void mostrarEstado() {
        System.out.println("--- ESTADO DEL JUGADOR ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Salud: " + salud);
        System.out.println("Puntaje: " + puntaje);
        System.out.println("Poder Activo: " + (poderActivo ? "SÍ" : "NO"));
    }
}
