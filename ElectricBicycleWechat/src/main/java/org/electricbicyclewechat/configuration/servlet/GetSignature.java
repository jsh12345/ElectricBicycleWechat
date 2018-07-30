package org.electricbicyclewechat.configuration.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Var;
import org.electricbicyclewechat.configuration.pojo.AccessToken;
import org.electricbicyclewechat.configuration.pojo.Ticket;
import org.electricbicyclewechat.configuration.util.BaseUtil;
import org.electricbicyclewechat.configuration.util.SignUtil;
import org.electricbicyclewechat.configuration.util.TicketThread;
import org.electricbicyclewechat.configuration.util.TokenThread;

import net.sf.json.JSONObject;


/**
 * Servlet implementation class getSignature
 */
@WebServlet("/getSignature")
public class GetSignature extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSignature() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		response.setCharacterEncoding("utf-8");		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
        String weburl = request.getParameter("url");  
        Long timestamp = System.currentTimeMillis()/1000;  
        int noncestr = new Random().nextInt();  

        AccessToken accessToken = TokenThread.accessToken; 
        String accessTokenStr = accessToken.getAccessToken();
        System.out.println("accessToken:" + accessTokenStr);
        //jsapi_ticket  
        Ticket ticket = TicketThread.ticket; 
        String jsapi_ticket = ticket.getTicket();
//        try {  
//            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessTokenStr + "&type=jsapi";  
//            JSONObject object = BaseUtil.doGetStr(url); 
////            JSONObject object = JSONObject.fromObject(responseText);  
//            if (object.containsKey("ticket")) {  
//                jsapi_ticket = object.getString("ticket");  
//            }  
//        } catch (Exception e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
        //signature  
       List<String> nameList = new ArrayList<String>();  
        nameList.add("noncestr");  
        nameList.add("timestamp");  
        nameList.add("url");  
        nameList.add("jsapi_ticket");  
        Map<String, Object> valueMap = new HashMap<String, Object>();  
        valueMap.put("noncestr", noncestr);  
        valueMap.put("timestamp", timestamp);  
        valueMap.put("url", weburl);  
        valueMap.put("jsapi_ticket", jsapi_ticket);  
        Collections.sort(nameList);  
        String origin = "";  
//        origin = "noncestr=" + valueMap.get("noncestr") + "&timestamp=" + valueMap.get("timestamp") + "&url=" + valueMap.get("url") + "&jsapi_ticket=" + valueMap.get("jsapi_ticket");
        for (int i = 0; i < nameList.size(); i++) {  
        	String name = nameList.get(i);
        	String value = valueMap.get(nameList.get(i)).toString();
        	origin += name + "=" +value +"&";
            //origin += nameList.get(i) + "=" + valueMap.get(nameList.get(i)).toString() + "&";  
        }  
        
        origin = origin.substring(0, origin.length() - 1);  
        String signature = SignUtil.getSha1(origin);  
        
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("jsapi_ticket", jsapi_ticket);  
        //map.put("appId", "wx6c5e2b145ca4653b");  
//        map.put("appId", "wxf1172d4291aafa24");
        map.put("appId", "wxa3c839c1ca76bcc0");
        map.put("signature", signature.toLowerCase());  
        map.put("timestamp", timestamp.toString());  
        map.put("noncestr", String.valueOf(noncestr));  
        response.setContentType("application/json; charset=utf-8");  
        PrintWriter writer = null;  
        try {  
            writer = response.getWriter();  
            JSONObject responseObject = JSONObject.fromObject(map);  
            writer.print(responseObject);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            writer.flush();  
            writer.close();  
        }  
	}

}
