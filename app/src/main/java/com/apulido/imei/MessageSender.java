package com.apulido.imei;

import  android.os.AsyncTask;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends  AsyncTask<String,Void,Void> {

    private String SERVER_IP;
    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids)
    {
        String message = voids[0];
        try
        {
            s = new Socket(SERVER_IP,1000);
            //s = new Socket("10.0.8.45",7800);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            s.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void IP (String IP_CON)
    {
        SERVER_IP = IP_CON;
    }

}
