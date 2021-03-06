import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ContentComponent} from "./content/content.component";
import {PayComponent} from "./pay/pay.component";
import {BlockComponent} from "./block/block.component";
import {AdminComponent} from "./admin/admin.component";

const routes: Routes = [
  {path : "", component: ContentComponent},
  {path : "main", component: ContentComponent},
  {path : "pay", component: PayComponent},
  {path : "fill", component: ContentComponent},
  {path : "block", component: BlockComponent},
  {path : "unblock", component: AdminComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
