package steps;

import cucumber.api.java.pt.Entao;
import org.junit.Assert;
import pages.RestricoesPage;
import pages.SimulacoesPage;

public class SimulacoesSteps {

	private SimulacoesPage simulacoesPage = new SimulacoesPage();

	@Entao("^realiza a consulta de todas as simulacoes existentes$")
	public void realizarConsultaSimulacoesExistentes() {
		Assert.assertTrue("Nao foi possivel realizar a consulta de todas as simulacoes existentes", simulacoesPage.realizarConsultaSimulacoesExistentes());
	}

	@Entao("^realiza inclusao de uma simulacao$")
	public void realizarInclusaoSimulacao() {
		Assert.assertTrue("Nao foi possivel realizar a inclusao de uma simulacao", simulacoesPage.realizarInclusaoSimulacao());
	}

	@Entao("^realiza a consulta de simulacoes de um CPF$")
	public void realizarConsultaSimulacoesCPF() {
		Assert.assertTrue("Nao foi possivel realizar a consulta de simulacoes de um CPF", simulacoesPage.realizarConsultaSimulacoesCPF());
	}

	@Entao("^realiza a alteracao de uma simulacao pelo CPF$")
	public void realizarAlteracaoSimulacaoCPF() {
		Assert.assertTrue("Nao foi possivel realizar a alteracao de uma simulacao pelo CPF", simulacoesPage.realizarAlteracaoSimulacaoCPF());
	}

	@Entao("^realiza a remocao de uma simulacao pelo ID$")
	public void realizarRemocaoSimulacaoID() {
		Assert.assertTrue("Nao foi possivel realizar a remocao de uma simulacao pelo ID", simulacoesPage.realizarRemocaoSimulacaoID());
	}

	@Entao("^realiza inclusao de uma simulacao com payload invalido$")
	public void realizarInclusaoSimulacaoPayloadInvalido() {
		Assert.assertTrue("Nao foi possivel realizar a inclusao de uma simulacao com payload invalido", simulacoesPage.realizarInclusaoSimulacaoPayloadInvalido());
	}

	@Entao("^realiza inclusao de uma simulacao com CPF duplicado$")
	public void realizarInclusaoSimulacaoCPFDuplicado() {
		Assert.assertTrue("Nao foi possivel realizar a inclusao de uma simulacao com CPF duplicado", simulacoesPage.realizarInclusaoSimulacaoCPFDuplicado());
	}

    @Entao("^realiza a consulta de simulacoes de um CPF invalido$")
	public void realizarConsultaSimulacoesCPFInvalido() {
		Assert.assertTrue("Nao foi possivel realizar a consulta de simulacoes de um CPF invalido", simulacoesPage.realizarConsultaSimulacoesCPFInvalido());
	}

	@Entao("^realiza a alteracao de uma simulacao pelo CPF para um CPF ja existente$")
	public void realizarAlteracaoSimulacaoCPFParaCPFExistente() {
		Assert.assertTrue("Nao foi possivel realizar a alteracao de uma simulacao pelo CPF para um CPF ja existente", simulacoesPage.realizarAlteracaoSimulacaoCPFParaCPFExistente());
	}

	@Entao("^realiza a alteracao de uma simulacao por um CPF inexistente$")
	public void realizarAlteracaoSimulacaoCPFInexistente() {
		Assert.assertTrue("Nao foi possivel realizar a alteracao de uma simulacao por um CPF inexistente", simulacoesPage.realizarAlteracaoSimulacaoCPFInexistente());
	}

	@Entao("^realiza a remocao de uma simulacao com ID invalido$")
	public void realizarRemocaoSimulacaoIDInvalido() {
		Assert.assertTrue("Nao foi possivel realizar a remocao de uma simulacao com ID invalido", simulacoesPage.realizarRemocaoSimulacaoIDInvalido());
	}
}
