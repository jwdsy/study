package cn.miao.test;

import cn.miao.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.Map;

public class WSCDTest {

	public static void main(String[] args) {
		String signResult = "<xml><cust_argno>999900627</cust_argno><respcod>CMBMB99</respcod><respmsg>签署协议成功</respmsg><noticepara>appid=1|profileId=999900627</noticepara><cust_no>999900627</cust_no><cust_pidty>1</cust_pidty><cust_open_d_pay>N</cust_open_d_pay><cust_pid_v>833428525630254567440175879005</cust_pid_v></xml>";
		String substring = signResult.substring(signResult.indexOf("<noticepara>")+12, signResult.indexOf("</noticepara>"));
		System.err.println(substring);
		try {
			post();
//			httpTest();
//			sync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void httpTest() throws Exception {
		String url = "http://www.wangshangchedu.com.cn/Service/Distributor.aspx";
		String json = "{\"SerialNo\":\"DF20170505\",\"Sign\":\"\",\"Type\":\"0\"}";

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity postingString = new StringEntity(json);// json传递
		post.setEntity(postingString);
		post.setHeader("Content-type", "text/html; charset=utf-8");
		HttpResponse response = httpClient.execute(post);
		String content = EntityUtils.toString(response.getEntity());
		// Log.i("test",content);
		System.out.println(content);
	}

	public static void sync() {
		try{
			String url = "http://www.wangshangchedu.com.cn/Service/Bank.aspx";
			String json = "{\"SerialNo\":\"DF20170505\",\"Sign\":\"\",\"Type\":\"0\"}";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			StringEntity postingString = new StringEntity(json);// json传递
			post.setEntity(postingString);
			post.setHeader("Content-type", "text/html; charset=utf-8");
			HttpResponse response = httpClient.execute(post);
			String bankjson = EntityUtils.toString(response.getEntity());
			System.out.println(bankjson);
		}catch(Exception e){
		}
	}


	public static void post() {
		try{
			String url = "http://m157474.nofollow.ax.mvote.cn/op.php";
//			Map<String, Object> params = new HashMap<>();
//			params.put("action", "dovote");
//			params.put("guid", "c22ab3a4-d0f6-d711-ecfc-4f943312b266");
//			params.put("ops", "3014320");
//			params.put("wxparam", "oNrjcvrJalnC-kW3zYGqJBfFsgGU|c22ab3a4-d0f6-d711-ecfc-4f943312b266|fa72baf75303707a6582cd430f27fba9|1498787487|voteoption");
//			HttpClient httpClient = new DefaultHttpClient();
//			HttpPost post = new HttpPost(url);
//			StringEntity postingString = new StringEntity(JSON.toJSONString(params));// json传递
//			post.setEntity(postingString);
//			post.setHeader("Content-type", "text/html; charset=utf-8");
//			HttpResponse response = httpClient.execute(post);
//			String bankjson = EntityUtils.toString(response.getEntity());
//			System.out.println(bankjson);
			String guid = "c22ab3a4-d0f6-d711-ecfc-4f943312b269";
			String wxparam1 = "oNrjcvrJalnC-kW3zYGqJBfFsgGA";
			String wxparam3 = "fa72baf75303707a6582cd430f27fba0";
			Long time = System.currentTimeMillis()/1000;
			String tokenRst = HttpClientUtil.buildPostRequest(url)
					                  .addParam("action", "dovote")
					                  .addParam("guid", guid)
					                  .addParam("ops", "3014320")
					                  .addParam("wxparam", wxparam1+"|"+guid+"|"+wxparam3+"|"+time.toString()+"|voteoption")
					                  .setConnectTimeOut(10000)
					                  .execute();
			System.err.println(tokenRst);
		}catch(Exception e){
		}
	}



}