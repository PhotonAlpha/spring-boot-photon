import { NgModule } from '@angular/core';
import { ReportsRoutingModule } from './reports-routing.module';
import { ShareModule } from 'src/app/routings/share.module';
import { ReportCatComponent } from 'src/app/components/main/reports/cat/report-cat.component';
import { ReportDogComponent } from 'src/app/components/main/reports/dog/report-dog.component';
import { ReportComponent } from 'src/app/components/main/reports/reports-portal.component';

@NgModule({
    declarations: [
        ReportComponent,
        ReportDogComponent,
        ReportCatComponent
    ],
    imports: [
        ShareModule,
        ReportsRoutingModule
    ],
})
export class ReportsModule {}
