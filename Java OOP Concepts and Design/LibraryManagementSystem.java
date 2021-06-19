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
public abstract class Account{
	private String id;
	private String password;
	private AccountStatus status;
	private Person person;
	
	public boolean resetPassword();
}

public class Librarian extends Account{
	public boolean addBookItem(BookItem bookItem);
	public boolean blockMember(Member member);
	public boolean unBlockMember(Member member);
}

public class Member extends Account{
	private Date dateOfMembership;
	private int totalBooksCheckout();
	public boolean reservBookItem(BookItem bookItem);
	private void incrementTotatBooksCheckOut();
	
	public boolean checkoutBookItem(BookItem bookItem) {
		if(this.getTotaoBooksCheckedOut() >= Constants.MAX_BOOKS_ISSUES_TO_A_USER) {
			ShowError("The user has already checked-out maximum number of books");
			return false;
		}
		
		BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
		if(bookReservation != null && bookReservation.getMemberId() != this.getId()) {
			ShowError("This book is reserved by another memeber");
			return false;
		}else if(bookReservation != null ) {
			bookReservation.updateStatus(ReservationStatus.COMPLETED);
		}
		if(!bookItem.checkout(this.getId())) {
			return false;
		}
		this.incrementTotatBooksCheckOut();
		return true;
	}
	
	private void checkForFine(String bookItemBarcode) {
		BookLending bookLending = bookLending.fetchLendingDetails(bookItemBarcode);
		Date dueDate = bookLending.getDueDate();
		Date today = new Date();
		
		if(today.compareTo(dueDate) > 0) {
			long diff = todayDate.getTime() - dueDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			Fine.collectFine(memberId, diffDays);
		}
	}
	
	
	public void returnBook(BokItem bookItem) {
		this.checkForFine(bookItem.getBarcode());
		BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
		if(bookReservation != null) {
			bookItem.updateBookItemStatus(BookStatus.RESERVED);
			bookReservation.sendBookAvailableNotification();
		}
		bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
	}

	public boolean renewBookItem(BookItem bookItem) {
	    this.checkForFine(bookItem.getBarcode());
	    BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
	    
	    if (bookReservation != null && bookReservation.getMemberId() != this.getMemberId()) {
	      ShowError("This book is reserved by another member");
	      member.decrementTotalBooksCheckedout();
	      bookItem.updateBookItemState(BookStatus.RESERVED);
	      bookReservation.sendBookAvailableNotification();
	      return false;
	    } else if (bookReservation != null) {
	      bookReservation.updateStatus(ReservationStatus.COMPLETED);
	    }
	    BookLending.lendBook(bookItem.getBarCode(), this.getMemberId());
	    bookItem.updateDueDate(LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS));
	    return true;
	  }
}

public class BookReservation{
	private Date creationDate;
	private ReservationStatus status;
	private String bookItemBarcode;
	private String memberId;
	
	public static BookReservation fetchReservationDetails(String barcode) ;
}

public class BookLending {
	private Date creationDate;
	private Date dueDate;
	private Date returnDate;
	private String bookItemBarcode;
	private String memberId;
	
	public static boolean  lendBook(String barcode, String memberId);
	public static bookLending fetchLendingDetails(String barcode);
}

public class Fine{
	private Date creationDate;
	private double bookItemBarcode;
	private String memberId;
	
	public static void collectFine(String memberId, long days) {}
}



public abstract class Book{
	private String ISBN;
	private String title;
	private String subject;
	private String publisher;
	private String language;
	private int numberOfPages;
	private List<Author> authors;
}



public class BookItem extends Book{
	private String barcode;
	private boolean isReferenceOnly;
	private Date borrowed;
	private Date dueDate;
	private double price;
	private BookFormat format ;
	private BookStatus status;
	private Date dateOfPurchase;
	private Date publicationDate;
	private Rack placedAt;
	
	public boolean checkout(String memberId) {
		if(bookItem.getIsReferenceInly()) {
			ShowError("This book is Reference only and cant be issued");
			return false;
		}
		if(!BookLending.lendBook(this.getBarCode(), memberId)) {
			return false;
		}
		this.updateBookItemStatus(BookStatus.LOANED);
		return true;
	}
	
}
