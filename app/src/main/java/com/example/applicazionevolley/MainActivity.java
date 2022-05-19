package com.example.applicazionevolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    private TextView prima_text;
    private TextView seconda_text;
    private TextView terza_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prima_text = findViewById(R.id.prima_text);
        seconda_text = findViewById(R.id.seconda_text);
        terza_text = findViewById(R.id.terza_text);


        getData(); //invocare il metodo getData()
    }

    private void getData() //creazione metodo getData() per poi poterlo invocare. Tale metodo recupererà i dati dall'API.
    {
        String myUrl = "https://postman-echo.com/get?=";
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
        response ->
        {
            try{
                //Create a JSON object containing information from the API.
                JSONObject myJsonObject = new JSONObject(response);
                prima_text.setText(myJsonObject.getString("cases"));
                seconda_text.setText(myJsonObject.getString("recovered"));
                terza_text.setText(myJsonObject.getString("deaths"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        },
        volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(myRequest);
}