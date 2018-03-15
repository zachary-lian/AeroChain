package consensus.mainStream.prePrepare;

import model.block.Block;

public class PrePrepareModel {
    int primary;

    int view;

    int height;

    Block block;

    String digest;

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public PrePrepareModel() {}

    @Override
    public String toString() {
        return "PrePrepareModel{" +
                "primary=" + primary +
                ", view=" + view +
                ", height=" + height +
                ", block=" + block +
                ", digest='" + digest + '\'' +
                '}';
    }
}
