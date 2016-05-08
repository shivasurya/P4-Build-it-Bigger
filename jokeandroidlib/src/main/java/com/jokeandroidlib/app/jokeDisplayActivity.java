package com.jokeandroidlib.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class jokeDisplayActivity extends AppCompatActivity {
    private String INTENT_JOKE = "JOKE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        getSupportActionBar().setTitle("TODAYS JOKE");
        TextView mjokeTextView = (TextView)findViewById(R.id.jokerman);
        String joke = getIntent().getStringExtra(INTENT_JOKE);
        if(joke!=null){
            mjokeTextView.setText(joke);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
