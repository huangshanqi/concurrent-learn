package cn.evilcoder;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * User: evilcoder
 * Date: 2016/3/20
 * Time: 22:59
 */
public class MySemaphore {

  private static final int PLAYER_NUM = 7;
  private static final int ROOM_NUm = 3;
  private static final Random random = new Random(System.currentTimeMillis());

  static class Player implements Runnable{

    Semaphore semaphore;
    int number;

    public Player(Semaphore semaphore, int number) {
      this.semaphore = semaphore;
      this.number = number;
    }

    @Override
    public void run() {
      int dressTime = random.nextInt(10);
      if (semaphore.availablePermits()>0){
        System.out.println(number+" 号不用等");
      }else {
        System.out.println(number+" 号正在等");
      }
      //申请试用更衣室
      try {
        semaphore.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        Thread.sleep(dressTime*1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(number+" 号用 " + dressTime+" 秒更衣完毕");
      //用完了释放资源
      semaphore.release();
    }
  }

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(ROOM_NUm);
    for(int i=1;i<=PLAYER_NUM;i++){
      new Thread(new Player(semaphore,i)).start();
    }
  }
}
