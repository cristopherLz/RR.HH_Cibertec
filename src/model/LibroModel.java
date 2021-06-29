package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Libro;
import util.MySqlDBConexion;

public class LibroModel {

	public int insertaLibro(Libro obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into libro values(null,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getAnio());
			pstm.setString(3, obj.getCategoria());
			pstm.setString(4, obj.getSerie());

			System.out.println(pstm);
			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		return salida;
	}

	/** Listar **/

	public List<Libro> listarLibro() {
		ArrayList<Libro> data = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from libro";
			pstm = conn.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			Libro l = null;

			while (rs.next()) {

				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setTitulo(rs.getString("titulo"));
				l.setAnio(rs.getString("anio"));
				l.setCategoria(rs.getString("categoria"));
				l.setSerie(rs.getString("serie"));

				data.add(l);

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

	/** eliminar **/
	public int eliminarLibro(int idLibro) {
		int eliminado = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql = "delete from libro where idLibro=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idLibro);

			eliminado = pstm.executeUpdate();
			System.out.println("Libro eliminado :  " + eliminado);
			System.out.println("SQL-->" + pstm);
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

	/** actualizar **/
	public int actualizaLibro(Libro l) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update libro set titulo=?, anio=?, categoria=?, serie=? where idLibro=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, l.getTitulo());
			pstm.setString(2, l.getAnio());
			pstm.setString(3, l.getCategoria());
			pstm.setString(4, l.getSerie());
			pstm.setInt(5, l.getIdLibro());

			actualizados = pstm.executeUpdate();
			System.out.println("actualizados :  " + actualizados);
			System.out.println("SQL-->" + pstm);
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
		return actualizados;
	}
   /**cl3**/
	public List<Libro> listaPorAño(String fechaIni, String fechaFin){
		ArrayList<Libro> lista = new ArrayList<Libro>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			  conn= MySqlDBConexion.getConexion();
			  
			  String sql="select * from libro where anio between ? and ?";
			  psmt = conn.prepareStatement(sql);
			  psmt.setString(1, fechaIni);
			  psmt.setString(2, fechaFin);
			  System.out.print("SQL ->" + psmt);
			  
			  rs= psmt.executeQuery();
			  Libro obj;
			  while(rs.next()) {
				  obj = new  Libro();
				  obj.setIdLibro(rs.getInt(1));
				  obj.setTitulo(rs.getString(2));
				  obj.setAnio(rs.getString(3));
				  obj.setCategoria(rs.getString(4));
				  obj.setSerie(rs.getString(5));
				  lista.add(obj);
			  }
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
			
			}
		}
		return lista;
	}
}
