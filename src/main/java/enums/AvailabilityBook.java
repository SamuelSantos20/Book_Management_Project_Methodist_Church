package enums;

public enum AvailabilityBook {

	    DISPONIVEL("Disponível para uso ou empréstimo"),
	    INDISPONIVEL("Atualmente indisponível"),
	    RESERVADO("Reservado por um usuário");
	
	    private final String description;

	    AvailabilityBook(String description) {
	        this.description = description;
	    }

	    public String getDescricao() {
	        return description;
	    }
	}
