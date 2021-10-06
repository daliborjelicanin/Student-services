import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamRegistration } from 'src/app/core';
import { HttpExamRegistrationService } from 'src/app/core/service/http-exam-registration.service';

@Component({
  selector: 'app-exam-registration-details',
  templateUrl: './exam-registration-details.component.html',
  styleUrls: ['./exam-registration-details.component.css']
})
export class ExamRegistrationDetailsComponent implements OnInit {

  examRegistration: ExamRegistration;

  constructor(private route: ActivatedRoute, private router: Router,
    private httpExamRegistration: HttpExamRegistrationService) { }

  ngOnInit(): void {
    const id =+ this.route.snapshot.paramMap.get('id');
    this.loadExamRegistration(id);
  }

  loadExamRegistration(id: number) {
    this.httpExamRegistration.getById(id)
    .subscribe(
      examRegistration => {
        this.examRegistration = examRegistration;
      }
    );
  }


}
