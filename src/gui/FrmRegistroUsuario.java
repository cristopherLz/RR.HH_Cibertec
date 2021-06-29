package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.RegistrarUsuario;
import model.RegistrarUsuarioModel;
import util.Validaciones;

public class FrmRegistroUsuario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtLogin;
	private JPasswordField Jpassword;
	private JTextField txtCorreo;
	private JTextField txtFechaNacimiento;

	Validaciones validaciones = new Validaciones();
	private JTextField txtdireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroUsuario frame = new FrmRegistroUsuario();
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
	public FrmRegistroUsuario() {
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de usuario");
		setBounds(100, 100, 413, 477);
		getContentPane().setLayout(null);

		JLabel lblRegistrarUsuario = new JLabel("Registrar Usuario");
		lblRegistrarUsuario.setForeground(new Color(255, 255, 255));
		lblRegistrarUsuario
				.setIcon(new ImageIcon(FrmRegistroUsuario.class.getResource("/imagenes/libro-de-contactos.png")));
		lblRegistrarUsuario.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 21));
		lblRegistrarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarUsuario.setBounds(0, 11, 397, 78);
		getContentPane().add(lblRegistrarUsuario);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(35, 110, 96, 21);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(141, 112, 214, 20);
		getContentPane().add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(141, 144, 214, 20);
		getContentPane().add(txtApellido);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(Color.WHITE);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido.setBounds(35, 142, 96, 21);
		getContentPane().add(lblApellido);

		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(141, 176, 156, 20);
		getContentPane().add(txtDni);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(Color.WHITE);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDni.setBounds(35, 174, 96, 21);
		getContentPane().add(lblDni);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(141, 207, 156, 20);
		getContentPane().add(txtLogin);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogin.setBounds(35, 206, 96, 21);
		getContentPane().add(lblLogin);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(35, 238, 120, 21);
		getContentPane().add(lblPassword);

		Jpassword = new JPasswordField();
		Jpassword.setBounds(141, 238, 156, 20);
		getContentPane().add(Jpassword);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(176, 269, 189, 20);
		getContentPane().add(txtCorreo);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
		lblCorreoElectronico.setForeground(Color.WHITE);
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCorreoElectronico.setBounds(10, 270, 156, 21);
		getContentPane().add(lblCorreoElectronico);

		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(200, 304, 165, 20);
		getContentPane().add(txtFechaNacimiento);

		JLabel lblDireccion = new JLabel("Fecha de Nacimiento");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDireccion.setBounds(20, 302, 194, 21);
		getContentPane().add(lblDireccion);

		JButton btnRegistrar = new JButton("Registrar\r\n");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnRegistrarJButton(arg0);
			}
		});
		btnRegistrar.setIcon(new ImageIcon(FrmRegistroUsuario.class.getResource("/imagenes/flecha-correcta.png")));
		btnRegistrar.setBackground(Color.GRAY);
		btnRegistrar.setForeground(new Color(0, 0, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegistrar.setBounds(36, 375, 145, 61);
		getContentPane().add(btnRegistrar);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(new ImageIcon(FrmRegistroUsuario.class.getResource("/imagenes/cerrar.png")));
		btnCerrar.setForeground(Color.BLACK);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCerrar.setBackground(Color.GRAY);
		btnCerrar.setBounds(220, 375, 145, 61);
		getContentPane().add(btnCerrar);

		JLabel lblDireccion_1 = new JLabel("Direcci\u00F3n:");
		lblDireccion_1.setForeground(Color.WHITE);
		lblDireccion_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDireccion_1.setBounds(35, 335, 96, 21);
		getContentPane().add(lblDireccion_1);

		txtdireccion = new JTextField();
		txtdireccion.setColumns(10);
		txtdireccion.setBounds(130, 337, 246, 20);
		getContentPane().add(txtdireccion);

	}

	protected void actionPerformedBtnRegistrarJButton(ActionEvent arg0) {

		String nombre = txtNombre.getText().trim();
		String apellido = txtApellido.getText().trim();
		String dni = txtDni.getText().trim();
		String login = txtLogin.getText().trim();
		String password = String.valueOf(Jpassword.getPassword()).trim();
		String correo = txtCorreo.getText().trim();
		String fechaNacimiento = txtFechaNacimiento.getText().trim();
		String direccion = txtdireccion.getText().trim();

		// validaciones
		if (!apellido.matches(Validaciones.TEXTO)) {
			mensaje("El apellido debe de tener de  2 a 100 caracteres y no acepta numeros");
		} else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El dni debe tener 8 digitos ,solo acepta numeros");
		} else if (!correo.matches(Validaciones.CORREO)) {
			mensaje("revise si su correo es correcto");
		} else if (fechaNacimiento.matches(Validaciones.FECHA) == false) {
			mensaje("Error de formato , escriba Año-Mes-Dia");
		} else {
			try {
				RegistrarUsuario reg = new RegistrarUsuario();
				reg.setNombre(nombre);
				reg.setApellido(apellido);
				reg.setDni(Integer.parseInt(dni));
				reg.setLogin(login);
				reg.setPassword(password);
				reg.setCorreo(correo);
				Date FechaNaci = Date.valueOf(fechaNacimiento);
				reg.setFechaNacimiento(FechaNaci);
				reg.setDireccion(direccion);

				RegistrarUsuarioModel regModel = new RegistrarUsuarioModel();
				int salida = regModel.insertaRegistroUsuario(reg);

				if (salida > 0) {
					mensaje("Registro exitoso");
				} else {
					mensaje("Registro erroneo");
				}

			} catch (Exception e) {
				e.printStackTrace();
				mensaje("No se pudo registrar");
			}
		}
	}

	private void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);

	}

}