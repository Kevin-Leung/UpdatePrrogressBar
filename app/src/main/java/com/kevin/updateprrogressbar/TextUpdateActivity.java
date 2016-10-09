package com.kevin.updateprrogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.kevin.progressbarlib.progressbarlib.ImageUpdateProgressBar;
import com.kevin.progressbarlib.progressbarlib.TextUpdateProgressBar;

/**
 * Rom update of ble devices in android.
 *
 * @author Kevin
 */
public class TextUpdateActivity extends AppCompatActivity {

    private TextUpdateProgressBar mProgressBar;
    private static final int REFRESH_TIME = 1;
    private int mRefreshCount;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_TIME:
                    if (mRefreshCount < 500) {
                        mRefreshCount++;
                        mProgressBar.setProgress(mRefreshCount);
                        mHandler.sendEmptyMessageDelayed(REFRESH_TIME, 10);
                    } else {
                        mHandler.removeMessages(REFRESH_TIME);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mRefreshCount = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mProgressBar = (TextUpdateProgressBar) findViewById(R.id.progressbar);
        mHandler.sendEmptyMessageDelayed(REFRESH_TIME, 100);
    }
}
