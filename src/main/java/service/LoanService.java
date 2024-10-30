package service;

import java.time.LocalDate;
import java.util.List;

import domain.Loan;
import dto.LoanDto;

public interface LoanService {


	// Método para persistir uma entidade T no banco de dados
	public void saveLoan(Loan loan);

	// Método para atualizar uma entidade T no banco de dados
	public void updateLoan(Loan loan);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void deleteLoan(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Loan findByIdLoan(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Loan> findAllLoan();
	
	public List<Loan> findByReturnDate(LocalDate localDate);
	
	public List<LoanDto> findAllLoanDto();
	
	public List<Loan> SerachLoan(String texto);
	
	public LoanDto findByIdLoanDto(Long id);

	public List<Loan> findByBook(Long id);
	
	public List<LoanDto> SerachLoanDto(String texto);
	
	
	
	
}
