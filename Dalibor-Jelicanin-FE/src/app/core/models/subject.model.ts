export interface SubjectDto {
  id?: number;
  name?: string;
  description?: string;
  noOfESP?: number;
  yearOfStudy?: number;
  semester?: Semester;
}

export enum Semester {
  SUMMER='Summer',
  WINTER='Winter'
}
