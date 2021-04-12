package validator;

import Utils.Report;
import Utils.Utils;
import Utils.ValidadorJSONObject;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class SimulacoesValidator {
	private JSONObject response;
	private JSONArray responseArray;
	private int responseCode;

	public boolean validaRetornoConsultaSimulacoesExistentes(Response response) {
		this.responseArray = new JSONArray(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\n" +
				"    \"id\": 0,\n" +
				"    \"nome\": \"String\",\n" +
				"    \"cpf\": \"String\",\n" +
				"    \"email\": \"String\",\n" +
				"    \"valor\": 0,\n" +
				"    \"parcelas\": 0,\n" +
				"    \"seguro\": true\n" +
				"  }");
		return ValidadorJSONObject.comparaObjetosReportaDiferencasContrato(this.responseArray.getJSONObject(0), expectedReturn);
	}

	public boolean validaRetornoInclusaoSimulacao(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\n" +
						"    \"id\": 0,\n" +
						"    \"nome\": \"String\",\n" +
						"    \"cpf\": \"String\",\n" +
						"    \"email\": \"String\",\n" +
						"    \"valor\": 0,\n" +
						"    \"parcelas\": 0,\n" +
						"    \"seguro\": true\n" +
						"  }");
		if(ValidadorJSONObject.comparaObjetosReportaDiferencasContrato(this.response, expectedReturn)) {
			Utils.setVariavel("id", this.response.getInt("id")+"");
			return true;
		}
		return false;
	}

	public boolean validaRetornoConsultaSimulacoesCPF(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\n" +
				"    \"id\": 0,\n" +
				"    \"nome\": \"String\",\n" +
				"    \"cpf\": \"String\",\n" +
				"    \"email\": \"String\",\n" +
				"    \"valor\": 0,\n" +
				"    \"parcelas\": 0,\n" +
				"    \"seguro\": true\n" +
				"  }");
		return ValidadorJSONObject.comparaObjetosReportaDiferencasContrato(this.response, expectedReturn);
	}

	public boolean validaRetornoAlteracaoSimulacaoCPF(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\n" +
				"    \"id\": 0,\n" +
				"    \"nome\": \"String\",\n" +
				"    \"cpf\": \"String\",\n" +
				"    \"email\": \"String\",\n" +
				"    \"valor\": 0,\n" +
				"    \"parcelas\": 0,\n" +
				"    \"seguro\": true\n" +
				"  }");
		return ValidadorJSONObject.comparaObjetosReportaDiferencasContrato(this.response, expectedReturn);
	}

	public boolean validaRetornoRemocaoSimulacaoID(Response response) {
		return response.getBody().asString().equals("OK");
	}

	public boolean validaRetornoInclusaoSimulacaoPayloadInvalido(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\n" +
				"\"erros\": {\n" +
				"\"parcelas\": \"Parcelas não pode ser vazio\",\n" +
				"\"valor\": \"Valor não pode ser vazio\",\n" +
				"\"cpf\": \"CPF não pode ser vazio\",\n" +
				"\"nome\": \"Nome não pode ser vazio\",\n" +
				"\"email\": \"E-mail não deve ser vazio\"\n" +
				"}\n"+
				"}");
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}

	public boolean validaRetornoInclusaoSimulacaoCPFDuplicado(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\"mensagem\":\"CPF duplicado\"}");//mensagem diferente da documentacao (swagger) "CPF já existente"
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}

	public boolean realizarInclusaoSimulacaoCPFIniciadoComZero(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\"mensagem\":\"CPF duplicado\"}");
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}

	public boolean validaRetornoConsultaSimulacoesCPFInvalido(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\"mensagem\":\"CPF 99999999999 não encontrado\"}"); // mensagem diferente da documentacao: Simulação não encontrada
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}

	public boolean validaRetornoAlteracaoSimulacaoCPFParaCPFExistente(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\"mensagem\":\"CPF duplicado\"}"); // mensagem diferente da documentacao: O CPF 999999999 possui restrição
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}

	public boolean validaRetornoAlteracaoSimulacaoCPFInexistente(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\"mensagem\":\"CPF 99999999999 não encontrado\"}"); // mensagem diferente da documentacao: O CPF 999999999 possui restrição
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}

	public boolean validaRetornoRemocaoSimulacaoIDInvalido(Response response) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\"mensagem\":\"Simulação não encontrada\"}"); // mensagem nao retornada como descrita no readme: Simulação não encontrada
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}
}