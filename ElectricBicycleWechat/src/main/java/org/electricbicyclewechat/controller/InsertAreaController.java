package org.electricbicyclewechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.Area;
import org.electricbicyclewechat.pojo.request.Area.AreaParam;
import org.electricbicyclewechat.service.AreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试区域信息增加
 * 
 * @author 杨恺
 *
 */
@Controller
@RequestMapping("/insertArea")
public class InsertAreaController {

	private static Logger logger = Logger.getLogger(InsertAreaController.class);

	@Autowired
	private AreaService areaService;

	@ResponseBody
	@RequestMapping("/insertArea")
	public String test(HttpServletRequest request, Model model,
			AreaParam areaParam) throws Exception {
		if (areaParam != null) {
			Area area = new Area();
			BeanUtils.copyProperties(areaParam, area);
			boolean flag = areaService.checkArea(area);
			if (flag) {
				int num = areaService.insertArea(area);
				if (num > 0) {
					logger.info("区域信息插入成功！");
					return "true";
				} else {
					logger.error("区域信息插入失败！");
					return "false";
				}
			} else {
				logger.error("区域信息插入失败！");
				return "false";
			}
		}
		logger.error("区域信息插入失败！");
		return "false";

	}

}