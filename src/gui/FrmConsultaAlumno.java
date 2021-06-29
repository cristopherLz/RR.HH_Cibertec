package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import model.AlumnoModel;
//import util.Validaciones;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmConsultaAlumno extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblConsultaAlumno;
	private JLabel lblFechaInicio;
	private JTextField txtFechaInicio;
	private JLabel lblFechaFinal;
	private JTextField txtFechaFinal;
	private JButton btnConsulta;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaAlumno frame = new FrmConsultaAlumno();
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
	public FrmConsultaAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Alumno");
		setBounds(100, 100, 611, 434);
		getContentPane().setLayout(null);
		
		lblConsultaAlumno = new JLabel("CONSULTA ALUMNO");
		lblConsultaAlumno.setFont(new Font("Tahoma", Font.ITALIC, 35));
		lblConsultaAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaAlumno.setBounds(0, 11, 643, 32);
		getContentPane().add(lblConsultaAlumno);
		
		lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setBounds(10, 64, 82, 22);
		getContentPane().add(lblFechaInicio);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setBounds(83, 65, 127, 20);
		getContentPane().add(txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		lblFechaFinal = new JLabel("Fecha final");
		lblFechaFinal.setBounds(247, 64, 82, 22);
		getContentPane().add(lblFechaFinal);
		
		txtFechaFinal = new JTextField();
		txtFechaFinal.setBounds(324, 65, 127, 20);
		getContentPane().add(txtFechaFinal);
		txtFechaFinal.setColumns(10);
		
		btnConsulta = new JButton("CONSULTA");
		btnConsulta.addActionListener(this);
		btnConsulta.setBounds(496, 64, 89, 23);
		getContentPane().add(btnConsulta);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 575, 292);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombres", "Apellidos", "DNI", "Correo", "Fecha de Nacimiento", "Fecha de Registro"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(87);
		table.getColumnModel().getColumn(3).setPreferredWidth(73);
		table.getColumnModel().getColumn(4).setPreferredWidth(97);
		table.getColumnModel().getColumn(5).setPreferredWidth(127);
		table.getColumnModel().getColumn(6).setPreferredWidth(119);
		scrollPane.setViewportView(table);


	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnConsulta) {
			btnConsultaActionPerformed(arg0);
		}
	}
	protected void btnConsultaActionPerformed(ActionEvent arg0) {
		
		String fecInicio = txtFechaInicio.getText();
		String fecFinal = txtFechaFinal.getText();
		
		//if (!fecInicio.matches(Validaciones.FECHA)) {
			//mensaje("La fecha inicio el fromato es ##-##-####");
			//return;
		//}
		//if (!fecFinal.matches(Validaciones.FECHA)) {
			//mensaje("La fecha final el fromato es ##-##-####");
			//return;
		//}
						
		AlumnoModel model = new AlumnoModel();
		List<Alumno> listaAlumno = null;
				
		if(fecInicio.equals("") || fecFinal.equals("")) {
			listaAlumno = model.ConsultaAlumno();
		}else {
			listaAlumno = model.listaAlumno(fecInicio, fecFinal);
		}
		
				
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		for (Alumno x : listaAlumno) {
			Object[] fila =  {x.getIdUsuario(), x.getNombre(), x.getApellido(), x.getDni(), x.getCorreo(), x.getFechaNacimiento(),
					x.getFechaRegistro()};
			dtm.addRow(fila);
		}
	}
	
	public void mensaje(String smg) {
		JOptionPane.showMessageDialog(this, smg);
	}
}
