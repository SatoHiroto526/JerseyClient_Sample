package com.example.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class SampleClient {
	
	public static void main(String[] args) {
		
		//URI定義
		String getUri = "http://localhost:8080/JerseySample/rest/api/getlist";
		String postUri = "http://localhost:8080/JerseySample/rest/api/getbyid";
		
		//パラメーター定義
		int id = 3;
		
		//GETメソッドのAPIをコール
		get(getUri);
		
		//POSTメソッドのAPIをコール
		post(postUri, id);
	}
	
	//GETメソッドのAPIをコール
	private static void get(String uri) {
		
		//クライアント作成
		Client client = ClientBuilder.newClient();
		
		//URIをターゲットに設定
		WebTarget target = client.target(uri);
		
		//URI、認証情報、リクエストタイプ、メソッドを指定してリクエスト
		Response response = target.register(HttpAuthenticationFeature.basic("test", "test"))
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		//レスポンスを表示
		System.out.println("GET(Response Status):" + response.getStatus());
		System.out.println("GET(Response Body):" + response.readEntity(String.class));
		
		//レスポンスとクライアントをクローズ
		response.close();
		client.close();
		
	}
	
	//POSTメソッドのAPIをコール
	private static void post(String uri, int id) {
		
		//クライアント作成
		Client client = ClientBuilder.newClient();
		
		//URIをターゲットに設定
		WebTarget target = client.target(uri);
		
		//リクエストボディ作成
		String requestBody = "{\"id\":" + id + "}";
		
		//URI、認証情報、リクエストタイプ、メソッド、リクエストボディを指定してリクエスト
		Response response = target.register(HttpAuthenticationFeature.basic("test", "test"))
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));
		
		//レスポンスを表示
		System.out.println("POST(Response Status):" + response.getStatus());
		System.out.println("POST(Response Body):" + response.readEntity(String.class));
		
		//レスポンスとクライアントをクローズ
		response.close();
		client.close();
		
	}

}
