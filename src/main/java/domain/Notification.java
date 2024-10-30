package domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "notification")
@SuppressWarnings("serial")
public class Notification extends AbstractEntity<Long> {
	
	public Notification(String userName, String userCpf, String message) {
		super();
		this.userName = userName;
		this.userCpf = userCpf;
		this.message = message;
	}


	@Column(name = "username")
	private String userName;
    
    @Column(name = "usercpf" , length = 12 , nullable = false , unique = false , updatable = true)
    private String userCpf;
    
    @Column(name = "message", length = 300 , nullable = false , unique = false , updatable = true)
    private String message;

    
    @ManyToMany(mappedBy = "notifications")
    private List<Loan> loans = new ArrayList<>();
}
