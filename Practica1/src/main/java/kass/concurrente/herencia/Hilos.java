package kass.concurrente.herencia;

import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase, debes crear un contador extendiendo de la clase Thread
 * @author Kassandra Mirael
 * @version 1.1
 */
public class Hilos extends Thread {
    private static final int RONDAS = 200;
    public static final Integer HILOS = 3;
    private int valor;

    /** Metodo constructor. */
       public Hilos() {
        this.valor = 0;
    }

    /**
     * Metodo que obtiene el valor.
     * @return El valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * Metodo que asigna un nuevo valor.
     * @param valor El nuevo valor
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public void run() {
        //System.out.println("DENTRO RUN");
        suma();
    }

    public void suma(){
        synchronized(this){
            for(int i = 0; i < RONDAS; ++i){
                valor = valor + 1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //Genera una estructura de datos (en donde después guardarás los hilos)
        List<Thread> hilos = new ArrayList<Thread>();
        // Crea 10 hilos y los guarda en la estructura de datos
        for (int i = 0; i < HILOS; i++) {
            Hilos h = new Hilos();
            h.start();
            hilos.add(h);
        }
        //Itera sobre la estructura de datos y espera a que cada hilo termine
        for (Thread t : hilos) {
            t.join();
        }
        
        // Itera sobre la estructura de datos y muestra el valor de cada hilo
       for (Thread t : hilos) {
            System.out.println("EL VALOR ES: " + ((Hilos)t).getValor());
        }
    }
}
