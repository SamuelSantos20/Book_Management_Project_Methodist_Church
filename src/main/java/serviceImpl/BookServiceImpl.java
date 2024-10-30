package serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dataImpl.BookDataImpl;
import domain.Book;
import dto.BookDto;
import lombok.RequiredArgsConstructor;
import service.BookService;

/**
 * Implementação do serviço BookService para manipular dados de livros, incluindo operações CRUD.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class BookServiceImpl implements BookService {

    private final BookDataImpl bookDataImpl;

    /**
     * Salva um novo livro no banco de dados.
     *
     * @param book a entidade Book a ser salva.
     */
    @Override
    public void saveBook(Book book) {
        bookDataImpl.save(book);
    }

    /**
     * Atualiza um livro existente no banco de dados.
     *
     * @param book a entidade Book a ser atualizada.
     */
    @Override
    public void updateBook(Book book) {
        bookDataImpl.update(book);
    }

    /**
     * Exclui um livro do banco de dados com base em seu ID.
     *
     * @param id o ID do livro a ser excluído.
     */
    @Override
    public void deleteBook(Long id) {
        bookDataImpl.delete(id);
    }

    /**
     * Encontra um livro pelo ID no banco de dados.
     *
     * @param id o ID do livro a ser encontrado.
     * @return a entidade Book encontrada, ou null se não existir.
     */
    @Override
    @Transactional(readOnly = true)
    public Book findByIdBook(Long id) {
        return bookDataImpl.findById(id);
    }

    /**
     * Retorna todos os livros no banco de dados.
     *
     * @return uma lista de entidades Book.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllBook() {
        return bookDataImpl.findAll();
    }

    /**
     * Verifica se um livro existente com o título especificado está no banco de dados.
     *
     * @param tile o título do livro a ser verificado.
     * @return um Optional contendo o livro se existir, ou Optional.empty() se não existir.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Book> Existing_book(String tile) {
        return bookDataImpl.Existing_book(tile);
    }

    /**
     * Retorna todos os livros no banco de dados, convertidos para o formato BookDto.
     *
     * @return uma lista de objetos BookDto contendo informações resumidas dos livros.
     */
    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAllBookDto() {
        List<Book> books = bookDataImpl.findAll();
        return books.stream()
                .map(b -> new BookDto(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getIsbn(),
                        b.getTypeCategory(),
                        b.getAvailabilitybook()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Pesquisa livros com base em uma palavra-chave que pode corresponder ao título, autor, ISBN, etc.
     *
     * @param texto a palavra-chave de pesquisa.
     * @return uma lista de livros que correspondem aos critérios de pesquisa.
     */
    @Override
    public List<Book> SerachBook(String texto) {
        return bookDataImpl.SerachBook(texto);
    }

    /**
     * Pesquisa livros com base em uma palavra-chave e retorna os resultados no formato BookDto.
     *
     * @param texto a palavra-chave de pesquisa.
     * @return uma lista de BookDto contendo os resultados da pesquisa.
     */
    @Override
    public List<BookDto> SerachBookDto(String texto) {
        List<Book> books = bookDataImpl.SerachBook(texto);
        return books.stream()
                .map(b -> new BookDto(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getIsbn(),
                        b.getTypeCategory(),
                        b.getAvailabilitybook()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Encontra um livro pelo ID e retorna o resultado como um objeto BookDto.
     *
     * @param id o ID do livro a ser encontrado.
     * @return um objeto BookDto correspondente ao livro encontrado, ou um objeto BookDto vazio se não for encontrado.
     */
    @Override
    public BookDto findByIdBookDto(Long id) {
        Book book = bookDataImpl.findById(id);
        List<Book> books = new ArrayList<>();
        books.add(book);

        List<BookDto> bookDtos = books.stream()
                .map(b -> new BookDto(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getIsbn(),
                        b.getTypeCategory(),
                        b.getAvailabilitybook()
                ))
                .collect(Collectors.toList());

        BookDto bookDto = new BookDto();

        for (BookDto dto : bookDtos) {
            bookDto = dto;
        }

        return bookDto;
    }
}
