import { NgModule } from '@angular/core';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardPortalComponent } from 'src/app/components/main/dashboard/dashboard-portal.component';
import { ShareModule } from 'src/app/routings/share.module';
import { CommonModule } from '@angular/common';
import { DeviceComponent } from '../components/main/dashboard/device/device.component';
import { DeviceAppComponent } from '../components/main/dashboard/application/device-application.component';
import { HostControlComponent } from '../components/main/dashboard/host-control/host-control.component';
import { ConfigComponent } from '../components/main/dashboard/config/config.component';
import { StatusComponent } from '../components/main/dashboard/status/status.component';
import { DeviceModifyComponent } from '../components/main/dashboard/device/device-modify/device-modify.component';
import { ApplicationViewComponent } from '../components/main/dashboard/application/application-view/application-view.component';

@NgModule({
    declarations: [
        DashboardPortalComponent,
        DeviceComponent,
        DeviceAppComponent,
        HostControlComponent,
        ConfigComponent,
        StatusComponent,
        DeviceModifyComponent,
        ApplicationViewComponent
    ],
    imports: [
        ShareModule,
        CommonModule,
        DashboardRoutingModule,
    ],
})
export class DashboardModule {}
