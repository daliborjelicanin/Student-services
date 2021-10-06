import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/core';
import { HttpStudentService } from 'src/app/core/service/http-student.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  student: Student

  constructor(private route: ActivatedRoute, private router: Router, private httpStudent: HttpStudentService) { }

  ngOnInit(): void {
    const id =+ this.route.snapshot.paramMap.get('id');
    this.loadStudent(id);
  }

  loadStudent(id: number) {
    this.httpStudent.getById(id)
    .subscribe(
      student => {
        this.student = student;
      }
    );
  }
}
