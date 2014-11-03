package com.thoughtworks.cathywu.myweather.util;

import android.app.Application;

import dagger.ObjectGraph;

public class MWApplication extends Application implements InjectorApplication {
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(getModules());
    }

    protected Object[] getModules() {
        return new Object[]{new MWModule(this)};
    }

    @Override
    public void inject(Object object) {
        graph.inject(object);
    }

}