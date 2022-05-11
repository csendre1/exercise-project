import jsPDF from "jspdf";
import autoTable from "jspdf-autotable";
import { Product } from "src/app/models/product";
import { IExport } from "../export";


export class ExportPDF extends IExport {

  constructor() { super() }

  public export(productList: Product[]): void {
    const doc = new jsPDF();
    doc.text("Product list", 50, 40);
    autoTable(doc, {
      head: [this.HEADERS],
      body: this.mapToPrintList(productList)
    })
    doc.save("table.pdf");
  }

  private mapToPrintList(productList: Product[]): any[] {
    let newList: any[] = []
    productList.map(prod => {
      let temp = [prod.productName, prod.serialNumber, prod.description, prod.itemCondition]
      newList.push(temp);
    })

    return newList;
  }

}
