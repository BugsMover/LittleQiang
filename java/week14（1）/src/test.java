import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.RandomAccess;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class test {

	public static void main(String[] args) throws IOException{
		RandomAccessFile file = new RandomAccessFile("e://bufferDome.dat", "rw");
		FileChannel fileChannel = file.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		
		String string = "a1";
		try {
		    int a = Integer.parseInt(string);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		
		byteBuffer.putInt(a);
		byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileChannel.close();
        
        int bytes = fileChannel.read(byteBuffer);
        byteBuffer.flip();
        
        while(byteBuffer.hasRemaining()){
        	System.out.println(byteBuffer.getInt());
        }
	}

}
