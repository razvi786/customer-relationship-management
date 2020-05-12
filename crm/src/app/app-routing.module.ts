import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CusDetailsComponent } from './cus-details/cus-details.component';
import { AdminAfterLoginComponent } from './admin-after-login/admin-after-login.component';
import { HomeComponent } from './home/home.component';
import { LogoutComponent } from './logout/logout.component';
import { SearchCusComponent } from './search-cus/search-cus.component';
import { CreateSubscriptionComponent } from './create-subscription/create-subscription.component';
import { ViewSubscriptionComponent } from './view-subscription/view-subscription.component';
import { MenubarComponent } from './menubar/menubar.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  /*{path:'login',component: LoginComponent },
  {path: 'cus-details', component: CusDetailsComponent },
  {path:'afterlogin',component: AdminAfterLoginComponent },
  {path: 'home', component: HomeComponent},
   {path: 'logout', component: LogoutComponent},
   {path: 'search-cus', component: SearchCusComponent},
   {path: 'create-subscription', component: CreateSubscriptionComponent},
   {path: 'view-subscription',component:ViewSubscriptionComponent}*/

   {
     path:'',
     component:MenubarComponent,
     children:[
      {
        path: '', 
        redirectTo: '/login',
        pathMatch: 'full'
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
      /*{
        path:'create-subscription',
        loadChildren:()=>import('./create-subscription/create-subscription.module').then(m=>m.CreateSubscriptionComponentModule)
      },*/
      {
        path:'view-subscription/:customerId',
        loadChildren:()=>import('./view-subscription/view-subscription.module').then(m=>m.ViewSubscriptionComponentModule),
        canActivate:[AuthGuard]
      },
      {
        path:'logout',
        loadChildren:()=>import('./logout/logout.module').then(m=>m.LogoutComponentModule)
      }
     ]
   },
   { 
    path: 'login',  
    loadChildren: () => import('./login/login.module'). then(m => m.LoginComponentModule)
   },
   { 
      path: '', 
      redirectTo: '/login',
      pathMatch: 'full' 
    },
   

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
