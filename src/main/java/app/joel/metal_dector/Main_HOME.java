package app.joel.metal_dector;
/*
Code written by Madushan joel(Dip in SE & HND in com)
student of the Sri-Lankan private university
***************************************************
This app work with magnetic sensor, please check sensor availability of your device before run this code
***********************************************************************************
 */
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



import java.net.URI;

import app.joel.metal_dector.Funs.Funtions_metaD;

public class Main_HOME extends AppCompatActivity implements  SensorEventListener {

    public SensorManager sensorManager;
    Funtions_metaD mtFunction;
    public LinearLayout back; //Layout
    ToggleButton  vibration_val;
    public TextView power_view,detect_view,valuer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //
        final  ToneGenerator toneGenerator=new ToneGenerator(AudioManager.STREAM_MUSIC,100);
        back=findViewById(R.id.layout_back);

        power_view=findViewById(R.id.powers_de);
        detect_view=findViewById(R.id.detec);
        valuer=findViewById(R.id.val);
        vibration_val=findViewById(R.id.vibra);


    }

    @Override
    protected void onResume() {
        super.onResume();
        // code for system's orientation sensor registered listeners
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStart() {

        super.onStart();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Get values from here
        mtFunction=new Funtions_metaD(event,vibration_val,power_view,detect_view,valuer,sensorManager,back,this);

        mtFunction.work_the_function();


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }


}
