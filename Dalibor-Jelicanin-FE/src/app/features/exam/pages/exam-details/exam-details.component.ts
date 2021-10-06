import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Exam } from 'src/app/core';
import { HttpExamService } from 'src/app/core/service/http-exam.service';

@Component({
  selector: 'app-exam-details',
  templateUrl: './exam-details.component.html',
  styleUrls: ['./exam-details.component.css']
})
export class ExamDetailsComponent implements OnInit {

  exam: Exam;

  constructor(private route: ActivatedRoute, private router: Router, private httpExam: HttpExamService) { }

  ngOnInit(): void {
    const id =+ this.route.snapshot.paramMap.get('id');
    this.loadExam(id);
  }

  loadExam(id: number) {
    this.httpExam.getById(id)
    .subscribe(
      exam => {
        this.exam = exam;
      }
    );
  }


}
