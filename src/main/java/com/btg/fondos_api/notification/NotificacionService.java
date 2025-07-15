package com.btg.fondos_api.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificacionService implements INotificacionService{
    @Override
    public void enviarSms(String destino, String fondo) {
        log.info("Enviando sms al numero {} para notificar suscripcion al fondo: {}", destino, fondo);
    }

    @Override
    public void enviarEmail(String destino, String fondo) {
        log.info("Enviando email al correo {} para notificar suscripcion al fondo: {}", destino, fondo);
    }
}
