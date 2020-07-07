import { Component, OnInit } from '@angular/core';
import { TokenService } from '../services/token.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  logoIconSrc: string = 'assets/images/main_logo.png';
  searchIconSrc: string = 'assets/images/icon_search.png';
  mypageIconSrc: string = 'assets/images/icon_mypage.png';
  helpIconSrc: string = 'assets/images/icon_help.png';
  logoutIconSrc: string = 'assets/images/icon_logout.png';

  private routeSub: Subscription;
  currentReviewId: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.routeSub = this.activatedRoute.params.subscribe(params => {
      this.currentReviewId = params['reviewId'];
    });
  }

  logout() {
    this.tokenService.logout();
    this.router.navigateByUrl('/blank', {
      skipLocationChange: true,
    })
      .then(
        () => {
          this.router.navigateByUrl(`/review/${this.currentReviewId}`);
        }
      )
  }

  onClickLogoutButton() {
    this.logout();
  }

}
