package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import entidad.Libro;
import entidad.RegistrarUsuario;
import model.LibroModel;
import model.UsuarioModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmReporteUsuario extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFechaIni;
	private JTextField txtFechaFin;
	private JButton btnConsultar;
	private JPanel panelUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteUsuario frame = new FrmReporteUsuario();
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
	public FrmReporteUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Usuario");
		setBounds(100, 100, 1122, 661);
		getContentPane().setLayout(null);

		JLabel lblReporteUsuario = new JLabel("REPORTE USUARIO");
		lblReporteUsuario.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblReporteUsuario.setBounds(402, 11, 250, 45);
		getContentPane().add(lblReporteUsuario);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(158, 114, 91, 14);
		getContentPane().add(lblFechaInicio);

		JLabel lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setBounds(489, 114, 99, 14);
		getContentPane().add(lblFechaFinal);

		txtFechaIni = new JTextField();
		txtFechaIni.setBounds(253, 111, 226, 20);
		getContentPane().add(txtFechaIni);
		txtFechaIni.setColumns(10);

		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		txtFechaFin.setBounds(564, 111, 226, 20);
		getContentPane().add(txtFechaFin);

		panelUsuario = new JPanel();
		panelUsuario.setLayout(new BorderLayout(0, 0));
		panelUsuario.setBounds(76, 170, 997, 428);
		getContentPane().add(panelUsuario);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(828, 102, 149, 38);
		getContentPane().add(btnConsultar);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnConsultar) {
			handle_btnConsultar_actionPerformed(arg0);
		}
	}

	protected void handle_btnConsultar_actionPerformed(ActionEvent arg0) {
		String fecInicio = txtFechaIni.getText().trim();
		String fecFinal = txtFechaFin.getText().trim();

		UsuarioModel model = new UsuarioModel();
		List<RegistrarUsuario> lstUsuario = model.consultaUsuario(fecInicio, fecFinal);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstUsuario);
		String file = "ReporteUsuario.jasper";
		JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);
		JRViewer jRViewer = new JRViewer(jasperPrint);

		panelUsuario.removeAll();
		panelUsuario.add(jRViewer);
		panelUsuario.repaint();
		panelUsuario.revalidate();
	}
}
