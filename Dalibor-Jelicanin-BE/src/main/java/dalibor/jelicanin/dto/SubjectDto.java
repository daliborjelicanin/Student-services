package dalibor.jelicanin.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dalibor.jelicanin.entity.Semester;

public class SubjectDto implements MyDto {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message = "Subject name is required.")
	@Size(min = 3, message = "Subject name must contain minimum 3 characters.")
	private String name;
	private String description;
	@NotNull(message = "Number of ESP is required.")
	private Integer noOfESP;
	@NotNull(message = "Year of study is required.")
	private Integer yearOfStudy;
	@NotNull(message = "Semester is required.")
	private Semester semester;
	
	public SubjectDto() {
	}

	public SubjectDto(Long id, String name, String description, Integer noOfESP, Integer yearOfStudy, Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfESP = noOfESP;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfESP() {
		return noOfESP;
	}

	public void setNoOfESP(Integer noOfESP) {
		this.noOfESP = noOfESP;
	}

	public Integer getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((noOfESP == null) ? 0 : noOfESP.hashCode());
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((yearOfStudy == null) ? 0 : yearOfStudy.hashCode());
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
		SubjectDto other = (SubjectDto) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (noOfESP == null) {
			if (other.noOfESP != null)
				return false;
		} else if (!noOfESP.equals(other.noOfESP))
			return false;
		if (semester != other.semester)
			return false;
		if (yearOfStudy == null) {
			if (other.yearOfStudy != null)
				return false;
		} else if (!yearOfStudy.equals(other.yearOfStudy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubjectDto [id=" + id + ", name=" + name + ", description=" + description + ", noOfESP=" + noOfESP
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + "]";
	}
	
}
