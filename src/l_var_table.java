public class l_var_table {// ローカル変数記号表
	private String var_name;
	private int var_type;// 1:char,2:boolean,3:integer
	private String var_min;
	private String var_max;
	private boolean array_type;// arrayか違うか1,0
	private int var_size;// arrayならmax-min+1,それ以外なら1
	private int var_offset;// 変数のオフセット
	private String proc_name;// 手続き名

	// コンストラクタの作成
	public l_var_table(String a, int b, String c, String d, boolean e, int f,
			int g, String h) {
		var_name = a;
		var_type = b;
		var_min = c;
		var_max = d;
		array_type = e;
		var_size = f;
		var_offset = g;
		proc_name = h;
	}

	// ゲッター
	public String getvar_name() {
		return var_name;
	}

	public int getvar_type() {
		return var_type;
	}

	public String getvar_min() {
		return var_min;
	}

	public String getvar_max() {
		return var_max;
	}

	public boolean getarray_type() {
		return array_type;
	}

	public int getvar_size() {
		return var_size;
	}

	public int getvar_offset() {
		return var_offset;
	}

	public String getproc_name() {
		return proc_name;
	}

	// セッター
	public void setvar_name(String name) {
		var_name = name;
	}

	public void setvar_type(int type) {
		var_type = type;
	}

	public void setvar_min(String min) {
		var_min = min;
	}

	public void setvar_max(String max) {
		var_max = max;
	}

	public void setarray_type(boolean type) {
		array_type = type;
	}

	public void setvar_size(int size) {
		var_size = size;
	}

	public void setvar_offset(int offset) {
		var_offset = offset;
	}

	public void setproc_name(String name) {
		proc_name = name;
	}
}
