package org.ivan;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import de.akquinet.android.androlog.Log;

public class Insertar extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
	
	private String fecha;
	private String hora;
	private String asunto;
	private Socket socket;
	private PrintWriter Salida;
        private BufferedReader Entrada;
	private String mensaje;
	private String orden="INSER";
        private EditText Fecha;
        private EditText Hora;
        private EditText Asunto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializes the logging
        Log.init();

        // Log a message (only on dev platform)
        Log.i(this, "onCreate");

        setContentView(R.layout.insertar);
        
         Fecha = (EditText)findViewById(R.id.FechaI);
         Hora = (EditText)findViewById(R.id.HoraI);
        Asunto = (EditText)findViewById(R.id.Asunto);
        
        
        
        try {
            socket =new Socket("192.168.1.130", 1235);  
             
         } catch (UnknownHostException ex) {
        	 Toast toast1 =Toast.makeText(getApplicationContext(),"Fallo", Toast.LENGTH_SHORT);
             toast1.show();
         } catch (IOException ex) {
             System.out.println("IO exception "+ex);
         }
        try {        
             Entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Error IO "+ex);
        }
        try {
            Salida = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error IO "+ex);
        }
	

	
    }
        
    public void insertar (View v) throws IOException{
    	
    	fecha=Fecha.getText().toString();
        hora=Hora.getText().toString();
        asunto=Asunto.getText().toString();
        
        mensaje=orden+" "+fecha+" "+hora+" "+asunto;
        
        
        try {
            IO.escribeLinea(mensaje,socket.getOutputStream());
            Salida.flush();
        } catch (IOException ex) {
            System.out.println("Error escribeLinea "+ex);
        }
        
   
	//mensaje=orden.concat(fecha);
        

        	// Toast toast1 =Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_SHORT);
            	 //toast1.show();

		
		////////////////////////////////////////
		Entrada.close();
		Salida.close();
		socket.close();
    	
    }
    
}//class
