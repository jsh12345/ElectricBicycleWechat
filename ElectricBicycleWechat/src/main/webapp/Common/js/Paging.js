/**
 * 分页脚本
 */
/**
 * 生成当前页的分页导航
 * @param PageNum当前第几页数
 * @param PageCount总页数
 * @param reload 获取此分页函数返回的结果的函数；
 */
function GetPaging(PageNum,PageCount,reload)
{

	var pageList = "<ul>";
	if(parseInt(PageNum)>1)
		{
			var temp1=PageNum-1;
			pageList+="<li><a href=\"javascript:void(0)\" onclick=\""+ reload+"("+temp1+")\">上一页</a></li>";
		}
	for (var i = (PageNum - 4) > 0 ? (PageNum - 4) : 1; i <= ((PageNum + 4) <= PageCount ? (PageNum + 4): PageCount); i++)
	{
		if (i == PageNum) {
			pageList += "<li><span class=\"current\">"
					+ i + "</span></li>";
		} else {
			pageList += "<li><a href=\"javascript:void(0)\" onclick=\""+ reload+"("+i+")\">" + i + "</a></li>";
		}
	}
	if ((PageNum + 5) < PageCount) 
	{
		pageList += "<li><a>...</a></li>";
		pageList += "<li><a href=\"javascript:void(0)\" onclick=\" "+ reload+"("+PageCount+")\">" + PageCount + "</a>";
	}
	if(parseInt(PageNum)<parseInt(PageCount))
		{
			var temp2=PageNum+1;
			pageList+="<li><a href=\"javascript:void(0)\" onclick=\""+ reload+"("+temp2+")\">下一页</a></li>"
		}
		pageList+="</ul>"
	return pageList.toString();
}
