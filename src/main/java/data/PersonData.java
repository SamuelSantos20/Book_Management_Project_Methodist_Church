package data;

import java.util.List;

import domain.Person;

public interface PersonData {
	// Método para persistir uma entidade T no banco de dados
	public void save(Person person);

	// Método para atualizar uma entidade T no banco de dados
	public void update(Person person);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void delete(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Person findById(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Person> findAll();

	public List<Person> SerachPerson(String texto);
}
