package com.example.hp.mymachinetest3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {
    ImageView iv;
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        iv=(ImageView)findViewById(R.id.imvv);
        tv1=(TextView)findViewById(R.id.tvv);
        tv2=(TextView)findViewById(R.id.tvvv);
        Intent getData= getIntent();
        String img_url = "http://iroidtech.com/wecare/uploads/news_events/";
        String Image=getData.getStringExtra("image");
        String text1=getData.getStringExtra("text1");
        String text2=getData.getStringExtra("text2");
        Picasso.with(SecondActivity.this).load(img_url+Image).into(iv);
        tv1.setText(text1);
        tv2.setText(text2);

    }
}
