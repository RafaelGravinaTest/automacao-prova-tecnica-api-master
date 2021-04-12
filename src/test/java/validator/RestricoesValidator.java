package validator;

import Utils.Report;
import Utils.ValidadorJSONObject;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class RestricoesValidator {
	private JSONObject response;
	private JSONArray responseArray;
	private int responseCode;

	public boolean validaRetornoConsultaCPFPossuiRestricao(Response response, String cpf) {
		this.response = new JSONObject(response.getBody().asString());
		JSONObject expectedReturn = new JSONObject("{\"mensagem\":\"O CPF "+cpf+" tem problema\"}"); // ****!!!!! MENSAGEM DIFERENTE NA DOCUMENTACAO "mensagem": "O CPF 999999999 possui restrição"
		return ValidadorJSONObject.comparaObjetosReportaDiferencasCompleta(this.response, expectedReturn);
	}

	public boolean validaRetornoConsultaCPFInvalidoPossuiRestricao(Response response) {
		this.responseCode = response.getStatusCode();

		if(this.responseCode == 204) {
			Report.logPass("Validacao realizada com sucesso!<br> Status do retorno: "+this.responseCode+" <br> Corpo do retorno: GET com retorno vazio");
		} else {
			Report.logFail("Falha na validacao do retorno! <br> Status esperado: 204 <br> Status do retorno: "+this.responseCode);
			return false;
		}
		return true;
	}
}