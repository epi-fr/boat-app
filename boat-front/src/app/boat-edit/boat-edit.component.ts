import {Component, OnInit} from '@angular/core';
import {Boat} from "../models/boat";
import {BoatService} from "../services/boat.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-boat-edit',
  templateUrl: './boat-edit.component.html',
  styleUrls: ['./boat-edit.component.css']
})
export class BoatEditComponent implements OnInit {

  currentBoat: Boat;

  constructor(private boatService: BoatService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getBoat(this.route.snapshot.paramMap.get('id'));
  }

  getBoat(id: any): void {
    this.boatService.get(id)
      .subscribe(
        data => {
          this.currentBoat = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}
