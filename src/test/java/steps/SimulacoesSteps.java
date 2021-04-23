package steps;

import Utils.*;
import cucumber.api.java.pt.Entao;
import io.restassured.response.Response;
import model.Simulacoes;
import model.Simulacoes;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SimulacoesSteps {

    private String baseUrl = "http://localhost:8080/";

    @Entao("^realiza a consulta de todas as simulacoes existentes$")
	public void realizarConsultaSimulacoesExistentes() {
		Response response = given()
		.get(baseUrl+"api/v1/simulacoes/")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		Simulacoes[] retornoSimulacao = response.getBody().as(Simulacoes[].class);
		assertTrue(retornoSimulacao.length > 0);
	}

	@Entao("^realiza inclusao de uma simulacao$")
	public void realizarInclusaoSimulacao() {
    	Utils.setVariavel("cpf", Utils.gerarCPF(false));
		Simulacoes simulacao = Simulacoes.builder().nome("Fulano de Tal").cpf(Utils.getVariavel("cpf")).email("email@email.com").valor(1200.0).parcelas(3).seguro(true).build();
		Response response = given()
		.contentType("application/json")
		.body(simulacao.asJSON())
		.post(baseUrl+"api/v1/simulacoes/")
		.then()
		.assertThat()
		.statusCode(201)
		.extract()
		.response();
		Simulacoes retornoSimulacao = response.getBody().as(Simulacoes.class);
		Utils.setVariavel("id", retornoSimulacao.getId().toString());
	}

	@Entao("^realiza a consulta de simulacoes de um CPF$")
	public void realizarConsultaSimulacoesCPF() {
		Response response = given()
		.get(baseUrl+"api/v1/simulacoes/66414919004")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		Simulacoes retornoSimulacao = response.getBody().as(Simulacoes.class);
		assertEquals("11", retornoSimulacao.getId().toString());
		assertEquals("Fulano", retornoSimulacao.getNome());
		assertEquals("66414919004", retornoSimulacao.getCpf());
		assertEquals("fulano@gmail.com", retornoSimulacao.getEmail());
		assertEquals("11000.0", retornoSimulacao.getValor().toString());
		assertEquals("3", retornoSimulacao.getParcelas().toString());
		assertEquals(true, retornoSimulacao.getSeguro());
	}

	@Entao("^realiza a alteracao de uma simulacao pelo CPF$")
	public void realizarAlteracaoSimulacaoCPF() {
		Simulacoes simulacao = Simulacoes.builder().nome("Fulano de Tal").cpf(Utils.getVariavel("cpf")).email("email@email.com").valor(1200.00).parcelas(3).seguro(true).build();
		Response response = given()
		.contentType("application/json")
		.body(simulacao.asJSON())
		.put(baseUrl+"api/v1/simulacoes/"+Utils.getVariavel("cpf"))
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		Simulacoes retornoSimulacao = response.getBody().as(Simulacoes.class);
		assertEquals(simulacao.getNome(), retornoSimulacao.getNome());
		assertEquals(simulacao.getCpf(), retornoSimulacao.getCpf());
		assertEquals(simulacao.getEmail(), retornoSimulacao.getEmail());
		assertEquals(simulacao.getValor().toString(), retornoSimulacao.getValor().toString());
		assertEquals(simulacao.getParcelas().toString(), retornoSimulacao.getParcelas().toString());
		assertEquals(simulacao.getSeguro(), retornoSimulacao.getSeguro());
	}

	//cenarios invalidos
	@Entao("^realiza inclusao de uma simulacao com payload invalido$")
	public void realizarInclusaoSimulacaoPayloadInvalido() {
		Simulacoes simulacao = Simulacoes.builder().nome("Fulano de Tal").email("email@email.com").valor(1200.00).parcelas(3).seguro(true).build();
		given()
		.contentType("application/json")
		.body(simulacao.asJSON())
		.post(baseUrl+"api/v1/simulacoes/")
		.then()
		.assertThat()
		.statusCode(400);
	}

	@Entao("^realiza inclusao de uma simulacao com CPF duplicado$")
	public void realizarInclusaoSimulacaoCPFDuplicado() {
		Simulacoes simulacao = Simulacoes.builder().nome("Fulano de Tal").cpf(Utils.getVariavel("cpf")).email("email@email.com").valor(1200.00).parcelas(3).seguro(true).build();
		given()
		.contentType("application/json")
		.body(simulacao.asJSON())
		.post(baseUrl+"api/v1/simulacoes/")
		.then()
		.assertThat()
		.statusCode(400)
		.body("mensagem", equalTo("CPF duplicado"));
    }

    @Entao("^realiza a consulta de simulacoes de um CPF invalido$")
	public void realizarConsultaSimulacoesCPFInvalido() {
		given()
		.get(baseUrl+"api/v1/simulacoes/99999999999")
		.then()
		.assertThat()
		.statusCode(404)
		.body("mensagem", equalTo("CPF 99999999999 não encontrado"));
	}

	@Entao("^realiza a alteracao de uma simulacao pelo CPF para um CPF ja existente$")
	public void realizarAlteracaoSimulacaoCPFParaCPFExistente() {
		Simulacoes simulacao = Simulacoes.builder().nome("Fulano de Tal").cpf("17822386034").email("email@email.com").valor(1200.00).parcelas(3).seguro(true).build();
		given()
		.contentType("application/json")
		.body(simulacao.asJSON())
		.put(baseUrl+"api/v1/simulacoes/66414919004")
		.then()
		.assertThat()
		.statusCode(400)
		.body("mensagem", equalTo("CPF duplicado"));
	}

	@Entao("^realiza a alteracao de uma simulacao por um CPF inexistente$")
	public void realizarAlteracaoSimulacaoCPFInexistente() {
		Simulacoes simulacao = Simulacoes.builder().nome("Fulano de Tal").cpf("17822386034").email("email@email.com").valor(1200.00).parcelas(3).seguro(true).build();
		given()
		.contentType("application/json")
		.body(simulacao.asJSON())
		.put(baseUrl+"api/v1/simulacoes/99999999999")
		.then()
		.assertThat()
		.statusCode(404)
		.body("mensagem", equalTo("CPF 99999999999 não encontrado"));
	}

	@Entao("^realiza a remocao de uma simulacao pelo ID$")
	public void realizarRemocaoSimulacaoID() {
		given()
		.delete(baseUrl+"api/v1/simulacoes/"+Utils.getVariavel("id"))
		.then()
		.assertThat()
		.statusCode(200)
		.body(equalTo("OK"));
	}
}
