package com.apulido.imei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TelephonyManager manager;
    TextView text_view_imei;
    TextView text_view_App_V;
    //TextView text_view_imei2;
    Button btn_Get_IMEI;
    EditText text_view_IP;
    public String IP_PC;
    String IMEI_1;
    //String IMEI_2;
    int Permission_Granted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Version name */
        text_view_App_V = (TextView)findViewById(R.id.textView2);
        String VerName = BuildConfig.VERSION_NAME;
        text_view_App_V.setText(VerName);


        text_view_imei = (TextView) findViewById(R.id.textView_IMEI);
        //text_view_imei2 = (TextView) findViewById(R.id.textView_IMEI2);
        text_view_IP = (EditText) findViewById(R.id.editText);
        btn_Get_IMEI = (Button) findViewById(R.id.ButtonGetImei);
        //TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        } else {
            if (manager == null)
            {
                Toast.makeText(this, "Hardware not supported", Toast.LENGTH_SHORT).show();
            } else {
                    Permission_Granted = 0;
            }
        }


    }

    public void GetIMEI (View IMEI) {
            if (Permission_Granted != 0)
            {
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
            } else {
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
                IMEI_1 = manager.getDeviceId();
                text_view_imei.setText(IMEI_1);
                //text_view_imei2.setText(IMEI_2);
            }
    }

    public void send (View v)
    {
        if (text_view_imei.getText().toString().equals("IMEI"))
        {
            Toast.makeText(this, "Consultar IMEI primero", Toast.LENGTH_SHORT).show();
        }else {
            StringBuilder builder = new StringBuilder();
            builder.append("IMEI :  ").append(text_view_imei.getText()).append("\n");
            //builder.append("IMEI SV: ").append(manager.getDeviceSoftwareVersion());
            IP_PC = text_view_IP.getText().toString();
            MessageSender messageSender = new MessageSender();
            messageSender.IP(IP_PC);
            //messageSender.execute(text_view_imei.getText().toString());
            messageSender.execute(builder.toString());
        }
    }


}
