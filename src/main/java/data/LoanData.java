package data;

import java.time.LocalDate;
import java.util.List;

import domain.Loan;

public interface LoanData {

	// Método para persistir uma entidade T no banco de dados
	public void save(Loan loan);

	// Método para atualizar uma entidade T no banco de dados
	public void update(Loan loan);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void delete(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Loan findById(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Loan> findAll();

	
	public List<Loan> findByReturnDate(LocalDate localDate);
	
	public List<Loan> findByBook(Long id);
	
	public List<Loan> SerachLoan(String texto);
}
