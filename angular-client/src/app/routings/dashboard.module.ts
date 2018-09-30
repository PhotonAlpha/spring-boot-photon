import { NgModule } from '@angular/core';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardPortalComponent } from 'src/app/components/main/dashboard/dashboard-portal.component';
import { DashboardAnnimationComponent } from 'src/app/components/main/dashboard/annimation/dash-annimation.component';
import { DashboardWheelComponent } from 'src/app/components/main/dashboard/wheel/dashboard-wheel.component';
import { ShareModule } from 'src/app/routings/share.module';
import { CommonModule } from '@angular/common';

@NgModule({
    declarations: [
        DashboardPortalComponent,
        DashboardAnnimationComponent,
        DashboardWheelComponent
    ],
    imports: [
        ShareModule,
        CommonModule,
        DashboardRoutingModule,
    ],
})
export class DashboardModule {}
