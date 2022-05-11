import { Component, EventEmitter, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Product, ProductConditionList, ProductConditionSelect, ProductForm } from 'src/app/models/product';
import { IExport } from 'src/app/utils/export';
import { ExportEXCEL } from 'src/app/utils/export/excel.export';
import { ExportPDF } from 'src/app/utils/export/pdf.export';
import { ItemService } from '../../service/item.service';

@Component({
  selector: 'app-items-page',
  templateUrl: './items-page.component.html',
  styleUrls: ['./items-page.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class ItemsPageComponent implements OnInit {

  productList: Product[] = []

  selectedProductList: Product[] = []

  displayDialog: boolean = false;

  exporter?: IExport;

  productToUpgrade: Product | null = null;

  exportTypes = [{ name: 'PDF', value: new ExportPDF() }, { name: 'EXCEL', value: new ExportEXCEL() }]

  constructor(private itemService: ItemService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  public showDialog(): void {
    this.displayDialog = true;
  }

  public deleteProduct(id: number): void {
    this.confirmationService.confirm({
      header: "Confirm delete product",
      message: 'Are you sure you want to delete this product?',
      accept: () => {
        this.onAcceptDelete(id)
      },
      reject: () => {
        this.messageService.add({ severity: "info", summary: 'Delete canceled.' })
      }
    })
  }

  public updateProduct(product: Product): void {
    this.productToUpgrade = product
    this.displayDialog = true;
  }


  public exportProducts() {
    this.exporter?.export(this.selectedProductList);
  }

  private onAcceptDelete(id: number) {
    this.itemService.deleteProduct(id).subscribe(resp => {
      this.productList = this.productList.filter(val => val.id != id)
      this.messageService.add({ severity: "success", summary: "Delete product with success" })
    })
  }

  private loadProducts(): void {
    this.itemService.getProducts().subscribe(items => {
      this.productList = items;
    })
  }

  closedDialog(event: Product | null) {
    this.displayDialog = false
    if (event != null)
      this.productList.push(event)
  }

}


