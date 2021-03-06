package ftn.rs.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;

    TextView tvAccelerometer;
    TextView tvLinearAccelerometer;
    TextView tvMagneticField;
    TextView tvProximitySensor;
    TextView tvGyroscope;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometar);
        tvLinearAccelerometer = (TextView) findViewById(R.id.tvLinearAccelerometer);
        tvMagneticField = (TextView) findViewById(R.id.tvMagneticField);
        tvProximitySensor = (TextView) findViewById(R.id.tvProximitySensor);
        tvGyroscope = (TextView) findViewById(R.id.tvGyroscope);
    }

    /*
    * Bitno je da zakacimo/otkacimo listener na pravom mestu. Kada zelmo da koristimo
    * odredjeni senzor, najbolje mesto da se listener zakci, da bi dobijali mernja,
    * jeste metoda onResume.
    * */
    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
    * Najbolje mesto da otkacimo listener jeste onPause. Voditi racuna
    * da se svi listener-i koji rade sa senzorima otkace, kada
    * vise ne zelimo da ih koristimo.
    * */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            tvAccelerometer.setText("Accelerometer: [" + x + ", " + y + ", " + z + "]");
        } else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            tvLinearAccelerometer.setText("Non-gravity accelerometer: [" + x + ", " + y + ", " + z + "]");
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            tvMagneticField.setText("Magnetic field: [" + x + ", " + y + ", " + z + "]");
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            tvGyroscope.setText("Gyroscpe : [" + x + ", " + y + ", " + z + "]");
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float[] values = event.values;
            float x = values[0];

            if (x > 0) {
                tvProximitySensor.setText("Daleko");
            } else {
                tvProximitySensor.setText("Blizu");
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
