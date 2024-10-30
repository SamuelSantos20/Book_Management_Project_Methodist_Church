package ValidationBook;

import dto.BookDto;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validador para verificar a validade do título de um livro.
 * Esta classe valida se o título do livro está presente e não é uma string vazia.
 */
@AllArgsConstructor
public class Title_Validation implements Validator {

    private BookDto bookDto; // Objeto DTO que contém os dados do livro a ser validado

    /**
     * Valida o título do livro.
     *
     * @return Uma mensagem de erro se o título for nulo ou uma string vazia.
     *         Retorna null se o título for válido.
     */
    @Override
    public String validate() {
        // Verifica se o título é nulo ou se é uma string vazia
        if (bookDto.getTitle() == null || bookDto.getTitle().strip() == null) {
            return "O CAMPO TITULO É OBRIGATÓRIO!"; // Mensagem de erro se o título não for válido
        }

        return null; // Retorna null se o título for válido
    }
}
