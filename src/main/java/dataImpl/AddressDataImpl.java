package dataImpl;

import org.springframework.stereotype.Repository;

import data.AddressData;
import domain.Address;

@Repository
public class AddressDataImpl extends AbstractDao<Address, Long> implements AddressData{

}
