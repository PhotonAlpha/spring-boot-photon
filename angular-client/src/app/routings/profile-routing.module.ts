import { ProfileViewComponent } from './../components/main/profile/profile-view/profile-view.component';
import { ProfilePortalComponent } from './../components/main/profile/profile-portal.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PortalDeactiveGuard } from 'src/app/utils/guard/deactive-guard.service';
import { ModifyPwdComponent } from '../components/main/profile/modify-pwd/modify-pwd.component';
import { ModifyNoComponent } from '../components/main/profile/modify-no/modify-no.component';
import { WebsocketLoginComponent } from '../components/main/profile/websocket-login/websocket-login.component';

const routes: Routes = [
    {
        path: '', component: ProfilePortalComponent,
        children: [
        { path: '', redirectTo: 'view', pathMatch: 'full' },
        { path: 'view', component: ProfileViewComponent},
        { path: 'phone', component: ModifyNoComponent},
        { path: 'secure', component: ModifyPwdComponent, canDeactivate: [PortalDeactiveGuard]},
        { path: 'ws', component: WebsocketLoginComponent },
        ]
    }
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ],
})
export class ProfileRoutingModule {}
