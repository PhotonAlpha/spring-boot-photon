import { ProfilePortalComponent } from './../components/main/profile/profile-portal.component';
import { NgModule } from '@angular/core';
import { ProfileRoutingModule } from './profile-routing.module';
import { ShareModule } from 'src/app/routings/share.module';
import { CommonModule } from '@angular/common';
import { ModifyPwdComponent } from '../components/main/profile/modify-pwd/modify-pwd.component';
import { ModifyNoComponent } from '../components/main/profile/modify-no/modify-no.component';
import { ProfileViewComponent } from '../components/main/profile/profile-view/profile-view.component';
import { WebsocketLoginComponent } from '../components/main/profile/websocket-login/websocket-login.component';

@NgModule({
    declarations: [
        ProfilePortalComponent,
        ModifyPwdComponent,
        ModifyNoComponent,
        ProfileViewComponent,
        WebsocketLoginComponent,
    ],
    imports: [
        ShareModule,
        CommonModule,
        ProfileRoutingModule
    ],
})
export class ProfileModule {}
