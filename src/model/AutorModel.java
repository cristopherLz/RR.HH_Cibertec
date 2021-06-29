package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Autor;
import util.MySqlDBConexion;

public class AutorModel {
//**************************************** INSERTAR AUTOR ****************************************
	public int insertarAutor(Autor a) {
		int salida = -1;

		Connection con = null;
		PreparedStatement pstm = null;
		try {

			con = MySqlDBConexion.getConexion();
			String sql = "insert into autor values(null,?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, a.getNombre());
			pstm.setString(2, a.getApellido());
			pstm.setString(3, a.getFechaNaci());
			pstm.setDate(4, (Date) a.getFechaRegis());
			pstm.setString(5, a.getNacionalidad());
			pstm.setString(6, a.getGrado());
			

			// 3 envia el sql y se recibe la cantidad de registrados
			salida = pstm.executeUpdate();

			System.out.println("SQL-->" + pstm);
			System.out.println("insertados :  " + salida);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}
	//**************************************** LISTAR AUTOR ****************************************

	public List<Autor> listarAutor() {

		ArrayList<Autor> data = new ArrayList<Autor>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "select * from autor";
			pstm = conn.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			Autor a = null;

			while (rs.next()) {
				a = new Autor();
				a.setIdAutor(rs.getInt("idAutor"));
				a.setNombre(rs.getString("nombres"));
				a.setApellido(rs.getString("apellidos"));
				a.setFechaNaci(rs.getString("fechaNacimiento"));
				a.setFechaRegis(rs.getDate("fechaRegistro"));
				a.setNacionalidad(rs.getString("nacionalidad"));
				a.setGrado(rs.getString("grado"));

				data.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;

	}
	//**************************************** ELIMINAR AUTOR ****************************************

	public int eliminarAutor(int idAutor) {
		int eliminado = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "delete from autor where idAutor=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idAutor);

			eliminado = pstm.executeUpdate();
			System.out.println("Autor eliminado :  " + eliminado);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminado;
	}
	//**************************************** ACTUALIZAR AUTOR ****************************************
	
	public int actualizaAutor(Autor a) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "update autor set nombres=?, apellidos=?, fechaNacimiento=?, fechaRegistro=?, nacionalidad=?, grado=?  where idAutor=?"; 
			pstm = con.prepareStatement(sql);
			pstm.setString(1, a.getNombre());
			pstm.setString(2, a.getApellido());
			pstm.setString(3, a.getFechaNaci());
			pstm.setDate(4, (Date)a.getFechaRegis());
			pstm.setString(5,a.getNacionalidad());
			pstm.setString(6,a.getGrado());
			pstm.setInt(7, a.getIdAutor());
			
			
			actualizados = pstm.executeUpdate();
			System.out.println("actualizados :  " + actualizados);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}
	
	//**************************************** CONSULTAR  POR FECHA DE REGISTRO ****************************************

	public List<Autor> consultaAutor(String fecIni, String fecFin){
		ArrayList<Autor> data = new ArrayList<Autor>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "select * from autor where fechaRegistro between ? and ?";
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(fecIni));
			pstm.setDate(2, Date.valueOf(fecFin));
			
			rs = pstm.executeQuery();
					
			Autor objAutor = null;
			while(rs.next()){
				objAutor = new Autor();
				objAutor.setIdAutor(rs.getInt(1));
				objAutor.setNombre(rs.getString(2));
				objAutor.setApellido(rs.getString(3));
				objAutor.setFechaNaci(rs.getString(4));
				objAutor.setFechaRegis(rs.getDate(5));
				objAutor.setNacionalidad(rs.getString(6));
				objAutor.setGrado(rs.getString(7));
				
				
				data.add(objAutor);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	//**************************************** LISTA CONSULTAR  AUTOR ****************************************
	public List<Autor> listaConsultaAutor() {
		ArrayList<Autor> data = new ArrayList<Autor>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; 
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "select * from autor";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);

			rs = pstm.executeQuery();

			Autor c = null;
			while (rs.next()) {
				c = new Autor();
				
				c.setIdAutor(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setApellido(rs.getString(3));
				c.setFechaNaci(rs.getString(4));
				c.setFechaRegis(rs.getDate(5));
				c.setNacionalidad(rs.getString(6));
				c.setGrado(rs.getString(7));
				
				
				data.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
