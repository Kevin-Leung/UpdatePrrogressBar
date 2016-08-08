package com.kevin.updateprrogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.kevin.progressbarlib.progressbarlib.UpdateProgressBar;

/**
 * Rom update of ble devices in android.
 *
 * @author Kevin
 */
public class DeviceUpdateActivity extends AppCompatActivity {

    private UpdateProgressBar mProgressBar;
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
                        mProgressBar.setCentralImage(getDrawable(R.mipmap.update_finish));
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
        setContentView(R.layout.activity_main);
        mProgressBar = (UpdateProgressBar) findViewById(R.id.progressbar);
        mHandler.sendEmptyMessageDelayed(REFRESH_TIME, 100);
    }
}
