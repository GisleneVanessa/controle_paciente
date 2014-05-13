package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fCod;
	private JTextField fNome;
	private JTextField fEnd;
	private JTextField fContato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UPaciente dialog = new UPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UPaciente() {
		setResizable(false);
		setModal(true);
		setTitle("Alterar Paciente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("C\u00F3digo");
		label.setBounds(28, 23, 258, 16);
		contentPanel.add(label);

		fCod = new JTextField();
		fCod.setColumns(10);
		fCod.setBounds(25, 40, 134, 28);
		contentPanel.add(fCod);

		fNome = new JTextField();
		fNome.setColumns(10);
		fNome.setBounds(28, 86, 310, 28);
		contentPanel.add(fNome);

		JLabel label_1 = new JLabel("Nome");
		label_1.setBounds(31, 69, 307, 16);
		contentPanel.add(label_1);

		fEnd = new JTextField();
		fEnd.setColumns(10);
		fEnd.setBounds(28, 132, 310, 28);
		contentPanel.add(fEnd);

		JLabel label_2 = new JLabel("Endere\u00E7o");
		label_2.setBounds(31, 115, 307, 16);
		contentPanel.add(label_2);

		fContato = new JTextField();
		fContato.setColumns(10);
		fContato.setBounds(28, 179, 131, 28);
		contentPanel.add(fContato);

		JLabel label_3 = new JLabel("Contato");
		label_3.setBounds(31, 162, 128, 16);
		contentPanel.add(label_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAtualizar = new JButton("Atualizar");
				btnAtualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnAtualizar.setActionCommand("OK");
				buttonPane.add(btnAtualizar);
				getRootPane().setDefaultButton(btnAtualizar);
			}
			{
				JButton btnVoltar = new JButton("Voltar");
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnVoltar.setActionCommand("Cancel");
				buttonPane.add(btnVoltar);
			}
		}
	}
}
