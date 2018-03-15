package node;

import consensus.mainStream.prepared.Prepared;
import consensus.signUp.SignUp;
import model.block.Block;
import communication.Reciever;
import init.Initial;
import util.hash.Hash;
import util.simulator.Simulator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点编号从1开始
 */

public class Node {
    //判断节点当前的工作状态，是否需要对外发送prepare消息
    private static boolean switcher = true;

    private static List<Block> blockChain = new ArrayList<>();

    private static int id = 1;

    private static int primary = 1;

    private static int view = 1;

    private static int nodeNums = 4;

    private static String faultyNodeNums = "1";

    private static int threshold = Integer.MAX_VALUE;

    private static List<Block> tmpBlocks;

    public static void main(String[] args) throws IOException {
        Initial.init();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Reciever());
        executorService.execute(new Simulator());
        executorService.execute(new GenerateBlock());
        executorService.execute(new Prepared());
        while (true){
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = stdin.readLine()) != null){
                if (input.equals("show"))
                    System.out.println(blockChain);
                if (input.equals("signUp"))
                    SignUp.generate();
                if (input.startsWith("f:"))
                    setFaultyNodeNums(input.replace("f:" , ""));
                if (input.equals("exit")){
                    Reciever.clean();
                    executorService.shutdown();
                    System.exit(0);
                }
            }
        }
    }

    public static boolean isPrimary(){
        int view = Integer.valueOf(Node.getView());
        int id = Integer.valueOf(Node.getId());
        int n = Integer.valueOf(Node.getNodeNums());
        return id == view % n;
    }

    public static String getLatestHash(){
        if (blockChain.size() == 0) return "non";
        return Hash.hash(blockChain.get(blockChain.size() - 1).toString());
    }

    public static int getBlockChainHeight(){
        return blockChain.size();
    }

    public static void addBlock(Block block){
        blockChain.add(block);
    }

    public static List<Block> getTmpBlocks() {
        return tmpBlocks;
    }

    public static void setTmpBlocks(List<Block> tmpBlocks) {
        Node.tmpBlocks = tmpBlocks;
    }

    public static String getFaultyNodeNums() {
        return faultyNodeNums;
    }

    public static void setFaultyNodeNums(String faultyNodeNums) {
        Node.faultyNodeNums = faultyNodeNums;
    }

    public static int getThreshold() {
        return threshold;
    }

    public static void setThreshold(int threshold) {
        Node.threshold = threshold;
    }

    public static boolean isSwitcher() {
        return switcher;
    }

    public static void setSwitcher(boolean switcher) {
        Node.switcher = switcher;
    }

    public static int getPrimary() {
        return primary;
    }

    public static void setPrimary(int primary) {
        Node.primary = primary;
    }

    public static int getView() {
        return view;
    }

    public static void setView(int view) {
        Node.view = view;
    }

    public static int getNodeNums() {
        return nodeNums;
    }

    public static void setNodeNums(int nodeNums) {
        Node.nodeNums = nodeNums;
    }

    public static int getId() {
        return id;
    }

    public static void setId(String id) {
        id = id;
    }

    public static List<Block> getBlockChain() {
        return blockChain;
    }

    public static void setBlockChain(List<Block> newBlockChain) {
        blockChain = newBlockChain;
    }
}
