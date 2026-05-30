package org.example.model;

import java.util.List;

public record RecommendRefreshResult(
        Class4 class4,
        List<Class3> class3Result,
        Class9 class9
) {
    public RecommendRefreshResult {
        if (class4 == null) {
            throw new IllegalArgumentException(
                    "Class4 결과는 null일 수 없습니다."
            );
        }

        if (class3Result == null || class3Result.isEmpty()) {
            throw new IllegalArgumentException(
                    "Class3 결과 목록은 비어 있을 수 없습니다."
            );
        }

        if (class9 == null) {
            throw new IllegalArgumentException(
                    "Class9 결과는 null일 수 없습니다."
            );
        }

        class3Result =
                List.copyOf(
                        class3Result
                );
    }
}