package br.com.adaptaconsultoria.amr.persistence;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import br.com.adaptaconsultoria.amr.persistence.dao.AceiteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaAcaoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaContatoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AmrLoginDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ArquivoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivacoesDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivacoesLCTODao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoAgendaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoPeriodoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoStatusDao;
import br.com.adaptaconsultoria.amr.persistence.dao.BancoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.BannerDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CadastroPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CapaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CarreiraStatusDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ComprasDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ComprasItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ConfigDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CursoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.DebtPaymentDao;
import br.com.adaptaconsultoria.amr.persistence.dao.DocumentoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoViewDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ExtratoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FinanceiroDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FormaPagamentoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.GraduacaoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.HelpVideoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ManutencaoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MatriculaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoEntregaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoPagamentoPSDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MeusPontosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MuralDao;
import br.com.adaptaconsultoria.amr.persistence.dao.NotificacaoPSDao;
import br.com.adaptaconsultoria.amr.persistence.dao.NovoEnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.OrgInfoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteCategoriaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeLinhaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PagamentoPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParametroDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosEnderecoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraClienteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeBinariaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeConfigDao;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeLinearDao;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeParcelasDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ReuniaoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ReunioesDao;
import br.com.adaptaconsultoria.amr.persistence.dao.SaldoResumidoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.SaqueDao;
import br.com.adaptaconsultoria.amr.persistence.dao.TransferenciaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioReadDao;
import br.com.adaptaconsultoria.amr.persistence.dao.VoucherDao;
import br.com.adaptaconsultoria.amr.persistence.dao.VoucherProdutoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("deprecation")
public class DaoFactory {

	private static DaoFactory instance = null;

	public static DaoFactory getInstance() {
		if (instance == null)
			instance = new DaoFactory();
		return instance;
	}

	private DaoFactory() {
		super();
	}

	private BeanFactory factory;
	private static RedeLinearDao REDELINEAR_DAO = null;
	private static ParceiroNegociosDao PARCEIRONEGOCIOS_DAO = null;
	private static UsuarioDao USUARIO_DAO = null;
	private static PacoteDao PACOTE_DAO = null;
	private static RedeBinariaDao REDEBINARIA_DAO = null;
	private static CadastroPendenteDao CADASTROPENDENTE_DAO = null;
	private static EnderecoDao ENDERECO_DAO = null;
	private static ParceiroNegociosEnderecoDao PARCEIRONEGOCIOSENDERECO_DAO = null;
	private static SaqueDao SAQUE_DAO = null;
	private static PagamentoPendenteDao PAGAMENTOPENDENTE_DAO = null;
	private static DocumentoDao DOCUMENTO_DAO = null;
	private static MuralDao MURAL_DAO = null;
	private static SaldoResumidoDao SALDORESUMIDO_DAO = null;
	private static ExtratoDao EXTRATO_DAO = null;
	private static FinanceiroDao FINANCEIRO_DAO = null;
	private static MeusPontosDao MEUSPONTOS_DAO = null;
	private static FranqueadoDao FRANQUEADO_DAO = null;
	private static ComprasDao COMPRAS_DAO = null;
	private static PacoteUpgradeDao PACOTEUPGRADE_DAO = null;
	private static PacoteUpgradeLinhaDao PACOTEUPGRADELINHA_DAO = null;
	private static TransferenciaDao TRANSFERENCIA_DAO = null;
	private static UsuarioReadDao USUARIOREAD_DAO = null;
	private static ArquivoDao ARQUIVO_DAO = null;
	private static VoucherProdutoDao VOUCHERPRODUTO_DAO = null;
	private static VoucherDao VOUCHER_DAO = null;
	private static ManutencaoDao MANUTENCAO_DAO = null;
	private static AceiteDao ACEITE_DAO = null;
	private static AtivoAgendaDao ATIVOAGENDA_DAO = null;
	private static AtivoDao ATIVO_DAO = null;
	private static AtivoPeriodoDao ATIVOPERIODO_DAO = null;
	private static MetodoEntregaDao METODOENTREGA_DAO = null;
	private static FormaPagamentoDao FORMAPAGAMENTO_DAO = null;
	private static PreCompraDao PRECOMPRA_DAO = null;
	private static PreCompraItemDao PRECOMPRAITEM_DAO = null;
	private static RedeConfigDao REDECONFIG_DAO = null;
	private static RedeParcelasDao REDEPARCELAS_DAO = null;
	private static CarrinhoComprasDao CARRINHOCOMPRAS_DAO = null;
	private static CarrinhoComprasItemDao CARRINHOCOMPRASITEM_DAO = null;
	private static BancoDao BANCO_DAO = null;
	private static GraduacaoDao GRADUACAO_DAO = null;
	private static PacoteCategoriaDao PACOTECATEGORIA_DAO = null;
	private static HelpVideoDao HELPVIDEO_DAO = null;
	private static PreCompraClienteDao PRECOMPRACLIENTE_DAO = null;
	private static NovoEnderecoDao NOVOENDERECO_DAO = null;
	private static AtivoStatusDao ATIVOSTATUS_DAO = null;
	private static CarreiraStatusDao CARREIRASTATUS_DAO = null;
	private static BannerDao BANNER_DAO = null;
	private static CapaDao CAPA_DAO = null;
	private static AgendaContatoDao AGENDACONTATO_DAO = null;
	private static ParametroDao PARAMETRO_DAO = null;
	private static AgendaAcaoDao AGENDAACAO_DAO = null;
	private static OrgInfoDao ORGINFO_DAO = null;
	private static ConfigDao CONFIG_DAO = null;
	private static EnderecoViewDao ENDERECOVIEW_DAO = null;
	private static MetodoPagamentoPSDao METODOPAGAMENTOPS_DAO = null;
	private static AmrLoginDao AMRLOGIN_DAO = null;
	private static DebtPaymentDao DEBTPAYMENT_DAO = null;
	private static NotificacaoPSDao NOTIFICACAOPS_DAO = null;
	private static CursoDao CURSO_DAO = null;
	private static MatriculaDao MATRICULA_DAO = null;
	private static AgendaDao AGENDA_DAO = null;
	private static AtivacoesDao ATIVACOES_DAO = null;
	private static AtivacoesLCTODao ATIVACOESLCTO_DAO = null;
	private static ComprasItemDao COMPRASITEM_DAO = null;
	private static ReuniaoDao REUNIAO_DAO = null;
	private static ReunioesDao REUNIOES_DAO = null;

	public BeanFactory getFactory() {
		if (factory == null) {
			Resource configuration = new ClassPathResource("applicationContext.xml");
			factory = new XmlBeanFactory(configuration);
		}
		return factory;
	}
	
	public ReuniaoDao getReuniaoDao() {
		if (REUNIAO_DAO == null)
			REUNIAO_DAO = (ReuniaoDao) getFactory().getBean("ReuniaoDao");
		return REUNIAO_DAO;
	}

	public ReunioesDao getReunioesDao() {
		if (REUNIOES_DAO == null)
			REUNIOES_DAO = (ReunioesDao) getFactory().getBean("ReunioesDao");
		return REUNIOES_DAO;
	}

	public RedeLinearDao getRedeLinearDao() {
		if (REDELINEAR_DAO == null)
			REDELINEAR_DAO = (RedeLinearDao) getFactory().getBean("RedeLinearDao");
		return REDELINEAR_DAO;
	}

	public ParceiroNegociosDao getParceiroNegociosDao() {
		if (PARCEIRONEGOCIOS_DAO == null)
			PARCEIRONEGOCIOS_DAO = (ParceiroNegociosDao) getFactory().getBean("ParceiroNegociosDao");
		return PARCEIRONEGOCIOS_DAO;
	}

	public UsuarioDao getUsuarioDao() {
		if (USUARIO_DAO == null)
			USUARIO_DAO = (UsuarioDao) getFactory().getBean("UsuarioDao");
		return USUARIO_DAO;
	}

	public PacoteDao getPacoteDao() {
		if (PACOTE_DAO == null)
			PACOTE_DAO = (PacoteDao) getFactory().getBean("PacoteDao");
		return PACOTE_DAO;
	}

	public RedeBinariaDao getRedeBinariaDao() {
		if (REDEBINARIA_DAO == null)
			REDEBINARIA_DAO = (RedeBinariaDao) getFactory().getBean("RedeBinariaDao");
		return REDEBINARIA_DAO;
	}

	public CadastroPendenteDao getCadastroPendenteDao() {
		if (CADASTROPENDENTE_DAO == null)
			CADASTROPENDENTE_DAO = (CadastroPendenteDao) getFactory().getBean("CadastroPendenteDao");
		return CADASTROPENDENTE_DAO;
	}

	public EnderecoDao getEnderecoDao() {
		if (ENDERECO_DAO == null)
			ENDERECO_DAO = (EnderecoDao) getFactory().getBean("EnderecoDao");
		return ENDERECO_DAO;
	}

	public ParceiroNegociosEnderecoDao getParceiroNegociosEnderecoDao() {
		if (PARCEIRONEGOCIOSENDERECO_DAO == null)
			PARCEIRONEGOCIOSENDERECO_DAO = (ParceiroNegociosEnderecoDao) getFactory().getBean("ParceiroNegociosEnderecoDao");
		return PARCEIRONEGOCIOSENDERECO_DAO;
	}

	public SaqueDao getSaqueDao() {
		if (SAQUE_DAO == null)
			SAQUE_DAO = (SaqueDao) getFactory().getBean("SaqueDao");
		return SAQUE_DAO;
	}

	public PagamentoPendenteDao getPagamentoPendenteDao() {
		if (PAGAMENTOPENDENTE_DAO == null)
			PAGAMENTOPENDENTE_DAO = (PagamentoPendenteDao) getFactory().getBean("PagamentoPendenteDao");
		return PAGAMENTOPENDENTE_DAO;
	}

	public DocumentoDao getDocumentoDao() {
		if (DOCUMENTO_DAO == null)
			DOCUMENTO_DAO = (DocumentoDao) getFactory().getBean("DocumentoDao");
		return DOCUMENTO_DAO;
	}

	public MuralDao getMuralDao() {
		if (MURAL_DAO == null)
			MURAL_DAO = (MuralDao) getFactory().getBean("MuralDao");
		return MURAL_DAO;
	}

	public SaldoResumidoDao getSaldoResumidoDao() {
		if (SALDORESUMIDO_DAO == null)
			SALDORESUMIDO_DAO = (SaldoResumidoDao) getFactory().getBean("SaldoResumidoDao");
		return SALDORESUMIDO_DAO;
	}

	public ExtratoDao getExtratoDao() {
		if (EXTRATO_DAO == null)
			EXTRATO_DAO = (ExtratoDao) getFactory().getBean("ExtratoDao");
		return EXTRATO_DAO;
	}

	public FinanceiroDao getFinanceiroDao() {
		if (FINANCEIRO_DAO == null)
			FINANCEIRO_DAO = (FinanceiroDao) getFactory().getBean("FinanceiroDao");
		return FINANCEIRO_DAO;
	}

	public MeusPontosDao getMeusPontosDao() {
		if (MEUSPONTOS_DAO == null)
			MEUSPONTOS_DAO = (MeusPontosDao) getFactory().getBean("MeusPontosDao");
		return MEUSPONTOS_DAO;
	}

	public FranqueadoDao getFranqueadoDao() {
		if (FRANQUEADO_DAO == null)
			FRANQUEADO_DAO = (FranqueadoDao) getFactory().getBean("FranqueadoDao");
		return FRANQUEADO_DAO;
	}

	public ComprasDao getComprasDao() {
		if (COMPRAS_DAO == null)
			COMPRAS_DAO = (ComprasDao) getFactory().getBean("ComprasDao");
		return COMPRAS_DAO;
	}

	public PacoteUpgradeDao getPacoteUpgradeDao() {
		if (PACOTEUPGRADE_DAO == null)
			PACOTEUPGRADE_DAO = (PacoteUpgradeDao) getFactory().getBean("PacoteUpgradeDao");
		return PACOTEUPGRADE_DAO;
	}

	public PacoteUpgradeLinhaDao getPacoteUpgradeLinhaDao() {
		if (PACOTEUPGRADELINHA_DAO == null)
			PACOTEUPGRADELINHA_DAO = (PacoteUpgradeLinhaDao) getFactory().getBean("PacoteUpgradeLinhaDao");
		return PACOTEUPGRADELINHA_DAO;
	}

	public TransferenciaDao getTransferenciaDao() {
		if (TRANSFERENCIA_DAO == null)
			TRANSFERENCIA_DAO = (TransferenciaDao) getFactory().getBean("TransferenciaDao");
		return TRANSFERENCIA_DAO;
	}

	public UsuarioReadDao getUsuarioReadDao() {
		if (USUARIOREAD_DAO == null)
			USUARIOREAD_DAO = (UsuarioReadDao) getFactory().getBean("UsuarioReadDao");
		return USUARIOREAD_DAO;
	}

	public ArquivoDao getArquivoDao() {
		if (ARQUIVO_DAO == null)
			ARQUIVO_DAO = (ArquivoDao) getFactory().getBean("ArquivoDao");
		return ARQUIVO_DAO;
	}

	public VoucherProdutoDao getVoucherProdutoDao() {
		if (VOUCHERPRODUTO_DAO == null)
			VOUCHERPRODUTO_DAO = (VoucherProdutoDao) getFactory().getBean("VoucherProdutoDao");
		return VOUCHERPRODUTO_DAO;
	}

	public VoucherDao getVoucherDao() {
		if (VOUCHER_DAO == null)
			VOUCHER_DAO = (VoucherDao) getFactory().getBean("VoucherDao");
		return VOUCHER_DAO;
	}

	public ManutencaoDao getManutencaoDao() {
		if (MANUTENCAO_DAO == null)
			MANUTENCAO_DAO = (ManutencaoDao) getFactory().getBean("ManutencaoDao");
		return MANUTENCAO_DAO;
	}

	public AceiteDao getAceiteDao() {
		if (ACEITE_DAO == null)
			ACEITE_DAO = (AceiteDao) getFactory().getBean("AceiteDao");
		return ACEITE_DAO;
	}

	public AtivoAgendaDao getAtivoAgendaDao() {
		if (ATIVOAGENDA_DAO == null)
			ATIVOAGENDA_DAO = (AtivoAgendaDao) getFactory().getBean("AtivoAgendaDao");
		return ATIVOAGENDA_DAO;
	}

	public AtivoDao getAtivoDao() {
		if (ATIVO_DAO == null)
			ATIVO_DAO = (AtivoDao) getFactory().getBean("AtivoDao");
		return ATIVO_DAO;
	}

	public AtivoPeriodoDao getAtivoPeriodoDao() {
		if (ATIVOPERIODO_DAO == null)
			ATIVOPERIODO_DAO = (AtivoPeriodoDao) getFactory().getBean("AtivoPeriodoDao");
		return ATIVOPERIODO_DAO;
	}

	public MetodoEntregaDao getMetodoEntregaDao() {
		if (METODOENTREGA_DAO == null)
			METODOENTREGA_DAO = (MetodoEntregaDao) getFactory().getBean("MetodoEntregaDao");
		return METODOENTREGA_DAO;
	}

	public FormaPagamentoDao getFormaPagamentoDao() 
	{
		if (FORMAPAGAMENTO_DAO == null)
			FORMAPAGAMENTO_DAO = (FormaPagamentoDao) getFactory().getBean("FormaPagamentoDao");
		return FORMAPAGAMENTO_DAO;
	}

	public PreCompraDao getPreCompraDao() {
		if (PRECOMPRA_DAO == null)
			PRECOMPRA_DAO = (PreCompraDao) getFactory().getBean("PreCompraDao");
		return PRECOMPRA_DAO;
	}

	public PreCompraItemDao getPreCompraItemDao() {
		if (PRECOMPRAITEM_DAO == null)
			PRECOMPRAITEM_DAO = (PreCompraItemDao) getFactory().getBean("PreCompraItemDao");
		return PRECOMPRAITEM_DAO;
	}

	public RedeConfigDao getRedeConfigDao() {
		if (REDECONFIG_DAO == null)
			REDECONFIG_DAO = (RedeConfigDao) getFactory().getBean("RedeConfigDao");
		return REDECONFIG_DAO;
	}

	public RedeParcelasDao getRedeParcelasDao() {
		if (REDEPARCELAS_DAO == null)
			REDEPARCELAS_DAO = (RedeParcelasDao) getFactory().getBean("RedeParcelasDao");
		return REDEPARCELAS_DAO;
	}

	public CarrinhoComprasDao getCarrinhoComprasDao() {
		if (CARRINHOCOMPRAS_DAO == null)
			CARRINHOCOMPRAS_DAO = (CarrinhoComprasDao) getFactory().getBean("CarrinhoComprasDao");
		return CARRINHOCOMPRAS_DAO;
	}

	public CarrinhoComprasItemDao getCarrinhoComprasItemDao() {
		if (CARRINHOCOMPRASITEM_DAO == null)
			CARRINHOCOMPRASITEM_DAO = (CarrinhoComprasItemDao) getFactory().getBean("CarrinhoComprasItemDao");
		return CARRINHOCOMPRASITEM_DAO;
	}

	public BancoDao getBancoDao() {
		if (BANCO_DAO == null)
			BANCO_DAO = (BancoDao) getFactory().getBean("BancoDao");
		return BANCO_DAO;
	}

	public GraduacaoDao getGraduacaoDao() {
		if (GRADUACAO_DAO == null)
			GRADUACAO_DAO = (GraduacaoDao) getFactory().getBean("GraduacaoDao");
		return GRADUACAO_DAO;
	}

	public PacoteCategoriaDao getPacoteCategoriaDao() {
		if (PACOTECATEGORIA_DAO == null)
			PACOTECATEGORIA_DAO = (PacoteCategoriaDao) getFactory().getBean("PacoteCategoriaDao");
		return PACOTECATEGORIA_DAO;
	}

	public HelpVideoDao getHelpVideoDao() {
		if (HELPVIDEO_DAO == null)
			HELPVIDEO_DAO = (HelpVideoDao) getFactory().getBean("HelpVideoDao");
		return HELPVIDEO_DAO;
	}

	public PreCompraClienteDao getPreCompraClienteDao() {
		if (PRECOMPRACLIENTE_DAO == null)
			PRECOMPRACLIENTE_DAO = (PreCompraClienteDao) getFactory().getBean("PreCompraClienteDao");
		return PRECOMPRACLIENTE_DAO;
	}

	public NovoEnderecoDao getNovoEnderecoDao() {
		if (NOVOENDERECO_DAO == null)
			NOVOENDERECO_DAO = (NovoEnderecoDao) getFactory().getBean("NovoEnderecoDao");
		return NOVOENDERECO_DAO;
	}

	public AtivoStatusDao getAtivoStatusDao() {
		if (ATIVOSTATUS_DAO == null)
			ATIVOSTATUS_DAO = (AtivoStatusDao) getFactory().getBean("AtivoStatusDao");
		return ATIVOSTATUS_DAO;
	}

	public CarreiraStatusDao getCarreiraStatusDao() {
		if (CARREIRASTATUS_DAO == null)
			CARREIRASTATUS_DAO = (CarreiraStatusDao) getFactory().getBean("CarreiraStatusDao");
		return CARREIRASTATUS_DAO;
	}

	public BannerDao getBannerDao() {
		if (BANNER_DAO == null)
			BANNER_DAO = (BannerDao) getFactory().getBean("BannerDao");
		return BANNER_DAO;
	}

	public CapaDao getCapaDao() {
		if (CAPA_DAO == null)
			CAPA_DAO = (CapaDao) getFactory().getBean("CapaDao");
		return CAPA_DAO;
	}
	
	public AgendaContatoDao getAgendaContatoDao() {
		if (AGENDACONTATO_DAO == null)
			AGENDACONTATO_DAO = (AgendaContatoDao) getFactory().getBean("AgendaContatoDao");
		return AGENDACONTATO_DAO;
	}
	
	public AgendaAcaoDao getAgendaAcaoDao() {
		if (AGENDAACAO_DAO == null)
			AGENDAACAO_DAO = (AgendaAcaoDao ) getFactory().getBean("AgendaAcaoDao");
		return AGENDAACAO_DAO;
	}

	public ParametroDao getParametroDao() {
		if (PARAMETRO_DAO == null)
			PARAMETRO_DAO = (ParametroDao) getFactory().getBean("ParametroDao");
		return PARAMETRO_DAO;
	}

	public OrgInfoDao getOrgInfoDao() {
		if (ORGINFO_DAO == null)
			ORGINFO_DAO = (OrgInfoDao) getFactory().getBean("OrgInfoDao");
		return ORGINFO_DAO;
	}

	public ConfigDao getConfigDao() {
		if (CONFIG_DAO == null)
			CONFIG_DAO = (ConfigDao) getFactory().getBean("ConfigDao");
		return CONFIG_DAO;
	}

	public EnderecoViewDao getEnderecoViewDao() {
		if (ENDERECOVIEW_DAO == null)
			ENDERECOVIEW_DAO = (EnderecoViewDao) getFactory().getBean("EnderecoViewDao");
		return ENDERECOVIEW_DAO;
	}

	public MetodoPagamentoPSDao getMetodoPagamentoPSDao() {
		if (METODOPAGAMENTOPS_DAO == null)
			METODOPAGAMENTOPS_DAO = (MetodoPagamentoPSDao) getFactory().getBean("MetodoPagamentoPSDao");
		return METODOPAGAMENTOPS_DAO;
	}

	public AmrLoginDao getAmrLoginDao() {
		if (AMRLOGIN_DAO == null)
			AMRLOGIN_DAO = (AmrLoginDao) getFactory().getBean("AmrLoginDao");
		return AMRLOGIN_DAO;
	}

	public DebtPaymentDao getDebtPaymentDao() {
		if (DEBTPAYMENT_DAO == null)
			DEBTPAYMENT_DAO = (DebtPaymentDao) getFactory().getBean("DebtPaymentDao");
		return DEBTPAYMENT_DAO;
	}

	public NotificacaoPSDao getNotificacaoPSDao() {
		if (NOTIFICACAOPS_DAO == null)
			NOTIFICACAOPS_DAO = (NotificacaoPSDao) getFactory().getBean("NotificacaoPSDao");
		return NOTIFICACAOPS_DAO;
	}

	public CursoDao getCursoDao() {
		if (CURSO_DAO == null)
			CURSO_DAO = (CursoDao) getFactory().getBean("CursoDao");
		return CURSO_DAO;
	}

	public MatriculaDao getMatriculaDao() {
		if (MATRICULA_DAO == null)
			MATRICULA_DAO = (MatriculaDao) getFactory().getBean("MatriculaDao");
		return MATRICULA_DAO;
	}

	public AgendaDao getAgendaDao() {
		if (AGENDA_DAO == null)
			AGENDA_DAO = (AgendaDao) getFactory().getBean("AgendaDao");
		return AGENDA_DAO;
	}

	public AtivacoesDao getAtivacoesDao() {
		if (ATIVACOES_DAO == null)
			ATIVACOES_DAO = (AtivacoesDao) getFactory().getBean("AtivacoesDao");
		return ATIVACOES_DAO;
	}

	public AtivacoesLCTODao getAtivacoesLCTODao() {
		if (ATIVACOESLCTO_DAO == null)
			ATIVACOESLCTO_DAO = (AtivacoesLCTODao) getFactory().getBean("AtivacoesLCTODao");
		return ATIVACOESLCTO_DAO;
	}

	public ComprasItemDao getComprasItemDao() {
		if (COMPRASITEM_DAO == null)
			COMPRASITEM_DAO = (ComprasItemDao) getFactory().getBean("ComprasItemDao");
		return COMPRASITEM_DAO;
	}

}
