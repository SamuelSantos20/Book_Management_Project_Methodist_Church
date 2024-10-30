package dataImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import data.PersonData;
import domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Implementação da interface PersonData para realizar operações de acesso aos dados de pessoas.
 */
@Repository
public class PersonDataImpl extends AbstractDao<Person, Long> implements PersonData {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Pesquisa pessoas com base em uma palavra-chave que pode corresponder ao CPF ou nome.
     *
     * @param texto a palavra-chave a ser pesquisada (pode ser parte do CPF ou nome da pessoa).
     * @return uma lista de pessoas que correspondem aos critérios de pesquisa, ou uma lista vazia se nenhuma pessoa for encontrada.
     */
    public List<Person> SerachPerson(String texto) {
        try {
            String sql = "select p from Person p where p.cpf like :cpf or p.name like :name";
            TypedQuery<Person> typedQuery = entityManager.createQuery(sql, Person.class);
            typedQuery.setParameter("cpf", "%" + texto + "%");
            typedQuery.setParameter("name", "%" + texto + "%");

            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Erro na pesquisa de pessoas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
