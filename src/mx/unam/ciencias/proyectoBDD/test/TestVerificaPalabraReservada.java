package mx.unam.ciencias.proyectoBDD.test;

import java.util.Random;
import mx.unam.ciencias.proyectoBDD.VerificaPalabraReservada;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link VerificaPalabraReservada}.
 */
public class TestVerificaPalabraReservada{
	private VerificaPalabraReservada vPR;
	private Random random;
	private String [] palabrasReservadas;

	public TestVerificaPalabraReservada(){
		vPR = new VerificaPalabraReservada();
		random = new Random();
		palabrasReservadas = vPR.getPalabrasReservadasSQLite();
	}

	/**
     * Prueba unitaria para {@link VerificaPalabraReservada#contenidoValido}.
     * verifica que el usuario no ingrese ningún string que pueda alterar el 
     * comportamiento de la base de datos
     */
    @Test public void testContenidoValido(){
    	for(String s : palabrasReservadas){
    		Assert.assertTrue(vPR.contenidoValido(s) == false);
    	}
    }
}