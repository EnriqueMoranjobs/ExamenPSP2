package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    final static int PORT = 5000;

    public static void main(String[] args) {

        ServerSocket server;
        Socket sc = null;
        DataOutputStream flujo_salida;
        DataInputStream flujo_entrada;
        String mensaje = null;
        String solucion = "elisa";
        String arraySolucion;
        boolean salida = false;
        String[] array;

        boolean bandera = false;

        try {
            server = new ServerSocket(PORT);
            System.out.println("aceptando conexeion...");


            while (true) {
                sc = server.accept();
                System.out.println("conectado!");
                //flujos
                flujo_entrada = new DataInputStream(sc.getInputStream());
                flujo_salida = new DataOutputStream(sc.getOutputStream());
                int contador = 0;
                //ENVIO
                //split divide una caden en fragmentos con un caracter en comun
                mensaje = ("Hola cliente! la palabra tiene...") + solucion.length();
                flujo_salida.writeUTF(mensaje);// E1

                //RECIBE
                String msg;
                while (contador < solucion.length() && !mensaje.equalsIgnoreCase(solucion)) {
                    mensaje = flujo_entrada.readUTF();
                    System.out.println("mensaje recibido de cliente" + mensaje);

                    if (mensaje.equalsIgnoreCase(solucion)) {
                        msg = "felicidades!";
                        flujo_salida.writeUTF(msg);
                    } else {
                        contador++;
                        if (contador < solucion.length()) {
                            msg = "Te voy a dar una pista..." + solucion.substring(0, contador);
                            flujo_salida.writeUTF(msg);
                        } else {
                            msg = "Se acabaron las oportunidades";
                            flujo_salida.writeUTF(msg);
                            System.out.println("se desconecta server");
                            //contador=100;
                        }
                    }
                }
                sc.close();
                flujo_entrada.close();
                flujo_salida.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

