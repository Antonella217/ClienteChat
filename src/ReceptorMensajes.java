import java.io.BufferedReader;
import java.io.IOException;

// Hilo encargado de recibir mensajes del servidor de forma continua
public class ReceptorMensajes implements Runnable {

    private final BufferedReader entrada;
    private volatile boolean activo = true;

    public ReceptorMensajes(BufferedReader entrada) {
        this.entrada = entrada;
    }

    @Override
    public void run() {
        String mensaje;
        try {
            // Punto 4: recibir mensajes del servidor
            while (activo && (mensaje = entrada.readLine()) != null) {
                System.out.println("[Servidor] " + mensaje);
            }
        } catch (IOException e) {
            if (activo) {
                System.err.println("[Receptor] Conexion perdida: " + e.getMessage());
            }
        }
        System.out.println("[Receptor] Hilo de recepcion finalizado.");
    }

    public void detener() {
        activo = false;
    }
}
