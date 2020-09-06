public class Arrays {


    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public static void main(String[] args) {

    }

    public void arrayOperation (){
        for(float x: arr) {
            x = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(02.f + i / 5) * Math.cos(02f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println((System.currentTimeMillis() - a) + " миллисекунд, время для просчета одного массива");
    }

    public void createSeparateArray() {
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, 0, a2, 0, h);

        Thread arrayF = new Thread(new Runnable() {
            @Override
            public void run() {
                for(float x: arr) {
                    x = 1;
                }
                long a = System.currentTimeMillis();
                for (int i = 0; i < a1.length; i++) {
                    a1[i] = (float)(a1[i] * Math.sin(02.f + i / 5) * Math.cos(02f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        arrayF.start();

        Thread arrayS = new Thread(new Runnable() {
            @Override
            public void run() {
                for(float x: arr) {
                    x = 1;
                }
                long a = System.currentTimeMillis();
                for (int i = 0; i < a2.length; i++) {
                    a2[i] = (float)(a2[i] * Math.sin(02.f + i / 5) * Math.cos(02f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        arrayS.start();



        System.arraycopy(a1,0, arr, 0, h);
        System.arraycopy(a2,0, arr, h, h);
        System.out.println((System.currentTimeMillis() - a) + " миллисекунд, время для просчета по двум массивам" );
    }


//    @Override
//    public void run() {
//        for(float x: arr) {
//            x = 1;
//        }
//        long a = System.currentTimeMillis();
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (float)(arr[i] * Math.sin(02.f + i / 5) * Math.cos(02f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//        System.out.println((System.currentTimeMillis() - a) + " время по одному массиву");
//    }
}

