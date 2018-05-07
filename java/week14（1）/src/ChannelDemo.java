import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ChannelDemo {

	public static void main(String[] args) throws IOException {
		RandomAccessFile source = 
				new RandomAccessFile("H:\\source.txt", "rw");
		RandomAccessFile destination = 
				new RandomAccessFile("H:\\destination.txt", "rw");
		
		FileChannel sourceFileChannel = source.getChannel();
		FileChannel destinationFileChannel = destination.getChannel();
		
		destinationFileChannel.transferFrom(
				sourceFileChannel, 0, sourceFileChannel.size());

	}

}
