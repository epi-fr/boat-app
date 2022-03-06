import {Component, OnInit} from '@angular/core';
import {BoatService} from "../services/boat.service";
import {ActivatedRoute, Router} from '@angular/router';
import {Boat} from "../models/boat";
import {NgForm} from "@angular/forms";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.css']
})
export class BoatDetailsComponent implements OnInit {

  currentBoat: Boat;
  editMode = false;


  constructor(private boatService: BoatService,
              private route: ActivatedRoute,
              private router: Router,
              private notifier: NotifierService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.currentBoat = new Boat();
      this.editMode = !isNaN(Number(params.get('id')));
      if (this.editMode) {
        this.getBoat(params.get('id'));
      } else {
        this.currentBoat = new Boat();
      }
    });

  }

  getBoat(id: any): void {
    this.boatService.get(id)
      .subscribe({

        next: data => {
          this.currentBoat = data;
          console.log(data);
        },
        error: error => {
          console.log(error);
        }
      });
  }


  createBoat(form: NgForm) {
    if (form.valid && !this.editMode) {

      const value = form.value;
      this.boatService.create({name: value.name, description: value.description})
        .subscribe({
          next: data => {

            this.notifier.notify('success', 'Created !');
            console.log(data);
            this.router.navigate(['/boat/' + data.id])
          },
          error: error => {
            console.log(error);
            this.notifier.notify('error', 'Failed to update boat !');
          }
        });
    }
  }

  updateBoat(form: NgForm) {
    if (form.valid && this.editMode) {

      const value = form.value;
      this.boatService.update(this.currentBoat.id, {name: value.name, description: value.description})
        .subscribe({
          next: response => {

            this.notifier.notify('success', 'Updated !');
            console.log(response);
          },
          error: error => {
            console.log(error);
            this.notifier.notify('error', 'Failed to update boat !');
          }
        });
    }
  }

  deleteBoat() {
    this.boatService.delete(this.currentBoat.id).subscribe(
      response => {

        this.notifier.notify('success', 'Deleted !');
        console.log(response);
        this.router.navigate(['/boats']);
      },
      error => {
        console.log(error);
        this.notifier.notify('error', 'Failed to delete boat !');
      });
  }

  onSubmitForm(f: NgForm) {
    if (this.editMode) {
      this.updateBoat(f);
    } else {
      this.createBoat(f)
    }
  }
}
