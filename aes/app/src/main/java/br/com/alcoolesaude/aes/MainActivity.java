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
import br.com.alcoolesaude.aes.R;


public class MainActivity extends AppCompatActivity {

    private final String url = "http://alcoolesude.com.br/appRequest.xhtml";
    private final String key = "11VzuKzy5k";
    Evaluation evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void syncData(){
        if(evaluation != null && evaluation.getSyncDate() == null) {

            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            if (response.contains(key)) {
                                evaluation.setSyncDate(new Date());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("dateCreated",String.valueOf(evaluation.getDateCreated().getTime()));
                    params.put("syncDate",String.valueOf(evaluation.getSyncDate().getTime()));
                    params.put("birth", String.valueOf(evaluation.getBirth().getTime()));
                    params.put("gender", evaluation.getGender());
                    params.put("drink", String.valueOf(evaluation.getDrink()));
                    params.put("audit1", String.valueOf(evaluation.getAudit1()));
                    params.put("audit2", String.valueOf(evaluation.getAudit2()));
                    params.put("audit3", String.valueOf(evaluation.getAudit3()));
                    params.put("audit4", String.valueOf(evaluation.getAudit4()));
                    params.put("audit5", String.valueOf(evaluation.getAudit5()));
                    params.put("audit6", String.valueOf(evaluation.getAudit6()));
                    params.put("audit7", String.valueOf(evaluation.getAudit7()));
                    params.put("audit8", String.valueOf(evaluation.getAudit8()));
                    params.put("audit9", String.valueOf(evaluation.getAudit9()));
                    params.put("monday", String.valueOf(evaluation.getMonday()));
                    params.put("tuesday", String.valueOf(evaluation.getTuesday()));
                    params.put("wednesday", String.valueOf(evaluation.getWednesday()));
                    params.put("thursday", String.valueOf(evaluation.getThursday()));
                    params.put("friday", String.valueOf(evaluation.getFriday()));
                    params.put("saturday", String.valueOf(evaluation.getSaturday()));
                    params.put("sunday", String.valueOf(evaluation.getSunday()));
                    params.put("email", evaluation.getEmail());
                    params.put("key", key);
                    return params;
                }
            };
            queue.add(stringRequest);
        }
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

    public void audit1QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit1);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_1))){
                evaluation.setAudit1(1);
            }else{
                evaluation.setAudit1(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_2))){
                evaluation.setAudit1(1);
            }else{
                evaluation.setAudit1(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_3))){
                evaluation.setAudit1(1);
            }else{
                evaluation.setAudit1(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_4))){
                evaluation.setAudit1(1);
            }else{
                evaluation.setAudit1(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_5))){
                evaluation.setAudit1(1);
            }else{
                evaluation.setAudit1(0);
            }


            setContentView(R.layout.activity_main);
        }
    }
}
