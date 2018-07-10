package org.electricbicyclewechat.service;

import java.util.List;

public interface OrderService {
	/**
	 * 获得大类
	 * @return
	 * @throws Exception
	 */
	public List<String> getSort() throws Exception;
	/**
	 * 根据大类获取车名
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<String> getMaterialName(String sort) throws Exception;
	/**
	 * 根据车名查询对应的规格
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<String> getMaterialSpec(String name) throws Exception;
	/**
	 * 根据车名和规格获取颜色
	 * @param name
	 * @param spec
	 * @return
	 * @throws Exception
	 */
	public List<String> getMaterialColor(String name,String spec) throws Exception;
    /**
     * 图片
     * @param name
     * @param spec
     * @param photo
     * @return
     * @throws Exception
     */
	public String getMaterialPhoto(String name,String spec,String color) throws Exception;
}
