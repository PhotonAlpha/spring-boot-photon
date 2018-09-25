import { AppConfigs } from './../app-config.module';
import { MessageService } from '../utils/injector/message.service';
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Pagination } from '../models/applications';
import { Users, Tokens } from 'src/app/models/users';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    loading = false;
    totalCount = 0;
    currPage = 1;
    pageSize = 5;
    pagination: Pagination;

    user: Users;

    constructor(private messageSvc: MessageService, private loginService: LoginService,
        private router: Router) {
        this.user = new Users();
    }

    ngOnInit(): void { }

    send() {
        let isSucess = false;
        this.loginService.getUserinfo().subscribe(repo => {
            isSucess = true;
            console.log('getUserinfo', repo);
        },
        (error) => this.loginService.sendMessage(error),
        () => {
            console.log('final', isSucess);
        });
    }
    clear() {
        this.messageSvc.clearMessage();
    }


    search(): void {
        this.loading = true;
        this.pagination = new Pagination();
        console.log('currPage' + this.currPage);
        this.pagination.pageSize = this.pageSize;
        console.log(this.loading);
    }

    prevPage() {
        this.currPage--;
        this.search();
    }

    nextPage() {
        this.currPage++;
        this.search();
    }

    goToPage(n: number) {
        this.currPage = n;
        this.search();
    }

    submit() {
        console.log(this.user);

        this.loginService.getAuthToken(this.user).subscribe((token: Tokens) => {
            localStorage.setItem(AppConfigs.token_key, token.authToken);
            console.log(token);
            this.router.navigate(['/main/header']);
        });
    }
}
