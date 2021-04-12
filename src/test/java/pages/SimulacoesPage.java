package pages;

import Utils.RestMethods;
import Utils.Utils;
import io.restassured.response.Response;
import validator.RestricoesValidator;
import validator.SimulacoesValidator;

import java.util.HashMap;
import java.util.Map;

public class SimulacoesPage extends RestMethods {

    private String baseUrl = "http://localhost:8080/";
    private SimulacoesValidator validator = new SimulacoesValidator();

    private void logStep() {
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
    }


    public boolean realizarConsultaSimulacoesExistentes() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        setHeaders(headers);
        setBody("");
        Response response = get(200);
        return validator.validaRetornoConsultaSimulacoesExistentes(response);
    }

    public boolean realizarInclusaoSimulacao() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("Content-Type", "application/json");
        setHeaders(headers);
        setBody("{\n" +
                "  \"nome\": \"Fulano de Tal\",\n" +
                "  \"cpf\": \""+ Utils.gerarCPF(false)+"\",\n" +
                "  \"email\": \"email@email.com\",\n" +
                "  \"valor\": 1200,\n" +
                "  \"parcelas\": 3,\n" +
                "  \"seguro\": true\n" +
                "}");
        Response response = post(201);
        return validator.validaRetornoInclusaoSimulacao(response);
    }

    public boolean realizarConsultaSimulacoesCPF() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/"+Utils.getVariavel("cpf"));
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        setHeaders(headers);
        setBody("");
        Response response = get(200);
        return validator.validaRetornoConsultaSimulacoesCPF(response);
    }

    public boolean realizarAlteracaoSimulacaoCPF() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/"+Utils.getVariavel("cpf"));
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("Content-Type", "application/json");
        setHeaders(headers);
        setBody("{\n" +
                "  \"nome\": \"Fulano de Tal\",\n" +
                "  \"cpf\": "+Utils.getVariavel("cpf")+",\n" +
                "  \"email\": \"email@email.com\",\n" +
                "  \"valor\": 1200,\n" +
                "  \"parcelas\": 5,\n" +
                "  \"seguro\": true\n" +
                "}");
        Response response = put(200);
        return validator.validaRetornoAlteracaoSimulacaoCPF(response);
    }

    public boolean realizarRemocaoSimulacaoID() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/"+Utils.getVariavel("id"));
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        setHeaders(headers);
        setBody("");
        Response response = delete(200);
        return validator.validaRetornoRemocaoSimulacaoID(response);
    }

    public boolean realizarInclusaoSimulacaoPayloadInvalido() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("Content-Type", "application/json");
        setHeaders(headers);
        setBody("{\n" +
                "  \"nome\": null,\n" +
                "  \"cpf\": null,\n" +
                "  \"email\": null,\n" +
                "  \"valor\": null,\n" +
                "  \"parcelas\": null,\n" +
                "  \"seguro\": null\n" +
                "}");
        Response response = post(400);
        return validator.validaRetornoInclusaoSimulacaoPayloadInvalido(response);
    }

    public boolean realizarInclusaoSimulacaoCPFDuplicado() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("Content-Type", "application/json");
        setHeaders(headers);
        setBody("{\n" +
                "  \"nome\": \"Fulano de Tal\",\n" +
                "  \"cpf\": 66414919004,\n" +
                "  \"email\": \"email@email.com\",\n" +
                "  \"valor\": 1200,\n" +
                "  \"parcelas\": 3,\n" +
                "  \"seguro\": true\n" +
                "}");
        Response response = post(409);
        return validator.validaRetornoInclusaoSimulacaoCPFDuplicado(response);
    }

    public boolean realizarConsultaSimulacoesCPFInvalido() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/99999999999");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        setHeaders(headers);
        setBody("");
        Response response = get(404);
        return validator.validaRetornoConsultaSimulacoesCPFInvalido(response);
    }

    public boolean realizarAlteracaoSimulacaoCPFParaCPFExistente() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/17822386034");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("Content-Type", "application/json");
        setHeaders(headers);
        setBody("{\n" +
                "    \"nome\": \"Deltrano\",\n" +
                "    \"cpf\": \"66414919004\",\n" +
                "    \"email\": \"deltrano@gmail.com\",\n" +
                "    \"valor\": 20000,\n" +
                "    \"parcelas\": 5,\n" +
                "    \"seguro\": false\n" +
                "  }");
        Response response = put(400);
        return validator.validaRetornoAlteracaoSimulacaoCPFParaCPFExistente(response);
    }

    public boolean realizarAlteracaoSimulacaoCPFInexistente() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/99999999999");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("Content-Type", "application/json");
        setHeaders(headers);
        setBody("{\n" +
                "    \"nome\": \"Teste\",\n" +
                "    \"cpf\": \"99999999999\",\n" +
                "    \"email\": \"teste@gmail.com\",\n" +
                "    \"valor\": 20000,\n" +
                "    \"parcelas\": 5,\n" +
                "    \"seguro\": false\n" +
                "  }");
        Response response = put(404);
        return validator.validaRetornoAlteracaoSimulacaoCPFInexistente(response);
    }

    public boolean realizarRemocaoSimulacaoIDInvalido() {
        logStep();
        setUrl(baseUrl+"api/v1/simulacoes/99999999999");
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        setHeaders(headers);
        setBody("");
        Response response = delete(404);
        return validator.validaRetornoRemocaoSimulacaoIDInvalido(response);
    }
}
