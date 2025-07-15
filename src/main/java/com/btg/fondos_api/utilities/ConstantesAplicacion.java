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
    public static final String PATH_MODIFICAR_CLIENTE = "/crear-cliente";
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
    public static final String REQUEST_PATH_AUTH = "autenticacion";
    /** Path request de atenticacion */
    public static final String PATH_LOGEAR_USUARIO = "/logear";
    /** Path request de atenticacion */
    public static final String PATH_REGISTRAR_USUARIO = "/registrar";

    // ------------------- Valores fijos del sistema---------------//
    /** Constante para el valor SMS */
    public static final String SMS = "SMS";

    /** Constante para el valor EMAIL */
    public static final String EMAIL = "EMAIL";

    /** Constante para el valor tipo de transaccion APORTE */
    public static final String APORTE = "APORTE";

    /** Constante para el valor tipo de transaccion ACREDITACION */
    public static final String ACREDITACION = "ACREDITACION";


    // ------------------- Mensajes del sistema ---------------//
    /** Constante para el mensaje de error al no encontrar clientes */
    public static final String ERROR_NO_SE_ENCUENTRAN_CLIENTES = "No hay clientes registrados en la base de datos";

    /** Constante para el mensaje de error al no encontrar fondos */
    public static final String ERROR_NO_SE_ENCUENTRAN_FONDOS = "No hay fondos registrados en la base de datos";

    /** Constante para el mensaje de error al registrar un usuario existente */
    public static final String ERROR_USUARIO_EXISTENTE = "El usuario ya se encuentra registrado";

    /** Constante para el mensaje de error al fallar el proceso de suscribirse a un fondo */
    public static final String ERROR_SUSCRIPCION_FONDO = "Fallo al realizar la suscripcion";

    /** Constante para el mensaje de error sin preferencia de notificacion */
    public static final String SIN_PREFERENCIA_NOTIFICACION = "Preferencia de notificación desconocida: {}";

    /** Constante para el mensaje de exito a la suscripcion */
    public static final String EXITOSO_SUSCRIPCION = "Suscripcion Exitosa";

    /** Constante para el mensaje de suscripcion a fondo */
    public static final String DESCRIPCION_SUSCRIPCION_FONDO = "Suscripcion a fondo";

    /** Constante para el mensaje de exito aL crear el cliente */
    public static final String EXITOSO_CLIENTE= "Cliente creado con exito";

    /** Constante para el mensaje de error aL crear el cliente */
    public static final String ERROR_CREAR_CLIENTE = "Error al crear el cliente";

    /** Constante para el mensaje de error aL crear una transaccion */
    public static final String ERROR_CREAR_TRANSACCION = "Transaccion Fallida";

    /** Constante para el mensaje de error al procesar la suscripcion */
    public static final String ERROR_PROCESAR_SUSCRIPCION = "Error al procesar la suscripcion";

    /** Constante para el mensaje de error sin saldo */
    public static final String ERROR_SIN_SALDO = "No tiene saldo disponible para vincularse al fondo <%s>";

    /** Constante para el mensaje de error ya tiene una suscripcion activa*/
    public static final String ERROR_SUSCRIPCION_ACTIVA = "Ya tiene una suscripcion al fondo <%s>";

    /** Constante para el mensaje de exitoso suscripcion eliminada*/
    public static final String EXITOSO_SUSCRIPCION_ELIMINADA= "La suscripción fue eliminada correctamente.";

    /** Constante para el mensaje de error al reversar el saldo*/
    public static final String ERROR_REVERSO_SALDO= "Reverso automático de la suscripción fallida";
}
