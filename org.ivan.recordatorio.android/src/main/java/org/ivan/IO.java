package org.ivan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	private static final int LF = 10;
	private static final int CR = 13;
	private static final byte[] SALTO_DE_LINEA = {CR, LF};

	public static int copia (InputStream entrada, OutputStream salida) throws IOException {
		int leido;
		int cont=0;
		leido = entrada.read();
            
		while(leido != -1) {
			salida.write(leido);
			cont++;
			leido = entrada.read();
			System.out.println(cont);
		}
		
		return cont;
	}
	
	public static String leeLinea (InputStream entrada) throws IOException {
		ByteArrayOutputStream acumulador = new ByteArrayOutputStream ();
		int unByte = 0;
		int byteAnterior = 0;

		while((unByte=entrada.read())!= -1) {
			if(unByte == CR){
				byteAnterior = CR;
			} else if (byteAnterior == CR && unByte == LF) {
				break;
			} else if(byteAnterior == CR && unByte != LF) {
				acumulador.write(byteAnterior);
				acumulador.write(unByte);
			} else {
				acumulador.write(unByte);
				byteAnterior = unByte;
			}
		}
		
		return acumulador.toString();
	}
	
	public static void escribeLinea (String linea, OutputStream salida) throws IOException {
		salida.write(linea.getBytes());
		salida.write (SALTO_DE_LINEA);
	}
}
