package ValidatiionLoan;

import dto.LoanDto;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validador para a data de entrega de um empréstimo de livro.
 * Esta classe verifica se a data de entrega foi fornecida.
 */
@AllArgsConstructor
public class ReturnDate_Validation implements Validator {

    private LoanDto loanDto;

    /**
     * Valida se a data de entrega do livro foi informada.
     *
     * @return Uma mensagem de erro caso a data de entrega não tenha sido informada,
     *         ou null se a data de entrega estiver válida.
     */
    @Override
    public String validate() {
        if (loanDto.getReturnDate() == null) {
            return "A DATA DE ENTREGA DO LIVRO É UM CAMPO OBRIGATÓRIO";
        }
        return null; // Retorna null se a data de entrega estiver informada
    }
}
