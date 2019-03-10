import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { MenuHeader } from '../models/menu-header';

@Injectable()
export class SharedService {
    private emitChangeSource = new Subject<MenuHeader>();
    changeEmitted$ = this.emitChangeSource.asObservable();

    emitMenuHeaderChange(change: MenuHeader) {
        this.emitChangeSource.next(change);
    }
}
