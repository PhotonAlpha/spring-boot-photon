import { CanDeactivate } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, OnDestroy {
    theamClassName: string;
    options: FormGroup;
    constructor(fb: FormBuilder) {
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
