package view;

import nodes.TopButton;

public class QueriesMenu implements QueriesMenuIf {

	@Override
	public void queriseMenu() {
		// TODO Auto-generated method stub
		TopButton queplay = new TopButton("查询剧目"),
				  quesch = new TopButton("演出计划");
		MainFrame.top.addAll(queplay,quesch);

	}

}
