package org.electricbicyclewechat.configuration.util;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public class SignUtil {
	
	private static Logger logger = Logger.getLogger(SignUtil.class);
	
	// 与接口配置信息中的Token要一致
    private static String token = "supplywechat";
 
    public static boolean checkSignature(String signature, String timestamp,String nonce) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);
 
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        logger.info(content.toString());
 
       /* String tmpStr = DigestUtils.sha1Hex(content.toString());
        logger.info("tmpStr="+tmpStr);*/
        
      //sh1����
  		String tmpStr = getSha1(content.toString());
 
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature) : false;
    }
    
  //sh1�����㷨��ʵ��
  	public static String getSha1(String str){
          if(str==null||str.length()==0){
              return null;
          }
          char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                  'a','b','c','d','e','f'};
          try {
              MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
              mdTemp.update(str.getBytes("UTF-8"));

              byte[] md = mdTemp.digest();
              int j = md.length;
              char buf[] = new char[j*2];
              int k = 0;
              for (int i = 0; i < j; i++) {
                  byte byte0 = md[i];
                  buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                  buf[k++] = hexDigits[byte0 & 0xf];      
              }
              return new String(buf);
          } catch (Exception e) {
              // TODO: handle exception
              return null;
          }
      }
    
    

}
