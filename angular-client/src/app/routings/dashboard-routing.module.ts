import { DeviceModifyComponent } from './../components/main/dashboard/device/device-modify/device-modify.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardPortalComponent } from 'src/app/components/main/dashboard/dashboard-portal.component';
import { DeviceComponent } from '../components/main/dashboard/device/device.component';
import { DeviceAppComponent } from '../components/main/dashboard/application/device-application.component';
import { StatusComponent } from '../components/main/dashboard/status/status.component';
import { ConfigComponent } from '../components/main/dashboard/config/config.component';
import { HostControlComponent } from '../components/main/dashboard/host-control/host-control.component';

const routes: Routes = [
    {
        path: '', component: DashboardPortalComponent,
        children: [
            { path: '', redirectTo: 'device', pathMatch: 'full' },
            { path: 'device', component: DeviceComponent },
            { path: 'device/add', component: DeviceModifyComponent },
            { path: 'applications', component: DeviceAppComponent },
            { path: 'status', component: StatusComponent },
            { path: 'config', component: ConfigComponent },
            { path: 'host-contril', component: HostControlComponent },
        ]
    }
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [RouterModule ],
})
export class DashboardRoutingModule {}
