import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ProductOrder } from 'src/app/models/pagination';
import { Product } from 'src/app/models/product';
import { ItemService } from '../../service/item.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.scss']
})
export class FilterComponent implements OnInit {

  @Output() filterValue: EventEmitter<any> = new EventEmitter<string>()

  filterValueField: string = ''

  order: ProductOrder = ProductOrder.ASC

  isAscending: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  public filter(): void {
    console.log('test')
    this.filterValue.emit({ value: this.filterValueField, order: this.order })
  }

  public changeOrder() {
    if (this.isAscending) {
      this.order = ProductOrder.DESC
      this.isAscending = false
    }
    else {
      this.order = ProductOrder.ASC
      this.isAscending = true
    }

    this.filter();
  }
}
