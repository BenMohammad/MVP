package com.benmohammad.mvp.di.component;


import com.benmohammad.mvp.di.PerService;
import com.benmohammad.mvp.di.module.ServiceModule;
import com.benmohammad.mvp.service.SyncService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);
}
