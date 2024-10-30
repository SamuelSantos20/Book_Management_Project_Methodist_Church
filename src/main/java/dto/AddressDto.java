package dto;

import java.util.List;

import domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	
	private String street;

	private String neighborhood;

	private Integer number;

	private String city;

	private String state;

	private String complement;

	private List<Person> persons;
	
}
