package steps;

import cucumber.api.java.pt.Entao;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestricoesSteps {

	private String baseUrl = "http://localhost:8080/";
	private Response response;

	@Entao("^realiza a consulta se o CPF \"([^\"]*)\" possui ou nao restricao$")
	public void realizarConsultaCPFPossuiRestricao(String CPF) {
		given()
		.get(baseUrl+"api/v1/restricoes/"+CPF)
		.then()
		.assertThat()
		.statusCode(200)
		.body("mensagem", equalTo("O CPF "+CPF+" tem problema"));
	}

	@Entao("^realiza a consulta se o CPF invalido \"([^\"]*)\" possui ou nao restricao$")
	public void realizarConsultaCPFInvalidoPossuiRestricao(String CPF)  {
		given()
		.get(baseUrl+"api/v1/restricoes/"+CPF)
		.then()
		.assertThat()
		.statusCode(204);
	}
}
