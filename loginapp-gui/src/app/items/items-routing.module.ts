import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ItemsPageComponent } from './components/items-page/items-page.component';

const routes: Routes = [
  {
    path: '',
    component: ItemsPageComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemsRoutingModule { }
