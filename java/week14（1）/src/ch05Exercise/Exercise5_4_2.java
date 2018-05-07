package ch05Exercise;
import ch05.CSTreeNode;
/**
 * 2.缂栧啓涓�涓▼搴忓疄鐜帮細鍏堝缓绔嬩竴妫典互瀛╁瓙鍏勫紵閾捐〃瀛樺偍缁撴瀯琛ㄧず鐨勬爲锛岀劧鍚庤緭鍑鸿繖妫垫爲鐨勫厛鏍归亶鍘嗗簭鍒楀拰鍚庢牴閬嶅巻搴忓垪銆�
 * 

 */

public class Exercise5_4_2 {
   
    public CSTreeNode createBiTree() {
		CSTreeNode D = new CSTreeNode('D');
		CSTreeNode E = new CSTreeNode('E');
		CSTreeNode C = new CSTreeNode('C', D, E);
		CSTreeNode B = new CSTreeNode('B', null, C);
		CSTreeNode A = new CSTreeNode('A', B, null);
		return A;
	}
    // 鍏堟牴閬嶅巻鏍戠殑閫掑綊绠楁硶
	public void preRootTraverse(CSTreeNode T) {
		if (T != null) {
			System.out.print(T.getData()); // 璁块棶鏍圭粨鐐�
			preRootTraverse(T.getFirstchild());// 璁块棶瀛╁瓙缁撶偣
			preRootTraverse(T.getNextsibling());// 璁块棶鍏勫紵缁撶偣
		}
	}

	// 鍚庢牴閬嶅巻鏍戠殑閫掑綊绠楁硶
	public void postRootTraverse(CSTreeNode T) {
		if (T != null) {
			postRootTraverse(T.getFirstchild());// 璁块棶瀛╁瓙缁撶偣
			System.out.print(T.getData()); // 璁块棶鏍圭粨鐐�
			postRootTraverse(T.getNextsibling());// 璁块棶鍏勫紵缁撶偣
		}
	}
	public static void main(String[] args) {
		Exercise5_4_2 e = new Exercise5_4_2();
		CSTreeNode root=e.createBiTree();
        		// 璋冭瘯鍏堟牴閬嶅巻
		System.out.println("该树的先根遍历为：");
		e.preRootTraverse(root);

		// 璋冭瘯鍚庢牴閬嶅巻
		System.out.println("\n该树的后根遍历为：");
		e.postRootTraverse(root);

	}
}
