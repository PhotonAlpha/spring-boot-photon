import { NgModule } from '@angular/core';
import { MatButtonModule, MatCheckboxModule, MatRadioModule,
    MatFormFieldModule, MatInputModule, MatOptionModule, MatSelectModule,
    MatIconModule, MatDialogModule, MatSliderModule, MatCardModule, MatProgressBarModule,
    MatMenuModule, MatDividerModule, MatListModule, MatSlideToggleModule } from '@angular/material';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

const matModules = [
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule, MatCheckboxModule, MatRadioModule, MatFormFieldModule, MatInputModule,
    MatOptionModule, MatSelectModule, MatIconModule, MatDialogModule, MatSliderModule, MatCardModule,
    MatProgressBarModule, MatSlideToggleModule,
    MatMenuModule, MatDividerModule, MatListModule
];

@NgModule({
    imports: [
        matModules,
        FontAwesomeModule
    ],
    exports: [
        matModules,
        FontAwesomeModule
    ],
})
export class ShareModule {}
