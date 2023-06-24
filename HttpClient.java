package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class HttpClient {
	
	static HttpURLConnection connection;

	public static HttpURLConnection createConnection(String link)
			throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(String.format(link));
		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {

			e.printStackTrace();
		}
		connection.setRequestMethod("GET");

		return connection;
	}

	public ArrayList<String> ReadResponse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		ArrayList<String> content = new ArrayList<>();
		while ((inputLine = in.readLine()) != null) {
			content.add(inputLine);
		}
		System.out.println(content);
		in.close();
		return content;

	}

	public static class GetResponse {
		public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {
			HttpClient cliente = new HttpClient();
			//Coloque no lugar do asterisco  o CEP requerido em busca
			HttpClient.createConnection("http://viacep.com.br/ws/*/json");

			cliente.ReadResponse();

		}

	}
}
