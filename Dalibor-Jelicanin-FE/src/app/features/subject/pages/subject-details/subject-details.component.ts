import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { SubjectDto } from 'src/app/core';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';

@Component({
  selector: 'app-subject-details',
  templateUrl: './subject-details.component.html',
  styleUrls: ['./subject-details.component.css']
})
export class SubjectDetailsComponent implements OnInit {

  destroy$: Subject<boolean> = new Subject();
  subject: SubjectDto;


  constructor(private route: ActivatedRoute, private router: Router, private httpSubject: HttpSubjectService) { }

  ngOnInit(): void {
    const id =+ this.route.snapshot.paramMap.get('id');
    this.loadSubject(id);
  }

  loadSubject(id: number) {
    this.httpSubject.getById(id)
    .subscribe(
      subject => {
        this.subject = subject;
      }
    );
  }

}
