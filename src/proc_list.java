public class proc_list {// 手続き情報リスト
	private String proc_name;// 手続き名
	private String para_name;// パラメータの変数名
	private int para_type;// 型の種類
	private int para_offset;// パラメータのオフセット

	// コンストラクタの作成
	public proc_list(String a, String b, int c, int d) {
		proc_name = a;
		para_name = b;
		para_type = c;
		para_offset = d;
	}

	// ゲッター
	public String getproc_name() {
		return proc_name;
	}

	public String getpara_name() {
		return para_name;
	}

	public int getpara_type() {
		return para_type;
	}

	public int getpara_offset() {
		return para_offset;
	}

	// セッター
	public void setproc_name(String name) {
		proc_name = name;
	}

	public void setpara_name(String name) {
		para_name = name;
	}

	public void setpara_type(int type) {
		para_type = type;
	}

	public void setpara_offset(int offset) {
		para_offset = offset;
	}
}
