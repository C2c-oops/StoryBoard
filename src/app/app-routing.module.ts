import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HompPageComponent } from './homp-page/homp-page.component';

const routes: Routes = [
  {
    path : '', component : HompPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
