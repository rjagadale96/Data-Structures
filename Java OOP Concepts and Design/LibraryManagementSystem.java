import java.util.Date;
import java.util.HashMap;
import java.util.List;

public enum BookFormat{
	HARDCOVER;
	PAPERBACK;
	AUDIO_BOOK;
	NEWSPAPER;
	MAGAZINE;
	JOURNAL;
}

public enum BookStatus{
	AVAILABLE;
	RESERVED;
	LOANED;
	LOST;
}

public enum ReservationStatus{
	WAITING;
	PENDING;
	CANCELLED;
	NONE;
}

public enum AccountStatus{
	ACTIVE;
	CLOSED;
	CANCELLED;
	BLACKLISTED;
	NONE;
}

public class Address{
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;
}

public class Person{
	private String name;
	private Address address;
	private String email;
	private String  phone;
}

public class Constants{
	public static final int MAX_BOOKS_ISSUES_TO_A_USER = 5;
	public static final int MAX_LENDING_DAYS  = 10;
}
