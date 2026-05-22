import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat {

    public static void main(String[] args) {

        // Parte 3: capturar direccion y puerto del usuario
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la direccion del servidor (ej: localhost o 192.168.1.x): ");
        String host = scanner.nextLine().trim();

        System.out.print("Ingrese el puerto del servidor (ej: 5000): ");
        int puerto = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Conectando con " + host + ":" + puerto + "...");

        // Punto 2: establecer conexion via Socket con el servidor
        try (
            Socket socket          = new Socket(host, puerto);
            BufferedReader entrada  = new BufferedReader(
                                         new InputStreamReader(socket.getInputStream()));
            PrintWriter    salida   = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader teclado  = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conexion establecida. Escribe mensajes ('salir' para terminar).\n");

            // Punto 3: hilo encargado de recibir mensajes del servidor
            ReceptorMensajes receptor = new ReceptorMensajes(entrada);
            Thread hiloReceptor = new Thread(receptor);
            hiloReceptor.setDaemon(true);
            hiloReceptor.start();

            // Punto 4: enviar mensajes ingresados por el usuario al servidor
            String mensajeUsuario;
            while ((mensajeUsuario = teclado.readLine()) != null) {
                salida.println("[Cliente]: " + mensajeUsuario);

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
