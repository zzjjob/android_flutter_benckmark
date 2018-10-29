package com.nabinbhandari.flutterbenchmark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import defrac.benchmark.DeltaBlue;
import defrac.benchmark.FluidMotion;
import defrac.benchmark.Havlak;
import defrac.benchmark.Richards;
import defrac.benchmark.Tracer;
import defrac.benchmark.AllBenchmarks;


public class TestActivity extends Activity {

    static {
        System.loadLibrary("benchmark");
    }

    public static final int MSG_RUN_JAVA = 1;
    public static final int MSG_RUN_NATIVE = 2;

    MainHandler handler = new MainHandler();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RUN_JAVA:
                    startPerformance();
                    break;
                case MSG_RUN_NATIVE:
//                    startNativePerformance();
                    break;
                default:
                    break;
            }
        }
    }

    public void onClickFlutter(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onClickJava(View view) {
        Message msg = Message.obtain();
        msg.what = MSG_RUN_JAVA;
        handler.sendMessage(msg);
    }

    public void onClickC(View view) {
        Message msg = Message.obtain();
        msg.what = MSG_RUN_NATIVE;
        handler.sendMessage(msg);
    }

    private void startPerformance() {
        AllBenchmarks.main();

        System.err.println("[benchmark] java - completed.");
    }
}
