package com.rodrigobresan.githubmvvm.model.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import dagger.multibindings.IntoSet;

/**
 * Created by rodrigobresan on 1/27/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
@AutoValue
public abstract class RepositoryOwner implements Serializable {

    private static final String JSON_PROPERTY_LOGIN = "login";
    private static final String JSON_PROPERTY_AVATAR_URL = "avatarUrl";

    public static Builder builder() {
        return new AutoValue_RepositoryOwner.Builder();
    }

    @NonNull
    public static TypeAdapter<RepositoryOwner> typeAdapter(Gson gson) {
        return new AutoValue_RepositoryOwner.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName(JSON_PROPERTY_LOGIN)
    public abstract String login();

    @Nullable
    @SerializedName(JSON_PROPERTY_AVATAR_URL)
    public abstract String avatarUrl();

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder login(@NonNull String login);

        @NonNull
        public abstract Builder avatarUrl(@NonNull String imageUrl);

        @NonNull
        public abstract RepositoryOwner build();
    }
}
