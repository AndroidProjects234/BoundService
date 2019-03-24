package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {
   private final IBinder buckysBinder=new MyLocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return buckysBinder;    //Returns binder reference variable
    }

    public String getCurrentTime(){ //It displays date in hh:mm:ss format
        SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss", Locale.US);
       return (df.format(new Date()));
    }

    public class MyLocalBinder extends Binder{  //this class bindes service with the client
      MyService getService(){   //Return reference of MyService for accessing MyService class methods
          return MyService.this;
      }

    }
}
