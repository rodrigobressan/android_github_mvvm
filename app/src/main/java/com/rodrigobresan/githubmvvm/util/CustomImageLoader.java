package com.rodrigobresan.githubmvvm.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by rodrigobresan on 3/5/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class CustomImageLoader implements ImageLoader {

    private Context context;

    public CustomImageLoader(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView target) {
        Glide.with(context).load(url).into(target);
    }
}
