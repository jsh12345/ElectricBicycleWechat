import org.apache.log4j.Logger;
import org.electricbicyclewechat.configuration.util.BaseUtil;
import org.electricbicyclewechat.configuration.util.TokenThread;

import net.sf.json.JSONObject;


public class WeixinTest {
	public static void main(String[] args) {
		try {
			
			String menu = JSONObject.fromObject(BaseUtil.initMenu()).toString();
//			String accessToken = BaseUtil.getAccessToken("wx6c5e2b145ca4653b", "5bb27830661395610d14614d658b0faf").getAccessToken();
			String accessToken = BaseUtil.getAccessToken("wxa3c839c1ca76bcc0", "418a0710c843e346eee9201950373051").getAccessToken();
			System.out.println(accessToken);
			int result = BaseUtil.createMenu(accessToken, menu);
			System.out.println(result);
			if(result==0){
				System.out.println("成功");
			}else{
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
