package dalibor.jelicanin.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class ProfessorEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "firstName", nullable = false, length = 30)
	private String firstName;
	@Column(name = "lastName", nullable = false, length = 30)
	private String lastName;
	@Column(name = "email", unique = true, length = 30)
	private String email;
	@Column(name = "address", length = 50)
	private String address;
	@Column(name = "phone", length = 15)
	private String phone;
	@Column(name = "reelection_date", nullable = false)
	private LocalDate reelectionDate;
	
	@ManyToOne
	@JoinColumn(name = "postalCode")
	private CityEntity city;
	
	@ManyToOne
	@JoinColumn(name = "title", nullable = false)
	private TitleEntity title;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "professor_engagement",
			joinColumns= @JoinColumn(name="professor_id"),
			inverseJoinColumns = @JoinColumn(name="subject_id"))
	private List<SubjectEntity> subjects;
	
	
	public ProfessorEntity() {
		subjects = new ArrayList<SubjectEntity>();
	}

	public ProfessorEntity(Long id, String firstName, String lastName, String email, String address, String phone,
			LocalDate reelectionDate, CityEntity city, TitleEntity title, List<SubjectEntity> subjects) {
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

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public TitleEntity getTitle() {
		return title;
	}

	public void setTitle(TitleEntity title) {
		this.title = title;
	}

	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectEntity> subjects) {
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
		ProfessorEntity other = (ProfessorEntity) obj;
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
		return "ProfessorEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", reelectionDate=" + reelectionDate + ", city=" + city
				+ ", title=" + title + ", subjects=" + subjects + "]";
	}
	
	
}
