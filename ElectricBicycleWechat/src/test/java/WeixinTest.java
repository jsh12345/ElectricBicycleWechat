import org.electricbicyclewechat.configuration.util.BaseUtil;
import net.sf.json.JSONObject;




public class WeixinTest {
	public static void main(String[] args) {
		try {
			
			String menu = JSONObject.fromObject(BaseUtil.initMenu()).toString();
			String accessToken = BaseUtil.getAccessToken("wxf1172d4291aafa24", "e945f6f799c771f2133054bf677f97e2").getAccessToken();
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
