package br.com.adaptaconsultoria.amr.springmvc.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TesteCielo {

  public static String teste() {

    String json = "{" + "    \"OrderNumber\": \"12344\","
        + "    \"SoftDescriptor\": \"Nome que aparecerá na fatura\"," + "    \"Cart\": {"
        + "        \"Discount\": {" + "            \"Type\": \"Percent\","
        + "            \"Value\": 10" + "        }," + "        \"Items\": [" + "            {"
        + "                \"Name\": \"Nome do produto\","
        + "                \"Description\": \"Descrição do produto\","
        + "                \"UnitPrice\": 100," + "                \"Quantity\": 2,"
        + "                \"Type\": \"Asset\","
        + "                \"Sku\": \"Sku do item no carrinho\","
        + "                \"Weight\": 200" + "            }" + "        ]" + "    },"
        + "    \"Shipping\": {" + "        \"Type\": \"Correios\","
        + "        \"SourceZipCode\": \"14400000\"," + "        \"TargetZipCode\": \"11000000\","
        + "        \"Address\": {" + "            \"Street\": \"Endereço de entrega\","
        + "            \"Number\": \"123\"," + "            \"Complement\": \"\","
        + "            \"District\": \"Bairro da entrega\","
        + "            \"City\": \"Cidade de entrega\"," + "            \"State\": \"SP\""
        + "        }," + "        \"Services\": [" + "            {"
        + "                \"Name\": \"Serviço de frete\"," + "                \"Price\": 123,"
        + "                \"Deadline\": 15" + "            }" + "        ]" + "    },"
        + "    \"Payment\": {" + "        \"BoletoDiscount\": 0," + "        \"DebitDiscount\": 10"
        + "     }," + "     \"Customer\": {" + "         \"Identity\": 11111111111,"
        + "         \"FullName\": \"Fulano Comprador da Silva\","
        + "         \"Email\": \"fulano@email.com\"," + "         \"Phone\": \"11999999999\""
        + "     }," + "     \"Options\": {" + "         \"AntifraudEnabled\": false" + "     }"
        + "}";

    URL url;
    HttpURLConnection connection;
    BufferedReader bufferedReader;

    try {
      url = new URL("https://cieloecommerce.cielo.com.br/api/public/v1/orders");

      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.addRequestProperty("MerchantId", "43d8053b-ab6a-4d1b-b87e-9cee1144d26e");
      connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
      connection.setDoOutput(true);

      DataOutputStream jsonRequest = new DataOutputStream(connection.getOutputStream());

      jsonRequest.writeBytes(json);
      jsonRequest.flush();
      jsonRequest.close();

      bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String responseLine;
      StringBuffer jsonResponse = new StringBuffer();

      while ((responseLine = bufferedReader.readLine()) != null) {
        jsonResponse.append(responseLine);
      }

      bufferedReader.close();

      connection.disconnect();
      System.out.println(jsonResponse.toString());
      return jsonResponse.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) throws Exception {
    teste();
  }
}