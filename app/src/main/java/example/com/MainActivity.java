package example.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView timerText;
    private SimpleDateFormat dataFormat = new SimpleDateFormat("mm:ss.SSS", Locale.US);

    // 3分= 3*60*1000 = 180000 msec
    final long countNumber = 10000;
    final long interval = 1000;

    final CountDown countDown = new CountDown(this, countNumber, interval);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.start_button);
        Button stopButton = findViewById(R.id.stop_button);

        timerText = findViewById(R.id.timer);
        timerText.setText(dataFormat.format(0));

        countDown.start();

        // スタートボタン
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 開始
                countDown.start();
            }
        });

        // ストップボタン
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 中止
                countDown.cancel();
                timerText.setText(dataFormat.format(0));
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction()) {
            // 指が離れていたらそのままカウントダウン実行
            case MotionEvent.ACTION_UP:
                break;
            // タッチ（長押し、スワイプなど）するとカウントダウンをリセットし実行
            default:
                Log.d("msg", "touch!");
                countDown.cancel();
                countDown.start();
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
//
//    class CountDown extends CountDownTimer {
//        CountDown(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        public void onFinish() {
//            // 完了
//            timerText.setText(dataFormat.format(0));
//        }
//
//        // インターバルで呼ばれる
//        public void onTick(long millisUntilFinished) {
//            // 残り時間を分、秒、ミリ秒に分割
////            long mm = millisUntilFinished / 1000 / 60;
////            long ss = millisUntilFinished / 1000 % 60;
////            long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;
////            timerText.setText(String.format("%1$02d:%2$02d/%3$03d", mm,ss,ms));
//
//            timerText.setText(dataFormat.format(millisUntilFinished));
//        }
//    }
}
