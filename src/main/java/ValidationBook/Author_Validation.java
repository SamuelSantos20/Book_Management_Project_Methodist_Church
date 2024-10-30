package ValidationBook;

import dto.BookDto;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validador para o autor de um livro.
 * Esta classe verifica se o campo do autor foi fornecido e não está vazio.
 */
@AllArgsConstructor
public class Author_Validation implements Validator {

    private BookDto bookDto;

    /**
     * Valida se o autor do livro foi informado.
     *
     * @return Uma mensagem de erro caso o autor não tenha sido informado ou esteja vazio,
     *         ou null se o autor estiver válido.
     */
    @Override
    public String validate() {
        if (bookDto.getAuthor() == null || bookDto.getAuthor().strip().isEmpty()) {
            return "O CAMPO AUTOR É OBRIGATÓRIO";
        }
        return null; // Retorna null se o autor estiver informado e não estiver vazio
    }
}
