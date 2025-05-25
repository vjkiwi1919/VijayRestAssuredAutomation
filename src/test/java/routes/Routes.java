package routes;

public class Routes {
	
	//Access the Booker URL.
	
	public static final String BASE_URL="https://restful-booker.herokuapp.com";
	
	//Retrieve all booking IDs.
	
	public static final String GET_ALL_BOOKING="/booking";
	
	//Retrieve details of a specific booking.
	public static final String RETRIEVE_SPECIFIC_BOOKING="/booking/{id}";
	
	//Create a new booking. (Use realistic JSON request body.)
	public static final String CREATE_NEW_BOOKING="/booking";
	
	
	//Update an existing booking (requires token).
	public static final String UPDATE_EXISTING_BOOKING="/booking/{id}";
	
	//- Delete a booking (requires token).
	public static final String DELETE_BOOKING="/booking/{id}";
	
	//POST /auth - Generate an authentication token to use for PUT/DELETE.
	public static final String GENERATE_AUTH_TOKEN="/auth";
	

}
