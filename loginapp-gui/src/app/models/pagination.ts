export interface Pagination {
  startingPosition: number,
  numberOfResults: number,
  value: string
  column: string
  order: ProductOrder
}

export enum ProductOrder {
  ASC, DESC
}
