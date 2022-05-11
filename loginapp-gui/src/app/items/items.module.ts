import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ItemsRoutingModule } from './items-routing.module';
import { ItemsPageComponent } from './components/items-page/items-page.component';
import { DesignModule } from '../design/design.module';
import { AddNewItemComponent } from './components/add-new-item/add-new-item.component';
import { TranslateModule } from '@ngx-translate/core';


@NgModule({
  declarations: [
    ItemsPageComponent,
    AddNewItemComponent
  ],
  imports: [
    CommonModule,
    ItemsRoutingModule,
    DesignModule,
    TranslateModule
  ]
})
export class ItemsModule { }
