package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.Sala;
import model.SalaModel;
import util.Validaciones;



public class FrmCrudSala extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNumero;
	private JTextField txtCapacidad;
	private JTextField txtRecursos;
	private JTable table;
	private JTextField txtPiso;
	private JTextField txtEstado;
	
	 int idSeleccionado = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudSala frame = new FrmCrudSala();
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
	public FrmCrudSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Sala");
		setBounds(100, 100, 739, 587);
		getContentPane().setLayout(null);
		
		JLabel lblMantenimientoSala = new JLabel("Mantenimiento Sala");
		lblMantenimientoSala.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblMantenimientoSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoSala.setBounds(63, 0, 594, 73);
		getContentPane().add(lblMantenimientoSala);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(53, 80, 46, 14);
		getContentPane().add(lblNumero);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(53, 120, 46, 14);
		getContentPane().add(lblPiso);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(53, 160, 63, 14);
		getContentPane().add(lblCapacidad);
		
		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setBounds(53, 200, 75, 14);
		getContentPane().add(lblRecursos);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(53, 240, 46, 14);
		getContentPane().add(lblEstado);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(204, 77, 135, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		txtCapacidad = new JTextField();
		txtCapacidad.setBounds(204, 157, 167, 20);
		getContentPane().add(txtCapacidad);
		txtCapacidad.setColumns(10);
		
		txtRecursos = new JTextField();
		txtRecursos.setBounds(204, 197, 252, 20);
		getContentPane().add(txtRecursos);
		txtRecursos.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Boton ingresar*/
				inserta();
				
			}
		});
		
		txtPiso = new JTextField();
		txtPiso.setBounds(204, 117, 135, 20);
		getContentPane().add(txtPiso);
		txtPiso.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(204, 237, 86, 20);
		getContentPane().add(txtEstado);
		txtEstado.setColumns(10);
		
		btnIngresar.setIcon(new ImageIcon(FrmCrudSala.class.getResource("/imagenes/anadir (1).png")));
		btnIngresar.setBounds(572, 84, 119, 34);
		getContentPane().add(btnIngresar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Boton actualizar*/
				actualiza();
				
			}
		});
		btnActualizar.setIcon(new ImageIcon(FrmCrudSala.class.getResource("/imagenes/editar (1).png")));
		btnActualizar.setBounds(572, 145, 119, 34);
		getContentPane().add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Boton eliminar*/
				
				eliminar();
			}
		});
		btnEliminar.setIcon(new ImageIcon(FrmCrudSala.class.getResource("/imagenes/cerrar.png")));
		btnEliminar.setBounds(572, 200, 119, 34);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 286, 663, 260);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*action mouse clicked*/
				if (arg0.getSource() == table) {
					busca();
				}
			}
			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}

			
			
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"idSala", "Numero", "Piso", "Capacidad", "Recursos", "Estado"
			}
		));
		 JTableHeader headerResumen = table.getTableHeader();

	        headerResumen.setReorderingAllowed(false);
	        headerResumen.setResizingAllowed(false);
	        
	       table.getColumnModel().getColumn(0).setPreferredWidth(5); 
	       table.getColumnModel().getColumn(1).setPreferredWidth(10); 
	       table.getColumnModel().getColumn(2).setPreferredWidth(10); 
	       table.getColumnModel().getColumn(3).setPreferredWidth(80); 
	       table.getColumnModel().getColumn(4).setPreferredWidth(80); 
	       table.getColumnModel().getColumn(5).setPreferredWidth(30); 
	       
	       /*Para centrar las colmunas  IdSala - Numero - Piso*/
	       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	       centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	       table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	       table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	       table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	       
	       /*Para seleccionar ujna sola fila */
	       table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	       
	       /*Para cambiar el color de lo seleccionado */
	       
	       /*table.setDefaultEditor(object.class, null);*/
	       
	       
		scrollPane.setViewportView(table);
		
		lista();
		

		
	} 
	
	void mensaje (String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
	
	void limpiarCajasTexto() {
		txtNumero.setText("");
		txtPiso.setText("");
		txtRecursos.setText("");
		txtCapacidad.setText("");
		txtEstado.setText("");
		txtNumero.requestFocus();
		
	}
	public void lista() {
		SalaModel m = new SalaModel();
		List<Sala> lista = m.listaSala();
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		for (Sala x  : lista) {
			Object[] f = {x.getIdsala(), x.getNumero(), x.getPiso(), x.getCapacidad(), x.getRecursos(), x.getEstado()};
			
			dtm.addRow(f);
			
		}
		
	}
	
	public void inserta() {
		
	
		String numero = txtNumero.getText();
		String piso = txtPiso.getText().trim();
		String capacidad = txtCapacidad.getText().trim();
		String recursos = txtRecursos.getText().trim();
		String estado = txtEstado.getText().trim();
		
		
		if(!numero.matches(Validaciones.PISO)) 
		{
			mensaje("El número de la sala es 1 caracter y con 2  o 3 dígitos");
			return;
		}
		else if(!piso.matches("[1-5]{1}")) 
		{
			mensaje("El piso maximo de el numero 5");
		 	return;
		}
		else if(!capacidad.matches(Validaciones.TEXTO_NUMERO)) 
		{
			mensaje("La capacidad de la sala es de 2 a 20 caracteres");
			return;
		} 
		else if(!recursos.matches(Validaciones.TEXTO_NUMERO)) 
		{
			mensaje("Los recursos de la sala es de 2 a 20 caracteres");
			return;
		} 
		else if(!estado.matches("[1-2]{1}")) 
		{
			mensaje("La sala solo puede estar 1=Activo o 2=Inactivo ");
		 	return;
		}
		else 
		{
			try {
			Sala obj = new Sala();
			obj.setNumero(numero);
			obj.setPiso(Integer.parseInt(piso));
			obj.setCapacidad(capacidad);
			obj.setRecursos(recursos);
			obj.setEstado(Byte.parseByte(estado));
		
			SalaModel m = new SalaModel();
			int salida = m.insertaSala(obj);
			
			if(salida>0)
			{
				mensaje("Registro Exitoso");
				lista();
				limpiarCajasTexto();
				idSeleccionado = 0;
			}
			else 
			{
				mensaje("Registro erróneo");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			mensaje("No se pudo registrar");
		}
			}
		
		
	}
	public void busca() {
		
		int fila = table.getSelectedRow();
		
		 idSeleccionado=(Integer)table.getValueAt(fila, 0);
		String numero = (String)table.getValueAt(fila, 1);
		int piso = (Integer)table.getValueAt(fila, 2);
		String capacidad = (String)table.getValueAt(fila, 3);
		String recursos = (String)table.getValueAt(fila, 4);
		byte estado = (byte)table.getValueAt(fila, 5);
		
		
		txtNumero.setText(numero);
		txtPiso.setText(String.valueOf(piso));
		txtCapacidad.setText(capacidad);
		txtRecursos.setText(recursos);
		txtEstado.setText(String.valueOf(estado));
		
		
	}
	
	
	
	public void actualiza() {
		
		String numero = txtNumero.getText().trim();
		String piso = txtPiso.getText().trim();
		String capacidad = txtCapacidad.getText().trim();
		String recursos = txtRecursos.getText().trim();
		String estado = txtEstado.getText().trim();
		
		if(idSeleccionado  ==  0) {
			mensaje("Seleccione una fila");
			return;
		}
		if(!numero.matches(Validaciones.PISO)) 
		{
			mensaje("El número de la sala es 1 caracter y con 2  o 3 dígitos");
			return;
		}
		if(!piso.matches("[1-5]{1}")) 
		{
			mensaje("El piso maximo de el numero 5");
		 	return;
		}
		if(!capacidad.matches(Validaciones.TEXTO_NUMERO)) 
		{
			mensaje("La capacidad de la sala es de 2 a 20 caracteres");
			return;
		} 
		if(!recursos.matches(Validaciones.TEXTO_NUMERO)) 
		{
			mensaje("Los recursos de la sala es de 2 a 20 caracteres");
			return;
		} 
		if(!estado.matches("[1-2]{1}")) 
		{
			mensaje("La sala solo puede estar 1=Activo o 2=Inactivo ");
		 	return;
		}
		
		Sala obj = new Sala();
		obj.setIdsala(idSeleccionado);
		obj.setNumero(numero);
		obj.setPiso(Integer.parseInt(piso));
		obj.setCapacidad(capacidad);
		obj.setRecursos(recursos);
		obj.setEstado(Byte.parseByte(estado));
		
		SalaModel m = new SalaModel();
		
		int s = m.actualizaSala(obj);
		
		
		if (s > 0) {
			mensaje("Se actualizó correctamente");
			lista();
			limpiarCajasTexto();
			
			idSeleccionado = 0;  
		} else {
			mensaje("Error en el registro");
		}
		}
		
		
		
	
	public void eliminar() {
		
		try {
		if (idSeleccionado == 0) {
			mensaje("Seleccione una fila");
		} else {
			SalaModel m = new SalaModel();
			int s = m.eliminaSala(idSeleccionado);
			if (s > 0) {
				mensaje("Se eliminó correctamente");
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
			} else {
				mensaje("Error al eliminar");
			}
		}}
		catch (Exception e) {
			e.printStackTrace();
			mensaje("No se pudo eliminar");
		
	
	
	
	}
		
	}
	
}




























