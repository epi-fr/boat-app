import {Component, OnInit} from '@angular/core';
import {BoatService} from "../services/boat.service";

@Component({
  selector: 'app-boats-list',
  templateUrl: './boats-list.component.html',
  styleUrls: ['./boats-list.component.css']
})
export class BoatsListComponent implements OnInit {

  boats: any;

  constructor(private boatService: BoatService) {
  }

  ngOnInit(): void {
    this.retrieveBoats();
  }

  retrieveBoats(): void {
    this.boatService.getAll()
      .subscribe({
        next: data => {
          this.boats = data;
          console.log(data);
        },
        error: error => {
          console.log(error);
        }
      });
  }


}
