import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { faMobileAlt, faShoppingCart, faGlobe, faCog, faClock } from '@fortawesome/free-solid-svg-icons';
import { MenuHeader } from 'src/app/models/menu-header';
import { SharedService } from 'src/app/service/shared.service';
 
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

    @Output() menu = new EventEmitter<MenuHeader>();

    constructor(private _sharedService: SharedService) {
        console.log('DeviceComponent');
        const header = new MenuHeader();
        header.avatar = 'avatar';
        header.title = '我的设备';
        header.currentUrl = '/main/dashboard/device';
        this._sharedService.emitMenuHeaderChange(header);
    }

    ngOnInit(): void {


    }



}
