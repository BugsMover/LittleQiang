import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class url {

	public static void main(String[] args) throws IOException {
		try {
		URL	url = new URL("http://www.baidu.com");
		InputStream in = url.openStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader bufr = new BufferedReader(isr);
		String str;
		while((str=bufr.readLine())!=null){
			String utf =new String(str.getBytes(),"UTF-8");
			System.out.println(utf);
		}
		bufr.close();
		isr.close();
		in.close();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		

	}

}
