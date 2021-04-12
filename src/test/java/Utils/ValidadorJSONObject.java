package Utils;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.json.JSONObject;

import java.util.Map;

public class ValidadorJSONObject {
    Map<String, Object> response;
    Map<String, Object> expectedReturn;
    MapDifference<String, Object> difference;

    public static boolean comparaObjetosReportaDiferencasCompleta(JSONObject response, JSONObject expectedReturn) {
        Map<String, Object> Mapresponse = FlatMapUtil.flatten(response);
        Map<String, Object> MapexpectedReturn = FlatMapUtil.flatten(expectedReturn);

        MapDifference<String, Object> MapDifference = Maps.difference(Mapresponse, MapexpectedReturn);

        if(!reportarEntidadesDiferentesEntreObjetos(MapDifference, false)) {
            return false;
        }
        Report.logPass("Validacao realizada com sucesso!<br><br> Retorno esperado: "+expectedReturn+" <br><br>Retorno do Servico: "+response);
        return true;
    }


    public static boolean comparaObjetosReportaDiferencasContrato(JSONObject response, JSONObject expectedReturn) {
        Map<String, Object> Mapresponse = FlatMapUtil.flattenKeys(response);
        Map<String, Object> MapexpectedReturn = FlatMapUtil.flattenKeys(expectedReturn);

        MapDifference<String, Object> MapDifference = Maps.difference(Mapresponse, MapexpectedReturn);

        if(!reportarEntidadesDiferentesEntreObjetos(MapDifference, true)) {
            return false;
        }
        Report.logPass("Validacao realizada com sucesso!<br><br> Retorno esperado: "+expectedReturn+"<br><br> Retorno do Servico: "+response);
        return true;
    }

    private static boolean reportarEntidadesDiferentesEntreObjetos(MapDifference<String, Object> difference, boolean contrato) {
        StringBuilder diferencasResponse = new StringBuilder();
        difference.entriesOnlyOnLeft()
                .forEach((key, value) -> diferencasResponse.append((key + (contrato ? "" :  ": " + value))+"<br>"));

        StringBuilder diferencasExpectedReturn = new StringBuilder();
        difference.entriesOnlyOnRight()
                .forEach((key, value) -> diferencasExpectedReturn.append(key + (contrato ? "" :  ": " + value)+"<br>"));

        StringBuilder diferencasAmbos = new StringBuilder();
        difference.entriesDiffering()
                .forEach((key, value) -> diferencasAmbos.append(key + (contrato ? "" :  ": " + value)+"<br>"));
        if (diferencasAmbos.length() > 0 || diferencasResponse.length() > 0 || diferencasExpectedReturn.length() > 0) {
            StringBuilder sb = new StringBuilder();
            if(diferencasResponse.length() > 0) {
                sb.append("Entidades encontradas no RETORNO DO SERVIÇO que não estão no retorno esperado: <br>" + diferencasResponse.toString());
                sb.append("<br><br>");
            }

            if(diferencasExpectedReturn.length() > 0) {
                sb.append("Entidades encontradas no RETORNO ESPERADO que não estão no retorno do serviço: <br>" + diferencasExpectedReturn.toString());
                sb.append("<br><br>");
            }

            if(diferencasAmbos.length() > 0) {
                sb.append("Entidades com VALORES DIFERENTES entre o retorno esperado e o retorno do serviço: <br>" + diferencasAmbos.toString());
                sb.append("<br><br>");
            }
            Report.logFail(sb.toString());
            return false;
        }
        return true;
    }
}
