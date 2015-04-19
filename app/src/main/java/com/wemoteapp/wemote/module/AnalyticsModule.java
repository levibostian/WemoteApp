package com.wemoteapp.wemote.module;

import android.content.Context;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(library = true, complete = false)
public class AnalyticsModule {

    public static final String MIXPANEL_TOKEN = "0a13748368bcdea6a66d72bbc95b91c2";

    @Singleton
    @Provides
    public MixpanelAPI provideMixpanelAPI(Context context) {
        return MixpanelAPI.getInstance(context, MIXPANEL_TOKEN);
    }

}
