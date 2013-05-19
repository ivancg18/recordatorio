package org.ivan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import de.akquinet.android.androlog.Log;

public class HelloAndroidActivity extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializes the logging
        Log.init();

        // Log a message (only on dev platform)
        Log.i(this, "onCreate");

        setContentView(0x7f030004);
                
    }
    
    public void insertar (View v){
    	
    	//Toast toast1 =
          //      Toast.makeText(getApplicationContext(),
            //            "Toast por defecto", Toast.LENGTH_SHORT);
     
    //        toast1.show();
    	
    	Intent intent = new Intent(HelloAndroidActivity.this, Insertar.class);
    	
    	startActivity(intent);
    	
    }
    
	    public void citasdia (View v){
	    	
	    	Intent intent = new Intent(HelloAndroidActivity.this, CitasDia.class);
	    	
	    	startActivity(intent);
	    	
	    }
    
		public void asunto (View v){
		    	
		    	Intent intent = new Intent(HelloAndroidActivity.this, Asunto.class);
		    	
		    	startActivity(intent);
		    	
		    }

		public void borrar (View v){
			
			Intent intent = new Intent(HelloAndroidActivity.this, Borrar.class);
			
			startActivity(intent);
			
		}

}

