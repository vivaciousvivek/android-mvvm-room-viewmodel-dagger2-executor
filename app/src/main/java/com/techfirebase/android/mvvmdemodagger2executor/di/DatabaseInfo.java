package com.techfirebase.android.mvvmdemodagger2executor.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by DUKE SINGH on 3/28/2018.
 *
 * <p>DatabaseInfo is used to provide the database name in the class dependency. Since a String
 * class in being provided as a dependency, it always a good idea to qualify it so that the Dagger
 * can explicitly resolve it.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseInfo {}
