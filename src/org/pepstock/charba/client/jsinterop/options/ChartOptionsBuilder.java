package org.pepstock.charba.client.jsinterop.options;

public final class ChartOptionsBuilder {

	/**
	 * 
	 */
	private ChartOptionsBuilder() {
		// do nothing
	}

//	public static NativeObject get(Type type) {
//		ChartOptions base = Defaults.get().chart(type);
//		Scale scale = Defaults.get().getScale();
//		GlobalOptions global = Defaults.get().getGlobal();
//
//		NativeObject chartOptions = base.cloneNativeObject();
//		NativeObject scaleOptions = scale.cloneNativeObject();
//		NativeObject globalOptions = global.cloneNativeObject();
// FIXME		
//		if (base.getScale()..hasProperty(key).getScale() != null) {
//			Helpers.mergeIf(chartOptions.getScale(), scaleOptions);
//		} else if (chartOptions.getScales() != null) {
//			if (chartOptions.getScales().getXAxes() != null) {
//				ArrayObject<NativeScale> xAxes = chartOptions.getScales().getXAxes();
//				for (int i=0; i<xAxes.length(); i++){
//					Helpers.mergeIf(xAxes.get(i), scaleOptions);
//				}
//			}
//			if (chartOptions.getScales().getYAxes() != null) {
//				ArrayObject<NativeScale> yAxes = chartOptions.getScales().getYAxes();
//				for (int i=0; i<yAxes.length(); i++){
//					Helpers.mergeIf(yAxes.get(i), scaleOptions);
//				}
//			}
//		}
//		return Helpers.mergeIf(chartOptions, globalOptions);
//	}
	
//	var chartOptions = new Object();
//	if ($wnd.Chart.defaults.hasOwnProperty(chartType)){
//		chartOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults[chartType]);
//	}
//	var scaleOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults.scale);
//    var globalOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults.global);
//	
//	if (chartOptions.hasOwnProperty("scale")){
//		var scaleChartOptions = $wnd.Chart.helpers.mergeIf(chartOptions.scale, scaleOptions);
//	} else if (chartOptions.hasOwnProperty("scales")){
//		var xAxes = chartOptions.scales.xAxes;
//		for (var i=0; i<xAxes.length; i++){
//			$wnd.Chart.helpers.mergeIf(xAxes[i], scaleOptions, xAxes[i]);
//		}
//		var yAxes = chartOptions.scales.yAxes;
//		for (var i=0; i<yAxes.length; i++){
//			$wnd.Chart.helpers.mergeIf(yAxes[i], scaleOptions);
//		}
//	}
//    $wnd.Chart.helpers.mergeIf(chartOptions, globalOptions);
//    return chartOptions;

}
