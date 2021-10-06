import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamPeriod } from 'src/app/core';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';

@Component({
  selector: 'app-exam-period-details',
  templateUrl: './exam-period-details.component.html',
  styleUrls: ['./exam-period-details.component.css']
})
export class ExamPeriodDetailsComponent implements OnInit {

  examPeriod: ExamPeriod;

  constructor(private route: ActivatedRoute, private router: Router, private httpExamPeriod: HttpExamPeriodService) { }

  ngOnInit(): void {
    const id =+ this.route.snapshot.paramMap.get('id');
    this.loadExamPeriod(id);
  }

  loadExamPeriod(id: number) {
    this.httpExamPeriod.getById(id)
    .subscribe(
      examPeriod => {
        this.examPeriod = examPeriod;
      }
    );
  }


}
