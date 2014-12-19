package learn2crack.jsonparsing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import learn2crack.jsonparsing.library.JSONParser;

public class MainActivity extends Activity {
  //URL to get JSON Array
  //private static String url = "http://api.learn2crack.com/android/json/";
  private static String url = "http://www.mocky.io/v2/5440667984d353f103f697c0";
  
  private ProgressDialog cargando;
  AtomicBoolean isRunning = new AtomicBoolean(false);
  
  //JSON Node Names
  private static final String TAG_TITLE = "title";
  private static final String TAG_IMAGE = "image";
  private static final String TAG_POINTS = "points";
  private static final String TAG_LINK = "link";
  JSONArray arreglo_json;
  
  	private List<Item> items = new ArrayList<Item>();
	private ListView listView;
	private ListAdapter adapter;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cargando = new ProgressDialog(this);
		cargando.setMessage("Cargando contenido...");
		cargando.show();
        
		Thread background = new Thread(new Runnable() {
			
			public void run() {
				try {
					for (int i=0;i<20 && isRunning.get(); i++) {
						secundario();
					}
				} catch (Throwable t) {
				}
			}
		});
    	
    	isRunning.set(true);
    	background.start();
    }
    
    @Override
	public void onDestroy() {
		super.onDestroy();
		eliminarCargando();
	}
    
    public void onStop() {
    	super.onStop();
    	isRunning.set(false);
    }
    
    protected void secundario() {
    	
    // Creating new JSON Parser
    JSONParser jParser = new JSONParser();
    
    // Getting JSON from URL
    JSONObject json = jParser.getJSONFromUrl(url);
    try {
      // Getting JSON Array
      arreglo_json = json.getJSONArray(json.toString());
      
      // Storing  JSON item in a Variable
      for (int i = 0; i < arreglo_json.length(); i++) {
    	  JSONObject jsonobject = arreglo_json.getJSONObject(i);
    	  String title = jsonobject.getString(TAG_TITLE);
    	  String image = jsonobject.getString(TAG_IMAGE);
    	  String points = jsonobject.getString(TAG_POINTS);
    	  String link = jsonobject.getString(TAG_LINK);
    	  Item item = new Item(title, image, points, link);
    	  items.add(item);
      }
      
      listView = (ListView) findViewById(R.id.listView1);
      adapter = new myListAdapter(MainActivity.this, items);
      listView.setAdapter(adapter);
      
      eliminarCargando();
      
//      lanzar(view, url);
      
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    }
    
    public void lanzar(View view, String url) {
        Intent i = new Intent(this, Mas.class );
        i.putExtra("url", url);
        startActivity(i);
    }
    
    private void eliminarCargando()
    {
		if (cargando != null)
		{
			cargando.dismiss();
			cargando = null;
		}
	}
}