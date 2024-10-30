package dataImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import data.LoanData;
import domain.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Implementação da interface LoanData para realizar operações de acesso aos dados de empréstimos.
 */
@Repository
public class LoanDataImpl extends AbstractDao<Loan, Long> implements LoanData {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Encontra empréstimos com base na data de retorno.
     *
     * @param localDate a data de retorno para filtrar os empréstimos.
     * @return uma lista de empréstimos com a data de retorno especificada.
     */
    public List<Loan> findByReturnDate(LocalDate localDate) {
        String sql = "select l from Loan l where l.returnDate = :return";
        TypedQuery<Loan> loanQuery = entityManager.createQuery(sql, Loan.class);
        loanQuery.setParameter("return", localDate);
        return loanQuery.getResultList();
    }

    /**
     * Pesquisa empréstimos com base em uma palavra-chave que pode corresponder ao nome da pessoa ou CPF.
     *
     * @param texto a palavra-chave a ser pesquisada (nome ou CPF da pessoa).
     * @return uma lista de empréstimos que correspondem aos critérios de pesquisa, ou uma lista vazia se nenhum empréstimo for encontrado.
     */
    public List<Loan> SerachLoan(String texto) {
        try {
            String sql = "select l from Loan l where l.person.name like :name or l.person.cpf like :cpf";
            TypedQuery<Loan> typedQuery = entityManager.createQuery(sql, Loan.class);
            typedQuery.setParameter("cpf", "%" + texto + "%");
            typedQuery.setParameter("name", "%" + texto + "%");
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Erro na pesquisa de empréstimos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Encontra empréstimos com base no ID do livro associado.
     *
     * @param id o ID do livro para o qual os empréstimos serão buscados.
     * @return uma lista de empréstimos associados ao ID do livro especificado.
     */
    public List<Loan> findByBook(Long id) {
        String sql = "select l from Loan l where l.book.id = :id";
        TypedQuery<Loan> typedQuery = entityManager.createQuery(sql, Loan.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getResultList();
    }
}
