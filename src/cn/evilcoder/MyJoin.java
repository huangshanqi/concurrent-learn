package cn.evilcoder;

/**
 * User: evilcoder
 * Date: 2016/3/20
 * Time: 23:32
 */
public class MyJoin {

  private static int num = 0;

  static class Counter implements Runnable{
    @Override
    public void run() {
      for(int i=0;i<1000;i++){
        num++;
      }
    }
  }
  public static void main(String[] args) {

    Thread subThead = new Thread(new Counter());
    subThead.start();
    try {
      subThead.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("num======"+num);
  }
}
