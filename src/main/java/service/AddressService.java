package service;

import java.util.List;

import domain.Address;

public interface AddressService {

	// Método para persistir uma entidade T no banco de dados
		public void saveAddress(Address address);

		// Método para atualizar uma entidade T no banco de dados
		public void updateAddress(Address address);

		// Método para excluir uma entidade T do banco de dados por sua chave primária
		// (PK)
		public void deleteAddress(Long id);

		// Método para encontrar uma entidade T pelo seu ID (PK)
		public Address  findByIdAddress(Long id);

		// Método para encontrar todas as entidades T no banco de dados
		public List<Address> findAllAddress();

	
	
}
