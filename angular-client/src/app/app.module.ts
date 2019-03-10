import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginService } from './service/login.service';
import { RouterHashFix } from './utils/router/router.hash.fix';
import { PaginationComponent } from './utils/pagination/pagination.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { OverviewDialogComponent } from 'src/app/components/dialog/overview-dialog.component';
import { PortalDeactiveGuard } from 'src/app/utils/guard/deactive-guard.service';
import { HttpInterceptorService } from 'src/app/utils/injector/request-interceptor';
import { Tab } from 'src/app/utils/tabs/tab';
import { Tabs } from 'src/app/utils/tabs/tabs';
import { LoginComponent } from './components/login/login.component';
import { MessageSubscriptionComponent } from './components/message/message-subscription.component';
import { MainComponent } from 'src/app/components/main/main.component';
import { ShareModule } from './routings/share.module';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SharedService } from './service/shared.service';



@NgModule({
  declarations: [
    AppComponent,

    RouterHashFix,
    PaginationComponent,
    LoginComponent,
    NotFoundComponent,
    MessageSubscriptionComponent,
    MainComponent,
    OverviewDialogComponent,
    Tab,
    Tabs
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ShareModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  entryComponents: [
    OverviewDialogComponent
  ],
  providers: [
    LoginService,
    SharedService,
    PortalDeactiveGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
