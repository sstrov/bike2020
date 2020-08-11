
var x = '37.5578711';
var y = '126.8025052';
function openMap(xp, yp, type, key){
	if(xp == null || xp == ''){xp = x}
	if(yp == null || yp == ''){yp = y}
	var _height = 700;
    var _width = 1200;
    _height = screen.height - 200;
    _width = screen.width - 100;
    var _left = (screen.width - _width) / 2;
    var _top = (screen.height - _height) / 2 - 50;
    var _attr = "left="+_left+",top="+_top+",width="+_width+",height="+_height+",menubar=no,status=no,toolbar=no,resizable=no,channelmode=no";
    var url = "/map/roadMap.do?type=nation&roadCd=ROAD_TYPE_NATION_001&lon="+xp+"&lat="+yp+"&zoom=7&type=" + type + "&key="+key;
    //location.href = url;
    var popWin = window.open(url, "", _attr);
    popWin.focus();
}