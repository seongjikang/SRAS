import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SrasRunCrawler {
	public static void main(String args[]) {
		System.out.println("crawler start");
		SrasRunCrawler.execute("java -jar D:\\home\\srasdomain\\srasApp\\lib\\srasCrawler-0.0.1-SNAPSHOT.jar");
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
	                if (successBufferReader != null) successBufferReader.close();
	                if (errorBufferReader != null) errorBufferReader.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
}
