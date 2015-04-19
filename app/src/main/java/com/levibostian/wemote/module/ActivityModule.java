package com.levibostian.wemote.module;

import com.levibostian.wemote.activity.MainActivity;
import dagger.Module;

@Module(injects = {MainActivity.class},
               complete = false,
               library = false)
public class ActivityModule {
}
