import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

public class Reserva extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textDNI;
	private JComboBox<String> comboBoxHabitacion;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnMedia, rdbtnCompleta,rdbtnTodo,rdbtnDesayuno;
	private JSpinner spinnerNoches;
	private JTextArea txtrResumen;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reserva frame = new Reserva();
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
	public Reserva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][]"));
		
		JLabel lblNombre = new JLabel("Nombre");
		contentPane.add(lblNombre, "cell 0 0,alignx trailing");
		
		textNombre = new JTextField();
		contentPane.add(textNombre, "cell 1 0,growx");
		textNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		contentPane.add(lblApellidos, "cell 0 1,alignx trailing");
		
		textApellidos = new JTextField();
		contentPane.add(textApellidos, "cell 1 1,growx");
		textApellidos.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI");
		contentPane.add(lblDNI, "cell 0 2,alignx trailing");
		
		textDNI = new JTextField();
		contentPane.add(textDNI, "cell 1 2,growx");
		textDNI.setColumns(10);
		
		JLabel lblHabitacion = new JLabel("Habitación");
		contentPane.add(lblHabitacion, "cell 0 3,alignx trailing");
		
		comboBoxHabitacion = new JComboBox();
		comboBoxHabitacion.setModel(new DefaultComboBoxModel(new String[] {"Individual", "Doble", "Triple", "Suit Deluxe"}));
		contentPane.add(comboBoxHabitacion, "cell 1 3,growx");
		
		JLabel lblRegimen = new JLabel("Régimen");
		contentPane.add(lblRegimen, "cell 0 4");
		
		rdbtnDesayuno = new JRadioButton("Desayuno");
		buttonGroup.add(rdbtnDesayuno);
		rdbtnDesayuno.setSelected(true);
		contentPane.add(rdbtnDesayuno, "flowx,cell 1 4");
		
		rdbtnCompleta = new JRadioButton("Pensión Completa");
		buttonGroup.add(rdbtnCompleta);
		contentPane.add(rdbtnCompleta, "flowx,cell 1 5");
		
		JLabel lblNumNoches = new JLabel("Núm. Noches");
		contentPane.add(lblNumNoches, "cell 0 6");
		
		rdbtnMedia = new JRadioButton("Media Pensión");
		buttonGroup.add(rdbtnMedia);
		contentPane.add(rdbtnMedia, "cell 1 4");
		
		rdbtnTodo = new JRadioButton("Todo Incluído");
		buttonGroup.add(rdbtnTodo);
		contentPane.add(rdbtnTodo, "cell 1 5");
		
		spinnerNoches = new JSpinner();
		spinnerNoches.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		contentPane.add(spinnerNoches, "cell 1 6");
		
		JLabel lblNewLabel = new JLabel("Resúmen:");
		contentPane.add(lblNewLabel, "cell 0 7");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 8,grow");
		
		txtrResumen = new JTextArea();
		scrollPane.setViewportView(txtrResumen);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptarYValidar();
			}
		});
		contentPane.add(btnAceptar, "cell 1 9,alignx center");
	}

	protected void aceptarYValidar() {
		if(textNombre.getText()==null||textNombre.getText().isBlank() || 
				textApellidos.getText()==null||textApellidos.getText().isBlank() || 
				textDNI.getText()==null||textDNI.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Debe introducir los campos nombre, apellidos y DNI."," Comprueble los datos", JOptionPane.WARNING_MESSAGE);
			return;	
		}
		String nombre= textNombre.getText();
		String apellidos= textApellidos.getText();
		String dni= textDNI.getText();
		
		int precio=60;
		
		String tipo=(String)comboBoxHabitacion.getSelectedItem();
		if (tipo.equals("Doble")){
			precio=85;
		}else if(tipo.equals("Tripe")){
			precio=102;
		}else if(tipo.equals("Suit Deluxe")){
			precio=350;
		}
		
		String regimen="Desayuno";
		if(rdbtnDesayuno.isSelected()) {
			precio=precio+3;
		}
		if(rdbtnMedia.isSelected()) {
			regimen="Media Pensión";
			precio=precio+5;
		}
		if(rdbtnCompleta.isSelected()) {
			regimen="Pensión Completa";
			precio=precio+7;
		}
		if(rdbtnTodo.isSelected()) {
			regimen="Todo incluído";
			precio=precio+16;
		}
		
		int noches=(int) spinnerNoches.getValue();
		precio=precio*noches;
		
		txtrResumen.setText("Nombre: "+nombre + "\n"+
				"Apellidos: "+apellidos + "\n"+
				"DNI: "+dni + "\n"+
				"tipo: "+tipo + "\n"+
				"Régimen: "+regimen + "\n"+
				"Núm. Noches: "+noches + "\n"+
				"Precio Total: "+precio + "\n");
	}

}
