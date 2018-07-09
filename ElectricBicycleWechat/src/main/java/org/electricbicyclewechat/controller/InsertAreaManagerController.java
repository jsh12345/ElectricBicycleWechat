package org.electricbicyclewechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.AreaManager;
import org.electricbicyclewechat.pojo.request.AreaManager.AreaManagerParam;
import org.electricbicyclewechat.service.AreaManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 区域经理信息的创建
 * 
 * @author 杨恺
 *
 */
@Controller
@RequestMapping("/insertAreaManager")
public class InsertAreaManagerController {
	private static Logger logger = Logger
			.getLogger(InsertAreaManagerController.class);
	@Autowired
	private AreaManagerService areaManagerService;

	@ResponseBody
	@RequestMapping("/insertAreaManager")
	public String test(HttpServletRequest request, Model model,
			AreaManagerParam areaManagerParam) throws Exception {
		if (areaManagerParam != null) {
			AreaManager areaManager = new AreaManager();
			BeanUtils.copyProperties(areaManagerParam, areaManager);
			boolean flag =  areaManagerService.checkAreaManager(areaManager);			
			if (flag) {
				int num = areaManagerService.insertAreaManager(areaManager);
				if (num > 0) {
					logger.info("区域经理信息添加成功！");
					return "true";
				} else {
					logger.error("区域经理信息添加失败！");
					return "false";
				}
			} else {
				logger.error("区域经理信息添加失败！");
				return "false";
			}
		}
		logger.error("区域经理信息添加失败！");
		return "false";
	}

}
