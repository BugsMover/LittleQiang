package ch02;


/**
 * 
 * 涓嶅甫澶寸粨鐐圭殑鍗曢摼琛ㄥ強鍏跺熀鏈搷浣�
 * 
 */
public class LinkList2 implements IList {

	private Node head;// 鍗曢摼琛ㄧ殑棣栫粨鐐规寚閽�

	// 鏋勯�犲嚱鏁�
	public LinkList2(int maxSize) {
		head = null;
	}

	// 灏嗕竴涓凡缁忓瓨鍦ㄧ殑鍗曢摼琛ㄧ疆鎴愮┖琛�
	public void clear() {
		head = null;
	}

	// 鍒ゆ柇褰撳墠鍗曢摼琛ㄦ槸鍚︿负绌�
	public boolean isEmpty() {
		return head == null;
	}

	// 姹傚崟閾捐〃涓殑鏁版嵁鍏冪礌涓暟骞剁敱鍑芥暟杩斿洖鍏跺��
	public int length() {
		Node p = head;// 鍒濆鍖�,p鎸囧悜棣栫粨鐐�,length涓鸿鏁板櫒
		int length = 0;
		while (p != null) {// 浠庨缁撶偣鍚戝悗鏌ユ壘锛岀洿鍒皃涓虹┖
			p = p.getNext();// 鎸囧悜鍚庣户缁撶偣
			++length;// 闀垮害澧�1
		}
		return length;
	}

	// 璇诲彇鍗曢摼琛ㄤ腑鐨勭i涓暟鎹厓绱�
	public Object get(int i) throws Exception {
		Node p = head;// 鍒濆鍖�,p鎸囧悜棣栫粨鐐�,j涓鸿鏁板櫒
		int j = 0;
		while (p != null && j < i) {// 浠庨缁撶偣鍚戝悗鏌ユ壘锛岀洿鍒皃鎸囧悜绗琲涓厓绱犳垨p涓虹┖
			p = p.getNext();// 鎸囧悜鍚庣户缁撶偣
			++j;// 璁℃暟鍣ㄧ殑鍊煎1
		}
		if (j > i || p == null) // i灏忎簬0鎴栬�呭ぇ浜庤〃闀垮噺1
			throw new Exception("绗�" + i + "涓厓绱犱笉瀛樺湪");// 杈撳嚭寮傚父

		return p.getData();// 杩斿洖鍏冪礌p
	}

	// 鍦ㄥ崟閾捐〃涓i涓暟鎹厓绱犱箣鍓嶆彃鍏ヤ竴涓�间负x鐨勬暟鎹厓绱�
	public void insert(int i, Object x) throws Exception {
		Node s = new Node(x);
		if (i == 0) { // 鎻掑叆浣嶇疆涓鸿〃澶�
			s.setNext(head);
			head = s;
			return;
		}

		Node p = head;
		int j = 0;// 绗琲涓粨鐐瑰墠椹辩殑浣嶇疆
		while (p != null && j < i - 1) {// 瀵绘壘i涓粨鐐圭殑鍓嶉┍
			p = p.getNext();
			++j;
		}
		if (j > i - 1 || p == null)
			throw new Exception("鎻掑叆浣嶇疆涓嶅悎鐞�");

		// 鎻掑叆浣嶇疆涓鸿〃鐨勪腑闂存垨琛ㄥ熬
		s.setNext(p.getNext());
		p.setNext(s);
	}

	// 灏嗙嚎鎬ц〃涓i涓暟鎹厓绱犲垹闄ゃ�傚叾涓璱鍙栧�艰寖鍥翠负锛�0鈮鈮ength()- 1,濡傛灉i鍊间笉鍦ㄦ鑼冨洿鍒欐姏鍑哄紓甯�
	public void remove(int i) throws Exception {
		Node p = head;// 鍒濆鍖杙涓洪缁撶偣,j涓鸿鏁板櫒
		Node q = null; // 鐢ㄦ潵璁板綍p鐨勫墠椹辩粨鐐�
		int j = 0;
		while (p != null && j < i) {// 瀵绘壘i涓粨鐐�
			q = p;
			p = p.getNext();
			++j;// 璁℃暟鍣ㄧ殑鍊煎1
		}
		if (j > i || p == null) // i灏忎簬0鎴栬�呭ぇ浜庤〃闀垮噺1
			throw new Exception("鍒犻櫎浣嶇疆涓嶅悎鐞�");// 杈撳嚭寮傚父

		if (q == null)
			head = null;// 鍒犻櫎棣栫粨鐐�
		else
			q.setNext(p.getNext());// 鍒犻櫎鍏朵粬缁撶偣
	}

	// 瀹炵幇鍒犻櫎鍗曢摼琛ㄤ腑鏁版嵁鍩熷�肩瓑浜巟鐨勭涓�涓粨鐐圭殑鎿嶄綔銆傝嫢鍒犻櫎鎴愬姛锛屽垯杩斿洖琚垹闄ょ粨鐐圭殑浣嶇疆锛涘惁鍒欙紝杩斿洖-1銆�
	public int remove(Object x) {
		Node p = head;// 鍒濆鍖�,p鎸囧悜棣栫粨鐐�
		Node q=null;  //q鐢ㄦ潵璁板綍p鐨勫墠椹辩粨鐐�
        int j = 0; //j涓鸿鏁板櫒
		while (p != null && !p.getData().equals(x)) {// 浠庡崟閾捐〃涓殑棣栫粨鐐瑰厓绱犲紑濮嬫煡鎵撅紝鐩村埌p.getData()鎸囧悜鍏冪礌x鎴栧埌杈惧崟閾捐〃鐨勮〃灏�
			 q=p;
             p = p.getNext();// 鎸囧悜涓嬩竴涓厓绱�
			 ++j;// 璁℃暟鍣ㄧ殑鍊煎1
		 }
		if (p!=null&&q==null) //鍒犻櫎鐨勬槸鍗曢摼琛ㄤ腑鐨勯缁撶偣
             head=p.getNext();
        else if (p != null) {// 鍒犻櫎鐨勬槸鍗曢摼琛ㄤ腑鐨勯潪棣栫粨鐐�
			   q.setNext(p.getNext());
		     }
        else
			 return -1;//鍊间负x鐨勭粨鐐瑰湪鍗曢摼琛ㄤ腑涓嶅瓨鍦�
	    return j;
    }


	// 鍦ㄥ甫澶寸粨鐐圭殑鍗曢摼琛ㄤ腑鏌ユ壘鍊间负x鐨勫厓绱狅紝濡傛灉鎵惧埌锛屽垯鍑芥暟杩斿洖璇ュ厓绱犲湪绾挎�ц〃涓殑浣嶇疆锛屽惁鍒欒繑鍥�-1
	public int indexOf(Object x) {
		Node p = head;// 鍒濆鍖�,p鎸囧悜棣栫粨鐐�,j涓鸿鏁板櫒
		int j = 0;
		while (p != null && !p.getData().equals(x)) {// 浠庡崟閾捐〃涓殑棣栫粨鐐瑰厓绱犲紑濮嬫煡鎵撅紝鐩村埌p.getData()鎸囧悜鍏冪礌x鎴栧埌杈惧崟閾捐〃鐨勮〃灏�
			p = p.getNext();// 鎸囧悜涓嬩竴涓厓绱�
			++j;// 璁℃暟鍣ㄧ殑鍊煎1
		}
		if (p != null)// 濡傛灉p鎸囧悜琛ㄤ腑鐨勬煇涓�鍏冪礌
			return j;// 杩斿洖x鍏冪礌鍦ㄩ『搴忚〃涓殑浣嶇疆
		else
			return -1;// x鍏冪礌涓嶅湪椤哄簭琛ㄤ腑
	}

	// 杈撳嚭绾挎�ц〃涓殑鏁版嵁鍏冪礌
	public void display() {
		Node node = head;// 鍙栧嚭甯﹀ご缁撶偣鐨勫崟閾捐〃涓殑棣栫粨鐐瑰厓绱�
		while (node != null) {
			System.out.print(node.getData() + " ");// 杈撳嚭鏁版嵁鍏冪礌鐨勫��
			node = node.getNext();// 鍙栦笅涓�涓粨鐐�
		}
		System.out.println();// 鎹㈣
	}

}
