import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {

	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("H:\\bufferDemo.dat", "rw");
		
		FileChannel fileChannel = file.getChannel(); //打开文件读写通道
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);  //申请一个32个Byte的Buffer
		
		/*
		byteBuffer.putInt(1000); //写一个整数到Buffer
		byteBuffer.putInt(1001); //写一个整数到Buffer
		byteBuffer.putInt(1002); //写一个整数到Buffer
		byteBuffer.flip();		//重新把position置为0
		fileChannel.write(byteBuffer);  //把Buffer中的内容写入文件
		fileChannel.close();
		*/
		
		int bytes = fileChannel.read(byteBuffer); //把数据从文件中读出，再【写入】Buffer
		byteBuffer.flip();
		
		while(byteBuffer.hasRemaining()) {
			System.out.println(byteBuffer.getInt());
		}		
		
		
		/*
		int bytesRead = fileChannel.read(byteBuffer); //把数据从文件中读取，并写入Buffer
		
		while (bytesRead != -1) {   //还没有读到文件末尾
			System.out.println("read " + bytesRead + " bytes.");
			byteBuffer.flip();		//把Buffer从“写”模式，转入“读”模式
			
			while(byteBuffer.hasRemaining()) {  //Buffer中还有未读的数据
				byte theByte = byteBuffer.get();  //get->从Buffer中读取一个Byte
				System.out.println("Byte: " + theByte);				
			}
			byteBuffer.clear();		//完全读完了Buffer中的内容，通过clear从“读”模式，转入“写”模式
			bytesRead = fileChannel.read(byteBuffer); //把数据从文件中读取，并写入Buffer
		}		
		*/
	}

}
