package org.electricbicyclewechat.configuration.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.configuration.pojo.SNSUserInfo;
import org.electricbicyclewechat.configuration.pojo.WeixinOauth2Token;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class AdvacedUtil {
	/**
     * 通过code换取网页授权access_token֤
     * 
     * @param appId 
     * @param appSecret 
     * @param code
     * @return WeixinAouth2Token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
    	Logger log = Logger.getLogger(AdvacedUtil.class);
        WeixinOauth2Token wat = null;
        
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("errcode:{"+  errorCode  + "} errmsg:{"+ errorMsg + "}");
            }
        }
        return wat;
    }
    
    /**
     * 使用access_token获取用户信息
     * 
     * @param accessToke֤
     * @param openId
     * @return SNSUserInfo
     */
    @SuppressWarnings( { "deprecation", "unchecked" })
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
    	Logger log = Logger.getLogger(AdvacedUtil.class);
        SNSUserInfo snsUserInfo = null;
        // ַ
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // ͨ
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                snsUserInfo.setCountry(jsonObject.getString("country"));
                snsUserInfo.setProvince(jsonObject.getString("province"));
                snsUserInfo.setCity(jsonObject.getString("city"));
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error(" errcode:{"+  errorCode  + "} errmsg:{"+ errorMsg + "}");
            }
        }
        System.out.println("snsUserInfo=" + snsUserInfo.getNickname() + " "  + snsUserInfo.getOpenId());
        return snsUserInfo;
    }

}
