import { City } from "./city.model";
import { Exam } from "./exam.model";

export interface Student {
  id?: number;
  indexNumber?: string;
  indexYear?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  address?: string;
  currentYearOfStudy?: number;
  city: City;
  exams: Exam[];
}
