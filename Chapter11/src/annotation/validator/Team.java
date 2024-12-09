package annotation.validator;

public class Team {
    @NotEmpty(message = "이름이 비었습니다.")
    private String name;
    @Range(min = 1, max = 999, message = "회원 수는 1과 999 사이여야 합니다.")
    private int memeberCount;

    public Team(String name, int memeberCount) {
        this.name = name;
        this.memeberCount = memeberCount;
    }

    public String getName() {
        return name;
    }

    public int getMemeberCount() {
        return memeberCount;
    }
}
