package TCP;

import javax.xml.parsers.SAXParser;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
    final static int PORT = 5000;
    final static String ADDRESS ="127.0.0.1";
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Socket sc;
        DataOutputStream flujo_salida;
        DataInputStream flujoEntrada;
        String mensaje="";
        boolean salida = false;

        System.out.println("Se inicia el cliente");
        try {
            sc = new Socket(ADDRESS,PORT);

            //flujos
            flujo_salida=new DataOutputStream(sc.getOutputStream());
            flujoEntrada=new DataInputStream(sc.getInputStream());

            //RECIBE
            mensaje = flujoEntrada.readUTF();//R1
            System.out.println(mensaje);

            //ENVIA
            while (!mensaje.equals("felicidades!") || !mensaje.equals("Se acabaron las oportunidades")) {
                System.out.println("ENVIA PALABRA");
                mensaje = teclado.next();
                flujo_salida.writeUTF(mensaje);//E2
                mensaje=flujoEntrada.readUTF();
                System.out.println(mensaje);
            }


            flujoEntrada.close();
            flujo_salida.close();
            sc.close();














        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
