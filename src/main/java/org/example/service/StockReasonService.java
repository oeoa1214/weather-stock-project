package org.example.service;

public class StockReasonService {

    public String getReason(String stockName) {

        if (stockName.equals("대한항공")) {
            return "여행객 증가";
        }

        if (stockName.equals("하나투어")) {
            return "여행 예약 증가";
        }

        if (stockName.equals("호텔신라")) {
            return "호텔·면세점 소비 증가";
        }

        if (stockName.equals("CJ CGV")) {
            return "영화관 방문 증가";
        }

        if (stockName.equals("신세계")) {
            return "백화점 소비 증가";
        }

        if (stockName.equals("BGF리테일")) {
            return "편의점 수요 증가";
        }

        if (stockName.equals("GS리테일")) {
            return "실내 소비 증가";
        }

        if (stockName.equals("쿠팡")) {
            return "온라인 주문 증가";
        }

        if (stockName.equals("오뚜기")) {
            return "간편식 수요 증가";
        }

        if (stockName.equals("CJ대한통운")) {
            return "배송 물량 증가";
        }

        if (stockName.equals("LG전자")) {
            return "냉방·공기청정 가전 수요 증가";
        }

        if (stockName.equals("삼성전자")) {
            return "에어컨 판매 증가";
        }

        if (stockName.equals("한국전력")) {
            return "냉방 전력 수요 증가";
        }

        if (stockName.equals("롯데칠성")) {
            return "음료 소비 증가";
        }

        if (stockName.equals("빙그레")) {
            return "아이스크림 소비 증가";
        }

        if (stockName.equals("한국가스공사")) {
            return "난방 가스 수요 증가";
        }

        if (stockName.equals("S-Oil")) {
            return "난방 연료 수요 증가";
        }

        if (stockName.equals("SK이노베이션")) {
            return "겨울 연료 수요 증가";
        }

        if (stockName.equals("경동나비엔")) {
            return "보일러 수요 증가";
        }

        if (stockName.equals("롯데웰푸드")) {
            return "겨울 간식 소비 증가";
        }

        if (stockName.equals("위닉스")) {
            return "공기청정기 수요 증가";
        }

        if (stockName.equals("코웨이")) {
            return "실내 위생 수요 증가";
        }

        if (stockName.equals("케이엠")) {
            return "마스크 수요 증가";
        }

        if (stockName.equals("깨끗한나라")) {
            return "위생용품 수요 증가";
        }

        return "테마 수혜 기대";
    }
}