import { Component, OnInit } from '@angular/core';
import { faMobileAlt, faShoppingCart, faGlobe, faCog, faClock } from '@fortawesome/free-solid-svg-icons';
 
@Component({
    selector: 'app-device',
    templateUrl: './device.component.html',
    styleUrls: ['./device.component.scss']
})
export class DeviceComponent implements OnInit {
    icons = {
        'phone': faMobileAlt,
        'shop': faShoppingCart,
        'earth': faGlobe,
        'cog': faCog,
        'clock': faClock
    };

    constructor() { }

    ngOnInit(): void { }



}
