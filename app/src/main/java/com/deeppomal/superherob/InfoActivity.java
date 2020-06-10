package com.deeppomal.superherob;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView title,q1,a1,q2,a2,q3,a3,q4,a4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title = (TextView)findViewById(R.id.title);
        q1 = (TextView)findViewById(R.id.q1);
        q2 = (TextView)findViewById(R.id.q2);
        q3 = (TextView)findViewById(R.id.q3);
        q4 = (TextView)findViewById(R.id.q4);
        a1 = (TextView)findViewById(R.id.a1);
        a2 = (TextView)findViewById(R.id.a2);
        a3 = (TextView)findViewById(R.id.a3);
        a4 = (TextView)findViewById(R.id.a4);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_heavy.otf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_bold.otf");
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_light.otf");

        title.setTypeface(typeface);

        q1.setTypeface(typeface2);
        q2.setTypeface(typeface2);
        q3.setTypeface(typeface2);
        q4.setTypeface(typeface2);

        a1.setTypeface(typeface3);
        a2.setTypeface(typeface3);
        a3.setTypeface(typeface3);
        a4.setTypeface(typeface3);

    }
}
