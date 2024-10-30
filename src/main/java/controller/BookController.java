package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Book;
import domain.Loan;
import dto.BookDto;
import enums.AvailabilityBook;
import enums.CategoryBook;

import lombok.RequiredArgsConstructor;
import serviceImpl.BookServiceImpl;
import serviceImpl.LoanServiceImpl;
import util.Gerador_IsBn;
import util.NotificationUtil;
import validationImpl.BookValidationImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A classe `BookController` é responsável por gerenciar ações relacionadas a livros, incluindo:
 * <ul>
 *   <li>Salvar um novo livro (SAVE)</li>
 *   <li>Listar todos os livros (LIST)</li>
 *   <li>Atualizar um livro existente (UPDATE)</li>
 *   <li>Excluir um livro (DELETE)</li>
 * </ul>
 * Esta classe utiliza os serviços `BookServiceImpl` e `LoanServiceImpl` para operações de banco de dados.
 */
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookServiceImpl; // Classe de serviço para operações de livros
    private final LoanServiceImpl loanServiceImpl; // Classe de serviço para operações de empréstimos
    
    private Gerador_IsBn gerador_IsBn = new Gerador_IsBn(); // Gerador de código ISBN único para cada livro
    private final NotificationUtil notificationUtil; // Utilitário para atualizar o número de notificações nas páginas

    /**
     * Mapeia a requisição para a página de registro de livro.
     *
     * @return ModelAndView com dados para preencher a página de registro de livro.
     */
    @GetMapping("/www.com.metodista.gerenciamento.consultas/pre/add-book")
    public ModelAndView preRegisterBook() {
        ModelAndView mv = new ModelAndView();

        try {
            mv.addObject("book", new BookDto());
            mv.addObject("number", notificationUtil.getNumberNotification()); // Atualiza o número de notificações
            mv.addObject("category", CategoryBook());
            mv.addObject("isbn", gerador_IsBn.GeradorCodigo());
            mv.addObject("Avalible", AvailabilityBook());
            mv.setViewName("html/adicionar-livro.html");
            return mv;
        } catch (Exception e) {
            System.out.println(e);
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    /**
     * Realiza o registro de um novo livro no sistema.
     *
     * @param bookDto Dados do livro encapsulados em BookDto.
     * @return ModelAndView indicando sucesso ou erro no registro.
     */
    @PostMapping("/www.com.metodista.gerenciamento.consultas/add-book")
    public ModelAndView RegisterBook(BookDto bookDto) {
        ModelAndView mv = new ModelAndView("html/adicionar-livro.html");

        // Verifica se o livro já está no sistema
        List<BookDto> bookDtos = bookServiceImpl.SerachBookDto(bookDto.getTitle());
        if (!bookDtos.isEmpty()) {
            mv.addObject("errorMessage", "O Livro já se encontra adicionado no sistema!");
            mv.addObject("book", new BookDto());
            mv.addObject("category", CategoryBook());
            mv.addObject("available", AvailabilityBook());
            mv.addObject("isbn", gerador_IsBn.GeradorCodigo());
            return mv;
        }

        // Validações do livro
        BookValidationImpl bookValidation = new BookValidationImpl(bookDto);
        List<String> erros = bookValidation.validate();
        if (!erros.isEmpty()) {
            return prepareModelAndView(mv, erros); // Método para preparar resposta com erros
        }

        // Tenta salvar o livro no sistema
        try {
            saveBook(bookDto);
            mv.addObject("successMessage", "Livro adicionado ao Sistema!");
            return prepareModelAndView(mv, null);
        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    /**
     * Prepara a ModelAndView com possíveis mensagens de erro.
     *
     * @param mv ModelAndView a ser preparada.
     * @param erros Lista de erros, se houver.
     * @return ModelAndView configurada.
     */
    private ModelAndView prepareModelAndView(ModelAndView mv, List<String> erros) {
        if (erros != null && !erros.isEmpty()) {
            mv.addObject("errorMessage", erros);
        }
        mv.addObject("book", new BookDto());
        mv.addObject("category", CategoryBook());
        mv.addObject("Avalible", AvailabilityBook());
        mv.addObject("Isbn", gerador_IsBn.GeradorCodigo());
        return mv;
    }

    /**
     * Salva o livro no sistema.
     *
     * @param bookDto Dados do livro a ser salvo.
     * @throws Exception se ocorrer algum erro ao salvar o livro.
     */
    private void saveBook(BookDto bookDto) throws Exception {
        try {
            Book book = new Book();
            book.setAuthor(bookDto.getAuthor());
            book.setIsbn(bookDto.getIsbn());
            book.setTitle(bookDto.getTitle());
            book.setTypeCategory(bookDto.getTypeCategory());
            book.setAvailabilitybook(bookDto.getAvailabilitybook());
            bookServiceImpl.saveBook(book);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar o livro: " + e.getMessage(), e);
        }
    }

    /**
     * Lista todos os livros no sistema.
     *
     * @return ModelAndView com a lista de livros.
     */
    @GetMapping("/www.com.metodista.gerenciamento.consultas/list/books")
    public ModelAndView ListBooks() {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("books", bookServiceImpl.findAllBookDto());
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.setViewName("html/livros.html");
            return mv;
        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    /**
     * Prepara a atualização de um livro.
     *
     * @param id ID do livro a ser atualizado.
     * @return ModelAndView com os dados do livro para edição.
     */
    @PostMapping("/www.com.metodista.gerenciamento.consultas/pre/edit/book")
    public ModelAndView preUpdateBook(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();

        try {
            BookDto bookDto = bookServiceImpl.findByIdBookDto(id);
            mv.addObject("book", bookDto);
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.addObject("category", CategoryBook());
            mv.addObject("Isbn", gerador_IsBn.GeradorCodigo());
            mv.addObject("Avalible", AvailabilityBook());
            mv.setViewName("html/adicionar-livro.html");
            return mv;
        } catch (Exception e) {
            System.out.println(e);
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    /**
     * Realiza a atualização dos dados de um livro.
     *
     * @param bookDto Dados do livro a serem atualizados.
     * @return ModelAndView indicando sucesso ou erro na atualização.
     */
    @PostMapping("/www.com.metodista.gerenciamento.consultas/update/book")
    public ModelAndView UpdateBook(BookDto bookDto) {
        ModelAndView mv = new ModelAndView("html/adicionar-livro.html");

        BookValidationImpl bookValidation = new BookValidationImpl(bookDto);
        List<String> erros = bookValidation.validate();

        if (!erros.isEmpty()) {
            return prepareModelAndView(mv, erros);
        }

        try {
            saveBook(bookDto);
            mv.addObject("successMessage", "Livro atualizado com sucesso!");
            return prepareModelAndView(mv, null);
        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    /**
     * Exclui um livro do sistema.
     *
     * @param id ID do livro a ser excluído.
     * @return ModelAndView indicando sucesso ou erro na exclusão.
     */
    @PostMapping("/www.com.metodista.gerenciamento.consultas/delete/book")
    public ModelAndView DeleteBook(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();

        List<Loan> loans = loanServiceImpl.findByBook(id);
        if (!loans.isEmpty()) {
            mv.addObject("errorMessage", "Livro associado a um Empréstimo!");
            mv.addObject("books", bookServiceImpl.findAllBookDto());
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.setViewName("html/livros.html");
            return mv;
        }

        try {
            bookServiceImpl.deleteBook(id);
            mv.addObject("successMessage", "Livro Deletado do Sistema!");
            mv.addObject("books", bookServiceImpl.findAllBookDto());
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.setViewName("html/livros.html");
            return mv;
        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    /**
     * @return Valores de `CategoryBook` para exibir categorias no front-end.
     */
    public CategoryBook[] CategoryBook() {
        return CategoryBook.values();
    }

    /**
     * @return Valores de `AvailabilityBook` para exibir disponibilidade no front-end.
     */
    public AvailabilityBook[] AvailabilityBook() {
        return AvailabilityBook.values();
    }
}
