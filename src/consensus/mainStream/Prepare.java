package consensus.mainStream;

import communication.Sender;
import node.Node;
import util.Log;

public class Prepare {
    private static final int view = 2;

    private static final int height = 3;

    private static final int prePrepareDigest = 5;

    private static final int prepareDigest = 4;

    private static int validPrepare;

    public static void generate(String[] prePrepare){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<prepare").append(",");
        stringBuilder.append(Node.getId()).append(",");
        stringBuilder.append(prePrepare[view]).append(",");
        stringBuilder.append(prePrepare[height]).append(",");
        stringBuilder.append(prePrepare[prePrepareDigest]).append(">");
        Sender.broadcast(stringBuilder.toString());
    }

    public static void process(String prepare){
        String[] strings = prepare.split(",");
        if (strings[view].equals(Node.getView()) && Integer.valueOf(strings[height]) == (Node.getBlockChainHeight() + 1) && strings[prepareDigest].equals(PrePrepare.getDigest())) {
            validPrepare++;
            Log.log(prepare, "prepareLog");
        }
    }

    public static int getValidPrepare() {
        return validPrepare;
    }
}
