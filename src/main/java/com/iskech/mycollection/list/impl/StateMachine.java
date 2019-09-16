package com.iskech.mycollection.list.impl;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/5 14:39
 * @description：使用枚举模拟简单状态机
 * @modified By：
 * @version: V1.0
 */
public class StateMachine {
  private MyColor color;

  private enum MyColor {
    RED(1),
    GREEN(2),
    BLUE(3),
    BLACK(4);
    private int state;

    public int getState() {
      return state;
    }

    MyColor(int state) {
      this.state = state;
    }
  }

  public void change() {
    switch (this.color) {
      case RED:
        final int state = color.state;
        this.color = MyColor.GREEN;
        System.out.println("state:=========" + state);
      case GREEN:
        final int state1 = color.state;
        this.color = MyColor.BLUE;
        System.out.println("state:=========" + state1);
      case BLUE:
        final int state3 = color.state;
        this.color = MyColor.BLACK;
        System.out.println("state:=========" + state3);
      case BLACK:
        final int state2 = color.state;
        System.out.println("state:=========" + state2);
      default:
    }
  }

  public StateMachine(MyColor color) {
    this.color = color;
  }

  public static void main(String[] args) {
    StateMachine stateMachine = new StateMachine(MyColor.RED);
    stateMachine.change();
  }
}
