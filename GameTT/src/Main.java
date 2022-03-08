import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {
    //資料刷新時間
    public static final int UPDATE_TIMES_PER_SEC=60;//每秒更興60遊戲邏輯
    public static final int NANOSECOND_PER_UPDATE=1000000000/UPDATE_TIMES_PER_SEC; //每一次要花費的奈秒數
    //畫面更新時間
    public static final int FRAME_LIMIT=60;
    public static final int LIMIT_DELTA_TIME=1000000000/FRAME_LIMIT;
    public static void main(String[] args) {

        JFrame jf=new JFrame();
        jf.setTitle("10thGameTest");
        jf.setSize(800,600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //關掉程式結束
        jf.setVisible(true);

        GameJPanel jp=new GameJPanel();
        jf.add(jp);
        jf.setVisible(true); //會觸發jp裡面的paint還有paintComponent

        long startTime=System.nanoTime();//取得目前系統時間的奈秒
        long passedUpdated=0;//實際更新的速度，用於當畫面延遲，補上資料運算的計算
        long lastRePaintTime=System.nanoTime();//最後一次畫面更新的時間
        int paintTime=0;
        long timer=System.nanoTime(); //算FPS

        //每10毫秒動一次
        while(true) {
            long currentTime = System.nanoTime();//系統當前時間
            long totalTime = currentTime - startTime;//系統開始到現在經過的時間
            long targetTotalUpdated = totalTime / (NANOSECOND_PER_UPDATE);//開始到現在應該更新的次數
            //input
            //input end
            while (passedUpdated < targetTotalUpdated) {//當前經過次次數小於實際應該要更新的次數
                //update 追上當前的次數
                jp.move();
                passedUpdated++;
            }
            if(currentTime-timer>=1000000000){
                System.out.println("FPS"+paintTime);
                paintTime=0;
                timer=currentTime;
            }
             if(LIMIT_DELTA_TIME<=currentTime-lastRePaintTime){
                 //畫出畫面
                 lastRePaintTime=currentTime; //最後一次畫的時間變成當前時間
                 jf.repaint(); //觸發繪圖
                 paintTime++;
             }
        }
        //此時時間不會與游戲同步，及lag時主角移動會變慢
//        Timer t = new Timer(10, (ActionEvent e)->{jf.repaint();});
//        t.start();
    }
}
