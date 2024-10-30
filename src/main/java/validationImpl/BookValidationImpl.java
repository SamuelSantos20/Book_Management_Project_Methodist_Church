package validationImpl;

import java.util.ArrayList;
import java.util.List;

import ValidationBook.Author_Validation;
import ValidationBook.Availabilitybook_Validation;
import ValidationBook.CategoryValidation;
import ValidationBook.Isb_Validation;
import ValidationBook.Title_Validation;
import dto.BookDto;
import lombok.RequiredArgsConstructor;
import validationInterface.Validator;

/**
 * Implementação da validação de um livro.
 * 
 * Esta classe agrupa diferentes validações específicas para um objeto BookDto,
 * utilizando o padrão de design Validator. Cada validação verifica uma parte do 
 * BookDto, como autor, categoria, ISBN, título e disponibilidade.
 */
@RequiredArgsConstructor
public class BookValidationImpl {

    private final List<Validator> validators = new ArrayList<>(); // Lista de validadores para o BookDto

    /**
     * Construtor que inicializa os validadores específicos para o BookDto.
     *
     * @param bookDto O objeto DTO do livro que contém os dados a serem validados.
     */
    public BookValidationImpl(BookDto bookDto) {
        validators.add(new Author_Validation(bookDto));
        validators.add(new CategoryValidation(bookDto));
        validators.add(new Isb_Validation(bookDto));
        validators.add(new Title_Validation(bookDto));
        validators.add(new Availabilitybook_Validation(bookDto));
    }

    /**
     * Executa todas as validações configuradas e retorna uma lista de erros.
     *
     * @return Uma lista de mensagens de erro, uma para cada validação falha. 
     *         Retorna uma lista vazia se todas as validações forem bem-sucedidas.
     */
    public List<String> validate() {
        List<String> erros = new ArrayList<>();

        for (Validator validator : validators) {
            String erro = validator.validate();
            if (erro != null) {
                erros.add(erro); // Adiciona o erro à lista se a validação falhar
            }
        }

        return erros;
    }
}
