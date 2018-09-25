import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginService } from './service/login.service';
import { GlobalErrorsHandler } from './utils/injector/errors.handler';
import { RouterHashFix } from './utils/router/router.hash.fix';
import { LoginComponent } from './login/login.component';
import { MessageSubscriptionComponent } from './message/message-subscription.component';
import { PaginationComponent } from './utils/pagination/pagination.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HeaderComponent } from './components/header/herder.component';
import { MainPortalComponent } from './components/main/main-portal.component';
import { MatButtonModule, MatCheckboxModule, MatRadioModule,
  MatFormFieldModule, MatInputModule, MatOptionModule, MatSelectModule, MatIconModule, MatDialogModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OverviewDialogComponent } from 'src/app/components/dialog/overview-dialog.component';
import { PortalDeactiveGuard } from 'src/app/utils/guard/deactive-guard.service';
import { HttpInterceptorService } from 'src/app/utils/injector/request-interceptor';
import { Tab } from 'src/app/utils/tabs/tab';
import { Tabs } from 'src/app/utils/tabs/tabs';

const matModules = [
  MatButtonModule, MatCheckboxModule, MatRadioModule, MatFormFieldModule, MatInputModule,
  MatOptionModule, MatSelectModule, MatIconModule, MatDialogModule
];

@NgModule({
  declarations: [
    AppComponent,
    RouterHashFix,
    PaginationComponent,
    LoginComponent,
    MessageSubscriptionComponent,
    MainPortalComponent,
    HeaderComponent,
    OverviewDialogComponent,
    Tab,
    Tabs
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    matModules
  ],
  entryComponents: [
    OverviewDialogComponent
  ],
  providers: [
    LoginService,
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
