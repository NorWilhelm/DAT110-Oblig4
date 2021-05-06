package no.hvl.dat110.aciotdevice.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub

	}

	private static String logpath = "/accessdevice/log";
	private static String host = Configuration.host;

	public void doPostAccessEntry(String message) {
		// TODO: implement a HTTP POST on the service to post the message
	}

	private static String codepath = "/accessdevice/code"; // TODO: consider not hardcoding...
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder()
					.url(codepath) //
					.get()
					.build();

			System.out.println(request.toString()); // TODO: Remove this later

			try (Response response = client.newCall(request).execute()) {
				int[] codeInReponse = new int[2];
				codeInReponse[0] = parseInt(response.body().toString().substring(0,1));
				codeInReponse[1] = parseInt(response.body().toString().substring(1,2));

				System.out.println("doGetAccessCode response code: ");
				System.out.println(codeInReponse);

				code.setAccesscode(codeInReponse);
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		return code;
	}
}