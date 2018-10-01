import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReportComponent } from 'src/app/components/main/reports/reports-portal.component';
import { ReportDogComponent } from 'src/app/components/main/reports/dog/report-dog.component';
import { ReportCatComponent } from 'src/app/components/main/reports/cat/report-cat.component';
import { PortalDeactiveGuard } from 'src/app/utils/guard/deactive-guard.service';

const routes: Routes = [
    {
        path: '', component: ReportComponent,
        children: [
        { path: 'dog', component: ReportDogComponent, canDeactivate: [PortalDeactiveGuard]},
        { path: 'cat', component: ReportCatComponent, canDeactivate: [PortalDeactiveGuard]}
        ]
    }
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ],
})
export class ReportsRoutingModule {}
