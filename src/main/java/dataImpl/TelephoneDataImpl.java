package dataImpl;

import org.springframework.stereotype.Repository;

import data.TelephoneData;
import domain.Telephone;

@Repository
public class TelephoneDataImpl extends AbstractDao<Telephone, Long> implements TelephoneData {

}
