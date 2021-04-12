package pages;

import Utils.RestMethods;
import io.restassured.response.Response;
import validator.RestricoesValidator;

import java.util.HashMap;
import java.util.Map;

public class RestricoesPage extends RestMethods {

    private String baseUrl = "http://localhost:8080/";
    private RestricoesValidator validator = new RestricoesValidator();

    private void logStep() {
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    public boolean realizarConsultaCPFPossuiRestricao(String cpf) {
        logStep();
        setUrl(baseUrl+"api/v1/restricoes/"+cpf);
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        setHeaders(headers);
        setBody("");
        Response response = get(200);
        return validator.validaRetornoConsultaCPFPossuiRestricao(response, cpf);
    }

    public boolean realizaConsultaCPFInvalidoPossuiRestricao(String cpf) {
        logStep();
        setUrl(baseUrl+"api/v1/restricoes/"+cpf);
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        setHeaders(headers);
        setBody("");
        Response response = get(204);
        return validator.validaRetornoConsultaCPFInvalidoPossuiRestricao(response);
    }
}
