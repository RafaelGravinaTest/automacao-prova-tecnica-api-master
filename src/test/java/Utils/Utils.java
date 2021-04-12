package Utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;


public class Utils {

	public static Map<String, String> map = new HashMap<String,String>();;

	public static void setVariavel(String key, String value) {
		map.put(key, value);
	}

	public static String getVariavel(String key){
		return map.get(key);
	}

	public static String gerarCPF(Boolean pontuacao) {
		int digito1 = 0, digito2 = 0, resto = 0;
		String nDigResult;
		String numerosContatenados;
		String numeroGerado;
		Random numeroAleatorio = new Random();
		// NUMEROS GERADOS
		int n1 = numeroAleatorio.nextInt(10);
		int n2 = numeroAleatorio.nextInt(10);
		int n3 = numeroAleatorio.nextInt(10);
		int n4 = numeroAleatorio.nextInt(10);
		int n5 = numeroAleatorio.nextInt(10);
		int n6 = numeroAleatorio.nextInt(10);
		int n7 = numeroAleatorio.nextInt(10);
		int n8 = numeroAleatorio.nextInt(10);
		int n9 = numeroAleatorio.nextInt(10);
		int soma = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
		int valor = (soma / 11) * 11;
		digito1 = soma - valor;
		// PRIMEIRO RESTO DA DIVISAO POR 11
		resto = (digito1 % 11);
		if (digito1 < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}
		int soma2 = digito1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
		int valor2 = (soma2 / 11) * 11;
		digito2 = soma2 - valor2;
		// SEGUNDO RESTO DA DIVISAO POR 11
		resto = (digito2 % 11);
		if (digito2 < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}
		// CONCATENANDO OS NUMEROS
		numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + (pontuacao ? "." : "") + String.valueOf(n4)
				+ String.valueOf(n5) + String.valueOf(n6) + (pontuacao ? "." : "") + String.valueOf(n7) + String.valueOf(n8)
				+ String.valueOf(n9) + (pontuacao ? "-" : "");
		// CONCATENANDO O PRIMEIRO RESTO COM O SEGUNDO
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
		numeroGerado = numerosContatenados + nDigResult;
		System.out.println("CPF Gerado " + numeroGerado);
		setVariavel("cpf", numeroGerado);
		return numeroGerado;
	}
}
