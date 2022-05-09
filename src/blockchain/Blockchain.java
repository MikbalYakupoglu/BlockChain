package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private final int difficulty;
    private List<Block> blocks = new ArrayList<>();

    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
        addBlock(createFirstBlock());
    }

    private Block createFirstBlock() {
        return new Block(0, System.currentTimeMillis(), null, "");
    }

    public Block lastBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public Block newBlock(String data) {
        return new Block(lastBlock().getIndex() + 1, System.currentTimeMillis(), lastBlock().getHash(), data);
    }

    public void addBlock(Block b) {
        if (b != null) {
            b.mineBlock(difficulty);
            blocks.add(b);
        }
    }

    public boolean validateFirstBlock() {
        Block firstBlock = blocks.get(0);

        if (firstBlock.getIndex() != 0
                || firstBlock.getPreviousHash() != null
                || firstBlock.getHash() == null
                || !Utils.generateHash(firstBlock.concatFields()).equals(firstBlock.getHash())) {
            return false;
        }

        return true;
    }

    public boolean validateNewBlock(Block newBlock, Block previousBlock) {
        if (newBlock != null && previousBlock != null) {

            if (newBlock.getPreviousHash() == null
                    || !newBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }

            if (newBlock.getHash() == null
                    || !Utils.generateHash(newBlock.concatFields()).equals(newBlock.getHash())) {
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean validateBlockchain() {
        if (!validateFirstBlock()) {
            return false;
        }

        for (int i = 1; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);
            Block previousBlock = blocks.get(i - 1);

            if (!validateNewBlock(currentBlock, previousBlock)) {
                return false;
            }
        }

        return true;
    }

    public int calculateMiningReward(){
        return blocks.size() * 100;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\n\nBLOCKCHAIN:\n");
        for (Block block : blocks){
            builder.append(block.getIndex()+"-"+block.getTransaction()+"-"+block.getHash()+"-"+block.getPreviousHash()+"- has just mined.");
            builder.append("\n");
        }

        builder.append("\n\nMiner's Reward: "+ calculateMiningReward());
        builder.append("\nBlockchain valid: " + validateBlockchain());

        builder.append("\n\nThe block chain:\n");
        builder.append("{\nblockChain:[\n");
        for (Block block : blocks) {
            builder.append("{\n");
            builder.append("id:"+block.getIndex());
            builder.append("\nnonce:"+block.getNonce());
            builder.append("\ntimeStamp:"+block.getTimestamp());
            builder.append("\nhash:"+block.getHash());
            builder.append("\npreviousHash:"+block.getPreviousHash());
            builder.append("\ntransaction:"+block.getTransaction());
            builder.append("\n},\n");
        }
        builder.append("]}");
        return builder.toString();
    }



}