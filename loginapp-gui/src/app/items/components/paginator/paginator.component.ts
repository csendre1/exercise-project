import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ItemService } from '../../service/item.service';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.scss']
})
export class PaginatorComponent implements OnInit {

  @Input() itemPerPageList: number[] = []

  @Output() pageLoaded: EventEmitter<Product[]> = new EventEmitter<Product[]>();

  pages: number[] = [1, 2, 3, 4]

  private currentPage: number = 0;

  private lastPage: number = 0;

  private itemPerPage = 3;

  constructor(private productService: ItemService) { }

  ngOnInit(): void {
    //TODO: get last page
    this.goToPage()
  }

  private goToPage() {
    //TODO: get the products of the page from the backend
    this.productService.paging(this.currentPage, this.itemPerPage).subscribe(resp => {
      console.log(resp)
      this.pageLoaded.emit(resp);
    })
  }

  public nextButton() {
    this.currentPage += 1;
    this.goToPage()
  }

  public prevButton() {
    this.currentPage -= 1;
    this.goToPage()
  }


}
