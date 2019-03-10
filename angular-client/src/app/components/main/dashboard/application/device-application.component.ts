import { Component, OnInit } from '@angular/core';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { MenuHeader } from 'src/app/models/menu-header';
import { SharedService } from 'src/app/service/shared.service';

@Component({
    selector: 'app-device-app',
    templateUrl: './device-app.component.html',
    styleUrls: ['./device-app.component.scss']
})
export class DeviceAppComponent implements OnInit {
    icons = {
        'search': faSearch
    };

    images = [1, 2, 3].map(() => `https://picsum.photos/900/500?random&t=${Math.random()}`);

    constructor(private _sharedService: SharedService) {
        console.log('DeviceComponent');
        const header = new MenuHeader();
        header.avatar = '首页';
        header.title = '应用';
        header.currentUrl = '/main/dashboard/applications';
        this._sharedService.emitMenuHeaderChange(header);
    }

    ngOnInit(): void { }
}
