package dalibor.jelicanin.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProfessorDto implements MyDto {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "Professor's firstName is required.")
	@Size(min = 3, message = "Professors's firstName must contains minimum 3 characters")
	private String firstName;
	@NotEmpty(message = "Professor's lastName is required.")
	@Size(min = 3, message = "Professors's lastName must contains minimum 3 characters")
	private String lastName;
	@Email(message = "Email is invalid, it must contain @")
	private String email;
	@Size(min = 3, message = "Professors's address must contains minimum 3 characters")
	private String address;
	@Size(min = 9, message = "Professors's phone must contains minimum 9 characters")
	private String phone;
	@NotNull(message = "Reelection date is required.")
	private LocalDate reelectionDate;
	private CityDto city;
	@NotNull(message = "Professor's title is required.")
	private TitleDto title;
	private List<SubjectDto> subjects;
	
	public ProfessorDto() {
		subjects = new ArrayList<SubjectDto>();
	}

	public ProfessorDto(Long id, String firstName, String lastName, String email, String address, String phone, 
			LocalDate reelectionDate, CityDto city, TitleDto title, List<SubjectDto>subjects) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.reelectionDate = reelectionDate;
		this.city = city;
		this.title = title;
		this.subjects = subjects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getReelectionDate() {
		return reelectionDate;
	}

	public void setReelectionDate(LocalDate reelectionDate) {
		this.reelectionDate = reelectionDate;
	}

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}

	public List<SubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		ProfessorDto other = (ProfessorDto) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProfessorDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", reelectionDate=" + reelectionDate + ", city=" + city
				+ ", title=" + title + ", subjects=" + subjects + "]";
	}
	
	
}
