package ValidationBook;

import dto.BookDto;
import lombok.AllArgsConstructor;
import validationInterface.Validator;

/**
 * Validador para verificar a validade do código ISBN de um livro.
 * Esta classe valida se o código ISBN do livro está presente, 
 * tem o tamanho correto e não é uma string vazia ou apenas espaços.
 */
@AllArgsConstructor
public class Isb_Validation implements Validator {

    private BookDto bookDto; // Objeto DTO que contém os dados do livro a ser validado

    /**
     * Valida o código ISBN do livro.
     *
     * @return Uma mensagem de erro se o código ISBN for nulo, 
     *         tiver mais de 13 caracteres ou for uma string vazia.
     *         Retorna null se o código ISBN for válido.
     */
    @Override
    public String validate() {
        // Verifica se o ISBN é nulo, tem mais de 13 caracteres ou é uma string vazia
        if (bookDto.getIsbn() == null || bookDto.getIsbn().length() > 13 || bookDto.getIsbn().strip() == null) {
            return "CODIGO ISBN INVALIDO!"; // Mensagem de erro se o ISBN não for válido
        }

        return null; // Retorna null se o ISBN for válido
    }
}
