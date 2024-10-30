package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
@Table(name = "loan")
public class Loan extends AbstractEntity<Long> {

	@Column(name = "loanDate", nullable = false, unique = false, updatable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate loanDate;

	@Column(name = "returnDate", nullable = false, unique = false, updatable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Person person;

	@Column(nullable = false)
	private Boolean isNotified; // Atributo para indicar se a notificação foi enviada

	@ManyToMany
	@JoinTable(name = "loan_notification", joinColumns = @JoinColumn(name = "loan_id"), inverseJoinColumns = @JoinColumn(name = "notification_id"))
	private List<Notification> notifications = new ArrayList<>();
	
	
	@ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
	
	
	@Override
	public String toString() {
	    return "Loan{id=" + getId() + 
	           ", loanDate=" + loanDate + 
	           ", returnDate=" + returnDate + 
	           ", person=" + (person != null ? person.getId() : null) + 
	           ", book=" + (book != null ? book.getId() : null) + 
	           ", isNotified=" + isNotified + 
	           ", notificationsCount=" + notifications.size() + 
	           "}";
	}

}
