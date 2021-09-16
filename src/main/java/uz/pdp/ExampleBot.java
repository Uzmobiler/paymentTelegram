package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.payments.SuccessfulPayment;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.service.TelegramService;
import uz.pdp.service.TelegramServiceImpl;

public class ExampleBot extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "aqllig22bot";
    }

    public String getBotToken() {
        return "1756057236:AAH90Oj_dGDXe0e6HMpxfhBhOJCbepOS2jo";
    }

    private TelegramService telegramService = new TelegramServiceImpl();

    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                Message message = update.getMessage();
                if (message.hasText()) {
                    String text = message.getText();
                    if (text.equals("/start")) {
                        execute(telegramService.sendInvoice(update));
                    }
                } else if (message.hasSuccessfulPayment()) {
                    SuccessfulPayment payment = update.getMessage().getSuccessfulPayment();
                    successfulPayment(payment, update);
                }
            } else if (update.hasPreCheckoutQuery()) {
                PreCheckoutQuery preCheckoutQuery = update.getPreCheckoutQuery();
                AnswerPreCheckoutQuery answerPreCheckoutQuery = new AnswerPreCheckoutQuery(preCheckoutQuery.getId(), true);
                execute(answerPreCheckoutQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void successfulPayment(SuccessfulPayment payment, Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("I: " + payment.getTotalAmount());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
