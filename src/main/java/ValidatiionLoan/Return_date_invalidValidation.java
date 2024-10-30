package ValidatiionLoan;

import java.time.LocalDate;

import dto.LoanDto;
import validationInterface.Validator;

/**
 * Validador para a data de devolução de um empréstimo de livro.
 * Esta classe verifica se a data de devolução é válida em relação à data atual.
 */
public class Return_date_invalidValidation implements Validator {

    private LoanDto loanDto;
    private LocalDate date = LocalDate.now();

    /**
     * Construtor para o validador de data de devolução.
     *
     * @param loanDto O objeto LoanDto que contém as informações do empréstimo.
     */
    public Return_date_invalidValidation(LoanDto loanDto) {
        super();
        this.loanDto = loanDto;
    }

    /**
     * Valida se a data de devolução não é anterior à data atual.
     *
     * @return Uma mensagem de erro caso a data de devolução seja anterior à data atual,
     *         ou null se a data de devolução estiver válida.
     */
    @Override
    public String validate() {
        if (loanDto.getReturnDate().isBefore(date)) {
            return "A data de devolução não pode ser anterior à data atual.";
        }
        return null; // Retorna null se a data de devolução for válida
    }
}
