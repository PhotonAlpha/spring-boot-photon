import { AppConfigs } from 'src/app/app-config.module';
import { catchError, switchMap, finalize, filter, take } from 'rxjs/operators';
import { Injectable, Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent,
    HttpHeaderResponse, HttpProgressEvent, HttpResponse, HttpErrorResponse, HttpUserEvent } from '@angular/common/http';
import { Observable, BehaviorSubject, throwError, of } from 'rxjs';
import { LoginService } from 'src/app/service/login.service';
import { Router } from '@angular/router';
import { OverviewDialogComponent } from 'src/app/components/dialog/overview-dialog.component';
import { MatDialog } from '@angular/material';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {
    isRefreshingToken: boolean = false;
    tokenSubject: BehaviorSubject<string> = new BehaviorSubject<string>(null);

    constructor(private injector: Injector, private router: Router, private dialog: MatDialog) {
    }

    addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
        console.log('addToken', token);
        let authority = '';
        if (token) {
            authority = `Bearer ${token}`;
            return req.clone({setHeaders: { Authorization: authority }});
        }
        return req.clone();
    }

    // tslint:disable-next-line:max-line-length
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
        let token = localStorage.getItem(AppConfigs.token_key);
        if (this.isRefreshingToken) {
            token = localStorage.getItem(AppConfigs.refresh_token_key);
        }

        return next.handle(this.addToken(req, token))
            .pipe(
                catchError(error => {
                    console.log('error', error);
                    if (error instanceof HttpErrorResponse) {
                        switch ((<HttpErrorResponse>error).status) {
                            case 400:
                                return this.handle400Error(error);
                            case 401:
                                return this.handle401Error(req, next);
                            case 500:
                                return this.handle500Error(error);
                            default:
                                this.handleDefaultError(error);
                        }
                    } else {
                        return throwError(error);
                    }
                })
            );
    }

    handle400Error(error) {
        if (error && error.status === 400 && error.error && error.error.error === 'invalid_grant') {
            return this.logoutUser();
        }
        return throwError(error);
    }

    handle401Error(req: HttpRequest<any>, next: HttpHandler) {
        if (!this.isRefreshingToken) {
            this.isRefreshingToken = true;
            // reset
            this.tokenSubject.next(null);
            const authService = this.injector.get(LoginService);
            return authService.getRefreshToken().pipe(
                switchMap((newToken: string) => {
                    if (newToken) {
                        this.tokenSubject.next(newToken);
                        return next.handle(this.addToken(req, newToken));
                    }
                    // if dont't get token , logout
                    return this.logoutUser();
                }),
                catchError(error => {
                    return this.logoutUser();
                }),
                finalize(() => {
                    this.isRefreshingToken = false;
                })
            );
        } else {
            return this.tokenSubject.pipe(
                filter(token => token != null),
                take(1),
                switchMap(token => {
                    return next.handle(this.addToken(req, token));
                })
            );
        }
    }

    handle500Error(error) {
        this.dialog.open(OverviewDialogComponent, {
            disableClose: true,
            'data': {
                title: 'Login Error!',
                message: [`${error.status} - ${error.error.error} - ${error.error.message}`],
                conform: 'Confirm' ,
                cancel: 'cancel'
            }
        });
        return throwError(error);
    }

    handleDefaultError(error) {
        // if (error) {
        //     this.router.navigate(['/login']);
        // }
        this.dialog.open(OverviewDialogComponent, {
            disableClose: true,
            'data': {
                title: 'Login Error!',
                message: [`${error.status} - ${error.error.error} - ${error.error.message}`],
                conform: 'Confirm' ,
                cancel: 'cancel'
            }
        }).afterClosed().subscribe(temp => {
            localStorage.clear();
            this.dialog.closeAll();
            this.router.navigate(['login']);
        });
    }

    logoutUser() {
        localStorage.removeItem(AppConfigs.token_key);
        this.router.navigate(['/login']);
        return throwError('');
    }
}
