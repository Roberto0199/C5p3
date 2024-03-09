package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot{

private List<String> mensajes = new ArrayList<>();
    @Override
    public String getBotUsername() {
        return "p3roberto";
    }

    @Override
    public String getBotToken() {
        return "7117623922:AAE4RWOKlGQUxZ8f_MQ3LPRx0cbsCd0XLbo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        //System.out.println(update);
        var usuario = update.getMessage().getFrom();
        var mensaje = update.getMessage().getText();
        var id = usuario.getId();

        mensajes.add(usuario+":"+mensaje);


        System.out.println("id:"+usuario.getId()+"nombre"+usuario.getFirstName()+ ""+usuario.getLastName()+ "Escribio:"+mensaje);

        sendText(id, mensaje);
        if (mensaje.contains("/desplegar")&& mensajes.size()>0){
            String lista = "";
            for (String mensaje1: mensajes){
                lista += mensaje1 + "\n";

            }
            sendText(id, lista);
        }
    }

        public void sendText(Long who, String what){
            SendMessage sm = SendMessage.builder()
                    .chatId(who.toString()) //Who are we sending a message to
                    .text(what).build();    //Message content
            try {
                execute(sm);                        //Actually sending the message
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);      //Any error will be printed here
            }
        }
    }

