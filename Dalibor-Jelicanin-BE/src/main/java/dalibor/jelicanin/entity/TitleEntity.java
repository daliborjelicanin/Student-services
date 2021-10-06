package dalibor.jelicanin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "title")
public class TitleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "title_name", nullable = false, length = 50)
	private TitleName titleName;
	
	public TitleEntity() {
	}

	public TitleEntity(Long id, TitleName titleName) {
		super();
		this.id = id;
		this.titleName = titleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TitleName getTitleName() {
		return titleName;
	}

	public void setTitleName(TitleName titleName) {
		this.titleName = titleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titleName == null) ? 0 : titleName.hashCode());
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
		TitleEntity other = (TitleEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titleName != other.titleName)
			return false;
		return true;
	}
	
	

}
