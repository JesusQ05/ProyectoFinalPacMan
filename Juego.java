import java.util.Scanner;
public class Juego {

    private Jugador jugador;
    private Tablero tablero;
    private Enemigo[] enemigos;
    private boolean juegoTerminado;
    private Scanner teclado = new Scanner(System.in);

    public void iniciarJuego() {

        tablero = new Tablero(11, 15);
        tablero.generarTablero(); // [cite: 104]
        tablero.agregarMuros();    // [cite: 106]
        tablero.agregarPuntos();   // [cite: 108]
        tablero.agregarPoderes();  // [cite: 110]

        jugador = new Jugador("Pac-Man", 1, 2);

        generarEnemigos(3); // Creamos 3 fantasmas [cite: 172]

        juegoTerminado = false; // [cite: 17]

        System.out.println("¡Juego Inicializado con Éxito!");
    }

    public void generarEnemigos(int cantidad) {
        enemigos = new Enemigo[cantidad]; // [cite: 15, 176]
        enemigos[0] = new Enemigo("Perseguidor", 3, 5); // [cite: 82]
        enemigos[1] = new Enemigo("Aleatorio", 5, 7);   // [cite: 84]
        enemigos[2] = new Enemigo("Fantasma", 7, 9);    // [cite: 86]
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
        tablero.mostrarTablero(); // [cite: 112]
        System.out.println("=================================");
        matrizMapa[jugador.getFila()][jugador.getColumna()] = charOriginalJugador;
    }

    public void mostrarEstado() {
        jugador.mostrarEstado(); // [cite: 57]
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
            System.out.println("\n💀 ¡GAME OVER! Los fantasmas te atraparon. 💀");
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
            System.out.println("\n🏆 ¡FELICIDADES! Has recolectado todos los puntos del tablero. 🏆");
        }
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}
