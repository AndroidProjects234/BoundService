package com.example.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyService buckysService;    //Reference to MyService
    boolean isBound=false;  //whether client bound to service or not

    public void showTime1(View view){ //Display current time to textview
        String currentTime=buckysService.getCurrentTime();
        TextView buckysText=(TextView)findViewById(R.id.buckysText);
        buckysText.setText(currentTime);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i=new Intent(this,MyService.class);
        bindService(i,buckysConnection, Context.BIND_AUTO_CREATE);  //bind service to activity
    }
    private ServiceConnection buckysConnection= new ServiceConnection() {       //for binding activity to service we need ServiceConnection class
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyLocalBinder binder=(MyService.MyLocalBinder)service;    //Creating MyLocalBinder class reference variable
           buckysService= binder.getService();//Call getService()
           isBound=true;    //activity is connected to service or not
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
                isBound=false;  //Service is disconnected
        }
    };
}
