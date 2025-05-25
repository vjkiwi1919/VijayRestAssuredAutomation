package pojo;

public class Booking {

	private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

	
	
	//constructor
	public Booking (String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingDates2, String additionalneeds) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingDates2;
		this.additionalneeds = additionalneeds;
		
		
	}
	
	//Getters and Setters
	

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public boolean getDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	public BookingDates getBookingdates() {
		return bookingdates;
	}
	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}
	public String getAdditionalneeds(){ 
		return additionalneeds;
	}
    public void setAdditionalneeds(String additionalneeds) { 
    	this.additionalneeds = additionalneeds; 
    }
    @Override
    public String toString() {
        return "Booking{firstname='" + firstname + "', lastname='" + lastname + "', totalprice=" + totalprice +
               ", depositpaid=" + depositpaid + ", bookingdates=" + bookingdates + ", additionalneeds='" + additionalneeds + "'}";
    }
}
