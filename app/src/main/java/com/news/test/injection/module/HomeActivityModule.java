/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.injection.module;

import com.news.test.injection.scope.ContainerId;
import com.news.test.ui.base.BaseActivity;
import com.news.test.ui.home.HomeActivity;
import com.news.test.ui.navigator.AppNavigator;
import com.news.test.ui.navigator.AppNavigatorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = HomeActivityModule.Declaration.class)
public class HomeActivityModule {


    @Provides
    @ContainerId
    public int provideContainerId(BaseActivity activity) {
        return activity.getContainerId();
    }


    @Module
    public interface Declaration {

        @Binds
        BaseActivity bindsBaseActivity(HomeActivity activity);

        @Binds
        AppNavigator bindsAppNavigator(AppNavigatorImpl appNavigator);
    }


}
