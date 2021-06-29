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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entidad.Alumno;
import model.AlumnoModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;
import util.Validaciones;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class FrmReporteAlumno extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblReporteAlumno;
	private JLabel lblFechaInicio;
	private JTextField txtFechaInicio;
	private JLabel lblFechaFinal;
	private JTextField txtFechaFinal;
	private JButton btnReportar;
	private JPanel pnlReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteAlumno frame = new FrmReporteAlumno();
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
	public FrmReporteAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Alumno");
		setBounds(100, 100, 635, 443);
		getContentPane().setLayout(null);

		lblReporteAlumno = new JLabel("REPORTE ALUMNO");
		lblReporteAlumno.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblReporteAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteAlumno.setBounds(0, 11, 645, 24);
		getContentPane().add(lblReporteAlumno);

		lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setBounds(10, 61, 78, 24);
		getContentPane().add(lblFechaInicio);

		txtFechaInicio = new JTextField();
		txtFechaInicio.setBounds(98, 63, 117, 20);
		getContentPane().add(txtFechaInicio);
		txtFechaInicio.setColumns(10);

		lblFechaFinal = new JLabel("Fecha final");
		lblFechaFinal.setBounds(262, 61, 78, 24);
		getContentPane().add(lblFechaFinal);

		txtFechaFinal = new JTextField();
		txtFechaFinal.setBounds(350, 63, 109, 20);
		getContentPane().add(txtFechaFinal);
		txtFechaFinal.setColumns(10);

		btnReportar = new JButton("REPORTAR");
		btnReportar.addActionListener(this);
		btnReportar.setBounds(508, 62, 98, 23);
		getContentPane().add(btnReportar);

		pnlReporte = new JPanel();
		pnlReporte
				.setBorder(new TitledBorder(null, "Reporte", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		pnlReporte.setBounds(10, 91, 596, 311);
		getContentPane().add(pnlReporte);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnReportar) {
			btnReportarActionPerformed(arg0);
		}
	}

	protected void btnReportarActionPerformed(ActionEvent arg0) {
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

		if (fecInicio.equals("") || fecFinal.equals("")) {
			listaAlumno = model.ConsultaAlumno();
		} else {
			listaAlumno = model.listaAlumno(fecInicio, fecFinal);
		}
		// 1 La data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(null);

		// 2 El diseño del reporte

		String file = "reporteAlumno.jasper";

		// 3 Se genera el reporte

		JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);

		// 4 Se muestra en el visor

		JRViewer jRViewer = new JRViewer(jasperPrint);

		// 5 Se añade el visor al panel

		pnlReporte.removeAll();

		pnlReporte.add(jRViewer);

		pnlReporte.repaint();

		pnlReporte.revalidate();
	}

	public void mensaje(String smg) {
		JOptionPane.showMessageDialog(this, smg);
	}
}
