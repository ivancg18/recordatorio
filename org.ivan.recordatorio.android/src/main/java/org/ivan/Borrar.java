package org.ivan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import de.akquinet.android.androlog.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Borrar extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
	
    private String fecha;
    private String hora;
    private String orden="DEL";
    private EditText Fecha;
    private  EditText Hora;
    private PrintWriter Salida;
    private BufferedReader Entrada;
    private Socket socket;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializes the logging
        Log.init();

        // Log a message (only on dev platform)
        Log.i(this, "onCreate");

        setContentView(R.layout.borrar);
             
         Fecha = (EditText)findViewById(R.id.Fecha);
         Hora = (EditText)findViewById(R.id.Hora);
       
        
        ///////////////////////////////////////
        //ABRIMOS SOCKETS Y FLUJOS//////////
        ///////////////////////////////////
        
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
        //////////////////////////////////
        //////FIN DE ABERTURAS/////////
        //////////////////////////////
      
    }
    
    public void borrar (View v){
    	///////////////////////////////////////////
        ////CAPTURAMOS LOS DATOS DE LOS EDITTEXTS//
        //////////////////////////////////////////
    	fecha=Fecha.getText().toString();
        hora=Hora.getText().toString();
        
        /////////LOS JUNTAMOS//////
       String mensaje=orden+" "+fecha+" "+hora;
        
       //////////LOS ENVIAMOS///////
       
        //Toast toast1 =Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_SHORT);
        //toast1.show();
            
       try {
            IO.escribeLinea(mensaje,socket.getOutputStream());
            Salida.flush();
        } catch (IOException ex) {
            System.out.println("Error escribeLinea "+ex);
        }
       
       
    }
    
}//class
