package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Alumno;
import model.AlumnoModel;
import util.Validaciones;

public class FrmRegistroAlumno extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtCorrero;
	private JTextField txtFechaNacimiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroAlumno frame = new FrmRegistroAlumno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 458, 448);
		getContentPane().setLayout(null);
		
		JLabel lblRegistrarUnNuevo = new JLabel("Registrar un nuevo Alumno");
		lblRegistrarUnNuevo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRegistrarUnNuevo.setBounds(37, 11, 332, 54);
		getContentPane().add(lblRegistrarUnNuevo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(50, 93, 71, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(147, 92, 145, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(50, 135, 71, 19);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(147, 136, 145, 20);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDni.setBounds(50, 177, 71, 20);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(147, 179, 145, 20);
		getContentPane().add(txtDni);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCorreo.setBounds(50, 227, 62, 20);
		getContentPane().add(lblCorreo);
		
		txtCorrero = new JTextField();
		txtCorrero.setColumns(10);
		txtCorrero.setBounds(147, 229, 263, 20);
		getContentPane().add(txtCorrero);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeNacimiento.setBounds(50, 271, 157, 20);
		getContentPane().add(lblFechaDeNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(232, 273, 145, 20);
		getContentPane().add(txtFechaNacimiento);
		

		JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro:");
		lblFechaDeRegistro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeRegistro.setBounds(50, 313, 157, 20);
		getContentPane().add(lblFechaDeRegistro);
		

		
		
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText().trim();
				String apellido = txtApellido.getText().trim();
				String dni = txtDni.getText().trim();
				String correo = txtCorrero.getText().trim();
				String fechaNacimiento = txtFechaNacimiento.getText().trim();
	
				
				
				if (!nombre.matches("[a-zA-ZáéíóúñÁÉÍÓÚÑ\\\\s]{2,100}")) {
					mensaje("El nombre debe de tener de 2 a 100 caracteres y no acepta numeros ");
				} else if (!apellido.matches("[a-zA-ZáéíóúñÁÉÍÓÚÑ\\\\\\\\s]{2,100}")){
					mensaje("El apellido debe de tener de  2 a 100 caracteres y no acepta numeros");
				}else if (!dni.matches("[0-9]{8}")) {
					mensaje("El dni debe tener 8 digitos ,solo acepta numeros");
				}else if(!correo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
					mensaje("revise si su correo es correcto");
				}else if(fechaNacimiento.matches(Validaciones.FECHA)==false) {
					mensaje("Error de formato , escriba Año-Mes-Dia");
				}
				
				else 
				
				{
					try {
					Alumno obj = new Alumno();
					obj.setNombre(nombre);
					obj.setApellido(apellido);
					obj.setDni(Integer.parseInt(dni));
					obj.setCorreo(correo);
					
					Date FechaNaci = Date.valueOf(fechaNacimiento);
					obj.setFechaNacimiento(FechaNaci);
					
					
					AlumnoModel objModel = new AlumnoModel();   
					int salida = objModel.insertaAlumno(obj);
					
					if (salida >0) {
						mensaje("Persona registrada exitosamente");
					}else {
						    mensaje("No se pudo registrar");
					      }
					}
					catch(Exception ex){
						ex.printStackTrace();
						 mensaje("No se pudo registrar");
					}
				}
	
			}
			
		});
		
		btnRegistrar.setBounds(174, 373, 89, 23);
		getContentPane().add(btnRegistrar);
		



	}public void mensaje(String ms){
				JOptionPane.showMessageDialog(this, ms);
			}
}


























