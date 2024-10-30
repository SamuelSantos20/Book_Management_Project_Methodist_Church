package validationImpl;

import java.util.ArrayList;
import java.util.List;

import ValidatiionLoan.LoanDate_Validation;
import ValidatiionLoan.LoanDate_Validation_Invalid;
import ValidatiionLoan.ReturnDate_Validation;
import ValidatiionLoan.Return_date_invalidValidation;
import dto.LoanDto;
import lombok.RequiredArgsConstructor;
import validationInterface.Validator;

/**
 * Implementação de validações de empréstimo.
 * 
 * A classe agrupa diversas validações para o objeto LoanDto, incluindo 
 * validações de datas de empréstimo e de retorno, para garantir que 
 * as regras de negócio relacionadas a empréstimos sejam atendidas.
 */
@RequiredArgsConstructor
public class LoanValidationImpl {

    private List<Validator> validators = new ArrayList<>(); // Lista de validadores para o LoanDto

    /**
     * Construtor que inicializa os validadores específicos para o LoanDto.
     *
     * @param loanDto O objeto DTO de empréstimo que contém os dados a serem validados.
     */
    public LoanValidationImpl(LoanDto loanDto) {
        validators.add(new LoanDate_Validation(loanDto));
        validators.add(new ReturnDate_Validation(loanDto));
        validators.add(new Return_date_invalidValidation(loanDto));
        validators.add(new LoanDate_Validation_Invalid(loanDto));
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
