public class Cat extends Team {


    public Cat(int jumpHeight, int runningLength) {
        this.jumpHeight = jumpHeight;
        this.runningLength = runningLength;
    }

    @Override
    public void jump() {
        System.out.println("кот прыгает");
    }

    @Override
    public void run() {
        System.out.println("кот бежит");
    }

}
