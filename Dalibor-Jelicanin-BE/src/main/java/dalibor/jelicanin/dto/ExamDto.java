package dalibor.jelicanin.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExamDto implements MyDto {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(message = "Exam's date is required.")
	private LocalDate date;
	private ExamPeriodDto examPeriod;
	private ProfessorDto professor;
	private SubjectDto subject;
	
	public ExamDto() {
	}

	public ExamDto(Long id, LocalDate date, ExamPeriodDto examPeriod, ProfessorDto professor, SubjectDto subject) {
		super();
		this.id = id;
		this.date = date;
		this.examPeriod = examPeriod;
		this.professor = professor;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ExamPeriodDto getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriodDto examPeriod) {
		this.examPeriod = examPeriod;
	}

	public ProfessorDto getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorDto professor) {
		this.professor = professor;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((examPeriod == null) ? 0 : examPeriod.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		ExamDto other = (ExamDto) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (examPeriod == null) {
			if (other.examPeriod != null)
				return false;
		} else if (!examPeriod.equals(other.examPeriod))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExamDto [id=" + id + ", date=" + date + ", examPeriod=" + examPeriod + ", professor=" + professor
				+ ", subject=" + subject + "]";
	}

	
}
