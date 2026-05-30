package org.example.service;

import org.example.model.Class9;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FoodInfoService {

    private final Random random =
            new Random();

    private final Map<String, List<Class9>> foodMap =
            Map.of(
                    "맑음",
                    Arrays.asList(
                            new Class9(
                                    "맑음",
                                    "샌드위치",
                                    "[외출, 소비 증가, 가벼운 식사]",
                                    "맑은 날에는  간편하게 먹기 좋은 음식이 어울립니다.",
                                    "가볍게 들고 나가기 좋고, 활동적인 날씨와 잘 맞습니다.",
                                    "/images/food/sunny_sandwich.jpg"
                            ),
                            new Class9(
                                    "맑음",
                                    "샐러드",
                                    "[산뜻함, 건강식, 가벼운 식사]",
                                    "맑은 날에는 산뜻하고 부담 없는 음식이 잘 어울립니다.",
                                    "상쾌한 날씨와 잘 맞는 가벼운 메뉴입니다.",
                                    "/images/food/sunny_salad.jpg"
                            ),
                            new Class9(
                                    "맑음",
                                    "김밥",
                                    "[나들이, 간편식, 외출]",
                                    "맑은 날에는 이동 중에도 먹기 편한 김밥이 잘 어울립니다.",
                                    "소풍이나 외출 상황에 어울리는 대표적인 간편식입니다.",
                                    "/images/food/sunny_kimbap.jpg"
                            ),
                            new Class9(
                                    "맑음",
                                    "과일 주스",
                                    "[상큼함, 야외활동, 기분전환]",
                                    "맑은 날에는 상큼한 음료가 활동적인 분위기와 잘 맞습니다.",
                                    "가볍게 마시기 좋고 산뜻한 기분을 줍니다.",
                                    "/images/food/sunny_juice.jpg"
                            ),
                            new Class9(
                                    "맑음",
                                    "파스타",
                                    "[외식, 소비 증가, 기분전환]",
                                    "맑은 날에는 분위기 있는 음식이 잘 어울립니다.",
                                    "외부 소비 증가 흐름과 연결하기 좋은 메뉴입니다.",
                                    "/images/food/sunny_pasta.jpg"
                            )
                    ),

                    "비",
                    Arrays.asList(
                            new Class9(
                                    "비",
                                    "파전",
                                    "[비, 전, 막걸리]",
                                    "비 오는 날에는 따뜻하고 기름진 음식이 잘 어울립니다.",
                                    "비 오는 날 대표적으로 떠올리기 쉬운 음식입니다.",
                                    "/images/food/rain_pajeon.jpg"
                            ),
                            new Class9(
                                    "비",
                                    "칼국수",
                                    "[따뜻한 국물, 비, 면요리]",
                                    "비 오는 날에는 따뜻한 국물 음식 수요가 늘어납니다.",
                                    "몸을 데워주고 포만감이 있어 비 오는 날과 잘 맞습니다.",
                                    "/images/food/rain_kalguksu.jpg"
                            ),
                            new Class9(
                                    "비",
                                    "라면",
                                    "[간편식, 실내, 국물]",
                                    "비 오는 날에는 실내에서 먹을 수 있는 음식이 어울립니다.",
                                    "조리 시간이 짧고 따뜻한 국물이 있어 비 오는 날에 적합합니다.",
                                    "/images/food/rain_ramen.jpg"
                            ),
                            new Class9(
                                    "비",
                                    "떡볶이",
                                    "[배달, 간식, 매운맛]",
                                    "비 오는 날에는 배달이나 간식 수요가 증가할 수 있습니다.",
                                    "매콤한 맛으로 실내 간식 메뉴로 잘 어울립니다.",
                                    "/images/food/rain_tteokbokki.jpg"
                            ),
                            new Class9(
                                    "비",
                                    "수프",
                                    "[따뜻함, 부드러움, 실내식]",
                                    "비 오는 날에는 따뜻하고 부드러운 음식이 잘 어울립니다.",
                                    "가볍지만 몸을 데워주는 음식입니다.",
                                    "/images/food/rain_soup.jpg"
                            )
                    ),

                    "폭염",
                    Arrays.asList(
                            new Class9(
                                    "폭염",
                                    "냉면",
                                    "[시원함, 여름, 더위]",
                                    "폭염에는 체온을 낮춰주는 차가운 음식이 어울립니다.",
                                    "더운 날 가장 직관적으로 떠올리기 좋은 시원한 메뉴입니다.",
                                    "/images/food/heat_naengmyeon.jpg"
                            ),
                            new Class9(
                                    "폭염",
                                    "초계국수",
                                    "[차가운 면, 여름식, 더위 완화]",
                                    "더운 날에는 차갑고 산뜻한 면 요리가 잘 어울립니다.",
                                    "가볍고 시원해 폭염 상황에 적합합니다.",
                                    "/images/food/heat_cold_noodle.jpg"
                            ),
                            new Class9(
                                    "폭염",
                                    "아이스크림",
                                    "[디저트, 냉방, 더위]",
                                    "폭염에는 차가운 디저트 수요가 증가합니다.",
                                    "즉각적으로 시원함을 느낄 수 있는 메뉴입니다.",
                                    "/images/food/heat_icecream.jpg"
                            ),
                            new Class9(
                                    "폭염",
                                    "수박",
                                    "[수분보충, 여름, 과일]",
                                    "더운 날에는 수분이 많은 과일이 잘 어울립니다.",
                                    "수분 보충과 더위 완화에 적합한 여름 음식입니다.",
                                    "/images/food/heat_watermelon.jpg"
                            ),
                            new Class9(
                                    "폭염",
                                    "빙수",
                                    "[디저트, 냉방, 폭염]",
                                    "폭염에는 시원한 빙과류와 디저트 수요가 늘어납니다.",
                                    "더위를 피하는 대표적인 여름 디저트입니다.",
                                    "/images/food/heat_bingsu.jpg"
                            )
                    ),

                    "한파",
                    Arrays.asList(
                            new Class9(
                                    "한파",
                                    "국밥",
                                    "[따뜻한 국물, 보온, 겨울]",
                                    "한파에는 몸을 데워주는 국물 음식이 잘 어울립니다.",
                                    "추운 날 든든하게 먹기 좋은 메뉴입니다.",
                                    "/images/food/cold_gukbap.jpg"
                            ),
                            new Class9(
                                    "한파",
                                    "전골",
                                    "[뜨거운 음식, 겨울, 보온]",
                                    "추운 날에는 여러 재료가 들어간  전골이 잘 어울립니다.",
                                    "체온 유지와 포만감을 동시에 줄 수 있습니다.",
                                    "/images/food/cold_hotpot.jpg"
                            ),
                            new Class9(
                                    "한파",
                                    "어묵탕",
                                    "[국물, 겨울간식, 한파]",
                                    "한파에는 따뜻한 길거리 음식이나 국물이 인기가 높습니다.",
                                    "추운 날 손쉽게 몸을 데울 수 있는 메뉴입니다.",
                                    "/images/food/cold_odeng.jpg"
                            ),
                            new Class9(
                                    "한파",
                                    "죽",
                                    "[부드러움, 따뜻함, 회복식]",
                                    "추운 날에는 속을 편하게 해주는 따뜻한 음식이 어울립니다.",
                                    "부담 없이 먹을 수 있고 몸을 따뜻하게 해줍니다.",
                                    "/images/food/cold_juk.jpg"
                            ),
                            new Class9(
                                    "한파",
                                    "호떡",
                                    "[겨울간식, 따뜻함, 당분]",
                                    "한파에는 따뜻한 겨울 간식 수요가 증가합니다.",
                                    "달콤하고 따뜻해 추운 날씨와 잘 어울립니다.",
                                    "/images/food/cold_hotteok.jpg"
                            )
                    ),

                    "미세먼지",
                    Arrays.asList(
                            new Class9(
                                    "미세먼지",
                                    "따뜻한 차",
                                    "[목관리, 수분, 미세먼지]",
                                    "미세먼지가 많은 날에는  수분을 보충하는 음식이 어울립니다.",
                                    "따뜻한 차는 건조함을 줄이고 컨디션 관리에 도움이 됩니다.",
                                    "/images/food/dust_tea.jpg"
                            ),
                            new Class9(
                                    "미세먼지",
                                    "샐러드",
                                    "[가벼운 식사, 건강식, 채소]",
                                    "미세먼지가 많은 날에는 부담 없는 건강식이 잘 어울립니다.",
                                    "채소 중심의 식사는 산뜻하고 가볍게 먹기 좋습니다.",
                                    "/images/food/dust_salad.jpg"
                            ),
                            new Class9(
                                    "미세먼지",
                                    "요거트",
                                    "[가벼움, 건강식, 실내식]",
                                    "미세먼지가 심한 날에는 실내에서  먹을 수 있는 음식이 좋습니다.",
                                    "부담이 적고 간편하게 먹기 좋은 메뉴입니다.",
                                    "/images/food/dust_yogurt.jpg"
                            ),
                            new Class9(
                                    "미세먼지",
                                    "과일",
                                    "[비타민, 수분, 건강]",
                                    "미세먼지가 많은 날에는 수분과 비타민이 있는 음식이 어울립니다.",
                                    "상큼하고 가벼운 음식으로 컨디션 관리에 좋습니다.",
                                    "/images/food/dust_fruit.jpg"
                            ),
                            new Class9(
                                    "미세먼지",
                                    "죽",
                                    "[부드러움, 실내식, 건강식]",
                                    "미세먼지가 많은 날에는 자극이 적고 부드러운 음식이 어울립니다.",
                                    "목과 속에 부담이 적은 음식입니다.",
                                    "/images/food/dust_porridge.jpg"
                            )
                    )
            );

    public Class9 createClass9(
            String weatherName
    ) {
        List<Class9> foods =
                foodMap.getOrDefault(
                        weatherName,
                        foodMap.get("맑음")
                );

        return foods.get(
                random.nextInt(
                        foods.size()
                )
        );
    }


}