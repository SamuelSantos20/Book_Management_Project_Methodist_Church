package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Book;
import domain.Loan;
import domain.Telephone;
import dto.LoanDto;
import enums.States;

import lombok.RequiredArgsConstructor;
import serviceImpl.BookServiceImpl;
import serviceImpl.LoanServiceImpl;
import util.DateConverter;
import util.NotificationUtil;
import validationImpl.LoanValidationImpl;
import validationImpl.PersonValidationImpl;
import validationImpl.TelephoneValidationImpl;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Classe Controller responsável pelo gerenciamento das operações de empréstimos
 * (Loan) no sistema, incluindo registro, listagem, atualização, remoção e
 * busca.
 * 
 * Esta classe utiliza os serviços `BookServiceImpl`, `LoanServiceImpl`, e
 * `NotificationUtil` para gerenciar os dados de empréstimos e notificações. As
 * validações são executadas através das classes `LoanValidationImpl`,
 * `PersonValidationImpl` e `TelephoneValidationImpl`.
 */
@Controller
@RequiredArgsConstructor
public class LoanController {

	private final BookServiceImpl bookServiceImpl;

	private final LoanServiceImpl loanServiceImpl;

	private final NotificationUtil notificationUtil;

	/**
	 * Prepara a tela de registro de empréstimo.
	 *
	 * @return ModelAndView com o formulário de registro de empréstimo.
	 */
	@GetMapping("/www.com.metodista.gerenciamento.consultas/pre/loans/register/")
	public ModelAndView preRegisterLoan() {

		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("loan", new LoanDto());
			mv.addObject("books", bookServiceImpl.findAllBookDto());
			mv.addObject("States", States.values());
			mv.addObject("number", notificationUtil.getNumberNotification());
			mv.setViewName("html/registro_emprestimo.html");
			return mv;

		} catch (Exception e) {
			mv.setViewName("redirect:/error");
			return mv;
		}
	}

	/**
	 * Registra um novo empréstimo no sistema após a validação dos dados.
	 *
	 * @param loanDto Dados do empréstimo a serem registrados.
	 * @return ModelAndView com mensagem de sucesso ou erros de validação.
	 */
	@PostMapping("/www.com.metodista.gerenciamento.consultas/loans/register/")
	public ModelAndView registerLoan(LoanDto loanDto) {

		ModelAndView mv = new ModelAndView("html/registro_emprestimo.html");

		try {
			List<String> erros = new ArrayList<>();

			LoanValidationImpl loanValidationImpl = new LoanValidationImpl(loanDto);
			List<String> loanErros = loanValidationImpl.validate();
			if (loanErros != null && !loanErros.isEmpty()) {
				erros.addAll(loanErros);
			}

			// Validação da pessoa associada ao empréstimo
			PersonValidationImpl personValidationImpl = new PersonValidationImpl(loanDto.getPerson());
			List<String> personErros = personValidationImpl.validate();
			erros.addAll(personErros);

			// Validação dos telefones
			for (Telephone telephone : loanDto.getPerson().getTelefones()) {
				TelephoneValidationImpl telephoneValidationImpl = new TelephoneValidationImpl(telephone);
				List<String> telephoneErros = telephoneValidationImpl.validate();
				if (telephoneErros != null && !telephoneErros.isEmpty()) {
					erros.addAll(telephoneErros);
				}
			}

			if (!erros.isEmpty()) {
				prepare(mv, erros);
				return mv;
			}

			saveLoanUser(loanDto);

			mv.addObject("successMessage", "Empréstimo registrado no Sistema!");
			mv.addObject("number", notificationUtil.getNumberNotification());
			return prepare(mv, null);

		} catch (Exception e) {
			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;
		}
	}

	// Métodos auxiliares

	/**
	 * Configura os dados para a tela de empréstimo.
	 *
	 * @param mv    O ModelAndView a ser preparado.
	 * @param erros Lista de mensagens de erro, se houver.
	 * @return ModelAndView com dados e erros, caso existam.
	 */
	private ModelAndView prepare(ModelAndView mv, List<String> erros) {
		if (erros != null && !erros.isEmpty()) {
			mv.addObject("errorMessage", erros);
		}
		mv.addObject("loan", new LoanDto());
		mv.addObject("books", bookServiceImpl.findAllBookDto());
		mv.addObject("States", States.values());
		mv.addObject("number", notificationUtil.getNumberNotification());
		mv.setViewName("html/registro_emprestimo.html");
		return mv;
	}

	/**
	 * Salva um novo empréstimo no sistema.
	 *
	 * @param loanDto Dados do empréstimo a ser salvo.
	 * @throws Exception em caso de erro ao salvar o empréstimo.
	 */
	private void saveLoanUser(LoanDto loanDto) throws Exception {
		try {
			Loan loan = new Loan();
			Book book = bookServiceImpl.findByIdBook(loanDto.getBook().getId());

			loan.setLoanDate(loanDto.getLoanDate());
			loan.setReturnDate(loanDto.getReturnDate());
			loan.setBook(book);
			loan.setPerson(loanDto.getPerson());
			loan.setIsNotified(false);

			if (loanDto.getPerson().getTelefones() != null) {
				Telephone telephone = new Telephone();
				for (Telephone telephone2 : loan.getPerson().getTelefones()) {
					telephone.setNumber(telephone2.getNumber());
				}
				telephone.setPerson(loanDto.getPerson());
				loanDto.getPerson().getTelefones().add(telephone);
			}

			loanServiceImpl.saveLoan(loan);

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception("Erro ao salvar o Empréstimo: " + e.getMessage(), e);
		}
	}

	// Métodos para listagem, atualização, remoção e busca de empréstimos

	/**
	 * Lista todos os empréstimos.
	 *
	 * @return ModelAndView com lista de empréstimos e datas formatadas.
	 */
	@GetMapping("/www.com.metodista.gerenciamento.consultas/lista/Emprestimos")
	public ModelAndView listLoans() {
		ModelAndView mv = new ModelAndView();
		try {
			List<LoanDto> loanDtos = loanServiceImpl.findAllLoanDto();
			List<Date> departureDates = new ArrayList<>();
			List<Date> returnDates = new ArrayList<>();

			for (LoanDto loanDto : loanDtos) {
				departureDates.add(DateConverter.convertLocalDateToDate(loanDto.getLoanDate()));
				returnDates.add(DateConverter.convertLocalDateToDate(loanDto.getReturnDate()));
			}

			mv.addObject("Emprestimos", loanServiceImpl.findAllLoanDto());
			mv.addObject("departuredate", departureDates);
			mv.addObject("returnDate", returnDates);
			mv.addObject("number", notificationUtil.getNumberNotification());
			mv.setViewName("html/lista_emprestimos.html");
			return mv;

		} catch (Exception e) {
			mv.setViewName("redirect:/error");
			return mv;
		}
	}

	// Métodos para atualização de empréstimo...

	/**
	 * Remove um empréstimo pelo ID.
	 *
	 * @param id ID do empréstimo a ser removido.
	 * @return ModelAndView com mensagem de sucesso ou redirecionamento de erro.
	 */
	@PostMapping("/www.com.metodista.gerenciamento.consultas/loans/delete/Emprestimo")
	public ModelAndView deleteLoan(@RequestParam("id") Long id) {
		ModelAndView mv = new ModelAndView();
		try {
			loanServiceImpl.deleteLoan(id);
			mv.addObject("successMessage", "Empréstimo Deletado com Sucesso!");
			mv.setViewName("html/lista_emprestimos.html");
			return mv;

		} catch (Exception e) {
			mv.setViewName("redirect:/error");
			return mv;
		}
	}

	// Outros métodos como busca de empréstimos
}
