package domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@SuppressWarnings("serial")
public class Address extends AbstractEntity<Long> {

	
	
	@Column(name = "street", nullable = false, length = 200, unique = false, updatable = true)
	private String street;

	@Column(name = "neighborhood", nullable = false, length = 200, unique = false, updatable = true)
	private String neighborhood;

	@Column(name = " number", nullable = false, unique = false , updatable = true)
	private Integer number;

	@Column(name = "city", nullable = false, length = 200, unique = false, updatable = true)
	private String city;

	@Column(name = "state", nullable = false, length = 300, unique = false, updatable = true)
	private String state;

	@Column(name = "complement", nullable = true, length = 300, unique = false, updatable = true)
	private String complement;

	@OneToMany(cascade = CascadeType.ALL , mappedBy = "address")
	List<Person> persons = new ArrayList<>();
	
	
	 @Override
	    public String toString() {
	        return "Address{" +
	                "street='" + street + '\'' +
	                ", neighborhood='" + neighborhood + '\'' +
	                ", number=" + number +
	                ", city='" + city + '\'' +
	                ", state='" + state + '\'' +
	                ", complement='" + complement + '\'' +
	                '}';
	    }
	
}
