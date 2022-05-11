import { NgModule } from '@angular/core';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { MessagesModule } from 'primeng/messages';
import { FileUploadModule } from 'primeng/fileupload';
import { ToolbarModule } from 'primeng/toolbar';
import { AvatarModule } from 'primeng/avatar';
import { SidebarModule } from 'primeng/sidebar';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { MultiSelectModule } from 'primeng/multiselect';
import { DropdownModule } from 'primeng/dropdown';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { TooltipModule } from 'primeng/tooltip';

@NgModule({
  declarations: [],
  exports: [
    ToastModule,
    InputTextModule,
    PasswordModule,
    ButtonModule,
    MessagesModule,
    FileUploadModule,
    ToolbarModule,
    AvatarModule,
    SidebarModule,
    TableModule,
    DialogModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextareaModule,
    MultiSelectModule,
    DropdownModule,
    ConfirmDialogModule,
    TooltipModule
  ]
})
export class DesignModule { }
