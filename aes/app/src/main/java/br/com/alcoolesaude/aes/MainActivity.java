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

/*import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
<<<<<<< HEAD
import com.android.volley.toolbox.Volley;
*/
=======
import com.android.volley.toolbox.Volley;*/

>>>>>>> 94f5cb97483966d4e468c45126cf917134465743
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
        setContentView(R.layout.feedback_pregnancyyes);


    }

    /*public void syncData(){
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
<<<<<<< HEAD
    }
*/
=======
    }*/

>>>>>>> 94f5cb97483966d4e468c45126cf917134465743
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

    public void audit2QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit2);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_1))){
                evaluation.setAudit2(1);
            }else{
                evaluation.setAudit2(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_2))){
                evaluation.setAudit2(1);
            }else{
                evaluation.setAudit2(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_3))){
                evaluation.setAudit2(1);
            }else{
                evaluation.setAudit2(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_4))){
                evaluation.setAudit2(1);
            }else{
                evaluation.setAudit2(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_5))){
                evaluation.setAudit2(1);
            }else{
                evaluation.setAudit2(0);
            }


            setContentView(R.layout.activity_main);
        }
    }

    public void audit3QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit3);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_1))){
                evaluation.setAudit3(1);
            }else{
                evaluation.setAudit3(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_2))){
                evaluation.setAudit3(1);
            }else{
                evaluation.setAudit3(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_3))){
                evaluation.setAudit3(1);
            }else{
                evaluation.setAudit3(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_4))){
                evaluation.setAudit3(1);
            }else{
                evaluation.setAudit3(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_5))){
                evaluation.setAudit3(1);
            }else{
                evaluation.setAudit3(0);
            }


            setContentView(R.layout.activity_main);
        }
    }


    public void audit4QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit4);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never))){
                evaluation.setAudit4(1);
            }else{
                evaluation.setAudit4(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once))){
                evaluation.setAudit4(1);
            }else{
                evaluation.setAudit4(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly))){
                evaluation.setAudit4(1);
            }else{
                evaluation.setAudit4(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly))){
                evaluation.setAudit4(1);
            }else{
                evaluation.setAudit4(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day))){
                evaluation.setAudit4(1);
            }else{
                evaluation.setAudit4(0);
            }
        }
    }

    public void audit5QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit5);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never))){
                evaluation.setAudit5(1);
            }else{
                evaluation.setAudit5(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once))){
                evaluation.setAudit5(1);
            }else{
                evaluation.setAudit5(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly))){
                evaluation.setAudit5(1);
            }else{
                evaluation.setAudit5(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly))){
                evaluation.setAudit5(1);
            }else{
                evaluation.setAudit5(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day))){
                evaluation.setAudit5(1);
            }else{
                evaluation.setAudit5(0);
            }
        }
    }

    public void audit6QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit6);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never))){
                evaluation.setAudit6(1);
            }else{
                evaluation.setAudit6(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once))){
                evaluation.setAudit6(1);
            }else{
                evaluation.setAudit6(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly))){
                evaluation.setAudit6(1);
            }else{
                evaluation.setAudit6(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly))){
                evaluation.setAudit6(1);
            }else{
                evaluation.setAudit6(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day))){
                evaluation.setAudit6(1);
            }else{
                evaluation.setAudit6(0);
            }
        }
    }

    public void audit7QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit7);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never))){
                evaluation.setAudit7(1);
            }else{
                evaluation.setAudit7(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once))){
                evaluation.setAudit7(1);
            }else{
                evaluation.setAudit7(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly))){
                evaluation.setAudit7(1);
            }else{
                evaluation.setAudit7(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly))){
                evaluation.setAudit7(1);
            }else{
                evaluation.setAudit7(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day))){
                evaluation.setAudit7(1);
            }else{
                evaluation.setAudit7(0);
            }
        };
    }

    public void audit8QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit8);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never))){
                evaluation.setAudit8(1);
            }else{
                evaluation.setAudit8(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once))){
                evaluation.setAudit8(1);
            }else{
                evaluation.setAudit8(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly))){
                evaluation.setAudit8(1);
            }else{
                evaluation.setAudit8(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly))){
                evaluation.setAudit8(1);
            }else{
                evaluation.setAudit8(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day))){
                evaluation.setAudit8(1);
            }else{
                evaluation.setAudit8(0);
            }
        }
    }

    public void audit9QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit9);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.no))){
                evaluation.setAudit9(1);
            }else{
                evaluation.setAudit9(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearNo))){
                evaluation.setAudit9(1);
            }else{
                evaluation.setAudit9(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearYes))){
                evaluation.setAudit9(1);
            }else{
                evaluation.setAudit9(0);
            }
        }
    }

    public void audit10QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit10);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.no))){
                evaluation.setAudit10(1);
            }else{
                evaluation.setAudit10(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearNo))){
                evaluation.setAudit10(1);
            }else{
                evaluation.setAudit10(0);
            }
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearYes))){
                evaluation.setAudit10(1);
            }else{
                evaluation.setAudit10(0);
            }
        }
    }

<<<<<<< HEAD
    public void abstainerLimits(View view){
        setContentView(R.layout.feedback_abstainers_limits);
    }

    public void backBack(View view){
        setContentView(R.layout.activity_main);
    }

    public void abstainerProblems(View view){
        setContentView(R.layout.feedback_abstainers_problems);
    }

    public void pregnancynoRecommended(View view){
        setContentView(R.layout.feedback_pregnancyno_recomendacoes);
    }

    public void pregnancynoDoubt(View view){
        setContentView(R.layout.feedback_pregnancyno_duvidas1);
    }

    public void pregnancynoRecommendednext(View view){
        setContentView(R.layout.feedback_pregnancyno_recomendacoes2);
    }

    public void pregnancynoRecommendednextnext(View view){
        setContentView(R.layout.feedback_pregnancyno_recomendacoes3);
    }

    public void pregnancynoBack(View view){
        setContentView(R.layout.feedback_pregnancyno);
    }

    public void pregnancynoDoubtc(View view){
        setContentView(R.layout.feedback_pregnancyno_continuarsembeber);
    }

    public void pregnancynoExperience(View view){
        setContentView(R.layout.feedback_pregnancyno_experimentar);
    }

    public void pregnancynoExperience2(View view){
        setContentView(R.layout.feedback_pregnancyno_experimentar2);
    }

    public void pregnancynoExperience3(View view){
        setContentView(R.layout.feedback_pregnancyno_experimentar3);
    }

    public void pregnancyyesRecommended(View view){
        setContentView(R.layout.feedback_pregnancyyes_recomendacoes);
    }
    public void pregnancyyesDoubt(View view){
        setContentView(R.layout.feedback_pregnancyyes_duvidas1);
    }
    public void pregnancyyesBack(View view){
        setContentView(R.layout.feedback_pregnancyyes);
    }
    public void pregnancyyesRecommendedNext(View view){
        setContentView(R.layout.feedback_pregnancyyes_duvidas1);
    }

    public void pregnancyyesConsumo(View view){
        setContentView(R.layout.feedback_pregnancyyes_consumo);
    }

    public void pregnancyyesBebida(View view){
        setContentView(R.layout.feedback_pregnancyyes_tipobebida);
    }

    public void pregnancyyesSegunda(View view){
        setContentView(R.layout.feedback_pregnancyyes_segundagestacao);
    }

    public void pregnancyyesRecommendedNextnext(View view){
        setContentView(R.layout.feedback_pregnancyyes_duvidas2);
    }

    public void pregnancyyesProblems(View view){
        setContentView(R.layout.feedback_pregnancyyes_problemasbebe);
    }

    public void pregnancyyesWhatdo(View view){
        setContentView(R.layout.feedback_pregnancyyes_oquefazer1);
    }

    public void pregnancyyesWhatdo2(View view){
        setContentView(R.layout.feedback_pregnancyyes_oquefazer2);
    }
    public void pregnancyyesStop(View view){
        setContentView(R.layout.feedback_pregnancyyes_comoparar1);
    }

    public void pregnancyyesStop2(View view){
        setContentView(R.layout.feedback_pregnancyyes_comoparar2);
    }
=======
    public void mainBack(View view){
        setContentView(R.layout.activity_main);
    }

    public void riscoProblemasBack(View view){
        setContentView(R.layout.sintomas_alcool_risco_problemas);
    }

    public void riscoProblemasAcidentes(View view){
        setContentView(R.layout.sintomas_alcool_risco_problemas_acidente);
    }

    public void riscoProblemasSaude(View view){
        setContentView(R.layout.sintomas_alcool_risco_problemas_saude);
    }

    public void riscoProblemasFeto(View view){
        setContentView(R.layout.sintomas_alcool_risco_problemas_feto);
    }

    public void riscoProblemasDependencia(View view){
        setContentView(R.layout.sintomas_alcool_risco_problemas_dependencia_danos_associados);
    }

>>>>>>> 94f5cb97483966d4e468c45126cf917134465743
}

