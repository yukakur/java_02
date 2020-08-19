public class Course {
    Course[] course;

    public Course() {
    }

    public Course(Course[] course) {
        this.course = course;
    }

    public void dolt(Team team) {
        for (Team group : team.team) {
            for (Course unit : course) {
                group.move(unit);
            }
        }
    }
}
