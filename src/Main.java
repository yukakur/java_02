public class Main {
    public static void main(String[] args) {

        Course course = new Course(new Course[]{
                new RunningTrack(900),
                new Wall(1),
                new RunningTrack(700),
        });

         Team team = new Team("смешанная", new Team[]{
                 new Human(2, 1500),
                 new Cat(3, 100),
                 new Robot(1, 3000),
                 new Human(1, 1000 ),
         });

//         for(Team group: team.team) {
//             for(Course x: course.course) {
//                 group.move(x);
//             }
//         }
        course.dolt(team);
        System.out.println();
        team.showResults();
    }
}
