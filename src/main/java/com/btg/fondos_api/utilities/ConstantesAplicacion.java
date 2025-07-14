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
    /** Path para crear un cliente */
    public static final String PATH_OBTENER_CLIENTE = "/obtener";
    /** Path para modificar un cliente */
    public static final String PATH_MODIFICAR_CLIENTE = "/modificar";
    /** Path para acreditarle saldo a un cliente */
    public static final String PATH_ACREDITAR_SALDO_CLIENTE = "/acreditarSaldo";

    // ------------------- Fondo Controller ---------------//
    /** Path request de cliente */
    public static final String REQUEST_PATH_FONDO = "fondo";

    // ------------------- Transaccion Controller ---------------//
    /** Path request de cliente */
    public static final String REQUEST_PATH_TRANSACCION = "transaccion";
}
