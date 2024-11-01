package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import serviceImpl.PersonSeviceImpl;
import util.NotificationUtil;

/**
 * Classe `PersonController` para gerenciar as operações relacionadas a pessoas no sistema.
 * Possui métodos para listar e buscar pessoas com base em texto de pesquisa.
 */
@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonSeviceImpl personSeviceImpl;  // Serviço para operações com pessoas
    private final NotificationUtil notificationUtil;  // Utilitário para notificações

    /**
     * Método GET para listar todas as pessoas.
     * Mapeado para o endpoint "/www.com.metodista.gerenciamento.consultas/list/person".
     *
     * @return ModelAndView com a lista de pessoas e o número de notificações.
     */
    @GetMapping("/www.com.metodista.gerenciamento.consultas/list/person")
    public ModelAndView listPersons() {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("persons", personSeviceImpl.findAllPersonDto());
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.setViewName("html/usuarios.html");
            return mv;

        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    
    @PostMapping("/www.com.metodista.gerenciamento.consultas/list/serach")
	public ModelAndView Serach(@RequestParam("text") String text) {
		ModelAndView mv = new ModelAndView();
		try {

			mv.addObject("persons", personSeviceImpl.SerachPersonDto(text));
			mv.addObject("number", notificationUtil.getNumberNotification());
			mv.setViewName("html/usuarios.html");
			return mv;

		} catch (Exception e) {

			mv.setViewName("redirect:/error");
			return mv;

		}
	}


    
    
    /**
     * Método POST para buscar pessoas com base em texto de pesquisa.
     * Mapeado para o endpoint "/www.com.metodista.gerenciamento.consultas/list/search".
     *
     * @param text Texto de pesquisa para buscar pessoas.
     * @return ModelAndView com o resultado da pesquisa e o número de notificações.
     */
    @PostMapping("/www.com.metodista.gerenciamento.consultas/list/search")
    public ModelAndView search(@RequestParam("text") String text) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("persons", personSeviceImpl.SerachPersonDto(text));
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.setViewName("html/usuarios.html");
            return mv;

        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }
}
