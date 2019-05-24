function getXMLHttpRequest(){
	if(window.ActiveXObject){ //브라우저가 엑티비티오브젝트냐 물어봄. 인터넷익스플로냐고 물어본거임.
		try{
			return new ActiveXObject("Msxml2.XMLHTTP"); // 얻어넴. 7이상 신버전
		}catch(e1){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP"); // 익스플로 7이하 구버전
			}catch(e2){
				return null;
			}
		}
	}else if(window.XMLHttpRequest){ //익스플로가 아니냐.
		return new XMLHttpRequest(); //서버하고 통신 할 수 있는 자바 스크렙트
	}else{
		return null;
	}
}

var httpRequest = null;

function sendRequest(url, params, callback, method){
	httpRequest = getXMLHttpRequest();
	
	var httpMethod = method ? method : 'GET'; // 메소드에 값이 있으면 메소드 아니면 겟을 써라.
	if(httpMethod != 'GET' && httpMethod != 'POST'){ // 오타 확인작업.
		httpMethod = 'GET';
	}
	var httpParams = (params == null || params == '') ? null : params; // 위에방식처럼 params ? 이렇게 써도됨 널포인트 방지.
	var httpUrl = url;
	if(httpMethod == 'GET' && httpParams != null){ // 넘겨줄게 있냐고 물어봄
		httpUrl = httpUrl + "?" + httpParams; // 파라미터 보내기. get방식
	}
	
	//alert("method == " + httpMethod + "\turl == " + httpUrl + "\tparam == " + httpParams);
	httpRequest.open(httpMethod, httpUrl, true); // ture 비 동기방식 단순 연결작업.
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.onreadystatechange = callback; 
	// 0~3넘어오는중 4정상적 넘어오면 이 숫자가 바뀔때마다 callback함수를 호출 비동기 0~3 로딩중 4일때 화면에 보여라.
	//alert(httpMethod == 'POST' ? httpParams : null);
	httpRequest.send(httpMethod == 'POST' ? httpParams : null); // 최종적으로 보내라 post방식
}