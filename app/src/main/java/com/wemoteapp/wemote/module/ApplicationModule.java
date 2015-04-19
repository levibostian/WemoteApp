package com.wemoteapp.wemote.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideApplicationContext() {
        return mContext;
    }
}
