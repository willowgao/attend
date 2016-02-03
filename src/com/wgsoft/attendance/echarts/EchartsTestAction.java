package com.wgsoft.attendance.echarts;

import java.util.Arrays;

import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.LineData;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import com.wgsoft.common.action.BaseAction;
import com.wgsoft.common.utils.EchartsUtils;
import com.wgsoft.diary.model.EchartsOfBar;

public class EchartsTestAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7177946659542553696L;

	public String getDatas() throws Exception {
		String jsonData = getBar().toString();
		this.renderText(response, jsonData);
		return null;
	}

	public String getBar() throws Exception {
		EchartsOfBar bar1 = new EchartsOfBar();
		bar1.setTitle("某地区蒸发量和降水量");
		bar1.setChildtitle("纯属虚构");
		bar1.setDataname("蒸发量");
		bar1.setDataunit("毫米");
		bar1.setxAxis(new String[] { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" });
		bar1.setData(new Double[] { 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3 });
		EchartsOfBar bar3 = new EchartsOfBar();
		bar3.setDataname("降水量");
		bar3.setDataunit("毫米");
		bar3.setData(new Double[] { 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3 });
		String json = EchartsUtils.getBar(Arrays.asList(bar1, bar3)).toString();
		this.renderText(response, json);
		return null;
	}

	public String getLine() throws Exception {
		GsonOption option = new GsonOption();
		option.legend("高度(km)与气温(°C)变化关系");

		option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar),
				Tool.restore, Tool.saveAsImage);

		option.calculable(true);
		option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}°C");

		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value} °C");
		option.xAxis(valueAxis);

		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.axisLine().onZero(false);
		categoryAxis.axisLabel().formatter("{value} km");
		categoryAxis.boundaryGap(false);
		categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
		option.yAxis(categoryAxis);

		Line line = new Line();
		line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5)
				.itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
		option.series(line);
		this.renderText(response, option.toString());
		return null;
	}

	public String getPie() throws Exception {
		this.renderText(response, getPie1().toString());
		return null;
	}

	public GsonOption getPie7() {
		ItemStyle dataStyle = new ItemStyle();
		dataStyle.normal().label(new Label().show(false)).labelLine().show(false);

		ItemStyle placeHolderStyle = new ItemStyle();
		placeHolderStyle.normal().color("rgba(0,0,0,0)").label(new Label().show(false)).labelLine().show(false);
		placeHolderStyle.emphasis().color("rgba(0,0,0,0)");

		GsonOption option = new GsonOption();
		option.title().text("你幸福吗？").subtext("From ExcelHome").sublink("http://e.weibo.com/1341556070/AhQXtjbqh").x(
				X.center).y(Y.center).itemGap(20).textStyle().color("rgba(30,144,255,0.8)").fontFamily("微软雅黑")
				.fontSize(35).fontWeight("bolder");
		option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
		option.legend().orient(Orient.vertical).x(10).y(56).itemGap(12).data("68%的人表示过的不错", "29%的人表示生活压力很大",
				"3%的人表示“我姓曾”");
		option.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);

		Pie p1 = new Pie("1");
		p1.clockWise(false).radius(90, 110).itemStyle(dataStyle).data(new Data("68%的人表示过的不错", 68),
				new Data("invisible", 32).itemStyle(placeHolderStyle));

		Pie p2 = new Pie("2");
		p2.clockWise(false).radius(60, 90).itemStyle(dataStyle).data(new Data("29%的人表示生活压力很大", 29),
				new Data("invisible", 71).itemStyle(placeHolderStyle));

		Pie p3 = new Pie("3");
		p3.clockWise(false).radius(30, 60).itemStyle(dataStyle).data(new Data("3%的人表示“我姓曾”", 3),
				new Data("invisible", 97).itemStyle(placeHolderStyle));

		option.series(p1, p2, p3);
		return option;

	}

	public GsonOption getPie1() {
		GsonOption option = new GsonOption();
		// 时间轴
		option.timeline().data("01", "02", "03", "04", "05",
				new LineData("06", "emptyStart6", 8), "07", "08", "09", "10",
				"11", new LineData("12", "star6", 8));
		option.timeline().autoPlay(true).label();
		// timeline变态的地方在于多个Option
		Option basic = new Option();
		basic.title().text("浏览器占比变化").subtext("纯属虚构");
		basic.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
		basic.legend().data("Chrome", "Firefox", "Safari", "IE9+", "IE8-");
		basic.toolbox().show(true).feature(
				Tool.mark,
				Tool.dataView,
				Tool.restore,
				Tool.saveAsImage,
				new MagicType(Magic.pie, Magic.funnel).option(new MagicType.Option().funnel(new Funnel().x("25%")
						.width("50%").funnelAlign(X.left).max(1548))));

		int idx = 1;
		basic.series(getPie(idx++).center("50%", "45%").radius("50%"));
		// 加入
		option.options(basic);
		// 构造11个数据
		Option[] os = new Option[11];
		for (int i = 0; i < os.length; i++) {
			os[i] = new Option().series(getPie(idx++));
		}
		option.options(os);
		return option;
	}

	/**
	 * ��ȡ��ͼ���
	 * 
	 * @param idx
	 * @return
	 */
	public Pie getPie(int idx) {
		return new Pie().name("浏览器（数据纯属虚构）").data(new PieData("Chrome", idx * 128 + 80),
				new PieData("Firefox", idx * 64 + 160), new PieData("Safari", idx * 32 + 320),
				new PieData("IE9+", idx * 16 + 640), new PieData("IE8-", idx * 8 + 1280));
	}

}
