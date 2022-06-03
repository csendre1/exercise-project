import { Component, EventEmitter, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ProductOrder } from 'src/app/models/pagination';
import { Product, ProductCondition, ProductConditionList, ProductConditionSelect, ProductForm } from 'src/app/models/product';
import { IExport } from 'src/app/utils/export';
import { ExportEXCEL } from 'src/app/utils/export/excel.export';
import { ExportPDF } from 'src/app/utils/export/pdf.export';
import { GlobalMessageService } from 'src/app/utils/service/global-message.service';
import { ItemService } from '../../service/item.service';

@Component({
  selector: 'app-items-page',
  templateUrl: './items-page.component.html',
  styleUrls: ['./items-page.component.scss'],
  providers: [ConfirmationService]
})
export class ItemsPageComponent implements OnInit {

  productList: Product[] = []

  itemConditionList: ProductConditionSelect[] = ProductConditionList;

  filteredItemCondition: ProductCondition | null = null;

  selectedProductList: Product[] = []

  displayDialog: boolean = false;

  exporter?: IExport;

  doFilter: EventEmitter<string> = new EventEmitter()

  refresh: EventEmitter<boolean> = new EventEmitter()

  filteredValue: string = ''

  column: string = ''

  order: ProductOrder = ProductOrder.ASC

  productToUpgrade: Product | null = null;

  exportTypes = [{ name: 'PDF', value: new ExportPDF() }, { name: 'EXCEL', value: new ExportEXCEL() }]

  constructor(private itemService: ItemService,
    private messageService: GlobalMessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
  }

  public openAddDialog(): void {
    this.productToUpgrade = null;
    this.displayDialog = true;
  }

  public closedDialog(event: Product | null) {
    this.displayDialog = false
    if (event != null)
      this.refreshData();
  }

  public refreshData() {
    this.refresh.emit(true)
  }

  public deleteProduct(id: number): void {
    this.confirmationService.confirm({
      header: "Confirm delete product",
      message: 'Are you sure you want to delete this product?',
      accept: () => {
        this.onAcceptDelete(id)
      },
      reject: () => {
        this.messageService.info("Delete canceled")
      }
    })
  }

  public filterProducts(data: any, column: string): void {
    this.filteredValue = data.value;
    this.column = column
    this.order = data.order
    this.doFilter.emit(this.filteredValue)
  }

  public pageLoaded(productList: any) {
    this.productList = productList
  }

  public updateProduct(product: Product): void {
    this.productToUpgrade = product
    this.displayDialog = true;
  }

  public filter(value: string) {
    this.filterProducts(value, "itemCondition");
  }

  public exportProducts() {
    this.exporter?.export(this.selectedProductList);
  }

  private onAcceptDelete(id: number) {
    this.itemService.deleteProduct(id).subscribe(() => {
      this.messageService.success("Product was deleted")
      this.refresh.emit(true)
    })
  }

}


