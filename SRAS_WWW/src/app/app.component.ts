import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';

  constructor(){}

  onDeactivate() {
    document.body.scrollTop = 0; // 라우팅 시 스크롤 맨 위로 올라가도록 설정
  }
}
