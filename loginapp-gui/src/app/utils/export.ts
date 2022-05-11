import { Product } from "src/app/models/product";

export abstract class IExport {

  HEADERS: string[] = ["ITEM NAME", "SERIAL NUMBER", "DESCRIPTION", "ITEM CONDITION", "ADDED TIME"];

  abstract export(productList: Product[]): void;
}
