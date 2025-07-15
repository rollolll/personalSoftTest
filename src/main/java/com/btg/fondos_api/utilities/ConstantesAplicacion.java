package com.btg.fondos_api.utilities;

/**
 * Clase donde se definen los path/rutas de la aplicación.
 *
 * @since 1.0.0
 */
public class ConstantesAplicacion {

    /** Constructor privado para evitar instanciación */
    private ConstantesAplicacion() {
        throw new IllegalStateException("Clase de constantes de aplicacion");
    }

    // ------------------- Cliente Controller ---------------//
    /** Path request de cliente */
    public static final String REQUEST_PATH_CLIENTE = "clientes";
    /** Path para crear un cliente */
    public static final String PATH_CREAR_CLIENTE = "/crear";
    /** Path para obtener los clientes registrados */
    public static final String PATH_OBTENER_CLIENTE = "/obtener-clientes";
    /** Path para modificar un cliente */
    public static final String PATH_MODIFICAR_CLIENTE = "/modificar-cliente";
    /** Path para acreditarle saldo a un cliente */
    public static final String PATH_ACREDITAR_SALDO_CLIENTE = "/acreditarSaldo";

    // ------------------- Fondo Controller ---------------//
    /** Path request de cliente */
    public static final String REQUEST_PATH_FONDO = "fondos";
    /** Path para obtener los fondos registrados */
    public static final String PATH_OBTENER_FONDOS = "/obtener-fondos";
    /** Path para suscribirse a un fondo */
    public static final String PATH_SUSCRIBIR_FONDO = "/suscribir-fondo";

    // ------------------- Transaccion Controller ---------------//
    /** Path request de cliente */
    public static final String REQUEST_PATH_TRANSACCION = "transaccion";


    // ------------------- Autenticacion Controller ---------------//
    /** Path request de atenticacion */
    public static final String REQUEST_PATH_AUTH = "auth";
    /** Path request de atenticacion */
    public static final String PATH_LOGEAR_USUARIO = "/login";
    /** Path request de atenticacion */
    public static final String PATH_REGISTRAR_USUARIO = "/registrar";


    // MENSAJES
    /** Constante para el mensaje de error al no encontrar clientes */
    public static final String ERROR_NO_SE_ENCUENTRAN_CLIENTES = "No hay clientes registrados en la base de datos";

    /** Constante para el mensaje de error al no encontrar fondos */
    public static final String ERROR_NO_SE_ENCUENTRAN_FONDOS = "No hay fondos registrados en la base de datos";

    /** Constante para el mensaje de error al registrar un usuario existente */
    public static final String ERROR_USUARIO_EXISTENTE = "El usuario ya se encuentra registrado";
}
