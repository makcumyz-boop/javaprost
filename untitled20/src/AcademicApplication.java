public class AcademicApplication extends ScholarshipApplication {
    private final int exam1;

    public AcademicApplication(int id, int studentId, int exam1, int exam2) {
        super(id, studentId);
        if (exam1 >= 0 && exam1 <=100) {
            this.exam1 = exam1;
        } else {
            throw new IllegalArgumentException("Только положительны числа");
        }
        if (exam2 >= 0 && exam2 <=100) {
            this.exam2 = exam2;
        } else {
            throw new IllegalArgumentException("Только положительны числа");
        }
    }

    public int getExam2() {
        return exam2;
    }

    public int getExam1() {
        return exam1;
    }

    private final int exam2;

    @Override
    String getDetails() {
        return "";
    }

    @Override
    public double calculateScore() {
        return (exam1 + exam2)/2.0;
    }
}
