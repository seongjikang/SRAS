import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReceiveRecommendReviewJson {
	public static void main(String args[]) throws Exception {
		receiveRecommendReview();
	}

	public static void receiveRecommendReview() throws Exception {

		File lastDateFile = new File("D:\\home\\srasdomain\\srasApp\\data\\lastDate.txt");
		String temp;
		String lastDate = "";
		BufferedReader br = new BufferedReader(new FileReader(lastDateFile));
		while ((temp = br.readLine()) != null) {
			lastDate = temp;
		}
		
		System.out.println("start recommend review");
		
		androidRecommendReview(lastDate);
		
		iosRecommendReview(lastDate);
	
		System.out.println("end recommend review");
	}

	public static void androidRecommendReview(String lastDate) throws Exception {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, "{}");
		Request request = new Request.Builder()
				.url("https://dev-shbapi.shinhan.com:8443/v2/sras/recommend?review_date_os="+lastDate+"A")
				.method("POST", body).build();
		Response response = client.newCall(request).execute();
	}
	
	public static void iosRecommendReview(String lastDate) throws Exception {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, "{}");
		Request request = new Request.Builder()
				.url("https://dev-shbapi.shinhan.com:8443/v2/sras/recommend?review_date_os="+lastDate+"I")
				.method("POST", body).build();
		Response response = client.newCall(request).execute();
	}
}
