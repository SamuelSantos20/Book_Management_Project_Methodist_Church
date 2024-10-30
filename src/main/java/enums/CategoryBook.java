package enums;

public enum CategoryBook {
	    FICCAO("FICÇÃO"),
	    NAO_FICCAO("NÃO-FICÇÃO"),
	    ROMANCE("ROMANCE"),
	    FANTASIA("FANTASIA"),
	    CIENCIA_FICCAO("CIÊNCIA FICÇÃO"),
	    TERROR("TERROR"),
	    SUSPENSE("SUSPENSE"),
	    BIOGRAFIA("BIOGRAFIA"),
	    POESIA("POESIA"),
	    HISTORIA("HISTÓRIA"),
	    AUTO_AJUDA("AUTOAJUDA"),
	    EDUCACAO("EDUCAÇÃO"),
	    PSICOLOGIA("PSICOLOGIA"),
	    FILOSOFIA("FILOSOFIA"),
	    CIENCIAS("CIÊNCIAS"),
	    RELIGIAO("RELIGIÃO"),
	    POLITICA("POLÍTICA"),
	    NEGOCIOS("NEGÓCIOS"),
	    TECNOLOGIA("TECNOLOGIA"),
	    ARTE("ARTE"),
	    MUSICA("MÚSICA"),
	    INFANTIL("INFANTIL"),
	    JUVENIL("JUVENIL"),
	    GASTRONOMIA("GASTRONOMIA"),
	    ESPORTES("ESPORTES"),
	    GUIAS("GUIAS"),
	    VIAGENS("VIAGENS"),
	    SAUDE("SAÚDE"),
	    CIENCIA_NATURAL("CIÊNCIA NATURAL"),
	    DIREITO("DIREITO");

	    private final String description;

	    
	    
	    
		private CategoryBook(String description) {
			this.description = description;
		}




		public String getDescription() {
			return description;
		}

	     
	    
	    
	    
	    
	}
