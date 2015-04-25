package mx.unam.ciencias.proyectoBDD;

import java.sql.*;

public class Controlador{
	private ConexionABase cAB;

	public Controlador(){
		cAB = new ConexionABase();
	}

	

	public void cierraConexion()throws SQLException{
		cAB.cerrarConexion();
	}

}