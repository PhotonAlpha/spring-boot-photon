import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardPortalComponent } from 'src/app/components/main/dashboard/dashboard-portal.component';
import { DashboardAnnimationComponent } from 'src/app/components/main/dashboard/annimation/dash-annimation.component';
import { DashboardWheelComponent } from 'src/app/components/main/dashboard/wheel/dashboard-wheel.component';

const routes: Routes = [
    {
        path: '', component: DashboardPortalComponent,
        children: [
            { path: '', redirectTo: 'annimation', pathMatch: 'full' },
            { path: 'annimation', component: DashboardAnnimationComponent },
            { path: 'wheel', component: DashboardWheelComponent },
        ]
    }
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [RouterModule ],
})
export class DashboardRoutingModule {}
