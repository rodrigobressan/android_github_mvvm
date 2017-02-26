package com.rodrigobresan.githubmvvm.api.entities;

import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.model.entities.RepositoryOwner;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by rodrigobresan on 2/26/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoryTest {

    @Test
    public void hashCode_shouldWorkCorrectly() {
        Repository repositoryOne = Repository.builder()
                .id(1)
                .description("Description")
                .repositoryOwner(new RepositoryOwner())
                .url("repository_url")
                .build();

        Repository repositoryOneCopy = Repository.builder()
                .id(1)
                .description("Description")
                .repositoryOwner(new RepositoryOwner())
                .url("repository_url")
                .build();

        Repository repositoryTwo = Repository.builder()
                .id(2)
                .description("Description")
                .repositoryOwner(new RepositoryOwner())
                .url("repository_url")
                .build();

        assertThat(repositoryOne.hashCode()).isEqualTo(repositoryOneCopy.hashCode());
        assertThat(repositoryOne.hashCode()).isNotEqualTo(repositoryTwo.hashCode());
    }
}
