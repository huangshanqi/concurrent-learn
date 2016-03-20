package cn.evilcoder;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * User: evilcoder
 * Date: 2016/3/20
 * Time: 21:59
 */
public class MyCountDownLatch {
  private static final int PLAYER_NUM = 7;
  private static final Random random = new Random(System.currentTimeMillis());

  static class Player implements Runnable{
    CountDownLatch playerLatch;
    CountDownLatch gunLatch;
    int number;

    public Player(CountDownLatch playerLatch, CountDownLatch gunLatch, int number) {
      this.playerLatch = playerLatch;
      this.gunLatch = gunLatch;
      this.number = number;
    }

    @Override
    public void run() {
      int ready = random.nextInt(10);
      System.out.println(number +" 号：我需要 " + ready + " 秒准备。");
      try {
        Thread.sleep(ready*1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(number+" 号准备好了");
      playerLatch.countDown();
      try {
        gunLatch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("开始了，" + number +" 号在奋力直跑");
    }
  }


  public static void main(String[] args) {

    //初始化为运动员数目
    CountDownLatch playerLatch = new CountDownLatch(PLAYER_NUM);
    //鸣枪一声
    CountDownLatch gunLatch = new CountDownLatch(1);

    for (int i=1;i<=PLAYER_NUM;i++){
      new Thread(new Player(playerLatch,gunLatch,i)).start();
    }
    System.out.println("预备！");
    try {
      playerLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    gunLatch.countDown();
    System.out.println("跑！");
  }
}
