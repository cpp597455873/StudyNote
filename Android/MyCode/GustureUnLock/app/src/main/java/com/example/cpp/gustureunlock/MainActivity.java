package com.example.cpp.gustureunlock;

import android.app.Activity;
import android.gesture.Gesture;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {
    String pass = "0125";
    GuestureLockView  glView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        glView = (GuestureLockView) findViewById(R.id.gLView);

        findViewById(R.id.btnAddPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glView.requestInput(new GuestureLockView.InputCallBack() {
                    @Override
                    public void onPasswordSetted(String password) {
                           pass = password;
                        Toast.makeText(MainActivity.this,"设置密码成功",Toast.LENGTH_LONG).show();
//                        }
                    }
                });
            }
        });

        findViewById(R.id.btnVerifyPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glView.reset();
                glView.requestInput(new GuestureLockView.InputCallBack() {
                    @Override
                    public void onPasswordSetted(String password) {
                        if (!password.equals(pass)) {
                            glView.showErrorPathForSometime(2000);
                            Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this,"密码正确",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
