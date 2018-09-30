import { NgModule } from '@angular/core';
import { MatButtonModule, MatCheckboxModule, MatRadioModule,
    MatFormFieldModule, MatInputModule, MatOptionModule, MatSelectModule,
    MatIconModule, MatDialogModule, MatSliderModule, MatCardModule, MatProgressBarModule } from '@angular/material';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

const matModules = [
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule, MatCheckboxModule, MatRadioModule, MatFormFieldModule, MatInputModule,
    MatOptionModule, MatSelectModule, MatIconModule, MatDialogModule, MatSliderModule, MatCardModule,
    MatProgressBarModule
];

@NgModule({
    imports: [
        matModules
    ],
    exports: [
        matModules
    ],
})
export class ShareModule {}
