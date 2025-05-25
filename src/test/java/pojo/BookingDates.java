package pojo;


	
	public class BookingDates {
	    private String checkin;
	    private String checkout;

	    // Default Constructor (needed by some JSON deserializers like Jackson)
	    public BookingDates() {
	    }

	    // Parameterized Constructor
	    public BookingDates(String checkin, String checkout) {
	        this.checkin = checkin;
	        this.checkout = checkout;
	    }

	    // Getters
	    public String getCheckin() {
	        return checkin;
	    }

	    public String getCheckout() {
	        return checkout;
	    }

	    // Setters
	    public void setCheckin(String checkin) {
	        this.checkin = checkin;
	    }

	    public void setCheckout(String checkout) {
	        this.checkout = checkout;
	    }

	    @Override
	    public String toString() {
	        return "BookingDates{" +
	               "checkin='" + checkin + '\'' +
	               ", checkout='" + checkout + '\'' +
	               '}';
	    }
	}


