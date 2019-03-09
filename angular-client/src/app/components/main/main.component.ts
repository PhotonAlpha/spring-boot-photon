import { CanDeactivate, Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

import { faUserCircle, faEllipsisV, faPlus } from '@fortawesome/free-solid-svg-icons';
import { faCircle } from '@fortawesome/free-regular-svg-icons';

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, OnDestroy {
    icons = {
        'avatar': faUserCircle,
        'ellipsis': faEllipsisV,
        'plus': faPlus,
        'circle': faCircle
    };


    theamClassName: string;
    options: FormGroup;
    constructor(fb: FormBuilder, private router: Router) {
        this.options = fb.group({
            hideRequired: false,
            floatLabel: 'auto',
        });
    }

    ngOnInit(): void {
        console.log('ngOnInit');
    }

    ngOnDestroy(): void {
        console.log('ngOnDestroy');
    }

    addDevice() {
        this.router.navigate(['/main/dashboard/device/add']);
    }


    lightTheam() {
        this.theamClassName = '';
    }
    darkTheam() {
        this.theamClassName = 'unicorn-dark-theme';
    }
    customerTheam() {
        this.theamClassName = 'unicorn-customer-theme';
    }

    canDeactive(): boolean {
        return false;
    }

}
