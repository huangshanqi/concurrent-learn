package cn.evilcoder;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * User: evilcoder
 * Date: 2016/3/20
 * Time: 22:33
 */
public class MyCyclicBarrier {

  private static final int LOOP_NUM = 2;
  private static final int PLAYER_NUM = 7;
  private static final Random random = new Random(System.currentTimeMillis());

  static class Player implements Runnable{

    int loop;
    int numer;
    CyclicBarrier barrier;

    public Player(int loop,int numer, CyclicBarrier barrier) {
      this.loop = loop;
      this.numer = numer;
      this.barrier = barrier;
    }

    @Override
    public void run() {
      int time = random.nextInt(10);
      System.out.println(numer +" 号需要 "+ time +" 秒完成第 " + loop+" 圈");
      try {
        Thread.sleep(time*1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(numer+" 号完成第 " +loop+ " 圈");
      try {
        barrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
  public static void main(String[] args) {

    CyclicBarrier barrier = new CyclicBarrier(PLAYER_NUM);
    for(int i=1;i<=LOOP_NUM;i++){
      System.out.println("第 "+i+" 轮比赛开始！");
      for(int j=1;j<=PLAYER_NUM;j++){
        new Thread(new Player(i,j,barrier)).start();
      }
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("比赛结束！");
  }
}
