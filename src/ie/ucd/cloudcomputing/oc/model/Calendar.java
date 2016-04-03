package ie.ucd.cloudcomputing.oc.model;

public class Calendar {
	private int id;
    private String eventName;
    private String startDate;
    private String endDate;
    private String email;
    
    
    public Calendar(int id, String eventName, String startDate, String endDate,
			String email) {
		
		this.id = id;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.email = email;
	}
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
   

}
