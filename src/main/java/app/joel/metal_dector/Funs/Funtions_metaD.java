package app.joel.metal_dector.Funs;
/*
* Separated class OOP for metal finder function
* it's help for clean code
* less code less tension
*
* */
import android.content.Context;
import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
public class Funtions_metaD
{
 SensorEvent event;
//vibration_val: to on of the vibration
    ToggleButton vibration_val;
    //power view: to display the metal power , detect_view: to display metal found or not, valuer: display the X,Y,Z axis
    public TextView power_view,detect_view,valuer;
    public SensorManager sensorManager;// Sensor manger
    public View view;
    public Context context;



    public Funtions_metaD(SensorEvent event, ToggleButton vibration_val, TextView power_view, TextView detect_view, TextView valuer, SensorManager sensorManager, View view, Context context) {
        this.event = event;

        this.vibration_val = vibration_val;
        this.power_view = power_view;
        this.detect_view = detect_view;
        this.valuer = valuer;
        this.sensorManager = sensorManager;
        this.view = view;
        this.context = context;
    }

public void work_the_function(){
        //Call this method in OnSensorChange
    //Don't forget to assume the values in constructor
    float x = event.values[0];
    float y = event.values[1];
    float z = event.values[2];


    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);//Vibration code
    double metalpower = Math.round(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2))); //Magnetic Power calculate
    String xyz="X:"+Float.toString(x)+" Y:"+Float.toString(y)+" Z:"+Float.toString(z);
    String pwtext = "Magnetic Power of Metal (in microTesla): " + Double.toString(metalpower);
    power_view.setText(pwtext);
    // Log.d("TAG", text);
    valuer.setText(xyz);
    //Also You Can use Switch
    if (metalpower>550) {
        //Shows High Magnified
        detect_view.setTextColor(Color.BLACK);
        detect_view.setText("Metal Detected");view.setBackgroundColor(Color.RED);
        detect_view.setText("Metal Detected: High Magnetic power ! ");view.setBackgroundColor(Color.RED);
        if(vibration_val.isChecked()) {
            vibrator.vibrate(100);
        }
    }else
    if (metalpower > 70) {
        //Iron Detected
        power_view.setTextColor(Color.BLACK);
        detect_view.setTextColor(Color.BLACK);
        detect_view.setText("Metal Detected");
        view.setBackgroundColor(Color.GREEN); //Change background color
        if(vibration_val.isChecked() /* If Vibration toggle on */) {
            //Vibration
            vibrator.vibrate(100);
        }
    }else
    if(metalpower<70) {
        detect_view.setTextColor(Color.WHITE);
        power_view.setTextColor(Color.WHITE);
        detect_view.setText("Not Detected");view.setBackgroundColor(Color.BLACK);

    }



}





}
