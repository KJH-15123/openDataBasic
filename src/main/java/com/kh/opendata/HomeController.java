package com.kh.opendata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.opendata.model.vo.DataVO;

@Controller
public class HomeController {

	@ResponseBody
	@RequestMapping(value = "/openData.do", produces = "application/json;charset=UTF-8")
	public String openData() throws IOException {

		// 인증키 준비
		final String serviceKey = "v6YzJBWl9w5x%2FRwvHr%2FGB6LWvnHoiDkudhbAlfa%2Fhc%2FM7k7XZB3mj27bdzVkQuETwbVqz%2B0DoJ3JjyAwhHzqgw%3D%3D";

		// 요청할 URL 준비
		String url = "http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
		url += "?serviceKey=" + serviceKey;// 서비스키 추가(필수 옵션)
		url += "&year=2025"; // 년도 입력 (필수 옵션)
		url += "&returnType=json";// 응답데이터 형식 json으로
		url += "&numOfRows=1";
		// HttpURLConnection 객체 이용하여 api 요청작업
		// java.net.URL

		// 1.요청하고자 하는 url을 전달하며 java.net.URL 객체를 생성
		URL requestURL = new URL(url);

		// 2.생성된 URL객체로 HttpURLConnection 객체를 생성한다.
		// .openConnection() 메소드를 이용하여 생성(다운캐스팅 필요 - URLConnection에서 HttpURLConnection으로
		HttpURLConnection urlCon = (HttpURLConnection) requestURL.openConnection();

		// 3.요청에 필요한 Header 설정
		urlCon.setRequestMethod("GET"); // get방식 요청

		// 4.해당 open api서버로 요청 후 스트림을 통해 데이터 읽어오기
		// urlCon에서 얻어올 수 있는 스트림은 inputStream이기 때문에 bufferedReader랑 쓰기 위해 reader로 변환 스트림
		// 이용하기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

		// 스트림 연결되었으니 한줄 읽기 및 담아주기
		String responseStr = br.readLine();

		System.out.println(responseStr);

		// json문자열 타입으로 처리된 응답 데이터
		return responseStr;
	}

	@ResponseBody
	@RequestMapping(value = "/openData2.do", produces = "application/json;charset=UTF-8")
	public ArrayList<DataVO> openData2() throws IOException, ParseException {

		// 인증키 준비
		final String serviceKey = "v6YzJBWl9w5x%2FRwvHr%2FGB6LWvnHoiDkudhbAlfa%2Fhc%2FM7k7XZB3mj27bdzVkQuETwbVqz%2B0DoJ3JjyAwhHzqgw%3D%3D";

		// 요청할 URL 준비
		String url = "http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
		url += "?serviceKey=" + serviceKey;// 서비스키 추가(필수 옵션)
		url += "&year=2025"; // 년도 입력 (필수 옵션)
		url += "&returnType=json";// 응답데이터 형식 json으로
		// url+="&numOfRows=1";
		// HttpURLConnection 객체 이용하여 api 요청작업
		// java.net.URL

		// 1.요청하고자 하는 url을 전달하며 java.net.URL 객체를 생성
		URL requestURL = new URL(url);

		// 2.생성된 URL객체로 HttpURLConnection 객체를 생성한다.
		// .openConnection() 메소드를 이용하여 생성(다운캐스팅 필요 - URLConnection에서 HttpURLConnection으로
		HttpURLConnection urlCon = (HttpURLConnection) requestURL.openConnection();

		// 3.요청에 필요한 Header 설정
		urlCon.setRequestMethod("GET"); // get방식 요청

		// 4.해당 open api서버로 요청 후 스트림을 통해 데이터 읽어오기
		// urlCon에서 얻어올 수 있는 스트림은 inputStream이기 때문에 bufferedReader랑 쓰기 위해 reader로 변환 스트림
		// 이용하기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

		// 스트림 연결되었으니 한줄 읽기 및 담아주기
		String responseStr = br.readLine();

		System.out.println(responseStr);

		// 응답 데이터 문자열을 JSON 화 시키기 (파싱)
		JSONObject totalObj = (JSONObject) new JSONParser().parse(responseStr);
		System.out.println(totalObj);
		JSONObject response = (JSONObject) totalObj.get("response");
		System.out.println(response);
		JSONObject body = (JSONObject) response.get("body");
		System.out.println(body);
		JSONArray items = (JSONArray) body.get("items");
		System.out.println(items);
		// items 속성에 접근하여 객체배열을 얻어냈으니 해당 객체배열에서
		// 데이터를 접근하여 VO에 담아주기
		// 우리가 정의한 VO에 각 데이터 담아서 ArrayList에 추가 후 list 출력해보기
		ArrayList<DataVO> list = new ArrayList<>();

		for (Object jobj : items) {
			// 다운캐스팅 처리해놓기
			JSONObject jo = (JSONObject) jobj;

			list.add(DataVO.builder().clearVal((String) jo.get("clearVal")).sn((String) jo.get("sn"))
					.districtName((String) jo.get("districtName")).dataDate((String) jo.get("dataDate"))
					.issueVal((String) jo.get("issueVal")).issueTime((String) jo.get("issueTime"))
					.clearDate((String) jo.get("clearDate")).issueDate((String) jo.get("issueDate"))
					.moveName((String) jo.get("moveName")).clearTime((String) jo.get("clearTime"))
					.issueGbn((String) jo.get("issueGbn")).itemCode((String) jo.get("itemCode")).build());
		}

		for (DataVO dv : list) {
			System.out.println(dv);
		}

		// json문자열 타입으로 처리된 응답 데이터
		return list;
	}

	
	@ResponseBody
	@RequestMapping(value="/openData3.do",produces="application/json;charset=UTF-8")
	public ArrayList<DataVO> openData3() throws IOException, ParseException {
		// 버튼을 누르면 미세먼지 api 요청후 응답 데이터를 받아
		// 해당 데이터를 각 속성명에 해당하는 테이블을 구성하여
		// tr로 추가해보기
		// 인증키 준비
		final String serviceKey = "v6YzJBWl9w5x%2FRwvHr%2FGB6LWvnHoiDkudhbAlfa%2Fhc%2FM7k7XZB3mj27bdzVkQuETwbVqz%2B0DoJ3JjyAwhHzqgw%3D%3D";

		// 요청할 URL 준비
		String url = "http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
		url += "?serviceKey=" + serviceKey;// 서비스키 추가(필수 옵션)
		url += "&year=2025"; // 년도 입력 (필수 옵션)
		url += "&returnType=json";// 응답데이터 형식 json으로
		// url+="&numOfRows=1";
		// HttpURLConnection 객체 이용하여 api 요청작업
		// java.net.URL

		// 1.요청하고자 하는 url을 전달하며 java.net.URL 객체를 생성
		URL requestURL = new URL(url);

		// 2.생성된 URL객체로 HttpURLConnection 객체를 생성한다.
		// .openConnection() 메소드를 이용하여 생성(다운캐스팅 필요 - URLConnection에서 HttpURLConnection으로
		HttpURLConnection urlCon = (HttpURLConnection) requestURL.openConnection();

		// 3.요청에 필요한 Header 설정
		urlCon.setRequestMethod("GET"); // get방식 요청

		// 4.해당 open api서버로 요청 후 스트림을 통해 데이터 읽어오기
		// urlCon에서 얻어올 수 있는 스트림은 inputStream이기 때문에 bufferedReader랑 쓰기 위해 reader로 변환 스트림
		// 이용하기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

		// 스트림 연결되었으니 한줄 읽기 및 담아주기
		String responseStr = br.readLine();
		
		// 응답 데이터 문자열을 JSON 화 시키기 (파싱)
		JSONObject totalObj = (JSONObject) new JSONParser().parse(responseStr);
		System.out.println(totalObj);
		JSONObject response = (JSONObject) totalObj.get("response");
		System.out.println(response);
		JSONObject body = (JSONObject) response.get("body");
		System.out.println(body);
		JSONArray items = (JSONArray) body.get("items");
		System.out.println(items);
		// items 속성에 접근하여 객체배열을 얻어냈으니 해당 객체배열에서
		// 데이터를 접근하여 VO에 담아주기
		// 우리가 정의한 VO에 각 데이터 담아서 ArrayList에 추가 후 list 출력해보기
		ArrayList<DataVO> list = new ArrayList<>();

		for (Object jobj : items) {
			// 다운캐스팅 처리해놓기
			JSONObject jo = (JSONObject) jobj;

			list.add(DataVO.builder().clearVal((String) jo.get("clearVal")).sn((String) jo.get("sn"))
					.districtName((String) jo.get("districtName")).dataDate((String) jo.get("dataDate"))
					.issueVal((String) jo.get("issueVal")).issueTime((String) jo.get("issueTime"))
					.clearDate((String) jo.get("clearDate")).issueDate((String) jo.get("issueDate"))
					.moveName((String) jo.get("moveName")).clearTime((String) jo.get("clearTime"))
					.issueGbn((String) jo.get("issueGbn")).itemCode((String) jo.get("itemCode")).build());
		}

		for (DataVO dv : list) {
			System.out.println(dv);
		}

		// json문자열 타입으로 처리된 응답 데이터
		return list;
	}

}
