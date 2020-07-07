import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendReviewJson {
	public static void main (String args[]) throws IOException, ParseException {
		
		SendReviewJson.sendReview();
		ArrayList<String> autoCrawler = SendReviewJson.killProcess("netstat -ano",
				"D:\\home\\srasdomain\\srasApp\\data\\srasRunnerPid.txt", "45111");
		for (int i = 0; i < autoCrawler.size(); i++) {
			SendReviewJson.execute("taskkill /f /pid " + autoCrawler.get(i));
		}

		ArrayList<String> runner = SendReviewJson.killProcess("netstat -ano",
				"D:\\home\\srasdomain\\srasApp\\datasrasCrawlerPid.txt", "55111");
		for (int i = 0; i < runner.size(); i++) {
			SendReviewJson.execute("taskkill /f /pid " + runner.get(i));
		}
	}
	
	public static void sendReview() throws IOException, ParseException {
		OkHttpClient client = new OkHttpClient();
		
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();

        cal.add(cal.DATE, -1);
        String yesterday = date.format(cal.getTime());
		
		File lastDateFile = new File("D:\\home\\srasdomain\\srasApp\\data\\lastDate.txt");
        String temp;
        String lastDate="";
        BufferedReader br = new BufferedReader(new FileReader(lastDateFile));
        while ((temp = br.readLine()) != null) {
            lastDate = temp;
        }

        ArrayList<String> dates = new ArrayList<String>();
        Date currentDate = date.parse(lastDate);

        while (currentDate.compareTo(date.parse(yesterday)) <= 0) {
            dates.add(date.format(currentDate));
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            currentDate = c.getTime();
        }

        ArrayList<String> noInputDates = new ArrayList<>();

        for (String noInputDate : dates) {
            System.out.println(noInputDate);
            noInputDates.add(noInputDate);
        }
		for (int i = 1; i < noInputDates.size(); i++) {

			MediaType mediaType = MediaType.parse("text/plain");
			RequestBody iosBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("file", "/D:/home/srasdomain/srasApp/json/" + noInputDates.get(i) + "I.json",
							RequestBody.create(MediaType.parse("application/octet-stream"),
									new File("/D:/home/srasdomain/srasApp/json/" + noInputDates.get(i) + "I.json")))
					.build();
			Request iosRequest = new Request.Builder().url("https://dev-shbapi.shinhan.com:8443/v2/sras/file-upload")
					.addHeader("Content-Transfer-Encoding", "multipart/form-data").method("POST", iosBody).build();
			Response iosResponse = client.newCall(iosRequest).execute();

			RequestBody androidBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("file", "/D:/home/srasdomain/srasApp/json/" + noInputDates.get(i) + "A.json",
							RequestBody.create(MediaType.parse("application/octet-stream"),
									new File("/D:/home/srasdomain/srasApp/json/" + noInputDates.get(i) + "A.json")))
					.build();
			Request androidRequest = new Request.Builder()
					.url("https://dev-shbapi.shinhan.com:8443/v2/sras/file-upload")
					.addHeader("Content-Transfer-Encoding", "multipart/form-data").method("POST", androidBody).build();
			Response androidResponse = client.newCall(androidRequest).execute();
		}
		
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\\\home\\\\srasdomain\\\\srasApp\\\\data\\\\lastDate.txt"));
        bw.write(yesterday);
        br.close();
        bw.close();
        
	}
	
	public static ArrayList<String> killProcess(String inputCmd, String filePath, String pid) {
		String cmd = inputCmd;

		try {

			Process process = Runtime.getRuntime().exec(cmd);
			InputStream in = process.getInputStream();

			File pidFile = new File(filePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(pidFile)); // 파일에서 문자 쓰기 (주의 : 파일을 쓰기 위해 기존의 내용을
																				// 지워버린다.)

			byte[] buf = new byte[256];

			OutputStream pidFileOutputStream = new FileOutputStream(pidFile);

			int numbytes = 0;

			while ((numbytes = in.read(buf, 0, 256)) != -1) {

				pidFileOutputStream.write(buf, 0, numbytes);

			}

			System.out.println("File is present at " + pidFile.getAbsolutePath());

			String temp;
			String pidInfo;
			String killPid;
			BufferedReader br = new BufferedReader(new FileReader(pidFile));
			ArrayList<String> killPids = new ArrayList<String>();
			while ((temp = br.readLine()) != null) { // 줄단위로 읽기
				// System.out.println(temp.replaceAll(" ",""));
				pidInfo = temp.replaceAll(" ", "");
				if (pidInfo.contains(pid)) {
					if (pidInfo.contains("LISTENING")) {
						killPid = pidInfo.split("LISTENING")[1];
						killPids.add(killPid);
					}
				}

			}

			pidFileOutputStream.close();
			br.close();

			return killPids;
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return null;

	}

	public static void execute(String cmd) {
		Process process = null;
		Runtime runtime = Runtime.getRuntime();
		StringBuffer successOutput = new StringBuffer(); // 성공 스트링 버퍼
		StringBuffer errorOutput = new StringBuffer(); // 오류 스트링 버퍼
		BufferedReader successBufferReader = null; // 성공 버퍼
		BufferedReader errorBufferReader = null; // 오류 버퍼
		String msg = null; // 메시지

		List<String> cmdList = new ArrayList<String>();

		// 운영체제 구분 (window, window 가 아니면 무조건 linux 로 판단)
		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			cmdList.add("cmd");
			cmdList.add("/c");
		} else {
			cmdList.add("/bin/sh");
			cmdList.add("-c");
		}
		// 명령어 셋팅
		cmdList.add(cmd);
		String[] array = cmdList.toArray(new String[cmdList.size()]);

		try {

			// 명령어 실행
			process = runtime.exec(array);

			// shell 실행이 정상 동작했을 경우
			successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));

			while ((msg = successBufferReader.readLine()) != null) {
				successOutput.append(msg + System.getProperty("line.separator"));
			}

			// shell 실행시 에러가 발생했을 경우
			errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
			while ((msg = errorBufferReader.readLine()) != null) {
				errorOutput.append(msg + System.getProperty("line.separator"));
			}

			// 프로세스의 수행이 끝날때까지 대기
			process.waitFor();

			// shell 실행이 정상 종료되었을 경우
			if (process.exitValue() == 0) {
				System.out.println("성공");
				System.out.println(successOutput.toString());
			} else {
				// shell 실행이 비정상 종료되었을 경우
				System.out.println("비정상 종료");
				System.out.println(successOutput.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				process.destroy();
				if (successBufferReader != null)
					successBufferReader.close();
				if (errorBufferReader != null)
					errorBufferReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
