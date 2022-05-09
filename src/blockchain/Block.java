package blockchain;

public class Block {

    private int index;
    private String transaction;
    private long timestamp;
    private String hash;
    private String previousHash;
    private String data;
    private int nonce;

    public Block(int index, long timestamp, String previousHash, String data) {
        this.index = index;
        this.transaction = "transaction"+(index+1);
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.data = data;
        nonce = 0;
        hash = Utils.generateHash(concatFields());
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public String getTransaction(){
        return transaction;
    }

    public int getNonce() {
        return nonce;
    }

    public String concatFields() {
        return index + timestamp + previousHash + data + nonce;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(index+"-"+transaction+"-"+hash+"-"+previousHash+"-");
        return builder.toString();
    }

    public void mineBlock(int difficulty) {
        this.nonce = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            builder.append('0');
        }
        String zeros = builder.toString();
        while (!getHash().substring(0, difficulty).equals(zeros)) {
            this.nonce++;
            this.hash = Utils.generateHash(concatFields());
        }

        System.out.println(+index+ "-" + transaction + "-" + hash + "-" + previousHash + "- has just mined." + "\nHash is: " + hash );
    }

}