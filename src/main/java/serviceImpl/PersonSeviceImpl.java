package serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dataImpl.PersonDataImpl;
import domain.Loan;
import domain.Person;
import domain.Telephone;
import dto.PersonDto;
import lombok.RequiredArgsConstructor;
import service.PersonService;

/**
 * Implementação do serviço PersonService para manipular dados de pessoas, incluindo operações CRUD.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class PersonSeviceImpl implements PersonService {

    private final PersonDataImpl personDataImpl;

    /**
     * Salva uma nova pessoa no banco de dados.
     *
     * @param person a entidade Person a ser salva.
     */
    @Override
    public void savePerson(Person person) {
        personDataImpl.save(person);
    }

    /**
     * Atualiza uma pessoa existente no banco de dados.
     *
     * @param person a entidade Person a ser atualizada.
     */
    @Override
    public void updatePerson(Person person) {
        personDataImpl.update(person);
    }

    /**
     * Exclui uma pessoa do banco de dados com base em seu ID.
     *
     * @param id o ID da pessoa a ser excluída.
     */
    @Override
    public void deletePerson(Long id) {
        personDataImpl.delete(id);
    }

    /**
     * Encontra uma pessoa pelo ID no banco de dados.
     *
     * @param id o ID da pessoa a ser encontrada.
     * @return a entidade Person encontrada, ou null se não existir.
     */
    @Override
    @Transactional(readOnly = true)
    public Person findByIdPerson(Long id) {
        return personDataImpl.findById(id);
    }

    /**
     * Retorna todas as pessoas no banco de dados.
     *
     * @return uma lista de entidades Person.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllPerson() {
        return personDataImpl.findAll();
    }

    /**
     * Retorna todas as pessoas no banco de dados, convertidas para o formato PersonDto.
     *
     * @return uma lista de objetos PersonDto contendo informações resumidas das pessoas.
     */
    @Override
    public List<PersonDto> findAllPersonDto() {
        List<Person> persons = personDataImpl.findAll();
        return persons.stream()
                .map(p -> new PersonDto(
                        p.getId(),
                        p.getCpf(),
                        p.getName(),
                        p.getAddress(),
                        p.getTelefones().stream().map(Telephone::getNumber).collect(Collectors.toSet()),
                        p.getLoans().stream().map(Loan::getBook).collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }

    /**
     * Pesquisa pessoas com base em uma palavra-chave que pode corresponder ao CPF ou nome.
     *
     * @param texto a palavra-chave de pesquisa.
     * @return uma lista de pessoas que correspondem aos critérios de pesquisa.
     */
    @Override
    public List<Person> SerachPerson(String texto) {
        return personDataImpl.SerachPerson(texto);
    }

    /**
     * Pesquisa pessoas com base em uma palavra-chave e retorna os resultados no formato PersonDto.
     *
     * @param texto a palavra-chave de pesquisa.
     * @return uma lista de PersonDto contendo os resultados da pesquisa.
     */
    @Override
    public List<PersonDto> SerachPersonDto(String texto) {
        List<Person> persons = personDataImpl.SerachPerson(texto);
        return persons.stream()
                .map(p -> new PersonDto(
                        p.getId(),
                        p.getCpf(),
                        p.getName(),
                        p.getAddress(),
                        p.getTelefones().stream().map(Telephone::getNumber).collect(Collectors.toSet()),
                        p.getLoans().stream().map(Loan::getBook).collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }
}
