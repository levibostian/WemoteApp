package com.wemoteapp.wemote.module;

import com.wemoteapp.wemote.activity.MainActivity;
import dagger.Module;

@Module(injects = {MainActivity.class},
               complete = false,
               library = false)
public class ActivityModule {
}
