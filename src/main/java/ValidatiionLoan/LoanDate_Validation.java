package ValidatiionLoan;

import dto.LoanDto;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validador para a data de empréstimo de um livro.
 * Esta classe verifica se a data de empréstimo (data de saída) está presente no objeto LoanDto.
 */
@AllArgsConstructor
public class LoanDate_Validation implements Validator {

    private LoanDto loanDto;

    /**
     * Valida se a data de empréstimo é fornecida.
     *
     * @return Uma mensagem de erro caso a data de saída não esteja presente,
     *         ou null se a data de saída estiver preenchida corretamente.
     */
    @Override
    public String validate() {
        if (loanDto.getLoanDate() == null) {
            return "O CAMPO DE DATA DE SAIDA É OBRIGATÓRIO";
        }
        return null; // Retorna null se a data de saída estiver preenchida
    }
}
