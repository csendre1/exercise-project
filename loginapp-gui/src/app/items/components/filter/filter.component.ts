import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ItemService } from '../../service/item.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.scss']
})
export class FilterComponent implements OnInit {

  @Input() column: string = ''

  @Output() filterValue: EventEmitter<string> = new EventEmitter<string>()

  filterValueField: string = ''

  constructor(private productService: ItemService) { }

  ngOnInit(): void {
  }

  public filter(filterValue: any): void {
    this.filterValue.emit(this.filterValueField)
  }
}
