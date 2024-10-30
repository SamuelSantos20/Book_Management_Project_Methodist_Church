package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import util.NotificationUtil;

/**
 * CLASSE INDEX DO PROJETO. SERVE APESNAS PARA CHAMAR A REQUISIÇÃ DA PAGINA PRINCIPAL
 *  E PARA FAZER A TULIZAÇÃO DO QUNTITATIVO DE NOTIFICAÇÕES DO SISTEMA */

@Controller
@RequiredArgsConstructor
public class IndexController {

	
	private final NotificationUtil notificationUtil;

	@GetMapping("/www.com.metodista.gerenciamento.consultas")
	public ModelAndView Index() {

		notificationUtil.generateDailyNotifications();

		int numberNotification = notificationUtil.getNumberNotification();//metodo passa o valor exato de noticações registradas no sistema 

		ModelAndView mv = new ModelAndView();
		mv.addObject("number", numberNotification);//envia o numero exato
		mv.setViewName("html/index.html");
		return mv;
	}
}
