import { Workbook, Worksheet } from "exceljs";
import { Product } from "src/app/models/product";
import { IExport } from "../export";
import * as fs from 'file-saver'
import { DatePipe, formatDate } from "@angular/common";


export class ExportEXCEL extends IExport {


  public export(productList: Product[]): void {
    let workbook = new Workbook();

    let worksheet = workbook.addWorksheet('Product list');

    let headerRow = worksheet.addRow(this.HEADERS);

    headerRow.eachCell((cell, rowNumber) => {
      cell.font = {
        name: 'Comic San Ms',
        family: 4,
        size: 14,
        bold: true
      }
      cell.alignment = {
        vertical: 'middle',
        horizontal: 'center'
      }
    })

    this.setColumnsWidth(worksheet);

    this.createRows(productList, worksheet);

    this.saveFile(workbook);
  }

  private createRows(productList: Product[], worksheet: Worksheet): void {
    productList.forEach(prod => {
      worksheet.addRow([prod.productName, prod.serialNumber, prod.description, prod.itemCondition, this.createDate(prod.addedTime)])
    })
  }

  private createDate(date: Date): string {
    return formatDate(date, 'shortDate', 'en-US')
  }

  private saveFile(workbook: Workbook): void {
    workbook.xlsx.writeBuffer().then((data) => {
      let blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      fs.saveAs(blob, 'product_list -' + new Date().valueOf() + '.xlsx');
    });
  }

  private setColumnsWidth(worksheet: Worksheet) {
    for (let i = 1; i <= this.HEADERS.length; ++i) {
      worksheet.getColumn(i).width = 30
    }
  }

}
