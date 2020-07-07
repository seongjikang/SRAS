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
			BufferedWriter bw = new BufferedWriter(new FileWriter(pidFile)); // ���Ͽ��� ���� ���� (���� : ������ ���� ���� ������ ������
																				// ����������.)

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
			while ((temp = br.readLine()) != null) { // �ٴ����� �б�
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
		StringBuffer successOutput = new StringBuffer(); // ���� ��Ʈ�� ����
		StringBuffer errorOutput = new StringBuffer(); // ���� ��Ʈ�� ����
		BufferedReader successBufferReader = null; // ���� ����
		BufferedReader errorBufferReader = null; // ���� ����
		String msg = null; // �޽���

		List<String> cmdList = new ArrayList<String>();

		// �ü�� ���� (window, window �� �ƴϸ� ������ linux �� �Ǵ�)
		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			cmdList.add("cmd");
			cmdList.add("/c");
		} else {
			cmdList.add("/bin/sh");
			cmdList.add("-c");
		}
		// ��ɾ� ����
		cmdList.add(cmd);
		String[] array = cmdList.toArray(new String[cmdList.size()]);

		try {

			// ��ɾ� ����
			process = runtime.exec(array);

			// shell ������ ���� �������� ���
			successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));

			while ((msg = successBufferReader.readLine()) != null) {
				successOutput.append(msg + System.getProperty("line.separator"));
			}

			// shell ����� ������ �߻����� ���
			errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
			while ((msg = errorBufferReader.readLine()) != null) {
				errorOutput.append(msg + System.getProperty("line.separator"));
			}

			// ���μ����� ������ ���������� ���
			process.waitFor();

			// shell ������ ���� ����Ǿ��� ���
			if (process.exitValue() == 0) {
				System.out.println("����");
				System.out.println(successOutput.toString());
			} else {
				// shell ������ ������ ����Ǿ��� ���
				System.out.println("������ ����");
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
