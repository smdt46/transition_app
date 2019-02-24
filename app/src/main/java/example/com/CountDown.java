package example.com;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

public class CountDown extends CountDownTimer {

    private Activity mActivity;

    CountDown(Activity activity, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        mActivity = activity;
    }

    // 完了時の動作
    public void onFinish() {
        Log.d("msg", "カウントダウン終わり");
        // 画面遷移
        mActivity.startActivity(new Intent(mActivity, SubActivity.class));
    }

    // インターバルで呼ばれる
    public void onTick(long millisUntilFinished) {
        long mm = millisUntilFinished / 1000 / 60;
        long ss = millisUntilFinished / 1000 % 60;
        Log.d("msg", String.format("%1$02d:%2$02d", mm,ss));
    }
}