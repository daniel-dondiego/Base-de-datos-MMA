package mx.unam.ciencias.proyectoBDD;

import javax.swing.JOptionPane;

/**
 * Clase para notificaciones
 */
public class Notificaciones{

	/**
	 * Constructor vacío.
	 */
	public Notificaciones() {

	}
	/**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario 
     */
    public void notificacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

}