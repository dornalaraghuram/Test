/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.injection.module;

import com.news.test.ui.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = HomeActivityModule.HomeFragmentProvider.class)
public class HomeActivityModule {


    @Module
    public abstract class HomeFragmentProvider {

        @ContributesAndroidInjector(modules = HomeFragmentModule.class)
        abstract HomeFragment provideHomeFragment();
    }
}
