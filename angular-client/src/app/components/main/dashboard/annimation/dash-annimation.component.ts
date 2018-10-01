import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-dash-annimation',
    templateUrl: './dash-annimation.component.html',
    styleUrls: ['./dash-annimation.component.scss']
})
export class DashboardAnnimationComponent implements OnInit {
    constructor() { }

    color = 'primary';
    mode = 'determinate';
    value = 50;
    bufferValue = 75;

    ngOnInit(): void { }
}
