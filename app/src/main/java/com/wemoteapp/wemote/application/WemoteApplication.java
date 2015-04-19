package com.wemoteapp.wemote.application;

import android.app.Application;
import com.wemoteapp.wemote.module.ActivityModule;
import com.wemoteapp.wemote.module.AnalyticsModule;
import com.wemoteapp.wemote.module.ApplicationModule;
import dagger.ObjectGraph;

import java.util.Arrays;
import java.util.List;

public class WemoteApplication extends Application {

    private static ObjectGraph sObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        sObjectGraph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(new ActivityModule(),
                             new AnalyticsModule(),
                             new ApplicationModule(getApplicationContext()));
    }

    public static void inject(Object object) {
        sObjectGraph.inject(object);
    }

}
