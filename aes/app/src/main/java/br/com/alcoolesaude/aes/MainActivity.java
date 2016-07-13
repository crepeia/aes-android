package br.com.alcoolesaude.aes;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    requestWS();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    public void requestWS() {
        System.out.println("requesting");
        SoapObject soap = new SoapObject("http://localhost:61626/WebApplication1",
                "hello");

        soap.addProperty("name", "aaa");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.setOutputSoapObject(soap);

        String url = "http://localhost:61626/WebApplication1/NewWebService";

        HttpTransportSE httpTransport = new HttpTransportSE(url);

        try {

            httpTransport.call("", envelope);

            Object msg = envelope.getResponse();

            System.out.println(msg.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
