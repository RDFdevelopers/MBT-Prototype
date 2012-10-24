package rdf.game.medievalbattletouchproto.connection;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;

public class Conexion {
	
	static String url;
	
	
	public Conexion(){
		url = "http://10.230.169.41/pruebaprepro.php";
	}
	
	public String envio (ArrayList<NameValuePair> argumento) {
		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		String respStr = null;
		ArrayList<NameValuePair> respJson = argumento;
		HttpClient httpcliente = new DefaultHttpClient();
		HttpPost m_post = new HttpPost("http://10.230.169.41/pruebaprepro.php");
		m_post.setHeader("Accept", "application/json");
		try {
			m_post.setEntity(new UrlEncodedFormEntity(respJson));
        	HttpResponse resp = httpcliente.execute(m_post);
        	respStr = EntityUtils.toString(resp.getEntity());
		} 
        catch(Exception ex)
        {
        	Log.e("ServicioRest","Error!", ex);
        }
		return respStr;
	}
}