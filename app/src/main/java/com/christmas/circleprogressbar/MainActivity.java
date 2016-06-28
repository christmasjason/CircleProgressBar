package com.christmas.circleprogressbar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  // 总共的时间是30s.
  public static final long MS_IN_FUTURE = DateUtils.MINUTE_IN_MILLIS / 2;
  public static final int MAX_PROGRESS = 100;
  public static final String PROGRESS_PROPERTY = "progress";

  private ProgressBar progressBarTop;
  private ProgressBar progressBarMiddle;
  private ProgressBar progressBarBottom;

  private TextView tvCountDownTop;
  private TextView tvCountDownMiddle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_main);

    progressBarTop = (ProgressBar) findViewById(R.id.pb_count_down_top);
    progressBarMiddle = (ProgressBar) findViewById(R.id.pb_count_down_middle);
    progressBarBottom = (ProgressBar) findViewById(R.id.pb_count_down_bottom);

    tvCountDownTop = (TextView) findViewById(R.id.tv_count_down_top);
    tvCountDownMiddle = (TextView) findViewById(R.id.tv_count_down_middle);
  }

  @Override
  protected void onResume() {
    super.onResume();

    initTopProgressBar();

    initMiddleProgressBar();

    initBottomProgressBar();
  }

  private void initBottomProgressBar() {
    // 当前进度
    progressBarBottom.setMax(MAX_PROGRESS);
    progressBarBottom.setProgress(0);

    ObjectAnimator
        .ofInt(progressBarBottom, PROGRESS_PROPERTY, progressBarBottom.getMax())
        .setDuration(MS_IN_FUTURE)
        .start();
  }

  private void initMiddleProgressBar() {
    // 当前进度
    progressBarMiddle.setMax(MAX_PROGRESS);
    progressBarMiddle.setProgress(0);

    ObjectAnimator objectAnimatorMiddle = ObjectAnimator
        .ofInt(progressBarMiddle, PROGRESS_PROPERTY, progressBarMiddle.getMax())
        .setDuration(MS_IN_FUTURE);
    objectAnimatorMiddle
        .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
            tvCountDownMiddle.setText(getString(R.string.timeElapse, (int) animation.getAnimatedValue()));
          }
        });
    objectAnimatorMiddle.start();
  }

  private void initTopProgressBar() {
    // 倒计时
    progressBarTop.setMax(MAX_PROGRESS);
    progressBarTop.setProgress(MAX_PROGRESS);

    ObjectAnimator objectAnimatorTop = ObjectAnimator
        .ofInt(progressBarTop, PROGRESS_PROPERTY, 0)
        .setDuration(MS_IN_FUTURE);
    objectAnimatorTop
        .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
            tvCountDownTop.setText(getString(R.string.timeLeft, (int) animation.getAnimatedValue()));
          }
        });
    objectAnimatorTop.start();
  }
}
