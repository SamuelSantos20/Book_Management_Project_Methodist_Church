package domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Book extends AbstractEntity<Long> {

	@Column(name = "title", length = 200, nullable = false, unique = true, updatable = true)
	private String title;

	@Column(name = "author", length = 200, nullable = false, unique = false, updatable = true)
	private String author;

	@Column(name = "isbn", length = 13, nullable = false, unique = true, updatable = false)
	private String isbn;

	@Column(name = "availabilitybook", length = 400, nullable = false, unique = false, updatable = true)
	private String availabilitybook;

	@Column(name = "typecategory", length = 200, nullable = false, unique = false, updatable = true)
	private String typeCategory;

	@OneToMany(mappedBy = "book")
	private List<Loan> loans = new ArrayList<>();

	
	@Override
	public String toString() {
	    return "Book{id=" + getId() + 
	           ", title='" + title + '\'' + 
	           ", author='" + author + '\'' + 
	           ", isbn='" + isbn + '\'' + 
	           ", availability='" + availabilitybook + '\'' + 
	           ", typeCategory='" + typeCategory + '\'' + 
	           ", loansCount=" + loans.size() + 
	           "}";
	}

	
}
