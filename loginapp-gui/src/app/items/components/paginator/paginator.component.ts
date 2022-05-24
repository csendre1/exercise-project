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

  @Input() column: string = ''

  @Input() filter: EventEmitter<string> = new EventEmitter()

  @Output() pageLoaded: EventEmitter<Product[]> = new EventEmitter<Product[]>();

  filterValue: string = ''

  pages: number[] = []

  isFirstPage: boolean = true;

  isLastPage: boolean = false;

  currentPage: number = 0;

  private numberOfProducts: number = 0;

  private itemPerPage = 3;

  constructor(private productService: ItemService) { }

  ngOnInit(): void {
    this.initializeNumberOfPages();
    this.loadPage()
    this.filter.subscribe(val => {
      console.log(val)
      this.filterValue = val;
      this.loadPage();
    })
  }

  public navigateOnPages(toPage: number) {
    this.currentPage = toPage;
    this.checkFinalPages()
    this.loadPage()
  }

  private initializeNumberOfPages() {
    this.productService.getNumberOfProducts().subscribe(num => {
      this.numberOfProducts = num;
      this.calculateNumberOfPages()
    }, err => {
      console.log(err)
    })
  }

  private loadPage() {
    this.productService.filter(this.currentPage, this.itemPerPage, this.filterValue, this.column).subscribe(resp => {
      this.pageLoaded.emit(resp);
    })
  }

  private calculateNumberOfPages() {
    let a = this.numberOfProducts / this.itemPerPage
    if (this.numberOfProducts % this.itemPerPage != 0)
      a += 1
    for (let i = 1; i <= a; ++i)
      this.pages.push(i)
  }

  private checkFinalPages(): void {
    this.isFirstPage = this.checkIfFirstPage();
    this.isLastPage = this.checkIfLastPage();
  }

  private checkIfLastPage(): boolean {
    return this.currentPage + 1 == this.pages[this.pages.length - 1];
  }

  private checkIfFirstPage(): boolean {
    return this.currentPage == 0;
  }

}
