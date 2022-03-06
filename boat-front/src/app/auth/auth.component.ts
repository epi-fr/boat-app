import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {NgForm} from "@angular/forms";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-login',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private notifier: NotifierService) {
  }

  ngOnInit(): void {
    sessionStorage.setItem('token', '');
  }

  login(f: NgForm) {
    let value = f.value;
    let url = 'http://localhost:8080/login';
    this.http.post<Observable<boolean>>(url, {
      userName: value.username,
      password: value.password
    }).subscribe({
      next: isValid => {
        if (isValid) {
          sessionStorage.setItem(
            'token',
            btoa(value.username + ':' + value.password)
          );
          this.router.navigate(['']);
        } else {

          this.notifier.notify('error', 'Authentication failed.');
        }
      },
      error: err => {
        console.error(err);
        this.notifier.notify('error', 'Authentication failed.');
      }
    });
  }
}
