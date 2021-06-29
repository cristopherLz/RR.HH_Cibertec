package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Alumno;
import util.MySqlDBConexion;


public class AlumnoModel {

	public int insertaAlumno(Alumno obj) {

		int salida = -1;

		Connection conn = null;

		PreparedStatement psmt = null;

		try {
			
		     
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into alumno values (null,?,?,?,?,?,curtime())";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, obj.getNombre());
			psmt.setString(2, obj.getApellido());
			psmt.setInt(3, obj.getDni());
			psmt.setString(4, obj.getCorreo());
			psmt.setDate(5, obj.getFechaNacimiento());
			
			System.out.println(psmt);
			
			salida = psmt.executeUpdate();
			
			

		} catch (Exception e) {

			System.out.println(salida);
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
	
	public List<Alumno>listaAlumnoPorNombre(String filtro){
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from alumno where nombres like ? ";
			psmt  = conn.prepareStatement(sql);
			psmt.setString(1,filtro + "%");
				
			rs = psmt.executeQuery();
			
			Alumno obj = null;
			while(rs.next()) {
				
				obj = new Alumno();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getInt(4));
				obj.setCorreo(rs.getString(5));
				obj.setFechaNacimiento(rs.getDate(6));
				obj.setFechaRegistro(rs.getDate(7));
				lista.add(obj);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs !=null)rs.close();
				if(psmt !=null)psmt.close();
				if(conn !=null)conn.close();
			} catch (Exception e2) {}
		}
		
		return lista;
		
		
		
	}
	
	public int eliminaAlumno(int idAlumno) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql ="delete from  alumno where idalumno=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idAlumno);
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
	
	public int actualizaAlumno(Alumno  a) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update alumno  set nombre=? , apellido=? , correo=?  , dni=? , fechadenacimiento=? , fechaderegistro=? where idalumno=? "; 
			pstm = con.prepareStatement(sql);
			pstm.setString(1, a.getNombre());
			pstm.setString(2, a.getApellido());
			pstm.setInt(3, a.getDni());
			pstm.setString(4, a.getCorreo());
			pstm.setDate(5, a.getFechaNacimiento());
			pstm.setDate(6, a.getFechaRegistro());
			pstm.setInt(7, a.getIdUsuario());
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
	public List<Alumno> listaAlumno(String fecInicio, String fecFinal){
		ArrayList<Alumno> lista =new ArrayList<>();
		Connection conn =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from sistema_biblioteca_simple.alumno where fechadenacimiento between ? and ?";
			psmt = conn.prepareStatement(sql);
			psmt.setDate(1, Date.valueOf(fecInicio));
			psmt.setDate(2, Date.valueOf(fecFinal));
			System.out.print("SQL-->" + psmt);
			
			rs = psmt.executeQuery();
			
			Alumno obj;			
			while(rs.next()) {
				obj = new Alumno();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getInt(4));
				obj.setCorreo(rs.getString(5));
				obj.setFechaNacimiento(rs.getDate(6));
				obj.setFechaRegistro(rs.getDate(7));
				lista.add(obj);				
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
		return lista;
	}

	public  List<Alumno> ConsultaAlumno(){
		ArrayList<Alumno> lista =new ArrayList<>();
		Connection conn =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from alumno";
			psmt = conn.prepareStatement(sql);
			System.out.print("SQL-->" + psmt);
			
			rs = psmt.executeQuery();
			
			Alumno obj;			
			while(rs.next()) {
				obj = new Alumno();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getInt(4));
				obj.setCorreo(rs.getString(5));
				obj.setFechaNacimiento(rs.getDate(6));
				obj.setFechaRegistro(rs.getDate(7));
				lista.add(obj);				
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
		return lista;
	}

}