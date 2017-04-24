package fuerback.imagemacao;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import java.io.IOException;
import java.util.Random;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Usuario on 24/04/2017.
 */

//----------------------------------------------------------------------------------------

public class Dado extends Activity implements SensorEventListener {

    private final int rollAnimations = 50;
    private final int delayTime = 15;
    private Resources res;
    private final int[] diceImages = new int[] { R.drawable.pessoag, R.drawable.objetog, R.drawable.acaog, R.drawable.dificilg, R.drawable.lazerg, R.drawable.mixg };
    private Drawable dice[] = new Drawable[6];
    private final Random randomGen = new Random();
    @SuppressWarnings("unused")
    private int diceSum;
    private int roll[] = new int[] { 6, 6 };
    private ImageView die1;
    private LinearLayout diceContainer;
    private SensorManager sensorMgr;
    private Handler animationHandler;
    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private boolean paused = false;
    private static final int UPDATE_DELAY = 50;
    private static final int SHAKE_THRESHOLD = 800;

    //----------------------------------------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        paused = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dado);
        res = getResources();
        for (int i = 0; i < 6; i++) {
            dice[i] = res.getDrawable(diceImages[i], null);
        }

        // inicializando com a imagem default
        die1 = (ImageView) findViewById(R.id.die1);
        die1.setImageDrawable(res.getDrawable(R.drawable.inicio, null));

        diceContainer = (LinearLayout) findViewById(R.id.diceContainer);
        diceContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    rollDice();
                } catch (Exception e) {};
            }
        });
        die1 = (ImageView) findViewById(R.id.die1);
        animationHandler = new Handler() {
            public void handleMessage(Message msg) {
                die1.setImageDrawable(dice[roll[0]]);
            }
        };
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        boolean accelSupported = sensorMgr.registerListener(this,
                sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),	SensorManager.SENSOR_DELAY_GAME);
        if (!accelSupported) sensorMgr.unregisterListener(this); //no accelerometer on the device
    }

    //----------------------------------------------------------------------------------------

    private void rollDice() {
        if (paused) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < rollAnimations; i++) {
                    doRoll();
                }
            }
        }).start();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        try {
            mp.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    //----------------------------------------------------------------------------------------

    private void doRoll() { // only does a single roll
        roll[0] = randomGen.nextInt(6);
        diceSum = roll[0] + 2; // 2 is added because the values of the rolls start with 0 not 1
        synchronized (getLayoutInflater()) {
            animationHandler.sendEmptyMessage(0);
        }
        try { // delay to alloy for smooth animation
            Thread.sleep(delayTime);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onResume() {
        super.onResume();
        // voltando para a imagem default
        die1 = (ImageView) findViewById(R.id.die1);
        die1.setImageDrawable(res.getDrawable(R.drawable.inicio, null));
        paused = false;
    }

    //----------------------------------------------------------------------------------------

    public void onPause() {
        super.onPause();
        paused = true;
    }

    //----------------------------------------------------------------------------------------

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getName() == Sensor.STRING_TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > UPDATE_DELAY) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];
                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                if (speed > SHAKE_THRESHOLD) { //the screen was shaked
                    rollDice();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    //----------------------------------------------------------------------------------------

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }

    //----------------------------------------------------------------------------------------
}
