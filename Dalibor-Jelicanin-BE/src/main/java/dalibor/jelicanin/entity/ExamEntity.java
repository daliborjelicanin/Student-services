package dalibor.jelicanin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "exam", uniqueConstraints = @UniqueConstraint(columnNames = { "exam_period_id", "professor_id", "subject_id" }))
public class ExamEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "date", nullable = false)
	private LocalDate date;
		
	@ManyToOne
    @JoinColumn(name = "exam_period_id")
	private ExamPeriodEntity examPeriod;

	@ManyToOne
    @JoinColumn(name = "professor_id")
	private ProfessorEntity professor;
	
	@ManyToOne
    @JoinColumn(name = "subject_id")
	private SubjectEntity subject;
	
	public ExamEntity() {
	}

	public ExamEntity(Long id, LocalDate date, ExamPeriodEntity examPeriod, ProfessorEntity professor, SubjectEntity subject) {
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

	public ExamPeriodEntity getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriodEntity examPeriod) {
		this.examPeriod = examPeriod;
	}

	public ProfessorEntity getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorEntity professor) {
		this.professor = professor;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
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
		ExamEntity other = (ExamEntity) obj;
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
		return "ExamEntity [id=" + id + ", date=" + date + ", examPeriod=" + examPeriod + ", professor=" + professor
				+ ", subject=" + subject + "]";
	}

	
	
}
