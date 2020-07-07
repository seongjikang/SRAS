import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from 'app/components/main/main.component';
import { LoginComponent } from 'app/components/login/login.component';
import { DetailComponent } from 'app/components/detail/detail.component';
import { SearchComponent } from 'app/components/search/search.component';

import { AuthGuard } from '../components/shared/auth.guard';
import { BlankComponent } from 'app/components/blank/blank.component';
import { NotYetComponent } from 'app/components/not-yet/not-yet.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'review',
    pathMatch: 'full',
    canActivate: [AuthGuard]// authguard를 적용하여 로그인을 해야만 이 페이지에 접근할 수 있음
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'review',
    component: MainComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'search', 
    component: SearchComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'review/:reviewId',
    component: DetailComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'stat', 
    component: NotYetComponent, // TODO : 주간통계 컴포넌트 작성 후 변경 issue #25
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'manual', 
    component: NotYetComponent, // TODO : 대응매뉴얼 컴포넌트 작성 후 변경 issue #26
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'blank',
    component: BlankComponent, // 답글 post 한 후 reload 할 때 필요한 빈 컴포넌트
    pathMatch: 'full',
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, {useHash: true})
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
