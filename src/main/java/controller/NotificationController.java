package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import serviceImpl.NotificationServiceImpl;
import util.NotificationUtil;

/**
 * Classe `NotificationController` para gerenciar notificações no sistema.
 * Responsável por listar e deletar notificações, além de atualizar o número de notificações.
 */
@Controller
@RequiredArgsConstructor
public class NotificationController {

    // Injeta o serviço de notificações para acessar os métodos de negócio
    private final NotificationServiceImpl notificationServiceImpl;

    // Injeta o utilitário de notificações para obter o número de notificações
    private final NotificationUtil notificationUtil;

    /**
     * Método GET para listar todas as notificações.
     * Mapeado para o endpoint "/www.com.metodista.gerenciamento.consultas/notificacoes".
     *
     * @return ModelAndView com a lista de notificações e o número de notificações.
     */
    @GetMapping("/www.com.metodista.gerenciamento.consultas/notificacoes")
    public ModelAndView listNotificacoes() {
        ModelAndView mv = new ModelAndView();

        try {
            mv.addObject("notification", notificationServiceImpl.findAllNotification());
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.setViewName("html/notificacoes.html");
            return mv;

        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }

    /**
     * Método POST para deletar uma notificação específica.
     * Mapeado para o endpoint "/www.com.metodista.gerenciamento.consultas/delete/notificacoes".
     *
     * @param id ID da notificação a ser deletada.
     * @return ModelAndView atualizado com a lista de notificações após a exclusão.
     */
    @PostMapping("/www.com.metodista.gerenciamento.consultas/delete/notificacoes")
    public ModelAndView deleteNotificacao(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();

        try {
            notificationServiceImpl.deleteNotification(id);
            mv.addObject("notification", notificationServiceImpl.findAllNotification());
            mv.addObject("number", notificationUtil.getNumberNotification());
            mv.setViewName("html/notificacoes.html");
            return mv;

        } catch (Exception e) {
            mv.setViewName("redirect:/error");
            return mv;
        }
    }
}
