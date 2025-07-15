package com.btg.fondos_api.notification;

public interface INotificacionService {
    void enviarSms(String destino, String mensaje);
    void enviarEmail(String destino, String mensaje);
}
