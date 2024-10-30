package service;

import java.util.List;
import java.util.Optional;

import domain.Book;
import dto.BookDto;

public interface BookService {

	// Método para persistir uma entidade T no banco de dados
	public void saveBook(Book book);

	// Método para atualizar uma entidade T no banco de dados
	public void updateBook(Book book);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void deleteBook(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Book findByIdBook(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Book> findAllBook();

	Optional<Book> Existing_book(String tile);
	
	public List<BookDto> findAllBookDto();
	
	public List<Book> SerachBook(String texto);
	
	public List<BookDto> SerachBookDto(String texto);
	
	public BookDto findByIdBookDto(Long id);
}
