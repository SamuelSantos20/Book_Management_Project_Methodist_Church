package ValidationsPerson;

import domain.Person;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validação de CPF para a entidade Person.
 *
 * Esta classe implementa a interface Validator e realiza a validação do CPF do objeto Person,
 * garantindo que o CPF seja preenchido e contenha exatamente 11 caracteres.
 */
@AllArgsConstructor
public class Cpf_Validation implements Validator {

    private Person person;

    /**
     * Executa a validação do CPF de uma pessoa.
     *
     * Verifica se o campo CPF da pessoa é nulo ou possui um comprimento diferente de 11 caracteres.
     * Retorna uma mensagem de erro caso a validação falhe.
     *
     * @return Mensagem de erro se o CPF for inválido, ou null se o CPF for válido.
     */
    @Override
    public String validate() {
        if (person.getCpf() == null || person.getCpf().length() < 11 || person.getCpf().length() > 11) {
            return "O CPF DIGITADO É INVALIDO!";
        }

        return null;
    }
}
