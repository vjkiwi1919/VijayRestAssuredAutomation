package testcases;



import pojo.AuthRequest;
import pojo.Booking;
import io.qameta.allure.Step; // Allure annotation for step-by-step reporting
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.PayloadReader;


public class RestfulBookerService extends BaseClass{

    private static final String BASE_URL = PayloadReader.getProperty("base_url");

    public RestfulBookerService() {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("Generating authentication token for username: {authRequest.username}")
    public String generateAuthToken() {
        AuthRequest authRequest = new AuthRequest(
                PayloadReader.getProperty("auth_username"),
                PayloadReader.getProperty("auth_password")
        );

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .log().body() // Log request body for debugging in reports
                .when()
                .post("/auth");

        response.then().statusCode(200)
                .log().body(); // Log response body for debugging in reports

        return response.jsonPath().getString("token");
    }

    @Step("Retrieving all booking IDs")
    public Response getAllBookingIds() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all() // Log request details
                .when()
                .get("/booking")
                .then()
                .log().all() // Log response details
                .extract().response();
    }

    @Step("Retrieving booking details for ID: {bookingId}")
    public Response getBookingById(int bookingId) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", bookingId)
                .log().all()
                .when()
                .get("/booking/{id}")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Creating a new booking")
    public Response createBooking(Booking bookingPayload) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingPayload)
                .log().all()
                .when()
                .post("/booking")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Updating booking ID: {bookingId}")
    public Response updateBooking(int bookingId, Booking bookingPayload, String token) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .body(bookingPayload)
                .log().all()
                .when()
                .put("/booking/{id}")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Deleting booking ID: {bookingId}")
    public Response deleteBooking(int bookingId, String token) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .log().all()
                .when()
                .delete("/booking/{id}")
                .then()
                .log().all()
                .extract().response();
    }
}