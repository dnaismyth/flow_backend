package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class RJournal extends BaseEntity {
	
	private static final long serialVersionUID = 877879274164789589L;
	
	@Column(name="entry", nullable=false)
	private String entry;
	
	public void setEntry(String entry){
		this.entry = entry;
	}
	
	public String getEntry(){
		return entry;
	}
	
}
