import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteChat {

    private static final String HOST  = "localhost";
    private static final int    PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("Conectando con el servidor " + HOST + ":" + PUERTO + "...");

        // Punto 2: establecer conexion via Socket con el servidor
        try (
            Socket socket          = new Socket(HOST, PUERTO);
            BufferedReader entrada  = new BufferedReader(
                                         new InputStreamReader(socket.getInputStream()));
            PrintWriter    salida   = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader teclado  = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conexion establecida.");
            System.out.println("Escribe un mensaje y presiona Enter. Escribe 'salir' para salir.\n");

            // Punto 3: lanzar un hilo para gestionar la recepcion de mensajes
            ReceptorMensajes receptor = new ReceptorMensajes(entrada);
            Thread hiloReceptor = new Thread(receptor);
            hiloReceptor.setDaemon(true);
            hiloReceptor.start();

            // Hilo principal: enviar mensajes al servidor (punto 4)
            String mensajeUsuario;
            while ((mensajeUsuario = teclado.readLine()) != null) {
                salida.println(mensajeUsuario);

                if (mensajeUsuario.equalsIgnoreCase("salir")) {
                    receptor.detener();
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error de conexion: " + e.getMessage());
        }

        System.out.println("Cliente desconectado.");
    }
}
