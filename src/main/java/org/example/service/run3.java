package org.example.service;

import org.example.model.Class3;

import java.util.ArrayList;
import java.util.List;

public class run3 {

    public List<Class3> createClass3List(
            Delivery3 delivery3
    ) {
        if (delivery3 == null) {
            throw new IllegalArgumentException(
                    "run3에 전달된 Delivery3는 null일 수 없습니다."
            );
        }

        List<Class3> class3List =
                new ArrayList<>();

        for (Hub3Data.Item item : delivery3.selectedItems()) {
            class3List.add(
                    new Class3(
                            item.name(),
                            item.symbol(),
                            item.theme(),
                            item.currentPrice(),
                            item.returnRate(),
                            createShortReason(
                                    item.theme()
                            )
                    )
            );
        }

        return class3List;
    }

    private String createShortReason(
            String detailTheme
    ) {
        if (detailTheme == null) {
            return "테마 수요 증가";
        }

        return switch (detailTheme) {
            case "편의점·근거리 소비" -> "편의점 수요 증가";
            case "간편식·식품" -> "간편식 수요 증가";
            case "항공·이동" -> "이동 수요 증가";
            case "여행예약" -> "여행 예약 증가";
            case "호텔·면세" -> "관광 소비 증가";
            case "영화·문화생활" -> "문화생활 수요 증가";
            case "백화점·유통" -> "외부 소비 증가";
            case "공기청정가전" -> "공기 관리 수요 증가";
            case "위생소모품" -> "위생용품 수요 증가";
            case "생활위생용품" -> "생활위생 수요 증가";
            case "냉방가전" -> "냉방가전 수요 증가";
            case "전력수요" -> "전력 수요 증가";
            case "여름음료·생수" -> "음료·생수 수요 증가";
            case "여름음료·빙과" -> "빙과류 수요 증가";
            case "도시가스·난방" -> "난방 수요 증가";
            case "정유·연료" -> "연료 수요 증가";
            case "정유·에너지" -> "에너지 수요 증가";
            case "보일러·난방기기" -> "난방기기 수요 증가";
            case "겨울간식·식품" -> "겨울간식 수요 증가";
            default -> "테마 수요 증가";
        };
    }
}