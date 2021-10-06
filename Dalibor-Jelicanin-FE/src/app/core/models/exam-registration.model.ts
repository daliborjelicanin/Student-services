import { Exam } from "./exam.model";
import { Student } from "./student.model";

export interface ExamRegistration {
  id?: number;
  student?: Student;
  exam?: Exam;
  date?: Date;
}
