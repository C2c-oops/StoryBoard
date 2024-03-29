import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HompPageComponent } from './homp-page/homp-page.component';
import { AuthGuard } from './user/auth.guard';

const routes: Routes = [
  {
    path : '', 
    component : HompPageComponent
  },
  {
    path : 'login', 
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  },
  {
    path : 'kanban', 
    loadChildren: () => import('./kanban/kanban.module').then(m => m.KanbanModule),
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{
    initialNavigation: 'enabled'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
