package util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Loan;
import domain.Notification;
import domain.Person;
import serviceImpl.LoanServiceImpl;
import serviceImpl.NotificationServiceImpl;

@Service
public class NotificationUtil {

    private final LoanServiceImpl loanServiceImpl;
    private final NotificationServiceImpl notificationServiceImpl;

    @Autowired
    public NotificationUtil(LoanServiceImpl loanServiceImpl, NotificationServiceImpl notificationServiceImpl) {
        this.loanServiceImpl = loanServiceImpl;
        this.notificationServiceImpl = notificationServiceImpl;
    }

    /**
     * Gera notificações diárias para empréstimos que devem ser devolvidos hoje.
     * 
     * @return Lista de notificações geradas.
     */
    public List<Notification> generateDailyNotifications() {
        LocalDate today = LocalDate.now();
        List<Notification> notifications = new ArrayList<>();

        List<Loan> loansDueToday = loanServiceImpl.findByReturnDate(today);

        for (Loan loan : loansDueToday) {
            // Verifica se a data de devolução é hoje e se já foi notificado
            if (loan.getReturnDate().isEqual(today) && !loan.getIsNotified()) {
                Person person = loan.getPerson();

                // Cria a mensagem de notificação
                String message = String.format("Usuário %s (CPF: %s) deve devolver o livro hoje.", person.getName(), person.getCpf());

                Notification notification = new Notification(person.getName(), person.getCpf(), message);
                notifications.add(notification);

                // Salva a notificação no banco de dados
                notificationServiceImpl.saveNotification(notification);

                // Marca o empréstimo como notificado
                loan.setIsNotified(true);
                loanServiceImpl.updateLoan(loan);
            }
        }
        return notifications;
    }

    /**
     * Conta o número total de notificações existentes.
     * 
     * @return Número total de notificações.
     */
    public int getNumberNotification() {
        return notificationServiceImpl.findAllNotification().size(); // Retorna o tamanho da lista de notificações
    }
}
