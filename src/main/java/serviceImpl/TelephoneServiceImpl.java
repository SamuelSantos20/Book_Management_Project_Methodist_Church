package serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import domain.Telephone;
import org.springframework.transaction.annotation.Transactional;

import dataImpl.TelephoneDataImpl;
import lombok.RequiredArgsConstructor;
import service.TelephoneService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class TelephoneServiceImpl implements TelephoneService  {

	private final TelephoneDataImpl telephoneDataImpl;
	
	@Override
	public void saveTelephone(Telephone telephone) {
	
		telephoneDataImpl.save(telephone);
		
	}

	@Override
	public void updateTelephone(Telephone telephone) {
		telephoneDataImpl.update(telephone);
		
	}

	@Override
	public void deleteTelephone(Long id) {
		
		telephoneDataImpl.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Telephone findByIdTelephone(Long id) {
	
		return telephoneDataImpl.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Telephone> findAllTelephone() {
		
		return telephoneDataImpl.findAll();
	}

}
