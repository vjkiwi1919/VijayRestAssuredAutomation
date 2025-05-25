 
package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description; // Allure annotation for test description
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Booking;


@Epic("Restful Booker API Automation")
@Feature("Booking Management")
public class E2EBookingTests {

    private RestfulBookerService bookingService;
    private int createdBookingId;
    private String authToken;
    private Booking initialBookingData;

    @BeforeClass
    @Description("Setup: Initialize API service and generate auth token")
    public void setup() {
        bookingService = new RestfulBookerService();
        authToken = bookingService.generateAuthToken();
        Assert.assertNotNull(authToken, "Auth token generation failed!");
        System.out.println("Generated Auth Token: " + authToken); // For console visibility

        initialBookingData = Payload.generateRandomBooking();
    }

    @Test(priority = 1)
    @Story("Create Booking")
    @Description("Verify that a new booking can be created successfully with valid data.")
    public void testCreateBooking() {
        Response response = bookingService.createBooking(initialBookingData);

        response.then().statusCode(200);

        // Extract bookingId
        createdBookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Created Booking ID: " + createdBookingId);

        // Assert response body details match the request payload
        Assert.assertNotNull(createdBookingId, "Booking ID should not be null.");
        Assert.assertTrue(createdBookingId > 0, "Booking ID should be a positive integer.");
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), initialBookingData.getFirstname(), "First name mismatch.");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), initialBookingData.getLastname(), "Last name mismatch.");
        Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), initialBookingData.getTotalprice(), "Total price mismatch.");
        Assert.assertEquals(response.jsonPath().getBoolean("booking.depositpaid"), initialBookingData.getDepositpaid(), "Deposit paid status mismatch.");
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), initialBookingData.getBookingdates().getCheckin(), "Checkin date mismatch.");
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), initialBookingData.getBookingdates().getCheckout(), "Checkout date mismatch.");
        Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), initialBookingData.getAdditionalneeds(), "Additional needs mismatch.");
    }

    @Test(priority = 2)
    @Story("Retrieve Booking")
    @Description("Verify retrieval of a specific booking by its ID.")
    public void testGetBookingById() {
        // Pre-condition: Ensure a booking was created in the previous test
        Assert.assertTrue(createdBookingId > 0, "Pre-condition failed: No booking ID available to retrieve.");

        Response response = bookingService.getBookingById(createdBookingId);

        response.then().statusCode(200);

        // Assert details match the initially created booking
        Assert.assertEquals(response.jsonPath().getString("firstname"), initialBookingData.getFirstname(), "Retrieved first name mismatch.");
        Assert.assertEquals(response.jsonPath().getString("lastname"), initialBookingData.getLastname(), "Retrieved last name mismatch.");
        Assert.assertEquals(response.jsonPath().getInt("totalprice"), initialBookingData.getTotalprice(), "Retrieved total price mismatch.");
        Assert.assertEquals(response.jsonPath().getBoolean("depositpaid"), initialBookingData.getDepositpaid(), "Retrieved deposit paid status mismatch.");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), initialBookingData.getBookingdates().getCheckin(), "Retrieved checkin date mismatch.");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), initialBookingData.getBookingdates().getCheckout(), "Retrieved checkout date mismatch.");
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), initialBookingData.getAdditionalneeds(), "Retrieved additional needs mismatch.");
    }

    @Test(priority = 3)
    @Story("Retrieve Bookings List")
    @Description("Verify retrieval of all booking IDs and confirm the existence of the newly created ID.")
    public void testGetAllBookingIds() {
        Response response = bookingService.getAllBookingIds();

        response.then().statusCode(200);

        // Assert that the list of booking IDs is not empty and contains the created ID
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty(), "Booking IDs list should not be empty.");
        // Check if the list contains the specific ID created earlier
        Assert.assertTrue(response.jsonPath().getList("bookingid").contains(createdBookingId), "Newly created booking ID should be present in the list.");
    }

    @Test(priority = 4)
    @Story("Update Booking")
    @Description("Verify that an existing booking can be fully updated successfully.")
    public void testUpdateBooking() {
        // Pre-conditions
        Assert.assertTrue(createdBookingId > 0, "Pre-condition failed: No booking ID available for update.");
        Assert.assertNotNull(authToken, "Pre-condition failed: Auth token is null.");

        // Generate new data for update
        Booking updatedBookingData = Payload.generateRandomBooking();
        updatedBookingData.setFirstname("Updated" + updatedBookingData.getFirstname()); // Ensure name change
        updatedBookingData.setTotalprice(updatedBookingData.getTotalprice() + 100); // Ensure price change

        Response response = bookingService.updateBooking(createdBookingId, updatedBookingData, authToken);

        response.then().statusCode(200);

        // Assert response reflects the updated data
        Assert.assertEquals(response.jsonPath().getString("firstname"), updatedBookingData.getFirstname(), "Updated first name mismatch.");
        Assert.assertEquals(response.jsonPath().getString("lastname"), updatedBookingData.getLastname(), "Updated last name mismatch.");
        Assert.assertEquals(response.jsonPath().getInt("totalprice"), updatedBookingData.getTotalprice(), "Updated total price mismatch.");
        Assert.assertEquals(response.jsonPath().getBoolean("depositpaid"), updatedBookingData.getDepositpaid(), "Updated deposit paid status mismatch.");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), updatedBookingData.getBookingdates().getCheckin(), "Updated checkin date mismatch.");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), updatedBookingData.getBookingdates().getCheckout(), "Updated checkout date mismatch.");
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), updatedBookingData.getAdditionalneeds(), "Updated additional needs mismatch.");

        // Optional: Verify with a GET call that the booking was indeed updated
        Response getResponse = bookingService.getBookingById(createdBookingId);
        getResponse.then().statusCode(200);
        Assert.assertEquals(getResponse.jsonPath().getString("firstname"), updatedBookingData.getFirstname(), "GET after update: First name mismatch.");
    }

    @Test(priority = 5)
    @Story("Delete Booking")
    @Description("Verify that an existing booking can be deleted successfully.")
    public void testDeleteBooking() {
        // Pre-conditions
        Assert.assertTrue(createdBookingId > 0, "Pre-condition failed: No booking ID available for deletion.");
        Assert.assertNotNull(authToken, "Pre-condition failed: Auth token is null.");

        Response response = bookingService.deleteBooking(createdBookingId, authToken);

        response.then().statusCode(201); // The API returns 201 for successful deletion
        Assert.assertEquals(response.asString(), "Created", "Delete response body should be 'Created'"); // API's specific success message

        // Verify with a GET call that the booking is no longer found (should return 404)
        Response getResponseAfterDelete = bookingService.getBookingById(createdBookingId);
        getResponseAfterDelete.then().statusCode(404);
        System.out.println("Verified booking ID " + createdBookingId + " is deleted.");
    }
}
