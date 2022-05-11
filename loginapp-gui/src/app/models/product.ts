export interface Product {
  id: number
  productName: string
  serialNumber: string
  description: string
  itemCondition: ProductCondition
  addedTime: Date
}

export enum ProductCondition {
  NEW, GOOD, BAD, BROKEN
}

export interface ProductConditionSelect {
  name: string
  value: ProductCondition
}

export const ProductConditionList: ProductConditionSelect[] = [
  { name: "NEW", value: ProductCondition.NEW },
  { name: "GOOD", value: ProductCondition.GOOD },
  { name: "BAD", value: ProductCondition.BAD },
  { name: "BROKEN", value: ProductCondition.BROKEN }
]

export interface ProductForm {
  productName: string
  serialNumber: string
  description: string
  itemCondition: ProductConditionSelect
}
