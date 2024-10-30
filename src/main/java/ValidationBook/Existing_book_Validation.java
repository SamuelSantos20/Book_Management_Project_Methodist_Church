package ValidationBook;

import java.util.Optional;

import org.springframework.stereotype.Service;

import domain.Book;
import dto.BookDto;
import serviceImpl.BookServiceImpl;
import validationInterface.Validator;

/**
 * Validador para verificar a existência de um livro no sistema.
 * Esta classe valida se um livro com o mesmo título já está cadastrado antes de permitir a adição de um novo livro.
 */
@Service
public class Existing_book_Validation implements Validator {

    private final BookServiceImpl bookServiceImpl; // Serviço para gerenciar livros
    private BookDto bookDto; // Objeto DTO que contém os dados do livro a ser validado

    /**
     * Construtor que recebe uma instância de BookServiceImpl.
     *
     * @param bookServiceImpl Instância do serviço de gerenciamento de livros
     */
    public Existing_book_Validation(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    /**
     * Define o objeto BookDto a ser validado.
     *
     * @param bookDto Objeto DTO que contém os dados do livro
     */
    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    /**
     * Valida se o livro já existe no sistema.
     *
     * @return Uma mensagem de erro se o livro já estiver cadastrado ou se o BookDto for nulo,
     *         ou null se o livro puder ser adicionado.
     */
    @Override
    public String validate() {
        // Verifica se o objeto BookDto não foi definido
        if (this.bookDto == null) {
            return "BookDto cannot be null"; // Mensagem de erro se o BookDto for nulo
        }

        // Verifica se já existe um livro com o mesmo título
        Optional<Book> optional = bookServiceImpl.Existing_book(bookDto.getTitle());
        
        // Se o livro já existe, retorna uma mensagem de erro
        if (optional.isPresent()) {
            return "ESSE LIVRO JÁ SE ENCONTRA ADICIONADO NO SISTEMA"; // Mensagem de erro se o livro já estiver cadastrado
        }

        return null; // Retorna null se o livro puder ser adicionado
    }
}
