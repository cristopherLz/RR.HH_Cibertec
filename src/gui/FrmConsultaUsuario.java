package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import entidad.RegistrarUsuario;
import entidad.Usuario;
import model.UsuarioModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmConsultaUsuario extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFecIn;
	private JTextField txtFecFin;
	private JTable table;
	private JButton btnConsultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaUsuario frame = new FrmConsultaUsuario();
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
	public FrmConsultaUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Usuario");
		setBounds(100, 100, 926, 645);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("CONSULTA USUARIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(230, 21, 350, 56);
		getContentPane().add(lblNewLabel);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(70, 121, 111, 14);
		getContentPane().add(lblFechaInicio);

		JLabel lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setBounds(348, 121, 92, 14);
		getContentPane().add(lblFechaFinal);

		txtFecIn = new JTextField();
		txtFecIn.setBounds(144, 118, 194, 20);
		getContentPane().add(txtFecIn);
		txtFecIn.setColumns(10);

		txtFecFin = new JTextField();
		txtFecFin.setColumns(10);
		txtFecFin.setBounds(430, 118, 194, 20);
		getContentPane().add(txtFecFin);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(655, 112, 117, 32);
		getContentPane().add(btnConsultar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 189, 854, 355);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "idUsuario", "nombre", "apellido", "dni",
				"login", "password", "correo", "fechaNacimiento", "Direccion" }));
		scrollPane.setViewportView(table);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnConsultar) {
			handle_btnConsultar_actionPerformed(arg0);
		}
	}

	protected void handle_btnConsultar_actionPerformed(ActionEvent arg0) {
		String fecInicio = txtFecIn.getText().trim();
		String fecFinal = txtFecFin.getText().trim();

		UsuarioModel model = new UsuarioModel();
		List<RegistrarUsuario> lstUsuario = null;

		if (fecInicio.equals("") || fecFinal.equals("")) {
			lstUsuario = model.listaConsultUsuario();
		} else {
			lstUsuario = model.consultaUsuario(fecInicio, fecFinal);
		}

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		for (RegistrarUsuario x : lstUsuario) {
			Object[] fila = { x.getIdUsuario(), x.getNombre(), x.getApellido(), x.getDni(), x.getLogin(),
					x.getPassword(), x.getCorreo(), x.getFechaNacimiento(), x.getDireccion() };
			dtm.addRow(fila);
		}

	}
}
