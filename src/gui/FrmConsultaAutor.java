package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entidad.Autor;
import model.AutorModel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmConsultaAutor extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtInicio;
	private JTextField txtFinal;
	private JTable tblDatos;
	private JButton btnConsultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaAutor frame = new FrmConsultaAutor();
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
	public FrmConsultaAutor() {
		getContentPane().setBackground(new Color(0, 204, 204));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Autor");
		setBounds(100, 100, 754, 545);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaAutorPor = new JLabel("CONSULTA AUTOR POR FECHA DE REGISTRO");
		lblConsultaAutorPor.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\archivo.png"));
		lblConsultaAutorPor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConsultaAutorPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaAutorPor.setBounds(10, 11, 718, 49);
		getContentPane().add(lblConsultaAutorPor);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFechaInicio.setBounds(31, 95, 85, 19);
		getContentPane().add(lblFechaInicio);
		
		txtInicio = new JTextField();
		txtInicio.setBounds(133, 95, 215, 20);
		getContentPane().add(txtInicio);
		txtInicio.setColumns(10);
		
		JLabel lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFechaFinal.setBounds(390, 94, 85, 19);
		getContentPane().add(lblFechaFinal);
		
		txtFinal = new JTextField();
		txtFinal.setColumns(10);
		txtFinal.setBounds(492, 94, 215, 20);
		getContentPane().add(txtFinal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 718, 331);
		getContentPane().add(scrollPane);
		
		tblDatos = new JTable();
		tblDatos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombres", "Apellidos", "Fecha Nacimiento", "Fecha Registro", "Nacionalidad", "Grado"
			}
		));
		scrollPane.setViewportView(tblDatos);
		
		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\buscar.png"));
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnConsultar.setBounds(574, 125, 133, 37);
		getContentPane().add(btnConsultar);


	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		String fecInicio = txtInicio.getText().trim();
		String fecFinal = txtFinal.getText().trim();
		
		AutorModel model = new AutorModel();
		List<Autor> lstAutor = null;
		
		if(fecInicio.equals("") || fecFinal.equals("")) {
			lstAutor = model.consultaAutor(fecInicio, fecFinal);
		}else {
			lstAutor = model.consultaAutor(fecInicio, fecFinal);
		}
		
		DefaultTableModel dtm = (DefaultTableModel)tblDatos.getModel();
		dtm.setRowCount(0);
		
		for(Autor x : lstAutor) {
			Object[] fila = {x.getIdAutor(), x.getNombre(),x.getApellido(),x.getFechaNaci(),x.getFechaRegis(),
							x.getNacionalidad(),x.getGrado()};
			dtm.addRow(fila);
		}
		
		
		
	}
}
