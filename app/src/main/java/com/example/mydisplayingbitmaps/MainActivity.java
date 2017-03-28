package com.example.mydisplayingbitmaps;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LruCache<String, Bitmap> mMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setNumColumns(3);
        final List<ImageView> listImageView = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
            Picasso.with(this)
                    .load(Uri.parse("https://progedu.github.io/asset/spapp-curriculum/images/sample.jpg"))
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(new CropTransformation())
                    .into(imageView);
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

    private class CropTransformation implements Transformation {

        @Override public Bitmap transform(Bitmap source) {

            Bitmap result = Bitmap.createBitmap(source, 340, 120, 360, 640);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "cropTransform()"; }
    }

}