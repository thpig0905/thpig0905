package 콜렉션벡터_이론;

import java.util.Scanner;
import java.util.Vector;

class Node3{
    private String mark;
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
}
class Player{
    private String name;
    private String mark;
    public Player(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }
    public String getName() {
        return name;
    }
    public String getMark() {
        return mark;
    }
}
class TicTakToe{
    private Vector<Player> players = new Vector<Player>();
    private Vector<Node3> nodes = new Vector<Node3>();
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private final int SIZE = 3;
    int cnt;
    public TicTakToe(){
        players = new Vector<>();
    }
    private void initNodes(){
        for (int i = 0; i < SIZE * SIZE; i++){
            nodes.add(new Node3());
        }
    }
    private void init(){
        players.add(new Player("홍길동", "O"));
        players.add(new Player("김철수", "X"));
        player = players.get(0);
    }
    private void printMap(){
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (nodes.get(i * SIZE + j).getMark() != null) {
                    System.out.print("[ " + nodes.get(i * SIZE + j).getMark() + " ]");
                } else {
                    System.out.print("[ " + " ]");
                }
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    private boolean check(String mark){
        return false;
    }
    private boolean checkRow(String mark){
        for (int i = 0; i < SIZE; i++){
            boolean flag = true;
            for (int j = 0; j < SIZE; j++){
                if (nodes.get(i * SIZE + j).getMark() != mark){
                    flag = false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }
        return false;
    }
    private boolean checkCol(String mark){
        for (int i = 0; i < SIZE; i++){
            boolean flag = true;
            for (int j = 0; j < SIZE; j++){
                if (nodes.get(j * SIZE + i).getMark() != mark){
                    flag = false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }
        return false;
    }
    private boolean checkCross(String mark){
        boolean flag = true;
        for (int i = 0; i < SIZE; i++){
            if (nodes.get(i * SIZE + i).getMark() != mark){
                flag = false;
                break;
            }
        }
        if (flag){
            return true;
        }
        flag = true;
        for (int i = 0; i < SIZE; i++){
            if (nodes.get(i * SIZE + (SIZE - 1 - i)).getMark() != mark){
                flag = false;
                break;
            }
        }
        if (flag){
            return true;
        }
        return false;
    }
    private boolean checkALL(String mark){
        return checkRow(mark) || checkCol(mark) || checkCross(mark);
    }
    public void run(){
        init();
        initNodes();
        System.out.println("틱택토 게임을 시작합니다.");
        while (true){
            printMap();
            System.out.println(player.getName() + "의 차례입니다. [" + player.getMark() + "]를 놓을 위치를 입력하세요.");
            int location = scanner.nextInt();
            if (location < 1 || location > 9){
                System.out.println("잘못된 위치입니다. 다시 입력하세요.");
                continue;
            }
            if (nodes.get(location - 1).getMark() != null){
                System.out.println("이미 입력된 위치입니다. 다시 입력하세요.");
                continue;
            }
            nodes.get(location - 1).setMark(player.getMark());
            cnt++;
            if (check(player.getMark())){
                System.out.println(player.getName() + "가 이겼습니다.");
                break;
            }
            if (cnt == SIZE * SIZE){
                System.out.println("비겼습니다.");
                break;
            }
            boolean check = checkALL(player.getMark());
            if (check){
                System.out.println(player.getName() + "가 이겼습니다.");
                printMap();
                break;
            }
            player = (player == players.get(0)) ? players.get(1) : players.get(0);
        }
    }
}
public class _09틱텍토 {
    public static void main(String[] args) {
        TicTakToe game = new TicTakToe();
        game.run();
    }
}
