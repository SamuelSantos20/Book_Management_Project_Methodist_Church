package dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import domain.Book;
import domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate loanDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnDate;

	private Person person;

	private Book book;
}
