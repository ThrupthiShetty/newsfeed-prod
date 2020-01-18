import { Component, OnInit } from '@angular/core';
import { NewsfeedService } from 'src/app/services/newsfeed.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss', './designmodo.scss']
})
export class HomeComponent implements OnInit {
  topnews: any;
  arrayLength: any;
  search: any;
  constructor(private newService: NewsfeedService) { }

  ngOnInit() {
    this.newService.getTopNews()
      .subscribe(news => {
        this.topnews = news;
   //     this.arrayLength  = news.articles.length;
        console.log(this.topnews)
      });
  }

}
