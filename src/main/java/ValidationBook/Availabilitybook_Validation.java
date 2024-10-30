package ValidationBook;

import dto.BookDto;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validador para a disponibilidade de um livro.
 * Esta classe verifica se o campo de disponibilidade foi fornecido e não está vazio.
 */
@AllArgsConstructor
public class Availabilitybook_Validation implements Validator {

    private BookDto bookDto;

    /**
     * Valida se a disponibilidade do livro foi informada.
     *
     * @return Uma mensagem de erro caso a disponibilidade não tenha sido informada ou esteja vazia,
     *         ou null se a disponibilidade estiver válida.
     */
    @Override
    public String validate() {
        if (bookDto.getAvailabilitybook() == null || bookDto.getAvailabilitybook().strip().isEmpty()) {
            return "DISPONIBILIDADE NÃO ADICIONADA";
        }
        return null; // Retorna null se a disponibilidade estiver informada e não estiver vazia
    }
}
