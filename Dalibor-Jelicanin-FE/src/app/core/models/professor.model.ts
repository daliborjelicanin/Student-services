import { City } from "./city.model";
import { SubjectDto } from "./subject.model";
import { Title } from "./title.model";

export interface Professor {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  address?: string;
  city?: City;
  phone?: string;
  reelectionDate?: Date;
  title: Title;
  subjects: SubjectDto[];
}
