public class Robot extends Team {


    public Robot(int jumpHeight, int runningLength) {
        this.jumpHeight = jumpHeight;
        this.runningLength = runningLength;
    }

    @Override
    public void jump() {
        System.out.println("робот прыгает");
    }

    @Override
    public void run() {
        System.out.println("робот бежит");
    }
}
