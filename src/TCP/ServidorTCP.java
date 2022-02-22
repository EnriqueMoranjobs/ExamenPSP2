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
        int contador = 0;
        boolean bandera = false;

        try {
            server = new ServerSocket(PORT);
            System.out.println("aceptando conexeion...");
            sc = server.accept();
            System.out.println("conectado!");

            //flujos
            flujo_entrada = new DataInputStream(sc.getInputStream());
            flujo_salida = new DataOutputStream(sc.getOutputStream());

            //ENVIO
            //split divide una caden en fragmentos con un caracter en comun
            mensaje = ("Hola cliente! la palabra tiene...") + solucion.length();
            flujo_salida.writeUTF(mensaje);// E1

            //RECIBE
            while (!bandera || contador == solucion.length()) {
                mensaje = flujo_entrada.readUTF();
                System.out.println("mensaje recibido de cliente" + mensaje);

                if (!mensaje.equals(solucion)) {
                    mensaje = "Te voy a dar una pista..." + solucion.substring(0, contador + 1);
                    flujo_salida.writeUTF(mensaje);
                    contador++;

                } if (mensaje.equals(solucion)) {
                    bandera = true;
                    mensaje = "felicidades!";
                    flujo_salida.writeUTF(mensaje);
                } if(contador==solucion.length()) {
                    mensaje = "Se acabaron las oportunidades";
                    flujo_salida.writeUTF(mensaje);
                    bandera=true;
                    System.out.println("se desconecta server");
                }
            }


            flujo_entrada.close();
            flujo_salida.close();
            sc.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
