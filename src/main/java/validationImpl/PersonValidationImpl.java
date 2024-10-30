package validationImpl;

import java.util.ArrayList;
import java.util.List;

import ValidationsPerson.Cpf_Validation;
import ValidationsPerson.Name_Validation;
import domain.Person;
import lombok.RequiredArgsConstructor;
import validationInterface.Validator;

/**
 * Implementação do validador de pessoa.
 * Esta classe é responsável por validar as informações de uma pessoa
 * utilizando diferentes validadores, como CPF e nome.
 */
@RequiredArgsConstructor
public class PersonValidationImpl {

    private List<Validator> validators = new ArrayList<>();

    /**
     * Construtor da classe PersonValidationImpl.
     *
     * @param person O objeto {@link Person} que contém as informações da pessoa a ser validada.
     *               Os validadores são inicializados com base nas informações fornecidas.
     */
    public PersonValidationImpl(Person person) {
        validators.add(new Cpf_Validation(person));
        validators.add(new Name_Validation(person));
    }

    /**
     * Valida as informações da pessoa.
     *
     * @return Uma lista de mensagens de erro, caso existam validações que falharam.
     *         Retorna uma lista vazia se todas as validações forem bem-sucedidas.
     */
    public List<String> validate() {
        List<String> erros = new ArrayList<>();

        for (Validator validator : validators) {
            String erro = validator.validate();
            if (erro != null) {
                erros.add(erro);
            }
        }

        return erros; // Retorna a lista de erros encontrados durante a validação
    }
}
