import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-report-dog',
    templateUrl: './report-dog.component.html',
    styleUrls: ['./report-dog.component.scss']
})
export class ReportDogComponent implements OnInit {
    constructor() { }

    ngOnInit(): void { }

    canDeactive(): boolean {
        return false;
    }
}
