package br.com.alcoolesaude.aes;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Evaluation evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView mTextView = (TextView) findViewById(R.id.text);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.125:8080/aes/test.xhtml";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if(response.contains("Alif")) {
                            mTextView.setText("Dados transferidos com sucesso");
                            //System.out.println(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                }){@Override
        protected Map<String, String> getParams()
        {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("test", "Alif");
            params.put("domain", "http://itsalif.info");

            return params;
        }};
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void startEvaluation(View view){
        evaluation = new Evaluation();
        evaluation.setDateCreated(new Date());
        setContentView(R.layout.question_gender);
    }

    public void genderQuestionBack(View view){
        setContentView(R.layout.activity_main);
    }

    public void genderQuestionNext(View view){
        RadioGroup radiogroup =  (RadioGroup) findViewById(R.id.radioGroupGender);
        int selectedId = radiogroup .getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if (radioButton != null) {

            if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.female))) {
                evaluation.setGender("F");
            }else {
                evaluation.setGender("M");
            }
            setContentView(R.layout.question_birth);
        }

    }

    public void birthQuestionBack(View view){
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerBirth);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        evaluation.setBirth(year,month,day);
        setContentView(R.layout.question_gender);
    }

    public void birthQuestionNext(View view){
        setContentView(R.layout.question_drink);
    }

    public void drinkQuestionBack(View view){
        setContentView(R.layout.question_birth);
    }

    public void drinkQuestionNext(View view){
        RadioGroup radiogroup =  (RadioGroup) findViewById(R.id.radioGroupGender);
        int selectedId = radiogroup .getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if (radioButton != null) {

            if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.yes))) {
                evaluation.setDrink(true);
            }else {
                evaluation.setDrink(false);
            }
            setContentView(R.layout.activity_main);
        }
    }
}
