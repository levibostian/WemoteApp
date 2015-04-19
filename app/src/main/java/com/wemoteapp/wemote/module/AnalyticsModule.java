package com.wemoteapp.wemote.module;

import android.content.Context;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Module(library = true, complete = false)
public class AnalyticsModule {

    @Singleton
    @Provides
    public MixpanelAPI provideMixpanelAPI(Context context) {
        String mixpanelToken = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("mixpanel-creds.txt")));
            mixpanelToken = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return MixpanelAPI.getInstance(context, mixpanelToken);
    }

}
