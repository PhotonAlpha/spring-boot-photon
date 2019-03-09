import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-device-modify',
  templateUrl: './device-modify.component.html',
  styleUrls: ['./device-modify.component.scss']
})
export class DeviceModifyComponent implements OnInit {
    deviceForm: FormGroup;
    macNoHolder = '973823';
    nickName = '苹果iPad';

    constructor(private formBuilder: FormBuilder, private router: Router) { }

    ngOnInit() {
            this.deviceForm = this.formBuilder.group({
                macNo: [''],
                nickName: ['']
            });
    }

    checkPlaceHolder() {
        if (this.macNoHolder) {
            this.macNoHolder = null;
            return;
        } else {
            this.macNoHolder = '973823';
            return;
        }
    }

    addApplication() {
        this.router.navigate(['/main/dashboard/device']);
    }

}
