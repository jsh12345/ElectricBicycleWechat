package org.electricbicyclewechat.configuration.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.electricbicyclewechat.configuration.pojo.AccessToken;
import org.electricbicyclewechat.configuration.pojo.Button;
import org.electricbicyclewechat.configuration.pojo.Menu;
import org.electricbicyclewechat.configuration.pojo.Ticket;
import org.electricbicyclewechat.configuration.pojo.ViewButton;


public class BaseUtil {
	
//	public static String baseUrl = "http://supplywechat.tunnel.qydev.com/ElectricBicycleWechat/";
	//public static String baseUrl = "http://supplierwechat.free.ngrok.cc/ElectricBicycleWechat/";
	public static String baseUrl = "http://zjyw.tunnel.qydev.com/ElectricBicycleWechat/";
	
//	private static final String APPID = "wxf1172d4291aafa24";
//	private static final String APPSECRET = "e945f6f799c771f2133054bf677f97e2";
	
	private static final String APPID = "wxa3c839c1ca76bcc0";
	private static final String APPSECRET = "418a0710c843e346eee9201950373051";
	
	private static final String ACCESS_TOKEN_URL= "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";	
	
	private static final String SNSAPI_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}
	
	/**
	 * POST请求
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	
	//获得AccessToken
	public static AccessToken getAccessToken(String appId ,String appSecret) {
		System.out.println("调用获取access_token");
		AccessToken accessToken = new AccessToken();
		try {
			//获取accessToken地址的url
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret);
			//打开链接
			URLConnection connection = url.openConnection();
			//建立时间链接
			connection.connect();
			//构建输出流
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//转换为json
			String line = in.readLine();
			JSONObject jo = JSONObject.fromObject(line);
			accessToken.setAccessToken(jo.getString("access_token"));
			accessToken.setExpiresin(jo.getInt("expires_in"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	//获得ticket
		public static Ticket getTicket(String accessTokenStr) {
			Ticket ticket  = null;
			try {
				//获取ticket地址的url
				URL url = new URL("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessTokenStr+"&type=jsapi");
				//打开链接
				URLConnection connection = url.openConnection();
				//建立时间链接
				connection.connect();
				//构建输出流
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				//转换为json
				String line = in.readLine();
				JSONObject jo = JSONObject.fromObject(line);
				String ticketString = jo.getString("ticket");
				if(ticketString==null){
					return null;
				}else{
					ticket = new Ticket();
					ticket.setTicket(jo.getString("ticket"));
					ticket.setExpiresin(jo.getInt("expires_in"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ticket;
		}
	
	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		
		/**
		 *进销存
		 */
		/*ViewButton initialAccount = new ViewButton();
		initialAccount.setName("期初入库");
		initialAccount.setType("view");
		//initialAccount.setUrl(baseUrl+"views/initialAccount/initialAccount.html");
		initialAccount.setUrl(baseUrl+"login/checkIfInitial");
		
		ViewButton purchaseManager = new ViewButton();
		purchaseManager.setName("车辆销售");
		purchaseManager.setType("view");
		purchaseManager.setUrl(baseUrl+"login/checkIfPurchase");
		
		ViewButton sellerBalanceInfo = new ViewButton();
		sellerBalanceInfo.setName("库存查询");
		sellerBalanceInfo.setType("view");
		sellerBalanceInfo.setUrl(baseUrl+"views/balanceManagement/balanceQueryMore.html");
		
		//订单管理（产品订单、售后订单、订单进度）
		ViewButton orderManager = new ViewButton();
		orderManager.setName("订单管理");
		orderManager.setType("view");
		orderManager.setUrl("www.google.com");
		
		Button managerButton = new Button();
		managerButton.setName("进销存");
		managerButton.setSub_button(new Button[]{initialAccount,purchaseManager,sellerBalanceInfo});*/
		
		/**
		 * 统计报表
		 */
		/*ViewButton invoicingReport = new ViewButton();
		invoicingReport.setName("进销存统计表");
		invoicingReport.setType("view");
		invoicingReport.setUrl(baseUrl+"views/reportQuery/invoicingReport.html");
		
		ViewButton sellSaleInfo = new ViewButton();
		sellSaleInfo.setName("销售明细表");
		sellSaleInfo.setType("view");
		//sellSaleInfo.setUrl("http://supplierwechat.free.ngrok.cc/ElectricBicycleWechat/views/reportQuery/saleQuery.html");
		sellSaleInfo.setUrl(baseUrl+"views/reportQuery/saleQueryMore.html");
		
		ViewButton saleSpecInfo = new ViewButton();
		saleSpecInfo.setName("销售车型排行榜");
		saleSpecInfo.setType("view");
		saleSpecInfo.setUrl(baseUrl+"views/reportQuery/salesRankings.html");
		
		Button reportQuery = new Button();
		reportQuery.setName("统计报表");
		reportQuery.setSub_button(new Button[]{invoicingReport,sellSaleInfo,saleSpecInfo});
*/
		/**
		 * 用户中心
		 */
		ViewButton login = new ViewButton();
		login.setName("登录");
		login.setType("view");
		login.setUrl(baseUrl+"views/login/login.html");
		
		ViewButton changePassword = new ViewButton();
		changePassword.setName("个人信息");
		changePassword.setType("view");
		changePassword.setUrl(baseUrl+"views/login/personalInfoHome.html");
		/*changePassword.setUrl(baseUrl+"views/login/changePassword.html");
		
		ViewButton unBundle = new ViewButton();
		unBundle.setName("解除绑定");
		unBundle.setType("view");
		unBundle.setUrl(baseUrl+"views/login/unBundle.html");*/
		
		Button userButton = new Button();
		userButton.setName("账户管理");
		userButton.setSub_button(new Button[]{login,changePassword});
		
	/*	ViewButton first = new ViewButton();
		first.setName("财务一审");
		first.setType("view");
		first.setUrl(baseUrl+"views/audit/firstCheck.html");
		
		ViewButton second = new ViewButton();
		second.setName("财务二审");
		second.setType("view");
		second.setUrl(baseUrl+"views/audit/secondCheck.html");
		
		ViewButton confirmOrder = new ViewButton();
		confirmOrder.setName("内勤确认订单");
		confirmOrder.setType("view");
		confirmOrder.setUrl(baseUrl+"views/userProperty/confirmOrder.html");*/
		ViewButton confirmOrder = new ViewButton();
		confirmOrder.setName("确认与审核");
		confirmOrder.setType("view");
		confirmOrder.setUrl(baseUrl+"views/userProperty/managerHome.html");
		
		Button orderButton = new Button();		
		orderButton.setName("订单审核");
		orderButton.setSub_button(new Button[]{confirmOrder});
		
		ViewButton order = new ViewButton();
		order.setName("下单");
		order.setType("view");
		order.setUrl(baseUrl+"views/order/searchProduct.html");
		
		ViewButton findOrder = new ViewButton();
		findOrder.setName("查询订单");
		findOrder.setType("view");
		findOrder.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa3c839c1ca76bcc0&redirect_uri=http%3A%2F%2Fzjyw.tunnel.qydev.com%2FElectricBicycleWechat%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
//		findOrder.setUrl(baseUrl+"views/order/showOrder.html");
		
		Button orderManageButton = new Button();		
		orderManageButton.setName("订单管理");
		orderManageButton.setSub_button(new Button[]{order,findOrder});
			
		/*Button otherButton = new Button();		
		otherButton.setName("其他");		
		otherButton.setSub_button(new Button[]{findOrder});*/
		
		menu.setButton(new Button[]{userButton,orderManageButton,orderButton});
		
//		menu.setButton(new Button[]{managerButton,reportQuery,userButton});
		return menu;
	}
	
	public static int createMenu(String token,String menu) throws ParseException, IOException{
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
			String msg = jsonObject.getString("errmsg");
			System.out.println(msg);
		}
		return result;
	}
		
}
