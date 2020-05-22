package com.diwixis.filmlibrary.repository;

import androidx.annotation.MainThread;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class RepositoryProvider {

    private static IRepository repository;

    public static IRepository providerRepository() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    @MainThread
    public static void init() {
        repository = new Repository();
    }
}
