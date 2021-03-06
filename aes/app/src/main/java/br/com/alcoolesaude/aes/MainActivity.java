package br.com.alcoolesaude.aes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

//imagem raiza
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBar;

//imagem raiza fim

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
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


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.alcoolesaude.aes.R;


public class MainActivity extends AppCompatActivity {

    private final String url = "http://alcoolesude.com.br/appRequest.xhtml";
    private final String key = "11VzuKzy5k";
    ActionBar bar;
    Evaluation evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            /*botão home
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.logo));
        botão home fim*/
    }

    /*botão home
    public boolean onOptionsItemSelected (int panel, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "logo botão", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
            return (true);

    }

    botão home fim*/

        //imagem raiza

    public void beer(View view){
        setContentView(R.layout.audit3_2_beer);
    }
    public void wine(View view){
        setContentView(R.layout.audit3_2_wine);
    }
    public void whiskey(View view){
        setContentView(R.layout.audit3_2_whiskey);
    }
    public void vodka(View view){
        setContentView(R.layout.audit3_2_vodka);
    }
    public void bottle(View view){
        setContentView(R.layout.audit3_2_bottle);
    }
    //imagem raiza fim

    @Override
    public void setContentView(int layoutId) {
        super.setContentView(layoutId);
        bar = getSupportActionBar();
        bar.setTitle(getResources().getResourceEntryName(layoutId));
        //bar.setCustomView();
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
                setContentView(R.layout.question_pregnant);

            }else {
                evaluation.setGender("M");
                setContentView(R.layout.question_birth);
            }
        }
    }

    public void pregnancyQuestionBack(View view){
        setContentView(R.layout.question_gender);
    }

    public void pregnancyQuestionNext(View view){
        RadioGroup radiogroup =  (RadioGroup) findViewById(R.id.radioGroupPregnancy);
        int selectedId = radiogroup .getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.yes))) {
            evaluation.setPregnant(true);
        }else {
            evaluation.setPregnant(false);
        }
        setContentView(R.layout.question_birth);
    }

    public void birthQuestionBack(View view){
        if(evaluation.isFemale()){
            setContentView(R.layout.question_pregnant);
        }
        else{
            setContentView(R.layout.question_gender);
        }
    }

    public void birthQuestionNext(View view){
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerBirth);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        evaluation.setBirth(year,month,day);
        setContentView(R.layout.question_drink);
    }

    public void drinkQuestionBack(View view){
        setContentView(R.layout.question_birth);
    }

    public void questionBackAudit10(View view){
        setContentView(R.layout.audit7_10);
    }

    public void questionBackAudit3(View view){
        setContentView(R.layout.audit3_3);
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

        }
        if ((!evaluation.isUnderage() && evaluation.isFemale() && evaluation.getPregnant()) && !evaluation.getDrink()) {
            setContentView(R.layout.feedback_pregnancyyes_drinkingno);
        } else if ((evaluation.isUnderage() && evaluation.isFemale() && evaluation.getPregnant()) && !evaluation.getDrink()) {
            setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes);
        } else if ((!evaluation.isUnderage() && evaluation.isFemale() && evaluation.getPregnant()) && evaluation.getDrink()) {
            setContentView(R.layout.feedback_pregnancyyes);
        } else if ((evaluation.isUnderage() && evaluation.isFemale() && evaluation.getPregnant()) && evaluation.getDrink()) {
            setContentView(R.layout.feedback_underage_pregnancyyes);
        } else if (evaluation.isUnderage() && !evaluation.getDrink()) {
            setContentView(R.layout.feedback_pregnancyno);
        } else if (evaluation.isUnderage() && evaluation.getDrink()) {
            setContentView(R.layout.feedback_underage_drinking_yes);
        } else if (!evaluation.getDrink()) {
            setContentView(R.layout.feedback_abstainer);


        }else{
            setContentView(R.layout.audit3_1);

        }
    }

    public void audit1QuestionNext(View view) {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit1);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if (radioButton != null) {
            if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_1)))
                evaluation.setAudit1(1);
            if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_2)))
                evaluation.setAudit1(2);
            if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_3)))
                evaluation.setAudit1(3);
            if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_4)))
                evaluation.setAudit1(4);
            if (String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_3_selectitem_5)))
                evaluation.setAudit1(5);
            setContentView(R.layout.audit3_2);
        }
    }

    public void audit2QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit2);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_1)))
                evaluation.setAudit2(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_2)))
                evaluation.setAudit2(2);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_3)))
                evaluation.setAudit2(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_4)))
                evaluation.setAudit2(4);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_2_5)))
                evaluation.setAudit2(5);


            setContentView(R.layout.audit3_3);
        }
    }

    public void audit3QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit3);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_1)))
                evaluation.setAudit3(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_2)))
                evaluation.setAudit3(2);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_3)))
                evaluation.setAudit3(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_4)))
                evaluation.setAudit3(4);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit3_3_5)))
                evaluation.setAudit3(5);
        }
        this.audit3();
    }

    public void audit3(){
        if(evaluation.audit3LimitExceeded() || evaluation.dayLimitExceeded() || evaluation.weekLimitExceeded()){
            setContentView(R.layout.audit7_4);
        }else if(evaluation.isMale() && evaluation.getAge() <= 65){
            setContentView(R.layout.feedback_recomendar_limites_homens_65anos);
        }else if(evaluation.isMale() && evaluation.getAge() > 65 || evaluation.isFemale()){
            setContentView(R.layout.feedback_recomendar_limites_homens_mulheres_mais65anos);
        }
    }

    public void audit4QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit4);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never)))
                evaluation.setAudit4(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once)))
                evaluation.setAudit4(2);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly)))
                evaluation.setAudit4(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly)))
                evaluation.setAudit4(4);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day)))
                evaluation.setAudit4(5);
            setContentView(R.layout.audit7_5);
        }
    }

    public void audit5QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit5);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never)))
                evaluation.setAudit5(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once)))
                evaluation.setAudit5(2);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly)))
                evaluation.setAudit5(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly)))
                evaluation.setAudit5(4);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day)))
                evaluation.setAudit5(5);
            setContentView(R.layout.audit7_6);
        }
    }

    public void audit6QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit6);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never)))
                evaluation.setAudit6(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once)))
                evaluation.setAudit6(2);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly)))
                evaluation.setAudit6(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly)))
                evaluation.setAudit6(4);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day)))
                evaluation.setAudit6(5);
            setContentView(R.layout.audit7_7);
        }
    }

    public void audit7QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit7);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never)))
                evaluation.setAudit7(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once)))
                evaluation.setAudit7(2);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly)))
                evaluation.setAudit7(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly)))
                evaluation.setAudit7(4);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day)))
                evaluation.setAudit7(5);
            setContentView(R.layout.audit7_8);
        }
    }

    public void audit8QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit8);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_never)))
                evaluation.setAudit8(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_once)))
                evaluation.setAudit8(2);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_monthly)))
                evaluation.setAudit8(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_weekly)))
                evaluation.setAudit8(4);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_every_day)))
                evaluation.setAudit8(5);
            setContentView(R.layout.audit7_9);
        }
    }

    public void audit9QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit9);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.no)))
                evaluation.setAudit9(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearNo)))
                evaluation.setAudit9(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearYes)))
                evaluation.setAudit9(5);
            setContentView(R.layout.audit7_10);
        }
    }

    public void audit10QuestionNext(View view){
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroupAudit10);
        int selectedId = radiogroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton != null){
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.no)))
                evaluation.setAudit10(1);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearNo)))
                evaluation.setAudit10(3);
            if(String.valueOf(radioButton.getText()).equals(getResources().getString(R.string.audit_yes_yearYes)))
                evaluation.setAudit10(5);
        }
        this.audit7();
    }

    public void audit7(){
        if(evaluation.getAuditFullSum() <= 17){
            if(evaluation.dayLimitExceeded() || evaluation.weekLimitExceeded()){
                setContentView(R.layout.sintomas_alcool_baixorisco_sim_uso);
            }else if(evaluation.isMale() && evaluation.getAge() <= 65){
                setContentView(R.layout.feedback_recomendar_limites_homens_65anos);
            }else if(evaluation.isMale() && evaluation.getAge() > 65 || evaluation.isFemale()){
                setContentView(R.layout.feedback_recomendar_limites_homens_mulheres_mais65anos);
            }
        }else if(evaluation.getAuditFullSum() >= 18 && evaluation.getAuditFullSum() <=25){
            setContentView(R.layout.sintomas_alcool_risco_uso_risco);
        }else if(evaluation.getAuditFullSum() >= 26 && evaluation.getAuditFullSum() <=29){
            setContentView(R.layout.sintomas_alcool_nocivo_sim_uso);
        }else if(evaluation.getAuditFullSum() >= 30 && evaluation.getAuditFullSum() <=50){
            setContentView(R.layout.sintomas_alcool_dependencia_uso_dependencia);
        }
    }

    public void audit1QuestionBack(View view){ setContentView(R.layout.question_drink);}

    public void audit2QuestionBack(View view){
        setContentView(R.layout.audit3_1);
    }

    public void audit3QuestionBack(View view){
        setContentView(R.layout.audit3_2);
    }

    public void audit4QuestionBack(View view){
        setContentView(R.layout.audit3_3);
    }

    public void audit5QuestionBack(View view){
        setContentView(R.layout.audit7_4);
    }

    public void audit6QuestionBack(View view){
        setContentView(R.layout.audit7_5);
    }

    public void audit7QuestionBack(View view){
        setContentView(R.layout.audit7_6);
    }

    public void audit8QuestionBack(View view){
        setContentView(R.layout.audit7_7);
    }

    public void audit9QuestionBack(View view){
        setContentView(R.layout.audit7_8);
    }

    public void audit10QuestionBack(View view){
        setContentView(R.layout.audit7_9);
    }

    public void abstainerLimits(View view){
        setContentView(R.layout.feedback_abstainers_limits);
    }

    public void abstainerFeedback(View view){
        setContentView(R.layout.feedback_abstainer);
    }

    public void backBack(View view){
        setContentView(R.layout.activity_main);
    }

    public void abstainerProblems(View view){
        setContentView(R.layout.feedback_abstainers_problems);
    }

    public void abstainerInfo(View view){
        setContentView(R.layout.feedback_moreinfo);
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

    public void pregnancynoInfo(View view){
        setContentView(R.layout.feedback_pregnancyno_info);
    }

    public void pregnancyno(View view){
        setContentView(R.layout.feedback_pregnancyno);
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

    public void pregnancyyesdrinkingnodoubt (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_duvidas1);
    }

    public void pregnancyyesdrinkingnoConsumo (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_consumo);
    }

    public void pregnancyyesdrinkingnoBebida (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_tipobebida);
    }

    public void pregnancyyesdrinkingnoSegunda (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_segundagestacao);
    }
    public void pregnancyyesdrinkingnoBack (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno);
    }

    public void pregnancyyesdrinkingnodoubt2 (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_duvidas2);
    }

    public void pregnancyyesdrinkingnoWhat2 (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_oquefazer2);
    }

    public void pregnancyyesdrinkingnoStop2 (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_comoparar2);
    }

    public void pregnancyyesdrinkingnoProblems (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_problemasbebe);
    }

    public void pregnancyyesdrinkingnoWhat (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_oquefazer1);
    }

    public void pregnancyyesdrinkingnoStop (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_comoparar1);
    }

    public void pregnancyyesdrinkingnoInfo (View view){
        setContentView(R.layout.feedback_pregnancyyes_drinkingno_info);
    }


    public void homens65recomendacoes (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_65anos_recomandacoes);
    }

    public void homens65recomendacoesespeciais (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_65anos_recomandacoes_especiais);
    }

    public void homens65maisinfo (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_65anos_maisinfo);
    }

    public void homens65 (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_65anos);
    }

    public void homensmulheres65recomendacoes (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_mulheres_mais65anos_recomandacoes);
    }

    public void homensmulheres65recomendacoesespeciais (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_mulheres_mais65anos_recomandacoes_especiais);
    }
    public void homensmulheres65maisinfo (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_mulheres_mais65anos_maisinfo);
    }
    public void homensmulheres65 (View view){
        setContentView(R.layout.feedback_recomendar_limites_homens_mulheres_mais65anos);
    }

    public void uderagedrinkingyes (View view){
        setContentView(R.layout.feedback_underage_drinking_yes);
    }

    public void underagedrinkingyesduvidas (View view){
        setContentView(R.layout.feedback_underage_drinking_yes_duvidas1);
    }

    public void underagedrinkingyesinfo (View view){
        setContentView(R.layout.feedback_underage_drinking_yes_info);
    }

    public void underagedrinkingyesnaobeber (View view){
        setContentView(R.layout.feedback_underage_drinking_yes_naobeber);
    }

    public void underagedrinkingyeswhat (View view){
        setContentView(R.layout.feedback_underage_drinking_yes_oquefazer);
    }

    public void underagedrinkingyeswhat2 (View view){
        setContentView(R.layout.feedback_underage_drinking_yes_oquefazer2);
    }

    public void underagedrinkingyesrecomendacoes (View view){
        setContentView(R.layout.feedback_underage_drinking_yes_recomendacoes);
    }

    public void underagedrinkingnorecomendacoes (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_recomendacoes);
    }


    public void underagedrinkingnoduvidas (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_duvidas1);
    }

    public void underagedrinkingnoinfo (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_info);
    }

    public void underagedrinkingno (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes);
    }

    public void underagedrinkingnorecomendacoes2 (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_recomendacoes2);
    }

    public void underagedrinkingnorecomendacoes3 (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_recomendacoes3);
    }

    public void underagedrinkingnocontinuar (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_continuarsembeber);
    }

    public void underagedrinkingnoexperimentar (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_experimentar);
    }

    public void underagedrinkingnoexperimentar2 (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_experimentar2);
    }

    public void underagedrinkingnoexperimentar3 (View view){
        setContentView(R.layout.feedback_underage_drinkingno_pregnancyyes_experimentar3);
    }

    public void underagepregnancyyes (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes);
    }

    public void underagepregnancyyesrecomendacoes (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_recomendacoes);
    }

    public void underagepregnancyyesrecomendacoes2 (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_recomendacoes2);
    }


    public void underagepregnancyyesduvidas (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_duvidas1);
    }

    public void underagepregnancyyesduvidas2 (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_duvidas2);
    }

    public void underagepregnancyyesinfo (View view){
        setContentView(R.layout.feedback_underage_pregnantyyes_info);
    }

    public void underagepregnancyyesconsumo (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_consumo);
    }

    public void underagepregnancyyestipo (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_tipobebida);
    }

    public void underagepregnancyyessegunda (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_segundagestacao);
    }

    public void underagepregnancyyesproblemas (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_problemasbebe);
    }

    public void underagepregnancyyesoque (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_oquefazer1);
    }

    public void underagepregnancyyesoque2 (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_oquefazer2);
    }

    public void underagepregnancyyescomo (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_comoparar1);
    }

    public void underagepregnancyyescomo2 (View view){
        setContentView(R.layout.feedback_underage_pregnancyyes_comoparar2);
    }

    public void SimUsoQuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_sim_uso2);
    }

    public void SimUso2QuestioNext(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_sim_uso3);
    }

    public void SimUso3QuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas);
    }

    public void AcidenteQuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas_acidente);
    }

    public void ProblemsQuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas_saude);
    }

    public void fetusQuestionNext(){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas_feto);
    }

    public void DependencyQuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas_dependencia_danos_associados);
    }

    public void DependencyQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas);
    }

    public void fetusQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas);
    }

    public void ProblemsQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas);
    }

    public void AcidenteQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_problemas);
    }

    public void ProblemsAlcoolQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_sim_uso3);
    }

    public void SimUso3QuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_sim_uso2);
    }

    public void SimUso2QuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_baixorisco_sim_uso);
    }

    public void dependencyQuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_uso_dependencia_continuacao);
    }

    public void dependencyContQuestionNext(View view) {
        setContentView(R.layout.sintomas_alcool_dependencia_uso_dependencia_questao);
    }

    public void QuestionDependencyQuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas);
    }

    public void QuestionDependencyQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_uso_dependencia_continuacao);
    }

    public void dependencyContQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_uso_dependencia);
    }

    public void dependencyProblemsQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_uso_dependencia_continuacao);
    }

    public void dependencyAcidenteQuestion(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas_acidente);
    }

    public void dependencyProblemsQuestion(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas_saude);
    }

    public void dependencyFetusQuestion(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas_feto);
    }

    public void dependencyQuestion(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas_dependencia_danos_associados);
    }

    public void dependencyAcidenteQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas);
    }

    public void dependencyHealthQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas);
    }

    public void dependencyFetusQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas);
    }

    public void dependencyDanosQuestionBack(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_problemas);
    }

    public void dependencyDanosQuestionNext(View view){
        setContentView(R.layout.sintomas_alcool_dependencia_uso_dependencia_questao);
    }

    public void nocivoproblemas (View view){
        setContentView(R.layout.sintomas_alcool_nocivo_problemas);
    }

    public void nocivoproblemasacidente (View view){
        setContentView(R.layout.sintomas_alcool_nocivo_problemas_acidente);
    }

    public void nocivoproblemassaude (View view){
        setContentView(R.layout.sintomas_alcool_nocivo_problemas_saude);
    }

    public void nocivoproblemasfeto (View view){
        setContentView(R.layout.sintomas_alcool_nocivo_problemas_feto);
    }

    public void nocivoproblemasdependencia (View view){
        setContentView(R.layout.sintomas_alcool_nocivo_problemas_dependencia_danos_associados);
    }

    public void nocivosimuso2 (View view){
        setContentView(R.layout.sintomas_alcool_nocivo_sim_uso_2);
    }

    public void nocivosimuso (View view){
        setContentView(R.layout.sintomas_alcool_nocivo_sim_uso);
    }

    public void paginaFinal (View view){
        setContentView(R.layout.pagina_final);
    }

    public void paginaSobre (View view){
        setContentView(R.layout.sobre);
    }

    public void paginaSobre2 (View view){
        setContentView(R.layout.sobre2);
    }

    public void paginaEmail (View view){
        setContentView(R.layout.feedback_annualscreening);
    }

    public void clicar (View view){
        String url = "http://www.alcoolesaude.com.br/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }









}


