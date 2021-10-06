import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css']
})
export class ProfessorDetailsComponent implements OnInit {

  professor: Professor;

  constructor(private route: ActivatedRoute, private router: Router, private httpProfessor: HttpProfessorService) { }

  ngOnInit(): void {
    const id =+ this.route.snapshot.paramMap.get('id');
    this.loadProfessor(id);
  }

  loadProfessor(id: number) {
    this.httpProfessor.getById(id)
    .subscribe(
      professor => {
        this.professor = professor;
      }
    );
  }
}
