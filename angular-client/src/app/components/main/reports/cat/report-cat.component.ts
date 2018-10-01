import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-report-cat',
    templateUrl: './report-cat.component.html',
    styleUrls: ['./report-cat.component.scss']
})
export class ReportCatComponent implements OnInit {
    constructor() { }

    ngOnInit(): void { }

    canDeactive(): boolean {
        return false;
    }
}
