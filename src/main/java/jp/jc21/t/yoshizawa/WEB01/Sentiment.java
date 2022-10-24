package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Senti message = getSentiment("眠い");
		if (message != null) {
			System.out.println("positive : " + message.documents[0].confidenceScores.positive);
			System.out.println("neutral : " + message.documents[0].confidenceScores.neutral);
			System.out.println("negative : " + message.documents[0].confidenceScores.negative);
		}
	}

	static Senti getSentiment(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3a05-text.cognitiveservices.azure.com/" + "text/analytics/v3.0/Sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "70f43f5f132d4409bde91c0615456f0d");

		Docs2 doc = new Docs2();
		doc.id = "1";
		doc.text = s;

		Source2 src = new Source2();
		src.documents = new Docs2[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		Senti message = null;
		if (reader != null) {
			message = gson.fromJson(reader, Senti.class);
			reader.close();
		}
		return message;
	}

}

class Senti {
	Documents2[] documents;
	String[] errors;
	String modelVersion;
}

class Documents2 {
	Confidence confidenceScores;
	String id;
	sentences[] sentences;
	String sentiment;
	String[] warnings;
}

class Confidence{
	String positive;
	String neutral;
	String negative;
}
class sentences{
	Targets[] targets;
	Confidence confidenceScores;
	String length;
	String offset;
	Assessments[] assessments;
	String sentiment;
	String text;
}

class Targets{
	Confidence confidenceScores;
	String length;
	String offset;
	Relations[] relations;
	String sentiment;
	String text;
}

class Assessments{
	Confidence confidenceScores;
	boolean isNegated;
	String length;
	String offset;
	String sentiment;
	String text;
}

class Relations{
	String ref;
	String relationType;
}
class Source2 {
	Docs2[] documents;
}

class Docs2 {
	String id;
	String text;
}
