package ValidationsPerson;

import domain.Person;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validação de Nome para a entidade Person.
 *
 * Esta classe implementa a interface Validator e realiza a validação do campo de nome 
 * do objeto Person, garantindo que o nome não seja nulo nem esteja em branco.
 */
@AllArgsConstructor
public class Name_Validation implements Validator {

    private Person person;

    /**
     * Executa a validação do campo de nome de uma pessoa.
     *
     * Verifica se o campo de nome da pessoa é nulo ou contém apenas espaços em branco.
     * Retorna uma mensagem de erro caso a validação falhe.
     *
     * @return Mensagem de erro se o nome for inválido, ou null se o nome for válido.
     */
    @Override
    public String validate() {
        if (person.getName() == null || person.getName().strip().isEmpty()) {
            return "O CAMPO NOME DEVE SER PREENCHIDO!";
        }
        return null;
    }
}
