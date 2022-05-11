import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ItemsRoutingModule } from './items-routing.module';
import { ItemsPageComponent } from './components/items-page/items-page.component';
import { DesignModule } from '../design/design.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddNewItemComponent } from './components/add-new-item/add-new-item.component';


@NgModule({
  declarations: [
    ItemsPageComponent,
    AddNewItemComponent
  ],
  imports: [
    CommonModule,
    ItemsRoutingModule,
    DesignModule
  ]
})
export class ItemsModule { }
