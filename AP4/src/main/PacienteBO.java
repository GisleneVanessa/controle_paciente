package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class PacienteBO {

	public List list()throws Exception {
		PacienteDAO pacienteDAO = new PacienteDAO();
		return pacienteDAO.list();
	}

	public void cadastrar(PacienteDTO pacienteDTO) throws Exception {
		PacienteDAO pacienteDAO = new PacienteDAO();

		// Valida��o de unicidade
//		if (!list().isEmpty()) {
//			if (pacienteDAO.unique(pacienteDTO))
//				throw new Exception("Paciente j� existe.");
//		}

		// Valida��o da matr�cula do paciente
		if (pacienteDTO.getCod() == 0)
			throw new Exception("Por favor preencha o c�digo.");

		// Valida��o do nome do paciente
		if (pacienteDTO.getNome() == null || pacienteDTO.getNome().isEmpty())
			throw new Exception("Por favor digite o nome do paciente!");
		else if (pacienteDTO.getNome().length() > 80)
			throw new Exception("Nome do paciente muito longo. M�ximo: 80 caracteres.");

		// Valida��o do endere�o
		if (pacienteDTO.getEnd() == null || pacienteDTO.getEnd().isEmpty())
			throw new Exception("Por favor digite o endere�o do paciente!");
		else if (pacienteDTO.getEnd().length() > 80)
			throw new Exception("Endere�o muito longo. M�ximo: 80 caracteres.");

		// Valida��o do celular
		if (pacienteDTO.getContato() == null || pacienteDTO.getContato().isEmpty())
			throw new Exception("Por favor digite o contato do paciente!");
		else if (pacienteDTO.getContato().length() < 0)
			throw new Exception("N�mero do contato incorreto.");
		else if (pacienteDTO.getContato().length() != 8)
			throw new Exception("N�mero do contato � exatamente 8 caracteres.");

		// Chamar m�todo inserir
		pacienteDAO.create(pacienteDTO);
		JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);

	}

	public void update(PacienteDTO pacienteDTO) throws Exception {
		// Valida��o da matr�cula do paciente
		if (pacienteDTO.getCod() == 0)
			throw new Exception("Por favor preencha o c�digo.");

		// Valida��o do nome do paciente
		if (pacienteDTO.getNome() == null || pacienteDTO.getNome().isEmpty())
			throw new Exception("Por favor digite o nome do paciente!");
		else if (pacienteDTO.getNome().length() > 80)
			throw new Exception("Nome do paciente muito longo. M�ximo: 80 caracteres.");

		// Valida��o do endere�o
		if (pacienteDTO.getEnd() == null || pacienteDTO.getEnd().isEmpty())
			throw new Exception("Por favor digite o endere�o do paciente!");
		else if (pacienteDTO.getEnd().length() > 80)
			throw new Exception("Endere�o muito longo. M�ximo: 80 caracteres.");

		// Valida��o do celular
		if (pacienteDTO.getContato() == null || pacienteDTO.getContato().isEmpty())
			throw new Exception("Por favor digite o celular do paciente!");
		else if (pacienteDTO.getContato().length() < 0)
			throw new Exception("N�mero do celular incorreto.");
		else if (pacienteDTO.getContato().length() != 8)
			throw new Exception("N�mero do celular � exatamente 8 caracteres.");

		// Chamar m�todo inserir do professorDAO
		PacienteDAO pacienteDAO = new PacienteDAO();
		pacienteDAO.update(pacienteDTO);
		JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
}

	public void delete(PacienteDTO pacienteDTO) throws Exception {
		PacienteDAO pacienteDAO = new PacienteDAO();
		pacienteDAO.delete(pacienteDTO);
	}
}
