package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        var safetyList = new SafetyList();
        System.out.println(safetyList.getSize());
        ListThread listThread1 = new ListThread(safetyList);
        ListThread listThread2 = new ListThread(safetyList);

        listThread1.start();
        listThread2.start();

        listThread1.join();
        listThread2.join();

        System.out.println(safetyList.getSize());
        // END
    }
}

