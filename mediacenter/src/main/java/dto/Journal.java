package dto;

/**
 * This will show as text in the feed (does not allow images)
 * @author DN
 *
 */
public class Journal {
	
	/**
	 * Owner of the journal entry
	 */
	private User owner;
	
	/**
	 * A daily entry
	 */
	private String entry;
	
	public Journal(){}
	
	public String getEntry(){
		return entry;
	}
	
	public void setEntry(String entry){
		this.entry = entry;
	}
	
	public User getOwner(){
		return owner;
	}
	
	public void setOwner(User owner){
		this.owner = owner;
	}
}
