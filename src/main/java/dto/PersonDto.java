package dto;

import java.util.HashSet;
import java.util.Set;

import domain.Address;
import domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

	private Long id;
	
	private String cpf;

	private String name;

	private Address address;

	private Set<String> telefones = new HashSet<>();

	private Set<Book> books = new HashSet<>();

}
