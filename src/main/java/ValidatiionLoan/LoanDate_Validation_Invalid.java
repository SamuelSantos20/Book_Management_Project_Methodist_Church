package ValidatiionLoan;

import java.time.LocalDate;

import dto.LoanDto;
import validationInterface.Validator;

/**
 * Validador para a data de empréstimo de um livro.
 * Esta classe implementa a interface {@link Validator} e fornece uma
 * validação para garantir que a data de saída (loanDate) não seja anterior
 * à data atual.
 */
public class LoanDate_Validation_Invalid implements Validator {

    private LoanDto loanDto;

    /**
     * Construtor da classe LoanDateValidator.
     *
     * @param loanDto O objeto {@link LoanDto} que contém as informações do empréstimo,
     *                incluindo a data de saída a ser validada.
     */
    public LoanDate_Validation_Invalid(LoanDto loanDto) {
        this.loanDto = loanDto;
    }

    /**
     * Valida a data de saída do empréstimo.
     *
     * @return Uma mensagem de erro se a data de saída for anterior à data atual,
     *         ou null se a validação for bem-sucedida.
     */
    @Override
    public String validate() {
        // Verifica se a data do empréstimo é nula
        if (loanDto.getLoanDate() == null) {
            return "A data de saída não pode ser nula.";
        }

        LocalDate currentDate = LocalDate.now(); // Captura a data atual aqui

        if (loanDto.getLoanDate().isBefore(currentDate)) {
            return "A data de saída não pode ser anterior à data atual.";
        }

        return null; // Validação bem-sucedida
    }
}
