$(function() {

	var ctx = $('#my-chart').get(0).getContext('2d');

	var data = {
		labels : labels,
		datasets : [ {
			label : 'Months',
			fillColor : 'rgba(4,151,179,0.5)',
			highlightFill : 'rgba(0,163,124,0.5)',
			data : dataset
		} ]
	};
	var options = {
		barStrokeWidth : 1,
		responsive : true,
		animation : true,
		animationSteps: 40,
		scaleShowLabels: true,
		maintainAspectRatio: true,    
	    scaleLabel: "<%=value%>%",
		barShowStroke : true,
		showLabel : true,
		scaleShowHorizontalLines: true,
		scaleShowVerticalLines: true,
		tooltipTemplate : "<%=value.toLocaleString()%>%",
		tooltipFontSize: 16,
		tooltipCaretSize: 0,
		tooltipXOffset: 500,
		tooltipYOffset: -40,
		tooltipXPadding: 2,
		tooltipYPadding: 2,
		tooltipFillColor: "rgba(0,0,0,0.8)",
		tooltipFontColor: "#fff",
	    onAnimationComplete: function() {
	    	this.showTooltip(this.datasets[0].bars, true);
	    	this.showTooltip(this.segments, true);
	    },
	    showTooltips: true,
	    tooltipEvents: []
	};

	var chart = new Chart(ctx).HorizontalBar(data, options);

});