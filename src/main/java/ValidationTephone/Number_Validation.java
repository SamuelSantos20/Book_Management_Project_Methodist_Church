package ValidationTephone;

import domain.Telephone;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validação de Número de Telefone.
 *
 * Esta classe implementa a interface Validator e valida o campo de número de telefone
 * de um objeto Telephone, garantindo que ele tenha exatamente 11 dígitos.
 */
@AllArgsConstructor
public class Number_Validation implements Validator {

    private Telephone telephone;

    /**
     * Executa a validação do número de telefone.
     *
     * Verifica se o número de telefone é nulo ou possui uma quantidade diferente de 11 dígitos.
     * Retorna uma mensagem de erro caso a validação falhe.
     *
     * @return Mensagem de erro se o número de telefone for inválido, ou null se o número for válido.
     */
    @Override
    public String validate() {
        if (telephone.getNumber() == null || telephone.getNumber().strip().length() != 11) {
            return "O NÚMERO DE TELEFONE DIGITADO É INVÁLIDO!";
        }
        return null;
    }
}
