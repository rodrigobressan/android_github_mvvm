package com.rodrigobresan.githubmvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
public class Repository implements Serializable {
    public int id;
    public String name;
    public String description;
    public String url;

    @SerializedName("owner")
    public RepositoryOwner repositoryOwner;
}
