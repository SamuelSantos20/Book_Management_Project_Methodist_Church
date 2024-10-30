package dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component

public class BookDto {

	private Long id;

	private String title;

	private String author;

	private String isbn;

	private String typeCategory;

	private String availabilitybook;
}
