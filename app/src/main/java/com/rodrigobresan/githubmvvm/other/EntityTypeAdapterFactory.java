package com.rodrigobresan.githubmvvm.other;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by rodrigobresan on 2/26/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@GsonTypeAdapterFactory
public abstract class EntityTypeAdapterFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return new AutoValueGson_EntityTypeAdapterFactory();
    }
}
