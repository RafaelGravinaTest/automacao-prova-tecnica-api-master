package steps;

import cucumber.api.PendingException;
import cucumber.api.java.pt.Entao;
import org.junit.Assert;
import pages.RestricoesPage;

public class RestricoesSteps {

	private RestricoesPage restricoesPage = new RestricoesPage();

	@Entao("^realiza a consulta se o CPF \"([^\"]*)\" possui ou nao restricao$")
	public void realizarConsultaCPFPossuiRestricao(String CPF) {
		Assert.assertTrue("Erro ao consultar se o CPF "+CPF+" possui ou nao restricao", restricoesPage.realizarConsultaCPFPossuiRestricao(CPF));
	}

	@Entao("^realiza a consulta se o CPF invalido \"([^\"]*)\" possui ou nao restricao$")
	public void realizarConsultaCPFInvalidoPossuiRestricao(String CPF)  {
		Assert.assertTrue("Erro ao consultar se o CPF invalido "+CPF+" possui ou nao restricao", restricoesPage.realizaConsultaCPFInvalidoPossuiRestricao(CPF));

	}
}
