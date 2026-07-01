public class Tablero {

    private int filas;
    private int columnas;
    private char[][] matriz;
    private Muro[] muros;
    private Punto[] puntos;
    private Poder[] poderes;

    private int contadorMuros = 0;
    private int contadorPuntos = 0;
    private int contadorPoderes = 0;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new char[filas][columnas];

        int totalCasilleros = filas * columnas;
        this.muros = new Muro[totalCasilleros];
        this.puntos = new Punto[totalCasilleros];
        this.poderes = new Poder[10];
    }

    public void generarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = ' ';
            }
        }
    }

    public void agregarMuros() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 0 || i == filas - 1 || j == 0 || j == columnas - 1 ||
                        (i == filas / 2 && j > 2 && j < columnas - 3)) {

                    matriz[i][j] = '#';

                    muros[contadorMuros] = new Muro(i, j);
                    contadorMuros++;
                }
            }
        }
    }

    public void agregarPuntos() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] == ' ') {
                    matriz[i][j] = '.';
                    puntos[contadorPuntos] = new Punto(i, j, 10);
                    contadorPuntos++;
                }
            }
        }
    }

    public void agregarPoderes() {
        if (matriz[1][1] == '.') {
            matriz[1][1] = 'O';
            poderes[contadorPoderes] = new Poder(1, 1, "Velocidad", 5);
            contadorPoderes++;
        }

        if (matriz[filas - 2][columnas - 2] == '.') {
            matriz[filas - 2][columnas - 2] = 'O';
            poderes[contadorPoderes] = new Poder(filas - 2, columnas - 2, "Congelar", 4);
            contadorPoderes++;
        }

        if (matriz[1][columnas - 2] == '.') {
            matriz[1][columnas - 2] = 'O';
            poderes[contadorPoderes] = new Poder(1, columnas - 2, "Salud", 1);
            contadorPoderes++;
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean esMovimientoValido(int fila, int columna) {

        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }

        if (matriz[fila][columna] == '#') {
            return false;
        }
        return true;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public Punto[] getPuntos() {
        return puntos;
    }

    public int getContadorPuntos() {
        return contadorPuntos;
    }
}
