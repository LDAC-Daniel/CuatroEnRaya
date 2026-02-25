package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.utilidades.Entrada;

public final class Consola {

    private Consola() {}

    public static String leerNombre() {
        String nombre;
        do {
            System.out.print("Introduce el nombre del jugador: ");
            nombre = Entrada.cadena();
        } while (nombre == null || nombre.isBlank());
        return nombre;
    }

    public static Ficha elegirColorFicha() {
        Ficha ficha = null;
        boolean valido = false;
        do {
            System.out.print("Elige color (AZUL/VERDE): ");
            String cadena = Entrada.cadena();
            try {
                ficha = Ficha.valueOf(cadena.trim().toUpperCase());
                valido = true;
            } catch (Exception e) {
                System.out.println("Color no válido.");
            }
        } while (!valido);
        return ficha;
    }

    public static Jugador leerJugador() {
        String nombre = leerNombre();
        Ficha color = elegirColorFicha();
        return new Jugador(nombre, color);
    }

    public static Jugador leerJugador(Ficha ficha) {
        String nombre = leerNombre();
        return new Jugador(nombre, ficha);
    }

    public static int leerColumna(Jugador jugador) {
        int columna;
        boolean valido = false;
        do {
            System.out.printf("%s, elige columna (0-%d): ", jugador.nombre(), Tablero.COLUMNAS - 1);
            columna = Entrada.entero();
            if (columna >= 0 && columna < Tablero.COLUMNAS) {
                valido = true;
            } else {
                System.out.println("Columna no válida.");
            }
        } while (!valido);
        return columna;
    }
}