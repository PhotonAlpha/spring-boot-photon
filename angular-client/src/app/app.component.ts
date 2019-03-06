import { Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'myapp';
  constructor(private router: Router) {

  }
  ngOnInit() {
    // this.router.navigate(['/welcome']);
  }

  ngOnDestroy() {
    console.log('destory');
  }
}
