import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/service/shared.service';
import { MenuHeader } from 'src/app/models/menu-header';

@Component({
  selector: 'app-application-view',
  templateUrl: './application-view.component.html',
  styleUrls: ['./application-view.component.scss']
})
export class ApplicationViewComponent implements OnInit {

  constructor(private _sharedService: SharedService) {
    console.log('DeviceComponent');
    const header = new MenuHeader();
    header.avatar = '返回';
    header.title = '我的应用';
    header.currentUrl = '/main/dashboard/application/add';
    this._sharedService.emitMenuHeaderChange(header);
}
  ngOnInit() {
  }

}
