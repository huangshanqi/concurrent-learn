package cn.evilcoder;

/**
 * User: evilcoder
 * Date: 2016/3/20
 * Time: 23:48
 */
public class MyThreadLocal {

  private static final int PLAYER_NUM = 7;
  private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
    @Override
    protected Integer initialValue() {
      return 0;
    }
  };
  static class Player implements Runnable{
    int number;

    public Player(int number) {
      this.number = number;
    }

    @Override
    public void run() {
      System.out.println(number +" 号运动员初始做了 " + threadLocal.get()+" 个俯卧撑");
      for(int i=0;i<10;i++){
        threadLocal.set(threadLocal.get()+1);
      }
      System.out.println(number+" 号运动员做了 " +threadLocal.get()+" 个俯卧撑");
    }
  }
  public static void main(String[] args) {

    for (int i=1;i<=PLAYER_NUM;i++){
      new Thread(new Player(i)).start();
    }
  }
}
