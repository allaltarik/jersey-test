package com.necoutezpas;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;

/**
 * Created by pascal on 10/05/2017.
 */
@Singleton
@Service
public class MyDependency {
    public MyDependency() {
        System.out.println("MyDependency constructor");
    }

    public String getFoo() {
        return "foobar";
    }
}
