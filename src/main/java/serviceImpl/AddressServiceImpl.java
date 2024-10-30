package serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dataImpl.AddressDataImpl;
import domain.Address;
import lombok.RequiredArgsConstructor;
import service.AddressService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class AddressServiceImpl implements AddressService {

	private final AddressDataImpl addressDataImpl;

	@Override
	public void saveAddress(Address address) {
		addressDataImpl.save(address);

	}

	@Override
	public void updateAddress(Address address) {
		addressDataImpl.update(address);

	}

	@Override
	public void deleteAddress(Long id) {
		addressDataImpl.delete(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Address findByIdAddress(Long id) {

		return addressDataImpl.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Address> findAllAddress() {

		return addressDataImpl.findAll();
	}

}
