package edu.eci.cvds.services;

public class BancoPropuestasException extends Exception {
    
    public static final String USUARIO_INCORRECTO = "El numero de documento ingresado es incorecto";
    
    public static final String CONTRASENA_INCORRECTA = "La contraseña ingresada es incorrecta";

    public BancoPropuestasException() {
    }

    public BancoPropuestasException(String message) {
        super(message);
    }

    public BancoPropuestasException(String message, Throwable cause) {
        super(message, cause);
    }

    public BancoPropuestasException(Throwable cause) {
        super(cause);
    }
    
}
