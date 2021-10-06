import { ExamPeriod } from "./exam-period.model";
import { Professor } from "./professor.model";
import { SubjectDto } from "./subject.model";

export interface Exam {
  id?: number;
  date?: Date;
  examPeriod?: ExamPeriod;
  professor?: Professor;
  subject?: SubjectDto;
}
