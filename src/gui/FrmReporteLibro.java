package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entidad.Libro;
import model.LibroModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;
import util.Validaciones;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmReporteLibro extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtInicio;
	private JTextField txtFinal;
	private JPanel pnlReporte;
	private JButton btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteLibro frame = new FrmReporteLibro();
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
	public FrmReporteLibro() {
		getContentPane().setBackground(new Color(255, 204, 102));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Libro");
		setBounds(100, 100, 902, 485);
		getContentPane().setLayout(null);

		JLabel lblReportesLibro = new JLabel("Reportes Libro");
		lblReportesLibro.setIcon(new ImageIcon(FrmReporteLibro.class.getResource("/imagenes/62863books_109231.png")));
		lblReportesLibro.setFont(new Font("Segoe Print", Font.PLAIN, 30));
		lblReportesLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportesLibro.setBounds(0, 11, 886, 79);
		getContentPane().add(lblReportesLibro);

		JLabel lblNewLabel = new JLabel("Consultar A\u00F1o(Desde)");
		lblNewLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 116, 203, 61);
		getContentPane().add(lblNewLabel);

		txtInicio = new JTextField();
		txtInicio.setColumns(10);
		txtInicio.setBounds(206, 134, 185, 29);
		getContentPane().add(txtInicio);

		JLabel lblConsultarAohasta = new JLabel("(Hasta)");
		lblConsultarAohasta.setFont(new Font("MV Boli", Font.BOLD, 16));
		lblConsultarAohasta.setBounds(437, 116, 83, 61);
		getContentPane().add(lblConsultarAohasta);

		txtFinal = new JTextField();
		txtFinal.setColumns(10);
		txtFinal.setBounds(516, 134, 185, 29);
		getContentPane().add(txtFinal);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBackground(new Color(255, 255, 255));
		btnFiltrar.addActionListener(this);
		btnFiltrar.setIcon(new ImageIcon(FrmReporteLibro.class.getResource("/imagenes/file_pdf_download_icon-icons.com_68954.png")));
		btnFiltrar.setFont(new Font("Georgia", Font.BOLD, 16));
		btnFiltrar.setBounds(733, 112, 143, 61);
		getContentPane().add(btnFiltrar);

		pnlReporte = new JPanel();
		pnlReporte.setBackground(new Color(255, 255, 153));
		pnlReporte.setLayout(new BorderLayout(0, 0));
		pnlReporte.setBorder(new TitledBorder(new LineBorder(new Color(255, 153, 51), 5), "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlReporte.setBounds(20, 187, 856, 257);
		getContentPane().add(pnlReporte);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}

	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String fechaIni = txtInicio.getText();
		String fechaFin = txtFinal.getText();

		if (!fechaIni.matches(Validaciones.NUMERO)) {
			mensaje("Inserte las Fechas que desea Consultar Solo 4 NUMEROS");
			return;
		}
		if (!fechaFin.matches(Validaciones.NUMERO)) {
			mensaje("Inserte las Fechas que desea Consultar Solo 4 NUMEROS");
			return;
		}

		LibroModel model = new LibroModel();
		List<Libro> lstLibro = model.listaPorAño(fechaIni, fechaFin);

		// 1 La data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstLibro);

		// 2 El diseño del reporte
		String file = "reporteLibro.jasper";

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

	private void mensaje(String msj) {
		JOptionPane.showMessageDialog(this, msj);

	}

}
