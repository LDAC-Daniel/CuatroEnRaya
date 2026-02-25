package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private final Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                casillas[f][c] = new Casilla();
            }
        }
    }

    public boolean estaVacio() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (casillas[f][c].estaOcupada()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estaLleno() {
        for (int c = 0; c < COLUMNAS; c++) {
            if (!columnaLlena(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean columnaLlena(int columna) {
        return casillas[0][columna].estaOcupada();
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
    }

    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new NullPointerException("La ficha no puede ser nula.");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        for (int f = FILAS - 1; f >= 0; f--) {
            if (!casillas[f][columna].estaOcupada()) {
                return f;
            }
        }
        return -1;
    }

    private boolean objetivoAlcanzado(int consecutivas) {
        return consecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int contador = 0;
        for (int c = 0; c < COLUMNAS; c++) {
            if (casillas[fila][c].getFicha() == ficha) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }
        return false;
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int contador = 0;
        for (int f = 0; f < FILAS; f++) {
            if (casillas[f][columna].getFicha() == ficha) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }
        return false;
    }

    private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
        int f = fila;
        int c = columna;
        while (f > 0 && c > 0) {
            f--;
            c--;
        }
        int contador = 0;
        while (f < FILAS && c < COLUMNAS) {
            if (casillas[f][c].getFicha() == ficha) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
            f++;
            c++;
        }
        return false;
    }

    private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
        int f = fila;
        int c = columna;
        while (f > 0 && c < COLUMNAS - 1) {
            f--;
            c++;
        }
        int contador = 0;
        while (f < FILAS && c >= 0) {
            if (casillas[f][c].getFicha() == ficha) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
            f++;
            c--;
        }
        return false;
    }

    private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
        return comprobarHorizontal(fila, ficha)
                || comprobarVertical(columna, ficha)
                || comprobarDiagonalNE(fila, columna, ficha)
                || comprobarDiagonalNO(fila, columna, ficha);
    }

    public boolean introducirFicha(int columna, Ficha ficha) {
        comprobarColumna(columna);
        comprobarFicha(ficha);

        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }

        int fila = getPrimeraFilaVacia(columna);
        casillas[fila][columna].setFicha(ficha);

        return comprobarTirada(fila, columna, ficha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int f = 0; f < FILAS; f++) {
            sb.append("|");
            for (int c = 0; c < COLUMNAS; c++) {
                if (casillas[f][c].estaOcupada()) {
                    sb.append(casillas[f][c].getFicha().toString());
                } else {
                    sb.append(" ");
                }
            }
            sb.append("|\n");
        }
        sb.append(" -------\n");
        return sb.toString();
    }
}