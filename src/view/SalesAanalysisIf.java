package view;

import java.util.Date;

public interface SalesAanalysisIf {
	//销售分析接口
	
	
	//剧院销售排行榜函数,降序显示截止目前剧院电影票房排行榜
	public void boxOffice();
	
	//显示售票员在给定日期区间的售票情况
	public void statSale(int uid ,Date stDate, Date enDate);
	
	//销售分析入口函数，显示菜单，菜单包含"降序显示截止目前剧院电影票房排行榜"，“显示或查询当日售票员售票情况”，
	//“查询给定日期区间某售票员售票情况”
	public void mgtEntry();
}
