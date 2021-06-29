package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Autor;
import model.AutorModel;
import util.Conversiones;
import util.Validaciones;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class FrmRegistroAutor extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtFechaNac;
	private JTextField txtFechRegi;
	private JTextField txtNaciona;
	private JTextField txtGrado;
	private JButton btnRegistro;

	Validaciones validaciones = new Validaciones();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroAutor frame = new FrmRegistroAutor();
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
	public FrmRegistroAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Autor");
		setBounds(100, 100, 691, 512);
		getContentPane().setLayout(null);

		JLabel lblIngresoDeAutor = new JLabel("Ingreso de Autor");
		lblIngresoDeAutor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIngresoDeAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresoDeAutor.setBounds(10, 24, 655, 46);
		lblIngresoDeAutor.setFont(new Font("Tahoma", Font.BOLD, 22));
		getContentPane().add(lblIngresoDeAutor);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(140, 107, 111, 14);
		getContentPane().add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellidos.setBounds(140, 132, 111, 14);
		getContentPane().add(lblApellidos);

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaNacimiento.setBounds(140, 157, 133, 14);
		getContentPane().add(lblFechaNacimiento);

		JLabel lblFechaRegistro = new JLabel("Fecha Registro");
		lblFechaRegistro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaRegistro.setBounds(140, 182, 119, 14);
		getContentPane().add(lblFechaRegistro);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNacionalidad.setBounds(140, 207, 111, 14);
		getContentPane().add(lblNacionalidad);

		JLabel lblGrado = new JLabel("Grado");
		lblGrado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGrado.setBounds(140, 232, 111, 14);
		getContentPane().add(lblGrado);

		txtNombre = new JTextField();
		txtNombre.setBounds(336, 101, 236, 20);
		txtNombre.setColumns(10);
		getContentPane().add(txtNombre);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(336, 126, 236, 20);
		txtApellidos.setColumns(10);
		getContentPane().add(txtApellidos);

		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(336, 151, 236, 20);
		txtFechaNac.setColumns(10);
		getContentPane().add(txtFechaNac);

		txtFechRegi = new JTextField();
		txtFechRegi.setBounds(336, 176, 236, 20);
		txtFechRegi.setColumns(10);
		getContentPane().add(txtFechRegi);

		txtNaciona = new JTextField();
		txtNaciona.setBounds(336, 201, 236, 20);
		txtNaciona.setColumns(10);
		getContentPane().add(txtNaciona);

		txtGrado = new JTextField();
		txtGrado.setBounds(336, 226, 236, 20);
		txtGrado.setColumns(10);
		getContentPane().add(txtGrado);

		btnRegistro = new JButton("Registro");
		btnRegistro.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\anadir (1).png"));
		btnRegistro.setBounds(160, 292, 166, 46);
		btnRegistro.addActionListener(this);
		getContentPane().add(btnRegistro);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(325, 182, 1, 1);
		getContentPane().add(desktopPane);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\new_page_document_16676.png"));
		btnLimpiar.setBounds(372, 292, 174, 46);
		getContentPane().add(btnLimpiar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\fodo.jpg"));
		label.setBounds(0, 0, 675, 482);
		getContentPane().add(label);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiar(arg0);
		}
		if (arg0.getSource() == btnRegistro) {
			btnRegistroActionPerformed(arg0);
		}
	}
	LocalDate myObj = LocalDate.now();
	private JButton btnLimpiar;

	protected void btnRegistroActionPerformed(ActionEvent arg0) {
		String nombre = txtNombre.getText().trim();
		String apellido = txtApellidos.getText().trim();
		String fechNaci = txtFechaNac.getText().trim();
		Date fechRegis = Conversiones.getFechaActual();
		String nacionalidad = txtNaciona.getText().trim();
		String grado = txtGrado.getText().trim();
		
		//VALIDACIONES
		if (!nombre.matches(Validaciones.TEXTO)) {
			mensaje("El nombre debe de tener de  2 a 100 caracteres y no acepta numeros");
		} else if (!apellido.matches(Validaciones.TEXTO)) {
			mensaje("El apellido debe de tener de  2 a 100 caracteres y no acepta numeros");
		}else if(!nacionalidad.matches(Validaciones.TEXTO)) {
			mensaje("La nacionalidad debe de tener de  2 a 100 caracteres y no acepta numeros");
		}else if(!fechNaci.matches(Validaciones.FECHA)) {
			mensaje("Error de formato , escriba Año-Mes-Dia");
		}else {
			Autor Obj = new Autor();

			Obj.setNombre(nombre);
			Obj.setApellido(apellido);
			Obj.setFechaNaci(fechNaci);
			Obj.setFechaRegis(fechRegis);
			Obj.setNacionalidad(nacionalidad);
			Obj.setGrado(grado);
			

			AutorModel autor = new AutorModel();
			int salidaAutor = autor.insertarAutor(Obj);
			
			//REGISTRO
			if (salidaAutor > 0) {
				mensaje("Registro exitoso");
			} else {
				mensaje("Registro erroneo");
			}
		}
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	protected void actionPerformedBtnLimpiar(ActionEvent arg0) {
		
		txtNombre.setText("");
		txtApellidos.setText("");
		txtFechaNac.setText("");
		txtFechRegi.setText("");
		txtNaciona.setText("");
		txtGrado.setText("");
		
	}
}