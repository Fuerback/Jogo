package fuerback.imagemacao;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Usuario on 26/04/2017.
 */

public class CronometroActivity extends Activity {

    TextView seconds;

    private static final String FORMAT = "%02d";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        //Cronometro
        seconds = (TextView)findViewById(R.id.timer);
        final CountDownTimer countdown = new CountDownTimer(60000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                seconds.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                MediaPlayer mp = MediaPlayer.create(CronometroActivity.this, R.raw.hornship);
                try {
                    mp.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
                seconds.setText("FIM!");
            }
        };

        //Botao PLay
        ImageView botaoPlay = (ImageView)findViewById(R.id.play);
        botaoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdown.start();
            }
        });

        //Botao Pause
        ImageView botaoPause = (ImageView)findViewById(R.id.pause);
        botaoPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdown.cancel();
            }
        });

        //Botao Termporario
        ImageView botaoVoltar = (ImageView)findViewById(R.id.volta_cronometro);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
