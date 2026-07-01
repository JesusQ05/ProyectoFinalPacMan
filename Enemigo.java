public class Enemigo {

    private String tipo;
    private int fila;
    private int columna;
    private int daño;
    private boolean activo;

    public Enemigo(String tipo, int fila, int columna) {
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
        this.daño = 1;
        this.activo = true;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }

    public void mover() {

    }
    public void atacar(Jugador j) {

    }
    public boolean verificarColision(Jugador j) {

        return false;
    }
    public void mostrarEstado() {
        System.out.println("--- ESTADO DEL ENEMIGO ---");
        System.out.println("Tipo: " + tipo);
        System.out.println("Posición: [" + fila + ", " + columna + "]");
        System.out.println("Daño: " + daño);
        System.out.println("Activo: " + (activo ? "SÍ" : "NO"));
    }
}
