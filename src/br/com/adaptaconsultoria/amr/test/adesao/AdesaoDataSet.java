package br.com.adaptaconsultoria.amr.test.adesao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.adaptaconsultoria.amr.model.Cadastro;
import br.com.adaptaconsultoria.amr.model.CadastroPendente;
import br.com.adaptaconsultoria.amr.model.PagamentoPendente;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.Produto;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.dao.CadastroPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PagamentoPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.persistence.jpa.HCadastroPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.jpa.HPagamentoPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.jpa.HParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.jpa.HUsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.service.AdesaoService;
import br.com.adaptaconsultoria.amr.service.CompraService;
import br.com.adaptaconsultoria.amr.service.PagamentoService;
import br.com.adaptaconsultoria.amr.service.UsuarioService;
import br.com.adaptaconsultoria.amr.util.io.FileUtil;
import br.com.adaptaconsultoria.amr.util.lang.TextoUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class AdesaoDataSet {

	public static final String EMPRESA_LOGIN = "NTB01";

	public void criaArquivoDataSetPadrao() throws Exception {
		String diretorio = new File("").getAbsolutePath() + "/res/dataset/";

		int patrocinador = 0;
		List<String> patrocinadores = new ArrayList<String>();
		patrocinadores.add(EMPRESA_LOGIN);

		boolean esquerda = true;

		Random random = new Random();
		List<String> sobrenomes = FileUtil.toStringListWithEncode(diretorio + "dataset-sobrenomes.txt");
		List<String> enderecos = FileUtil.toStringListWithEncode(diretorio + "dataset-enderecos.txt");
		List<String> cpfs = FileUtil.toStringList(diretorio + "dataset-cpf.txt");

		List<String> nomesMasculinos = FileUtil.toStringListWithEncode(diretorio + "dataset-nomes-masculinos.txt");
		String arquivo = "";

		for (int i = 0; i < nomesMasculinos.size(); i++) {
			String nome = nomesMasculinos.get(i) + " " + sobrenomes.get(random.nextInt(sobrenomes.size()));
			String login = TextoUtil.removeAcentos(nomesMasculinos.get(i).toUpperCase());

			// patrocinador
			String linha = patrocinadores.get(patrocinador);
			// nome
			linha += ";" + nome;
			// cpfCnpj
			linha += ";" + cpfs.get(i);
			// dataNascimento
			linha += ";01/01/1990";
			// genero
			linha += ";M";
			// email
			linha += ";" + login.toLowerCase() + "@adaptateste.com.br";
			// usuario
			linha += ";" + login;

			// cep;rua;numero;bairro;cidade;regiao
			linha += ";" + enderecos.get(random.nextInt(enderecos.size()));

			// Produtos
			linha += ";2";

			// Esquerda ou direita
			linha += ";" + (esquerda ? "E" : "D");

			patrocinadores.add(login);
			if (!esquerda)
				patrocinador++;

			esquerda = !esquerda;

			arquivo += "\n" + linha;
		}

		int cnpjIndex = nomesMasculinos.size() + 1;
		List<String> nomesFemininos = FileUtil.toStringListWithEncode(diretorio + "dataset-nomes-femininos.txt");
		for (int i = 0; i < nomesFemininos.size(); i++) {
			String nome = nomesFemininos.get(i) + " " + sobrenomes.get(random.nextInt(sobrenomes.size()));
			String login = TextoUtil.removeAcentos(nomesFemininos.get(i).toUpperCase());

			// patrocinador
			String linha = patrocinadores.get(patrocinador);
			// nome
			linha += ";" + nome;
			// cpfCnpj
			linha += ";" + cpfs.get(i + cnpjIndex);
			// dataNascimento
			linha += ";01/01/1990";
			// genero
			linha += ";F";
			// email
			linha += ";" + login.toLowerCase() + "@adaptateste.com.br";
			// usuario
			linha += ";" + login;

			// cep;rua;numero;bairro;cidade;regiao
			linha += ";" + enderecos.get(random.nextInt(enderecos.size()));

			// Produtos
			linha += ";2";

			// Esquerda ou direita
			linha += ";" + (esquerda ? "E" : "D");

			patrocinadores.add(login);
			if (!esquerda)
				patrocinador++;

			esquerda = !esquerda;

			arquivo += "\n" + linha;
		}

		FileUtil.save(arquivo.replaceFirst("\n", ""), diretorio + "dataset-adesao.txt");

	}

	private void adicionaAdesoes() throws Exception {

		UsuarioDao usuarioDao = new HUsuarioDao();
		ParceiroNegociosDao parceiroNegociosDao = new HParceiroNegociosDao();
		CadastroPendenteDao cadastroPendenteDao = new HCadastroPendenteDao();
		PagamentoPendenteDao pagamentoPendenteDao = new HPagamentoPendenteDao();

		Usuario patrocinador = usuarioDao.carregaPorLogin(EMPRESA_LOGIN, AMRProperties.getInstance().getPropriedade("adclientid"));

		String diretorio = new File("").getAbsolutePath() + "/res/dataset/";

		AdesaoService manager = new AdesaoService();
		CompraService compraManager = new CompraService();
		List<Produto> produtos = new ArrayList<Produto>(); // manager.listaProdutosDisponiveis("");

		List<String> adesoes = FileUtil.toStringListWithEncode(diretorio + "dataset-adesao.txt");
		String ultimoCrescimento = "D";
		int quantidade = 0;
		for (int i = 0; i < adesoes.size(); i++) {
			try {
				String[] dado = adesoes.get(i).split(";");

				System.out.println("PATROCINADOR: " + dado[0] + "    Estado: " + dado[12] + "    Login: " + dado[6]);

				Usuario patrocinador2 = usuarioDao.carregaPorLogin(dado[0], AMRProperties.getInstance().getPropriedade("adclientid"));

				Cadastro cadastro = new Cadastro();
				cadastro.setPatrocinador(patrocinador2.getParceiroNegocios().getCodigo());
				cadastro.setNome(dado[1].trim());
				cadastro.setCpfCnpj(dado[2].trim());
				cadastro.setDataNascimento(dado[3].trim());
				cadastro.setGenero(dado[4].trim());
				cadastro.setEmail(dado[5].trim());
				cadastro.setUsuario(dado[6].trim());
				cadastro.setSenha("123");
				cadastro.setCep(dado[7].trim());
				cadastro.setRua(dado[8].trim());
				try {
					cadastro.setNumero(String.valueOf(Integer.parseInt(dado[9].trim())));
				} catch (Exception e) {
					cadastro.setNumero("10");
				}
				cadastro.setBairro(dado[10].trim());
				if (dado[11].equalsIgnoreCase("FAEAF05D03964AABB17585FF7C15CE53"))
					cadastro.setCidade("39609246D204473AAF8C0FD91D435612");
				else
					cadastro.setCidade(dado[11].trim());
				cadastro.setEstado(dado[12]);

				cadastro.setTipoTelefone1("Celular");
				cadastro.setDddTelefone1("49");
				cadastro.setNumeroTelefone1("8888-8888");

				manager.enviaAdesao(cadastro);

				Usuario novo = null;

				while (novo == null) {
					novo = usuarioDao.carregaPorLogin(cadastro.getUsuario(), AMRProperties.getInstance().getPropriedade("adclientid"));
				}

				String[] produto = dado[13].split(",");
				for (int j = 0; j < produto.length; j++) {
					while (true) {
						try {

							Produto ps = produtos.get(Integer.parseInt(produto[j]));
							// compraManager.enviaCompra(novo.getId(), ps.getId(), false, null);
							break;
						} catch (Exception e) {
							System.out.println("ERRO AO EFETUAR COMPRA: " + e.getMessage());
						}
					}
				}

				// Atualiza regra de crescimento
				String crescimento = dado[14];
				System.out.println(crescimento);

				List<CadastroPendente> cadastrosPendentes = cadastroPendenteDao.carregaPorUsuario(patrocinador2.getId());
				UsuarioService usuarioManager = new UsuarioService();
				String direto = null;
				while (cadastrosPendentes == null || cadastrosPendentes.isEmpty()) {
					System.out.println("TENTANDO");
					cadastrosPendentes = cadastroPendenteDao.carregaPorUsuario(patrocinador2.getId());
				}

				for (int j = 0; j < cadastrosPendentes.size(); j++) {

					while (true) {
						direto = cadastrosPendentes.get(j).getDireto();
						cadastro = usuarioManager.criaCadastroDeParceiroDeNegocios(direto, false, patrocinador2.getId());
						cadastro.setCrescimento(crescimento);
						usuarioManager.salvaCadastroPendente(cadastro);

						try {
							cadastro = usuarioManager.criaCadastroDeParceiroDeNegocios(direto, false, patrocinador2.getId());
							ultimoCrescimento = cadastro.getCrescimento();

							if (ultimoCrescimento == null || crescimento == null || !ultimoCrescimento.equalsIgnoreCase(crescimento)) {
								System.out.println("OPS! Erro de crescimento, não foi atualizado.");
							}

							else
								break;

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}

				// Paga o cadastro
				List<PagamentoPendente> pagamentosPendentes = pagamentoPendenteDao.carregaPorUsuario(patrocinador.getId());
				PagamentoService pagamentoManager = new PagamentoService();
				for (int j = 0; j < pagamentosPendentes.size(); j++) {
					ParceiroNegocios pn = parceiroNegociosDao.carregaPorId(direto);
					String perfil = pn.getPerfil();
					pagamentoManager.pagaTitulo(patrocinador.getId(), pagamentosPendentes.get(j).getcDebtPaymentId(), "123", null);
					while (true) {
						try {
							pn = parceiroNegociosDao.carregaPorId(direto);
							if (!perfil.trim().equalsIgnoreCase(pn.getPerfil().trim()))
								break;
							else
								System.out.println("Perfil: '" + perfil + "' = '" + pn.getPerfil() + "'");
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				if (!e.getMessage().equalsIgnoreCase("duplicar valor da chave viola a restrição de unicidade \"ad_user_un_username\""))
					throw e;
			}

			quantidade++;
			if (quantidade >= 23) {
				pagamentoPendenteDao.pagamentoTeste(AMRProperties.getInstance().getPropriedade("adclientid"));
				quantidade = 0;
			}

		}
	}

	public static void main(String[] args) throws Exception {
		AdesaoDataSet dataSet = new AdesaoDataSet();
		dataSet.adicionaAdesoes();
	}

}