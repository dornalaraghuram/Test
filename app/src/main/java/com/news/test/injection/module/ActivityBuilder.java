/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.injection.module;

import com.news.test.injection.provider.HomeFragmentProvider;
import com.news.test.injection.scope.ActivityScope;
import com.news.test.ui.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = {HomeActivityModule.class, HomeFragmentProvider.class})
    abstract HomeActivity bindHomeActivity();


}
