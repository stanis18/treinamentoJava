package br.jus.trt6.cursojee.testes;

import java.util.List;

import org.junit.Test;

import br.jus.trt6.cursojee.entidades.*;
import br.jus.trt6.cursojee.dao.*;


public class Aula8Test extends TesteGenerico {

	@Test
	public void testConsultarPessoasRelatorio() {
		ServidorDAO dao = (ServidorDAO) getDAO(Servidor.class);
		
		List<Servidor> servidoresRelatorio = dao.listarRelatorio();
		assertNotNull(servidoresRelatorio);
		assertFalse(servidoresRelatorio.isEmpty());
		
		// verifica os campos preenchidos
		for (Servidor servidor : servidoresRelatorio) {
			assertNotNull("ID deveria estar preenchido", servidor.getId());
			assertNotNull("Email deveria estar preenchido", servidor.getEmail());
			assertNotNull("Nome deveria estar preenchido", servidor.getNome());
			assertNull("CPF não deveria estar preenchido", servidor.getCpf());
			assertNull("Data de nascimento não deveria estar preenchido", servidor.getDataNascimento());
			
			assertNotNull("Cidade deveria estar preenchido", servidor.getIdCidade());
			assertNotNull("Nome da cidade deveria estar preenchido", servidor.getIdCidade().getNome());
			assertNull("ID da cidade não deveria estar preenchido", servidor.getIdCidade().getId());
			
			assertNotNull("UF deveria estar preenchido", servidor.getIdCidade().getuf());
			assertNotNull("Sigla da UF deveria estar preenchido", servidor.getIdCidade().getuf().getSigla());
			assertNull("Cidades da UF não deveria estar preenchido", servidor.getIdCidade().getuf().getCidades());
			assertNull("ID da UF não deveria estar preenchido", servidor.getIdCidade().getuf().getId());
			
			
		}
	}

	private <TIPO> IDAO<TIPO> getDAO(Class<TIPO> clazz) {
		return DAOFactory.createDAO(clazz, getJpa().getEm());
	}

	
}
