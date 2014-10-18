package learn2crack.jsonparsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import learn2crack.jsonparsing.library.JSONParser;

public class MainActivity extends Activity {
  //URL to get JSON Array
  private static String url = "http://api.learn2crack.com/android/json/";
  //private static String url = "http://www.mocky.io/v2/5440667984d353f103f697c0";
  
  //JSON Node Names
  private static final String TAG_TITLE = "title";
  private static final String TAG_IMAGE = "image";
  private static final String TAG_POINTS = "points";
  private static final String TAG_LINK = "link";
  JSONArray arreglo_json;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
      }
      
      //Importing TextView
      final TextView uid = (TextView)findViewById(R.id.uid);
      final TextView name1 = (TextView)findViewById(R.id.name);
      final TextView email1 = (TextView)findViewById(R.id.email);
      
      //Set JSON Data in TextView
      uid.setText(id);
      name1.setText(name);
      email1.setText(email);
      
  } catch (JSONException e) {
    e.printStackTrace();
  }
    }
}