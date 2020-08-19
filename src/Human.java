public class Human  extends Team  {


    public Human(int jumpHeight, int runningLength) {
        this.jumpHeight = jumpHeight;
        this.runningLength = runningLength;
    }


    @Override
    public void jump() {
        System.out.println("человек прыгает");
    }

    @Override
    public void run() {
        System.out.println("человек бежит");
    }

//    public void move(Course course) {
//        if (!success) {
////            System.out.println("сошел с дистанции");
//        } else {
//            if (course instanceof Wall) {
//                Wall wall = (Wall) course;
//                if (wall.height > this.jumpHeight) {
//                    System.out.println("не перепрыгнул");
//                    success = false;
//                } else this.jump();
//            } else {
//                RunningTrack track = (RunningTrack) course;
//                distancePass += track.length;
//                if (track.length > this.runningLength || distancePass > runningLength) {
//                    System.out.println("не пробежал");
//                    success = false;
//                } else {
//                    this.run();
//
//                }
//            }
//
//        }
//    }
//    public void move(Wall wall) {
//        if (wall.height > this.jumpHeight) {
//            System.out.println("не перепрыгнул");
//            success = false;
//        } else this.jump();
//    }
//    public void move(RunningTrack track) {
//        distancePass += track.length;
//        if (track.length > this.runningLength || distancePass > runningLength) {
//            System.out.println("не пробежал");
//            success = false;
//        } else {
//            this.run();
//        }
//    }
}
