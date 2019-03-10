import { Component, OnInit } from '@angular/core';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { faCircle } from '@fortawesome/free-regular-svg-icons';

@Component({
    selector: 'app-host-control',
    templateUrl: './host-control.component.html',
    styleUrls: ['./host-control.component.scss']
})
export class HostControlComponent implements OnInit {
    icons = {
        'plus': faPlus,
        'circle': faCircle
    };
    checked: true;

    constructor() { }

    ngOnInit(): void { }
}
