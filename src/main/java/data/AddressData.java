package data;

import java.util.List;

import domain.Address;


public interface AddressData {

	// Método para persistir uma entidade T no banco de dados
	public void save(Address address);

	// Método para atualizar uma entidade T no banco de dados
	public void update(Address address);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void delete(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Address  findById(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Address> findAll();

}
