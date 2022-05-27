import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Attachment } from 'src/app/models/attachment';
import { GlobalMessageService } from 'src/app/utils/service/global-message.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  providers: [MessageService]
})
export class RegistrationComponent implements OnInit {

  newUserForms !: FormGroup

  newAttachment?: Attachment

  constructor(private authService: AuthService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: GlobalMessageService) { }

  ngOnInit(): void {
    this.buildForm();
  }

  public register(): void {
    if (this.newUserForms.valid) {
      this.authService.register(this.newUserForms.value, this.newAttachment?.data!).subscribe(resp => {
        this.messageService.success("Registration was successful.")
        this.router.navigate(['/login'])
      });
    }
    else {
      this.messageService.error("The fields are not completed correctly.")
    }
  }

  public cancel(): void {
    this.router.navigate(['/login']);
  }

  public onSelect(event: any): void {
    let profilePicture: File = event.currentFiles[0]
    this.newAttachment = { name: profilePicture.name, type: profilePicture.type, data: profilePicture }
  }

  public isErrorPresent(field: string): boolean {
    return this.newUserForms.get(field)?.errors?.['required'] && this.newUserForms.controls[field].touched;
  }

  private buildForm(): void {
    this.newUserForms = this.formBuilder.group({
      name: [''],
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

}
