public abstract class ScholarshipApplication implements Scorable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isAwarded() {
        return awarded;
    }

    public void setAwarded(boolean awarded) {
        this.awarded = awarded;
    }

    private  int id;

    public ScholarshipApplication(int id, int studentId) {
        if (id >= 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Только положительны числа");
        }
        if (studentId >= 0) {
            this.studentId = studentId;
        } else {
            throw new IllegalArgumentException("Только положительны числа");
        }
        this.awarded = false;
    }

    private int studentId;
    private boolean awarded;
    abstract String  getDetails();
}
