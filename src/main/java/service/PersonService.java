package service;

import java.util.List;

import domain.Person;
import dto.PersonDto;

public interface PersonService {

	// Método para persistir uma entidade T no banco de dados
	public void savePerson(Person person);

	// Método para atualizar uma entidade T no banco de dados
	public void updatePerson(Person person);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void deletePerson(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Person findByIdPerson(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Person> findAllPerson();

	public List<PersonDto> findAllPersonDto();
	
	public List<Person> SerachPerson(String texto);
    
	public List<PersonDto> SerachPersonDto(String texto);

}
