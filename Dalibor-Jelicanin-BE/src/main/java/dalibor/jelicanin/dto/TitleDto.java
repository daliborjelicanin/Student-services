package dalibor.jelicanin.dto;

import dalibor.jelicanin.entity.TitleName;

public class TitleDto implements MyDto {
	private static final long serialVersionUID = 1L;

	private Long id;
	private TitleName titleName;
	
	public TitleDto() {
	}

	public TitleDto(Long id, TitleName titleName) {
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
		TitleDto other = (TitleDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titleName != other.titleName)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TitleDto [id=" + id + ", titleName=" + titleName + "]";
	}
	
	
}
