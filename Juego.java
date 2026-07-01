import java.util.Scanner;
public class Juego {

    private Jugador jugador;
    private Tablero tablero;
    private Enemigo[] enemigos;
    private boolean juegoTerminado;
    private Scanner teclado = new Scanner(System.in);

    public void iniciarJuego() {

        tablero = new Tablero(11, 15);
        tablero.generarTablero();
        tablero.agregarMuros();
        tablero.agregarPuntos();
        tablero.agregarPoderes();
        
        jugador = new Jugador("Pac-Man", 1, 2);

        generarEnemigos(3);

        juegoTerminado = false;

        System.out.println("¡Juego Inicializado con Éxito!");
    }

    public void generarEnemigos(int cantidad) {
        enemigos = new Enemigo[cantidad];
        enemigos[0] = new Enemigo("Perseguidor", 3, 5);
        enemigos[1] = new Enemigo("Aleatorio", 5, 7);
        enemigos[2] = new Enemigo("Fantasma", 7, 9);
    }

    public void actualizarTablero() {

        char[][] matrizMapa = tablero.getMatriz();

        char charOriginalJugador = matrizMapa[jugador.getFila()][jugador.getColumna()];

        matrizMapa[jugador.getFila()][jugador.getColumna()] = 'P';

        for (Enemigo e : enemigos) {
            if (e != null) {
                matrizMapa[e.getFila()][e.getColumna()] = 'G';
            }
        }

        System.out.println("\n=================================");
        tablero.mostrarTablero();
        System.out.println("=================================");
        matrizMapa[jugador.getFila()][jugador.getColumna()] = charOriginalJugador;
    }

    public void mostrarEstado() {
        jugador.mostrarEstado();
    }

    public void ejecutarTurno() {
        System.out.print("\nMuévete (W: Arriba, S: Abajo, A: Izquierda, D: Derecha): ");
        String direccion = teclado.nextLine();

        int proximaFila = jugador.getFila();
        int proximaColumna = jugador.getColumna();

        switch (direccion.toUpperCase()) {
            case "W":
                proximaFila--;
                break;
            case "S":
                proximaFila++;
                break;
            case "A":
                proximaColumna--;
                break;
            case "D":
                proximaColumna++;
                break;
        }

        if (tablero.esMovimientoValido(proximaFila, proximaColumna)) {
            jugador.mover(direccion);

            char[][] matriz = tablero.getMatriz();
            if (matriz[jugador.getFila()][jugador.getColumna()] == '.') {

                for (Punto p : tablero.getPuntos()) {
                    if (p != null && p.getFila() == jugador.getFila() && p.getColumna() == jugador.getColumna()) {
                        jugador.recogerPunto(p);
                        matriz[jugador.getFila()][jugador.getColumna()] = ' ';
                        break;
                    }
                }
            }
        } else {
            System.out.println("¡PARED! No puedes atravesar los muros.");
        }
    }

    public void verificarFinJuego() {
        if (!jugador.estaVivo()) {
            juegoTerminado = true;
            System.out.println("\n ¡GAME OVER! Los fantasmas te atraparon. ");
            return;
        }

        boolean quedanPuntos = false;
        char[][] matriz = tablero.getMatriz();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == '.') {
                    quedanPuntos = true;
                    break;
                }
            }
        }

        if (!quedanPuntos) {
            juegoTerminado = true;
            System.out.println("\n Has recolectado todos los puntos del tablero. ");
        }
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}
