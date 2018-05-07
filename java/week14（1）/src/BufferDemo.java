import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {

	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("H:\\bufferDemo.dat", "rw");
		
		FileChannel fileChannel = file.getChannel(); //���ļ���дͨ��
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);  //����һ��32��Byte��Buffer
		
		/*
		byteBuffer.putInt(1000); //дһ��������Buffer
		byteBuffer.putInt(1001); //дһ��������Buffer
		byteBuffer.putInt(1002); //дһ��������Buffer
		byteBuffer.flip();		//���°�position��Ϊ0
		fileChannel.write(byteBuffer);  //��Buffer�е�����д���ļ�
		fileChannel.close();
		*/
		
		int bytes = fileChannel.read(byteBuffer); //�����ݴ��ļ��ж������١�д�롿Buffer
		byteBuffer.flip();
		
		while(byteBuffer.hasRemaining()) {
			System.out.println(byteBuffer.getInt());
		}		
		
		
		/*
		int bytesRead = fileChannel.read(byteBuffer); //�����ݴ��ļ��ж�ȡ����д��Buffer
		
		while (bytesRead != -1) {   //��û�ж����ļ�ĩβ
			System.out.println("read " + bytesRead + " bytes.");
			byteBuffer.flip();		//��Buffer�ӡ�д��ģʽ��ת�롰����ģʽ
			
			while(byteBuffer.hasRemaining()) {  //Buffer�л���δ��������
				byte theByte = byteBuffer.get();  //get->��Buffer�ж�ȡһ��Byte
				System.out.println("Byte: " + theByte);				
			}
			byteBuffer.clear();		//��ȫ������Buffer�е����ݣ�ͨ��clear�ӡ�����ģʽ��ת�롰д��ģʽ
			bytesRead = fileChannel.read(byteBuffer); //�����ݴ��ļ��ж�ȡ����д��Buffer
		}		
		*/
	}

}
