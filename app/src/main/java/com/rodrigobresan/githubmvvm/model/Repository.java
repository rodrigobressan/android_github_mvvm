package com.rodrigobresan.githubmvvm.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
@AutoValue
public abstract class Repository implements Serializable {

    private static final String JSON_PROPERTY_ID = "id";
    private static final String JSON_PROPERTY_NAME = "name";
    private static final String JSON_PROPERTY_DESCRIPTION = "description";
    private static final String JSON_PROPERTY_URL = "url";
    private static final String JSON_PROPERTY_OWNER = "owner";

    @Nullable
    @SerializedName(JSON_PROPERTY_ID)
    public abstract Integer id();

    @Nullable
    @SerializedName(JSON_PROPERTY_NAME)
    public abstract String name();

    @Nullable
    @SerializedName(JSON_PROPERTY_DESCRIPTION)
    public abstract String description();

    @Nullable
    @SerializedName(JSON_PROPERTY_URL)
    public abstract String url();

    @Nullable
    @SerializedName(JSON_PROPERTY_OWNER)
    public abstract RepositoryOwner repositoryOwner();

    public static Builder builder() {
        return new AutoValue_Repository.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder id(Integer id);

        public abstract Builder name(String name);

        public abstract Builder description(String description);

        public abstract Builder url(String url);

        public abstract Builder repositoryOwner(RepositoryOwner owner);

        public abstract Repository build();
    }
}
