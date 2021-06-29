package model;

import java.sql.Connection;

import java.sql.PreparedStatement;

import entidad.RegistrarUsuario;
import util.MySqlDBConexion;

public class RegistrarUsuarioModel {
	public int insertaRegistroUsuario(RegistrarUsuario obj) {
		int salida = -1;

		Connection conn = null;
		PreparedStatement psmt = null;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into usuario values (null,?,?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, obj.getNombre());
			psmt.setString(2, obj.getApellido());
			psmt.setInt(3, obj.getDni());
			psmt.setString(4, obj.getLogin());
			psmt.setString(5, obj.getPassword());
			psmt.setString(6, obj.getCorreo());
			psmt.setDate(7, obj.getFechaNacimiento());
			psmt.setString(8, obj.getDireccion());

			System.out.println("SQL-->" + psmt);
			System.out.println("insertados :  " + salida);
			salida = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
		return salida;
	}
}
