import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root',
})
export class GlobalMessageService {

  constructor(private messageService: MessageService) { }

  public success(detailMsg: string, summary?: string): void {
    this.messageService.add({ severity: 'success', summary: summary || "Success", detail: detailMsg })
  }

  public error(detailMsg: string, summary?: string): void {
    this.messageService.add({ severity: 'success', summary: summary || "Error", detail: detailMsg })
  }

  public warning(detailMsg: string, summary?: string): void {
    this.messageService.add({ severity: 'success', summary: summary || "Warning", detail: detailMsg })
  }

  public info(msg: string, summary?: string): void {
    this.messageService.add({ severity: 'success', summary: summary || "Info", detail: msg })
  }
}
