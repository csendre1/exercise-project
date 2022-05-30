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

  @Input() refresh: EventEmitter<boolean> = new EventEmitter();

  @Output() pageLoaded: EventEmitter<Product[]> = new EventEmitter<Product[]>();

  pages: number[] = []

  isFirstPage: boolean = true;

  isLastPage: boolean = false;

  currentPage: number = 0;

  numberOfProducts: number = 0;

  itemPerPage = 3;

  private filterValue: string = ''

  constructor(private productService: ItemService) { }

  ngOnInit(): void {
    this.initData();
    this.refreshData();
    this.startFiltering();
  }

  public navigateOnPages(toPage: number) {
    this.currentPage = toPage;
    this.checkFinalPages()
    this.loadPage()
  }

  public displayedItems(): number {
    const num = (this.currentPage + 1) * this.itemPerPage;
    if (num <= this.numberOfProducts)
      return num

    return this.numberOfProducts

  }

  private initData() {
    this.initializeNumberOfPages();
    this.loadPage()
  }

  private refreshData() {
    this.refresh.subscribe(resp => {
      this.initData();
    })
  }

  private startFiltering() {
    this.filter.subscribe(val => {
      this.filterValue = val;
      this.loadPage();
    })
  }

  private initializeNumberOfPages() {
    this.productService.getNumberOfProducts().subscribe(num => {
      this.numberOfProducts = num;
      this.calculateNumberOfPages()
    }, console.error)
  }

  private loadPage() {
    this.productService.filter(this.currentPage, this.itemPerPage, this.filterValue, this.column).subscribe(resp => {
      this.pageLoaded.emit(resp);
    })
  }

  private calculateNumberOfPages() {
    this.pages.length = 0
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
