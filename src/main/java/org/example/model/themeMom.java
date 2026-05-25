package org.example.model;
//테마명 전달 해주는 부모클래스입니다!!!
public abstract class themeMom {

    private final String themeName;

    protected themeMom(String themeName) {

        if (themeName == null || themeName.isBlank()) {
            throw new IllegalArgumentException(
                    "테마명은 비어 있을 수 없습니다."
            );
        }

        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }
}