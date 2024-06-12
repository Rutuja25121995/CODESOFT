package com.tech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

public class Currency_Converter {

	public static void main(String[] args) throws IOException {

		HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();
		currencyCodes.put(1, "USD");
		currencyCodes.put(2, "CAD");
		currencyCodes.put(3, "EUR");
		currencyCodes.put(4, "HKD");
		currencyCodes.put(5, "INR");

		String fromCode, toCode;
		double amount;

		Scanner sc = new Scanner(System.in);
		System.out.println("welcome to currency converter");

		System.out.println("currency  converting starting from?");

		System.out.println("1:USD \t 2:CAD \t 3:EUR \t 4:HKD \t 5:INR");
		fromCode = currencyCodes.get(sc.nextInt());

		System.out.println("currency  converting to?");
		System.out.println("1:USD \t 2:CAD \t 3:EUR \t 4:HKD \t 5:INR");
		toCode = currencyCodes.get(sc.nextInt());

		System.out.println("Amount you wish to convert?");
		amount = sc.nextFloat();

		sendHttpGETRequest(fromCode, toCode, amount);
		System.out.println("thank you for using currency convreter");
	}
 
	private static void sendHttpGETRequest(String fromCode, String toCode, double amount) throws IOException {
		String GET_URL = "https://api.exchangeratesapi.io/v1/latest?base="+ toCode + "&symbols=" + fromCode;
				
		URL url = new URL(GET_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
	
		int responseCode = httpURLConnection.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);

			}
			in.close();
			JSONObject obj = new JSONObject(response.toString());
			System.out.println(response);
			Double exchangeRate = obj.getJSONObject("rates").getDouble(fromCode);
			System.out.println(obj.getJSONObject("rates"));
			System.out.println(exchangeRate);
			System.out.println();
			System.out.println(amount + fromCode + " = " + amount / exchangeRate + toCode);
		} else {
			System.out.println("GET request failed");
		}

	}

}
