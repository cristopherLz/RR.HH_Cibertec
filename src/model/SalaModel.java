package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Sala;
import util.MySqlDBConexion;

public class SalaModel {

	public int insertaSala(Sala obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into sala values (null,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, obj.getNumero());
			psmt.setInt(2, obj.getPiso());
			psmt.setString(3, obj.getCapacidad());
			psmt.setString(4, obj.getRecursos());
			psmt.setInt(5, obj.getEstado());

			System.out.println(psmt);

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
	
	public List<Sala> listaSala() {
		ArrayList<Sala> data = new ArrayList<Sala>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="select * from sala";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			
			//En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();
			
			//Se pasa la data del rs al ArrayList(data)
			Sala c = null;
			while(rs.next()){
				c = new Sala();
				
				// Se colocan los campos de la base de datos
				c.setIdsala(rs.getInt("idSala"));
				c.setNumero(rs.getString("numero"));
				c.setPiso(rs.getInt("piso"));
				c.setCapacidad(rs.getString("capacidad"));
				c.setRecursos(rs.getString("recursos"));
				c.setEstado(rs.getByte("estado"));
				
				data.add(c);
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
	
	public int actualizaSala(Sala  s) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update sala  set numero=? , piso=? , capacidad=?  , recursos=? ,estado = ?  where idsala=? "; 
			pstm = con.prepareStatement(sql);
			pstm.setString(1, s.getNumero());
			pstm.setInt(2, s.getPiso());
			pstm.setString(3, s.getCapacidad());
			pstm.setString(4, s.getRecursos());
			pstm.setByte(5, s.getEstado());
			pstm.setInt(6, s.getIdsala());
			actualizados = pstm.executeUpdate();
			System.out.println("SQL-->" + pstm);
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
	
	public int eliminaSala(int idSala) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql ="delete from  sala where idsala=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idSala);
			eliminados = pstm.executeUpdate();
			System.out.println(pstm);
			System.out.println("eliminados :  " + eliminados);
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
		
		return eliminados;
		}

	
	public List<Sala> listaPorPiso(double pisoIni ,double pisoFin){
		ArrayList<Sala> lista = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "Select * from sala where piso between ? and ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setDouble(1, pisoIni);
			psmt.setDouble(2, pisoFin);
			System.out.println("SQL ->" + psmt);
			
			rs = psmt.executeQuery();
			
			Sala obj;
			while(rs.next()) {
				obj = new Sala();
				obj.setIdsala(rs.getInt(1));
				obj.setNumero(rs.getString(2));
				obj.setPiso(rs.getInt(3));
				obj.setCapacidad(rs.getString(4));
				obj.setRecursos(rs.getString(5));
				obj.setEstado(rs.getByte(6));
				lista.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {}
		}
		return lista;
		
	   }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

