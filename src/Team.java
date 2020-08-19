import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class Team implements Runnable, Jump{
    String name;
    public int jumpHeight;
    public int runningLength;
    public boolean success = true;
    public int distancePass = 0;
    Team[] team;
    public static List<String> list = new ArrayList<>();


    public Team() {}

    public Team(String name, Team[] team) {
        this.name = name;
        this.team = team;

        }

    public void move(Course course) {

        if (!success) {
//            System.out.println("сошел с дистанции");
        } else {
            if (course instanceof Wall) {
                Wall wall = (Wall) course;
                if (wall.height > this.jumpHeight) {
//                    System.out.println(this.getClass() + " не перепрыгнул");
                    System.out.println(this.getClass() + " не перепрыгнул " + this.jumpHeight);
                    success = false;
                } else this.jump();
            } else {
                RunningTrack track = (RunningTrack) course;
                distancePass += track.length;
                if (track.length > this.runningLength || distancePass > runningLength) {
//                    System.out.println(this.getClass() + " не пробежал");
                    System.out.println(this.getClass() + " не пробежал " + this.runningLength);
                    success = false;
                } else {
                    this.run();
                    System.out.println(this.getClass() + " прыгнул");

                }
            }

        }
    }

    public void showResults() {
        for(Team x: team) {
            if(x.success) {
                if(x instanceof Human) System.out.println("Человек из команды \"" + this.name + "\"  прошел полосу препятствий");
                else if(x instanceof Cat) System.out.println("Кот из команды \"" + this.name + "\"  прошел полосу препятствий");
                else if(x instanceof Robot) System.out.println("Робот из команды \"" + this.name + "\"  прошел полосу препятствий");
            } else {
                if(x instanceof Human) System.out.println("Человек из команды \"" + this.name + "\"  провалил состязание");
                else if(x instanceof Cat) System.out.println("Кот из команды \"" + this.name + "\"  провалил состязание");
                else if(x instanceof Robot) System.out.println("Робот из команды \"" + this.name + "\"  провалил состязание");
            }
        }
    }

    @Override
    public void jump() {

    }

    @Override
    public void run() {

    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", jumpHeight=" + jumpHeight +
                ", runningLength=" + runningLength +
                ", success=" + success +
                ", distancePass=" + distancePass +
                ", team=" + Arrays.toString(team) +
                '}';
    }
}
