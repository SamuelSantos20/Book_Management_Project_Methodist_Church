package data;

import java.util.List;
import java.util.Optional;

import domain.Book;

public interface BookData {

	// Método para persistir uma entidade T no banco de dados
	public void save(Book book);

	// Método para atualizar uma entidade T no banco de dados
	public void update(Book book);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void delete(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Book findById(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Book> findAll();

	Optional<Book> Existing_book(String tile);
	
	public List<Book> SerachBook(String texto);
	
}
