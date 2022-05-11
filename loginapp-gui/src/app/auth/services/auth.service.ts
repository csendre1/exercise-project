import { HttpClient, HttpStatusCode } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Attachment, AttachmentRESP } from 'src/app/models/attachment';
import { AuthUser } from 'src/app/models/authuser';

const BASE_URL = 'http://localhost:8080'

const URL_LOGIN = `${BASE_URL}/login`;

const URL_HOME = `${BASE_URL}/home`;

const URL_REGISTER = `${BASE_URL}/register`;

const URL_IMAGE = `${BASE_URL}/attachments`;

const TOKEN = "token";

const USERNAME = "username"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedIn = new EventEmitter<boolean>();

  constructor(private httpClient: HttpClient) { }

  public login(user: AuthUser): Observable<any> {
    return this.httpClient.post<any>(URL_LOGIN, user);
  }

  public setCredentials(username: string, token: string) {
    sessionStorage.setItem(TOKEN, token);
    sessionStorage.setItem(USERNAME, username);
  }

  public getUsername(): string | null {
    return sessionStorage.getItem(USERNAME);
  }

  public isLoggedIn(): boolean {
    return sessionStorage.getItem(TOKEN) != null;
  }

  public cleanStorage(): void {
    sessionStorage.clear();
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN);
  }

  public callHome(): Observable<string> {
    return this.httpClient.get<string>(URL_HOME)
  }

  public register(newUser: AuthUser, file: File) {
    const formData = new FormData();
    formData.append('authUser', JSON.stringify(newUser));
    formData.append('file', file);
    return this.httpClient.post<any>(URL_REGISTER, formData);
  }

  public getProfilePicture(username: string | null) {
    if (username == null)
      throw Error("Username can't be null!")

    return this.httpClient.get<AttachmentRESP>(`${URL_IMAGE}/${username}`)
  }

  public logout() {
    this.httpClient.get("")
  }
}
