package lesson_15;

public enum Links {
    LOGIN_PAGE("https://qa-course-01.andersenlab.com/login");
    private String link;

    public String getLink(){
        return link;
    }

    Links(String link) {
        this.link = link;
    }
}
