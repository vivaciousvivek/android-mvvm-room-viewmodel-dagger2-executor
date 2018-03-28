package com.techfirebase.android.mvvmdemodagger2executor.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by DUKE SINGH on 3/28/2018.
 *
 * <p>This Custom annotation is created
 *
 * <p>@Scope is used to specify the scope in which a dependency object persists.
 *
 * <p>If a class getting dependencies, have members injected with classes annotated with a scope,
 * then each instance of that class asking for dependencies will get its own set of member
 * variables.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}
