import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  username: string | null = '';

  image: string = ''

  imageNotNull: boolean = false;

  languages = [{ label: "English", value: 'en-US' }, { label: "Romanian", value: "ro" }]

  constructor(private authService: AuthService,
    private router: Router,
    private translateService: TranslateService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.retrieveProfilePicture();
  }

  public changeLanguage(event: any) {
    this.translateService.setDefaultLang(event.value)
  }

  private retrieveProfilePicture() {
    this.authService.getProfilePicture(this.authService.getUsername()).subscribe(resp => {
      if (resp.data != null) {
        this.image = `data:${resp.type};base64,${resp.data}`;
        this.checkImageIsNull();
      }
    }, err => {
      //*If error is occurred when retrieving profile picture than set it ot the default
      this.checkImageIsNull();
    });
  }

  public openUserMenu() {
    // TODO : to implement method
  }

  private checkImageIsNull() {
    this.imageNotNull = !!this.image;
  }

  public logout() {
    this.authService.cleanStorage();
    this.authService.logout();
    this.authService.loggedIn.emit(false)
    this.router.navigate(['login'])
  }

}
