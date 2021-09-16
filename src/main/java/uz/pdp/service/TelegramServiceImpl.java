package uz.pdp.service;


import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TelegramServiceImpl implements TelegramService {

    private String providerToken = "398062629:TEST:999999999_F91D8F69C042267444B74CC0B3C747757EB0E065";

    @Override
    public SendInvoice sendInvoice(Update update) {
        SendInvoice sendInvoice = new SendInvoice();
        sendInvoice.setChatId(update.getMessage().getChat().getId().intValue());
        sendInvoice.setTitle("To'lov");
        sendInvoice.setDescription("\nTo'lovni amalga oshiring.");
        sendInvoice.setPayload(UUID.randomUUID().toString());
        List<LabeledPrice> labeledPrices = new ArrayList<>();
        Double amount = 5000D;
        Integer integer = amount.intValue();
        labeledPrices.add(new LabeledPrice("label2", integer * 100));
        sendInvoice.setProviderToken(providerToken);
        sendInvoice.setStartParameter("busycube");
        sendInvoice.setCurrency("UZS");
        sendInvoice.setPrices(labeledPrices);
        return sendInvoice;
    }
}
