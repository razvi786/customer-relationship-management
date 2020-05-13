import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MenubarComponent } from './menubar/menubar.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
   {
     path:'',
     component:MenubarComponent,
     children:[
      {
        path: '', 
        loadChildren:()=>import('./home/home.module').then(m=>m.HomeComponentModule)
      },
      {
        path:'home',
        loadChildren:()=>import('./home/home.module').then(m=>m.HomeComponentModule)
      },
      {
        path:'cus-details',
        loadChildren:()=>import('./cus-details/cus-details.module').then(m=>m.CusDeatailsComponentModule),
        canActivate:[AuthGuard]
      },
      {
        path:'search-cus',
        loadChildren:()=>import('./search-cus/search-cus.module').then(m=>m.SearchCusComponentModule),
        canActivate:[AuthGuard]
      },
     {
        path:'create-subscription/:customerId',
        loadChildren:()=>import('./create-subscription/create-subscription.module').then(m=>m.CreateSubscriptionComponentModule),
      },
      {
        path:'view-subscription/:customerId',
        loadChildren:()=>import('./view-subscription/view-subscription.module').then(m=>m.ViewSubscriptionComponentModule),
        canActivate:[AuthGuard]
      },
      { 
        path: 'login',  
        loadChildren: () => import('./login/login.module'). then(m => m.LoginComponentModule)
       },
      {
        path:'logout',
        loadChildren:()=>import('./logout/logout.module').then(m=>m.LogoutComponentModule)
      }
     ]
   }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
