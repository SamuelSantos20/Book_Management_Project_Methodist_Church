package ValidationBook;

import dto.BookDto;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validador para a categoria de um livro.
 * Esta classe verifica se o tipo de categoria foi fornecido e não está vazio.
 */
@AllArgsConstructor
public class CategoryValidation implements Validator {

    private BookDto bookDto;

    /**
     * Valida se a categoria do livro foi informada.
     *
     * @return Uma mensagem de erro caso a categoria não tenha sido informada ou esteja vazia,
     *         ou null se a categoria estiver válida.
     */
    @Override
    public String validate() {
        if (bookDto.getTypeCategory() == null || bookDto.getTypeCategory().strip().isEmpty()) {
            return "A CATEGORIA É OBRIGATÓRIA"; // Mensagem de erro caso a categoria não seja válida
        }
        return null; // Retorna null se a categoria estiver informada e não estiver vazia
    }
}
