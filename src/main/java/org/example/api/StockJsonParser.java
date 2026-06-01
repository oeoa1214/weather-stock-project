package org.example.api;

import java.util.ArrayList;
import java.util.List;

//→ 주가 JSON에서 현재가와 최근 7일 종가만 뽑기
public final class StockJsonParser {

    //주식 API 응답 JSON에서 숫자만 뽑아내는 클래스
    private StockJsonParser() {
    }
    //9번줄 생성자에 private
    //json 문자열
    //현재가는 한개 종가는 여러개라서 리스트더블
    public static List<Double> parseClosePrices(String json) {

        List<Double> prices = new ArrayList<>();

        try {
            String closeKey = "\"close\":[";
            int start = json.indexOf(closeKey);
            //\"close\":["를 찾음
            //indexOf=처음 등장하는 인덱스를 찾음
            //못 찾았을떄
            if (start == -1) {
                return prices;
            }
            //숫자 시작 위치로 이동
            //처음 등장하는 인덱스+\"close\":["크기= 숫자 시작위치
            start += closeKey.length();

            int end = json.indexOf("]", start);
            //indexof(a,b) b위치 부터시작해서 a를 찾아라
            String closeData = json.substring(start, end);
            //substring=문자열을 자름
            String[] values = closeData.split(",");
            //split 특정기준을 잘라서 배열로 만듬
            for (int i = 0; i < values.length; i++) {

                if (!values[i].equals("null")) {
                    //리스트에 추가
                    prices.add(
                            Double.parseDouble(values[i])
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("종가 파싱 실패");
        }

        return prices;
    }
//문자열 받아서 double로반환
    public static double parseCurrentPrice(String json) {

        try {
            String key = "\"regularMarketPrice\":";
            int start = json.indexOf(key);

            if (start == -1) {
                return -1;
            }

            start += key.length();

            int end = json.indexOf(",", start);
            String value = json.substring(start, end);

            return Double.parseDouble(value);

        } catch (Exception e) {
            System.out.println("현재가 파싱 실패");
        }

        return -1;
    }
}