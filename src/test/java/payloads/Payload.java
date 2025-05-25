package payloads;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import pojo.BookingDates;
import pojo.Booking;


public class Payload {	
	
	private static Faker faker = new Faker();
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Booking generateRandomBooking() {
        // Generate checkin date (e.g., today or in the near future)
        Date checkinDate = faker.date().future(30, TimeUnit.DAYS);

        // Generate checkout date (e.g., 1-10 days after checkin)
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkinDate);
        cal.add(Calendar.DAY_OF_MONTH, faker.number().numberBetween(1, 10));
        Date checkoutDate = cal.getTime();

        BookingDates bookingDates = new BookingDates(
                SDF.format(checkinDate),
                SDF.format(checkoutDate)
        );

        return new Booking(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(100, 1000),
                faker.bool().bool(),
                bookingDates,
                faker.options().option("Breakfast", "Parking", "WiFi", "None") // More realistic additional needs
        );
    }

		
	}



