package validationImpl;

import java.util.ArrayList;
import java.util.List;

import ValidationTephone.Number_Validation;
import domain.Telephone;
import lombok.RequiredArgsConstructor;
import validationInterface.Validator;

/**
 * Implementação de validações de telefone.
 * 
 * A classe agrupa diversas validações para o objeto Telephone, garantindo que 
 * as regras de negócio relacionadas a dados de telefone sejam atendidas.
 */
@RequiredArgsConstructor
public class TelephoneValidationImpl {

    private List<Validator> validators = new ArrayList<>(); // Lista de validadores para o Telephone

    /**
     * Construtor que inicializa os validadores específicos para o Telephone.
     *
     * @param telephone O objeto de telefone que contém os dados a serem validados.
     */
    public TelephoneValidationImpl(Telephone telephone) {
        validators.add(new Number_Validation(telephone)); // Adiciona a validação do número de telefone
    }

    /**
     * Executa todas as validações configuradas e retorna uma lista de erros.
     *
     * @return Uma lista de mensagens de erro, uma para cada validação que falhar. 
     *         Retorna uma lista vazia se todas as validações forem bem-sucedidas.
     */
    public List<String> validate() {
        List<String> erros = new ArrayList<>();

        for (Validator validator : validators) {
            String erro = validator.validate();
            if (erro != null) {
                erros.add(erro); // Adiciona o erro à lista caso a validação falhe
            }
        }

        return erros;
    }
}
