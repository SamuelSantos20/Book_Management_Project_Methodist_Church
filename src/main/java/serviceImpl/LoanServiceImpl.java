package serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dataImpl.LoanDataImpl;
import domain.Loan;
import dto.LoanDto;
import lombok.RequiredArgsConstructor;
import service.LoanService;

/**
 * Implementação do serviço LoanService para manipular dados de empréstimos, incluindo operações CRUD.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class LoanServiceImpl implements LoanService {

    private final LoanDataImpl loanDataImpl;

    /**
     * Salva um novo empréstimo no banco de dados.
     *
     * @param loan a entidade Loan a ser salva.
     */
    @Override
    public void saveLoan(Loan loan) {
        loanDataImpl.save(loan);
    }

    /**
     * Atualiza um empréstimo existente no banco de dados.
     *
     * @param loan a entidade Loan a ser atualizada.
     */
    @Override
    public void updateLoan(Loan loan) {
        loanDataImpl.update(loan);
    }

    /**
     * Exclui um empréstimo do banco de dados com base em seu ID.
     *
     * @param id o ID do empréstimo a ser excluído.
     */
    @Override
    public void deleteLoan(Long id) {
        loanDataImpl.delete(id);
    }

    /**
     * Encontra um empréstimo pelo ID no banco de dados.
     *
     * @param id o ID do empréstimo a ser encontrado.
     * @return a entidade Loan encontrada, ou null se não existir.
     */
    @Override
    public Loan findByIdLoan(Long id) {
        return loanDataImpl.findById(id);
    }

    /**
     * Retorna todos os empréstimos no banco de dados.
     *
     * @return uma lista de entidades Loan.
     */
    @Override
    public List<Loan> findAllLoan() {
        return loanDataImpl.findAll();
    }

    /**
     * Retorna uma lista de empréstimos com base na data de devolução.
     *
     * @param localDate a data de devolução a ser usada como critério de busca.
     * @return uma lista de empréstimos que têm a data de devolução correspondente.
     */
    @Override
    public List<Loan> findByReturnDate(LocalDate localDate) {
        return loanDataImpl.findByReturnDate(localDate);
    }

    /**
     * Retorna todos os empréstimos no banco de dados, convertidos para o formato LoanDto.
     *
     * @return uma lista de objetos LoanDto contendo informações resumidas dos empréstimos.
     */
    @Override
    public List<LoanDto> findAllLoanDto() {
        List<Loan> loans = loanDataImpl.findAll();
        return loans.stream()
                .map(l -> new LoanDto(l.getId(), l.getLoanDate(), l.getReturnDate(), l.getPerson(), l.getBook()))
                .collect(Collectors.toList());
    }

    /**
     * Pesquisa empréstimos com base em uma palavra-chave que pode corresponder a diversos atributos.
     *
     * @param texto a palavra-chave de pesquisa.
     * @return uma lista de empréstimos que correspondem aos critérios de pesquisa.
     */
    @Override
    public List<Loan> SerachLoan(String texto) {
        return loanDataImpl.SerachLoan(texto);
    }

    /**
     * Pesquisa empréstimos com base em uma palavra-chave e retorna os resultados no formato LoanDto.
     *
     * @param texto a palavra-chave de pesquisa.
     * @return uma lista de LoanDto contendo os resultados da pesquisa.
     */
    @Override
    public List<LoanDto> SerachLoanDto(String texto) {
        List<Loan> loans = loanDataImpl.SerachLoan(texto);
        return loans.stream()
                .map(l -> new LoanDto(l.getId(), l.getLoanDate(), l.getReturnDate(), l.getPerson(), l.getBook()))
                .collect(Collectors.toList());
    }

    /**
     * Encontra um empréstimo pelo ID e retorna o resultado como um objeto LoanDto.
     *
     * @param id o ID do empréstimo a ser encontrado.
     * @return um objeto LoanDto correspondente ao empréstimo encontrado, ou um objeto LoanDto vazio se não for encontrado.
     */
    @Override
    public LoanDto findByIdLoanDto(Long id) {
        Loan loan = loanDataImpl.findById(id);
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);

        List<LoanDto> loanDtos = loans.stream()
                .map(l -> new LoanDto(l.getId(), l.getLoanDate(), l.getReturnDate(), l.getPerson(), l.getBook()))
                .collect(Collectors.toList());

        LoanDto loanDto = new LoanDto();

        for (LoanDto loanDto2 : loanDtos) {
            loanDto = loanDto2;
        }

        return loanDto;
    }

    /**
     * Retorna uma lista de empréstimos associados a um livro específico.
     *
     * @param id o ID do livro para o qual os empréstimos devem ser encontrados.
     * @return uma lista de empréstimos associados ao livro.
     */
    @Override
    public List<Loan> findByBook(Long id) {
        return loanDataImpl.findByBook(id);
    }
}
