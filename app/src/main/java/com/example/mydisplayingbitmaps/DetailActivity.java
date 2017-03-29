package com.example.mydisplayingbitmaps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView mDetailImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.mDetailImage = (ImageView) findViewById(R.id.detail_image);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.contains(action)
                && "text/plain".equals(type)) {
            String url = intent.getStringExtra(Intent.EXTRA_TEXT);
            Picasso.with(this)
                    .load(Uri.parse(url))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mDetailImage);
        }
    }
}
