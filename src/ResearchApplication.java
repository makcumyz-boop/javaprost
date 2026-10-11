public class ResearchApplication extends ScholarshipApplication {
    private final int articles;

    public ResearchApplication(int id, int studentId, int articles, int talks) {
        super(id, studentId);
        if (articles >= 0 && articles <=5) {
            this.articles = articles;
        } else {
            throw new IllegalArgumentException("Только положительны числа");
        }
        if (talks >= 0 && talks <=10) {
            this.talks = talks;
        } else {
            throw new IllegalArgumentException("Только положительны числа");
        }
    }

    public int getTalks() {
        return talks;
    }

    public int getArticles() {
        return articles;
    }

    private final int talks;

    @Override
    String getDetails() {
        return "";
    }

    @Override
    public double calculateScore() {
        if ((20 * articles)+(10*talks) <=100){
            return (20 * articles)+(10*talks);
        }
        else {return 100;}
    }
}
