package dalibor.jelicanin.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDto implements MyDto {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "Index number is required.")
	@Size(min = 4, max = 4, message = "Index number must have exact 4 characters")
	private String indexNumber;
	@NotNull(message = "Index year is required.")
	@Min(value = 2000, message = "Index year must be between 2000 and 2100")
	@Max(value = 2100, message = "Index year must be between 2000 and 2100")
	private Integer indexYear;
	@NotEmpty(message = "Student's firstName is required.")
	@Size(min = 3, message = "Student's firstName must contains minimum 3 characters")
	private String firstName;
	@NotEmpty(message = "Student's lastName is required.")
	@Size(min = 3, message = "Student's lastName must contains minimum 3 characters")
	private String lastName;
	@Email(message = "Email is invalid, it must contain @")
	private String email;
	@Size(min = 3, message = "Student's address must contains minimum 3 characters")
	private String address;
	@NotNull(message = "Student's current year of study is required.")
	private Integer currentYearOfStudy;
	private CityDto city;
	private List<ExamDto> exams;
	
	public StudentDto() {
		exams = new ArrayList<ExamDto>();
	}

	public StudentDto(Long id, String indexNumber, int indexYear, String firstName, String lastName, String email,
			String address, Integer currentYearOfStudy, CityDto city) {
		super();
		this.id = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.currentYearOfStudy = currentYearOfStudy;
		this.city = city;
		exams = new ArrayList<ExamDto>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public int getIndexYear() {
		return indexYear;
	}

	public void setIndexYear(int indexYear) {
		this.indexYear = indexYear;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(Integer currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public List<ExamDto> getExams() {
		return exams;
	}

	public void setExams(List<ExamDto> exams) {
		this.exams = exams;
	}

	public void setIndexYear(Integer indexYear) {
		this.indexYear = indexYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indexNumber == null) ? 0 : indexNumber.hashCode());
		result = prime * result + indexYear;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indexNumber == null) {
			if (other.indexNumber != null)
				return false;
		} else if (!indexNumber.equals(other.indexNumber))
			return false;
		if (indexYear != other.indexYear)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", address=" + address
				+ ", currentYearOfStudy=" + currentYearOfStudy + ", city=" + city + ", exams=" + exams + "]";
	}
	
}
