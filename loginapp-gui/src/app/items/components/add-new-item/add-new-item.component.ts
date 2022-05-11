import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Product, ProductConditionList, ProductConditionSelect, ProductForm } from 'src/app/models/product';
import { ItemService } from '../../service/item.service';

const NEW_PRODUCT_HEADER: string = "Add new product";

const UPDATE_HEADER: string = "Update product"

@Component({
  selector: 'app-add-new-item',
  templateUrl: './add-new-item.component.html',
  styleUrls: ['./add-new-item.component.scss'],
  providers: [MessageService]
})
export class AddNewItemComponent implements OnInit {

  itemConditionList: ProductConditionSelect[] = ProductConditionList;

  dialogHeader: string = NEW_PRODUCT_HEADER

  @Input() opened: boolean = false;

  @Output() dialogClosed: EventEmitter<Product | null> = new EventEmitter();

  @Input() product: Product | null = null

  newProductForm !: FormGroup

  addClicked: boolean = true;

  constructor(private formBuilder: FormBuilder, private itemService: ItemService, private messageService: MessageService) { }

  ngOnInit(): void { }

  ngOnChanges(): void {
    if (this.product) {
      this.buildForm();
      this.newProductForm.setValue(this.createProductForm())
    }
    else {
      this.buildForm();
    }
  }

  public confirmUpdate(): void {
    if (this.newProductForm.valid) {
      let updateProduct: Product = this.newProductForm.value

      this.product!.productName = updateProduct.productName
      this.product!.serialNumber = updateProduct.serialNumber
      this.product!.description = updateProduct.description
      this.product!.itemCondition = updateProduct.itemCondition

      this.itemService.updateProduct(this.product!).subscribe(updateProduct => {
        this.messageService.add({ severity: 'success', summary: 'Successfully updated', detail: 'The new product is updated.' })
        this.product!.itemCondition = updateProduct.itemCondition
        this.closeDialog();
      });
    }
  }

  public confirm() {
    if (this.product)
      this.confirmUpdate();
    else
      this.addProduct()
  }

  public closeDialog(): void {
    this.opened = false;
    this.dialogClosed.emit(null)
  }

  public addProduct(): void {
    if (this.newProductForm.valid) {
      this.itemService.addNewProduct(this.newProductForm.value).subscribe(addedProduct => {
        this.messageService.add({ severity: 'success', summary: 'Successfully saved', detail: 'The new product saved.' })
        this.dialogClosed.emit(addedProduct)
      });
    }
    else {
      this.messageService.add({ severity: 'error', summary: 'Invalid form', detail: 'The new product info is not completed correctly.' })
    }
  }

  private buildForm() {
    this.newProductForm = this.formBuilder.group({
      productName: ['', Validators.required],
      serialNumber: ['', Validators.pattern('^[0-9]{11}$')],
      description: [''],
      itemCondition: ['']
    })
  }

  private createProductForm(): ProductForm {
    return {
      productName: this.product?.productName!,
      description: this.product?.description!,
      serialNumber: this.product?.serialNumber!,
      itemCondition: { name: this.product?.itemCondition.toString()!, value: this.product?.itemCondition.valueOf()! }
    }

  }

}


