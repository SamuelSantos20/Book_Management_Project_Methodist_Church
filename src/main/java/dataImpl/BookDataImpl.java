package dataImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import data.BookData;
import domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Implementação da interface BookData para realizar operações de acesso aos dados de livros.
 */
@Repository
public class BookDataImpl extends AbstractDao<Book, Long> implements BookData {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Verifica a existência de um livro no banco de dados com base no título.
     *
     * @param title o título do livro a ser verificado.
     * @return um Optional contendo o livro encontrado, ou Optional.empty() se nenhum livro for encontrado.
     */
    public Optional<Book> Existing_book(String title) {
        try {
            String sql = "select b from Book b where b.title = :title";
            TypedQuery<Book> query = entityManager.createQuery(sql, Book.class);
            query.setParameter("title", title);
            
            // Retorna o primeiro resultado como Optional, ou Optional.empty() se não houver nenhum resultado
            return query.getResultStream().findFirst();
            
        } catch (Exception e) {
            // Log de erro, se necessário
            System.err.println("Erro ao buscar o livro: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Pesquisa livros com base em uma palavra-chave que pode corresponder ao título, ISBN, autor,
     * disponibilidade ou categoria do livro.
     *
     * @param texto a palavra-chave a ser pesquisada.
     * @return uma lista de livros que correspondem aos critérios de pesquisa, ou uma lista vazia se nenhum livro for encontrado.
     */
    public List<Book> SerachBook(String texto) {
        try {
            String sql = "select b from Book b where b.title like :title or b.isbn like :isbn or b.author like :author or b.availabilitybook like :availabilitybook or b.typeCategory like :typeCategory";
            TypedQuery<Book> typedQuery = entityManager.createQuery(sql , Book.class);
            
            typedQuery.setParameter("title", "%" + texto + "%");
            typedQuery.setParameter("isbn", "%" + texto + "%");
            typedQuery.setParameter("author", "%" + texto + "%");
            typedQuery.setParameter("availabilitybook", "%" + texto + "%");
            typedQuery.setParameter("typeCategory", "%" + texto + "%");
            
            return typedQuery.getResultList();
            
        } catch (Exception e) {
            // Log de erro, se necessário
            System.err.println("Erro na pesquisa de livros: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
