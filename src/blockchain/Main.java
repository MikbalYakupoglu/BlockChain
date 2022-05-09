package blockchain;

public class Main {

        public static void main(String[] args)
        {
            System.out.println("run:");

            Blockchain blockchain = new Blockchain(2);
            blockchain.addBlock(blockchain.newBlock( ""));
            blockchain.addBlock(blockchain.newBlock(""));
            blockchain.addBlock(blockchain.newBlock(""));
            blockchain.addBlock(blockchain.newBlock(""));

            System.out.println(blockchain);


        }
}
