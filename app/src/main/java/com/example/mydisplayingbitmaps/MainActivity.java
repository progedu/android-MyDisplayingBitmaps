package com.example.mydisplayingbitmaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setNumColumns(3);

        final List<ImageView> listImageView = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.sample, options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            String imageType = options.outMimeType;
            Log.d("MainActivity", "height:" + imageHeight + " width:" + imageWidth + " type:" + imageType);

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
            imageView.setImageBitmap(bm);
            listImageView.add(imageView);
        }

        BaseAdapter mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return listImageView.size();
            }

            @Override
            public Object getItem(int i) {
                return listImageView.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return listImageView.get(i);
            }
        };
        mGridView.setAdapter(mAdapter);
    }
}