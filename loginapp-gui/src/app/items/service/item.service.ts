import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from 'src/app/models/product';

const PRODUCT_URL = 'http://localhost:8080/product';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private httpClient: HttpClient) { }

  public getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(PRODUCT_URL);
  }

  public addNewProduct(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(PRODUCT_URL, product);
  }

  public deleteProduct(id: number): Observable<string> {
    return this.httpClient.delete<string>(`${PRODUCT_URL}/${id}`)
  }

  public updateProduct(product: Product): Observable<Product> {
    return this.httpClient.put<Product>(PRODUCT_URL, product);
  }

  public paging(pageNum: number, itemsPerPage: number) {
    return this.httpClient.get<Product[]>(`${PRODUCT_URL}/paging`, {
      params: { page: pageNum, maxNum: itemsPerPage }
    })
  }
}
