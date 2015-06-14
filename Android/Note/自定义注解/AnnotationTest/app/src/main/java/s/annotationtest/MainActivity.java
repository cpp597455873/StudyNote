package s.annotationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import s.annotationtest.annotation.OnClick;
import s.annotationtest.annotation.ViewInject;
import s.annotationtest.annotation.ViewUtils;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tv)
    private TextView tv;

    @OnClick(R.id.btnSayHi)
    private void sayHi(View v) {
        Toast.makeText(this, "Hi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.injectView(this);
        tv.setText("good");
    }

}
