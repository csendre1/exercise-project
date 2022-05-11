import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.retrieveProfilePicture();
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
