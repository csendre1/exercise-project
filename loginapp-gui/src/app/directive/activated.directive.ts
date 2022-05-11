import { Directive, ElementRef, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';

@Directive({
  selector: '[appActivated]'
})
export class ActivatedDirective {

  @Input() appActivated: string = '';

  @Output() opened: EventEmitter<string> = new EventEmitter();

  constructor(private element: ElementRef) {
  }

  @HostListener('click')
  public onClick() {
    this.element.nativeElement.style.background = '#ccffcc'
    this.opened.emit(this.appActivated)

  }
}
