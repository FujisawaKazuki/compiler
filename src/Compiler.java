import java.io.*;
import java.util.LinkedList;

public class Compiler {
	private static String ziked;
	private static String ziku;// 字句
	private static String c_ziku;
	private static String cp_ziku;
	private static String typed;
	private static String token;// トークン
	private static String c_token;
	private static String cp_token;
	private static String line;// 読み込んだ文字列
	private static String linee;
	private static String nLined;
	private static String nLine;// 行数
	private static String c_nLine;
	private static String cp_nLine;
	private static String assign_varname;// 代入文の左辺の変数名
	private static String instant_varname1;// 代入文と因子の変数で必要
	private static String instant_varname2;// 変数の添字が変数の時必要
	private static String instant_varname3;// 変数の添字が変数の時必要
	private static String instant_paraname;// 代入文と因子のパラメータで必要
	private static String procedure_name;// 手続き名
	private static int const_num = 0;// 文字列の数
	private static int offset = 0;// SUBA =○
	private static int l_counter = 0;
	private static int g_counter = 0;
	private static int g_offset = 0;
	private static int g_size = 0;
	private static boolean gl_flag = false;// グローバルフラグfalse:ローカル
	private static boolean index_flag = false;// 添字フラグfalse:添字なし
	private static boolean index_flag1 = false;// 添字フラグtrue:現在添字の中
	private static boolean index_flag2 = false;// 添字フラグtrue:現在添字の中の中
	private static boolean integer_mflag = false;// true:-
	private static boolean output_flag = false;// 出力文内ならtrue
	private static boolean char_flg = false;// char型の配列型のデータならtrue、出力文で使用
	private static int gl_label = 0;// ラベル
	private static l_var_table Var_l = new l_var_table("", 0, "0", "0", false,
			1, 0, "");
	private static g_var_table Var_g = new g_var_table("", 0, "0", "0", false,
			1, 0);
	private static proc_list Proc = new proc_list("", "", 0, 2);
	private static LinkedList<String> array1 = new LinkedList<String>();
	private static LinkedList<String> array2 = new LinkedList<String>();
	private static LinkedList<String> array3 = new LinkedList<String>();
	private static LinkedList<String> tmp1 = new LinkedList<String>();
	private static LinkedList<String> tmp2 = new LinkedList<String>();
	private static LinkedList<String> tmp3 = new LinkedList<String>();
	private static LinkedList<String> const_tmp = new LinkedList<String>();
	private static LinkedList<l_var_table> l_var_table = new LinkedList<l_var_table>();
	private static LinkedList<g_var_table> g_var_table = new LinkedList<g_var_table>();
	private static LinkedList<proc_list> proc_list = new LinkedList<proc_list>();
	private static FileWriter out;
	private static BufferedWriter bw;

	private static void scan() {
		ziku = "";
		c_ziku = "";
		ziku = array1.removeFirst();
		c_ziku = ziku;
		token = "";
		c_token = "";
		token = array2.removeFirst();
		c_token = token;
		nLine = "";
		c_nLine = "";
		nLine = array3.removeFirst();
		c_nLine = nLine;
	}

	private static void bcan() {
		array1.addFirst(c_ziku);
		array2.addFirst(c_token);
		array3.addFirst(c_nLine);
	}

	static int make_label() throws IOException {
		return gl_label++;
	}

	private static void syntax_err() {
		System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
				+ "syntax err");
		System.exit(-1);
	}

	private static void program_st() throws IOException {// プログラム
		scan();
		name();
		scan();
		if (!token.equals("SLPAREN")) {
			syntax_err();
		}
		scan();
		nameseq_st();
		scan();
		if (!token.equals("SRPAREN")) {
			syntax_err();
		}
		scan();
		if (!token.equals("SSEMICOLON")) {
			syntax_err();
		}
		scan();
		if ((token.equals("SVAR")) || (token.equals("SPROCEDURE"))) {
			block_st();
			bw.write("MAIN\tSTART\n");
			bw.write("\tLAD\tGR6,0\n");
			bw.write("\tLAD\tGR7,LIBBUF\n");
			bw.write("\tLAD\tGR3,GV\n");
			scan();
		}
		if (!token.equals("SBEGIN")) {
			syntax_err();
		}
		compound_st();
		bw.write("\tRET\n");
		bw.write(";;;;;;;;;library buffer;;;;;;;;;\n");
		bw.write("LIBBUF\tDS\t256\n");
		if (g_var_table.size() > 0) {// DS
			bw.write(";;;;;;;;;GLOBAL;;;;;;;;;;\n");
			bw.write("GV\tDS\t0\n");
			for (int i = 0; i < g_var_table.size(); i++) {
				bw.write(g_var_table.get(i).getvar_name() + "\tDS\t"
						+ g_var_table.get(i).getvar_size() + "\n");
			}
		}
		if (const_tmp.size() > 0) {// DC
			bw.write(";;;;;;;;;CONST;;;;;;;;;;\n");
			for (int j = 0; j < const_tmp.size(); j++) {
				bw.write("ST" + j + "\tDC\t" + const_tmp.get(j) + "\n");
			}
		}
		bw.write("\tEND\n");
		scan();
		if (!token.equals("SDOT")) {
			syntax_err();
		} else {
			System.out.println("OK");
		}
	}

	private static void block_st() throws IOException {// ブロック
		if (token.equals("SVAR")) {
			gl_flag = true; // グローバル変数の宣言
			scan();
			varseq_st();
			scan();
		}
		if (token.equals("SPROCEDURE")) {
			subprogramseq_st();
		}
		bcan();
	}

	private static void varseq_st() {// 変数宣言の並び
		int type;
		String past, now;
		varnameseq_st();
		scan();
		if (!token.equals("SCOLON")) {
			syntax_err();
		}
		scan();
		type = type_st();
		if (!gl_flag) {// ローカル変数
			for (int i = 0; i < tmp1.size(); i++) {
				if (i == 0) {
					l_var_table.add(new l_var_table(tmp1.get(i), type, Var_l
							.getvar_min(), Var_l.getvar_max(), Var_l
							.getarray_type(), Var_l.getvar_size(), -1,
							procedure_name));
					l_counter++;
					offset = offset + Var_l.getvar_size();
				}
				if (i >= 1) {
					Var_l.setvar_offset(l_var_table.get(l_counter - 1)
							.getvar_offset()
							- l_var_table.get(l_counter - 1).getvar_size());
					l_var_table.add(new l_var_table(tmp1.get(i), type, Var_l
							.getvar_min(), Var_l.getvar_max(), Var_l
							.getarray_type(), Var_l.getvar_size(), Var_l
							.getvar_offset(), procedure_name));
					l_counter++;
					offset = offset + Var_l.getvar_size();
				}
			}
		} else {// グローバル変数
			for (int i = 0; i < tmp1.size(); i++) {
				if (i == 0) {
					if (g_counter != 0) {
						Var_g.setvar_offset(g_offset + g_size);
					} else {
						Var_g.setvar_offset(0);
					}
					g_counter++;
					g_var_table.add(new g_var_table(tmp1.get(i), type, Var_g
							.getvar_min(), Var_g.getvar_max(), Var_g
							.getarray_type(), Var_g.getvar_size(), Var_g
							.getvar_offset()));
				}
				if (i >= 1) {
					Var_g.setvar_offset(g_var_table.get(i - 1).getvar_offset()
							+ g_var_table.get(i - 1).getvar_size());
					g_counter++;
					g_var_table.add(new g_var_table(tmp1.get(i), type, Var_g
							.getvar_min(), Var_g.getvar_max(), Var_g
							.getarray_type(), Var_g.getvar_size(), Var_g
							.getvar_offset()));
				}
			}
		}
		g_size = 0;
		if (g_counter != 0) {
			g_offset = g_var_table.get(g_counter - 1).getvar_offset();
			g_size = g_var_table.get(g_counter - 1).getvar_size();
		}
		tmp1.clear();
		scan();
		if (!token.equals("SSEMICOLON")) {
			syntax_err();
		}
		scan();
		if (token.equals("SIDENTIFIER")) {
			do {
				varnameseq_st();
				for (int k = 0; k < tmp1.size(); k++) {
					now = tmp1.get(k);
					if (!gl_flag) {
						for (int j = 0; j < l_var_table.size(); j++) {
							past = l_var_table.get(j).getvar_name();
							if (past.equals(now)) {
								System.err.println("Line" + "\t" + nLine + "\t"
										+ ":" + "\t"
										+ "variable is defined duplicately.");// 3
								System.exit(-1);
							}
						}
					} else {
						for (int j = 0; j < g_var_table.size(); j++) {
							past = g_var_table.get(j).getvar_name();
							if (past.equals(now)) {
								System.err.println("Line" + "\t" + nLine + "\t"
										+ ":" + "\t"
										+ "variable is defined duplicately.");// 3
								System.exit(-1);
							}
						}
					}
				}
				scan();
				if (!token.equals("SCOLON")) {
					syntax_err();
				}
				scan();
				type = type_st();
				if (gl_flag) {// グローバル変数
					for (int i = 0; i < tmp1.size(); i++) {
						if (i == 0) {
							Var_g.setvar_offset(g_offset + g_size);
							g_counter++;
							g_var_table.add(new g_var_table(tmp1.get(i), type,
									Var_g.getvar_min(), Var_g.getvar_max(),
									Var_g.getarray_type(), Var_g.getvar_size(),
									Var_g.getvar_offset()));
						}
						if (i >= 1) {
							Var_g.setvar_offset(g_var_table.get(g_counter - 1)
									.getvar_offset()
									+ g_var_table.get(g_counter - 1)
											.getvar_size());
							g_counter++;
							g_var_table.add(new g_var_table(tmp1.get(i), type,
									Var_g.getvar_min(), Var_g.getvar_max(),
									Var_g.getarray_type(), Var_g.getvar_size(),
									Var_g.getvar_offset()));
						}
					}
				}
				if (g_counter != 0) {
					g_offset = g_var_table.get(g_counter - 1).getvar_offset();
					g_size = g_var_table.get(g_counter - 1).getvar_size();
				}
				tmp1.clear();
				scan();
				if (!token.equals("SSEMICOLON")) {
					syntax_err();
				}
				scan();
			} while (token.equals("SIDENTIFIER"));
		}
		bcan();
	}

	private static void varnameseq_st() {// 変数名の並び
		varname();
		tmp1.addLast(ziku);
		scan();
		if (token.equals("SCOMMA")) {
			do {
				scan();
				varname();
				if (tmp1.contains(ziku)) {
					System.err.println("Line" + "\t" + nLine + "\t" + ":"
							+ "\t" + "variable is defined duplicately.");// 3
					System.exit(-1);
				}
				tmp1.addLast(ziku);
				scan();
			} while (token.equals("SCOMMA"));
		}
		bcan();
	}

	private static void varname() {// 変数名
		if (!token.equals("SIDENTIFIER")) {
			syntax_err();
		}

	}

	static int type_st() {// 型
		int type1, type2;
		if (token.equals("SARRAY")) {
			if (!gl_flag) {
				Var_l.setarray_type(true);
			} else {
				Var_g.setarray_type(true);
			}
			type1 = array_st();
			return type1;
		} else {
			if (!gl_flag) {
				Var_l.setarray_type(false);
				Var_l.setvar_size(1);
			} else {
				Var_g.setarray_type(false);
				Var_g.setvar_size(1);
			}

			type2 = normal_st();
			return type2;
		}
	}

	static int array_st() {// 配列型
		scan();
		if (!token.equals("SLBRACKET")) {
			syntax_err();
		}
		scan();
		integer();
		if (!gl_flag) {
			if (integer_mflag) {
				Var_l.setvar_min("-" + ziku);
			} else {
				Var_l.setvar_min(ziku);
			}
			scan();
			if (!token.equals("SRANGE")) {
				syntax_err();
			}
			scan();
			integer();
			if (integer_mflag) {
				Var_l.setvar_max("-" + ziku);
			} else {
				Var_l.setvar_max(ziku);
			}
			if (Var_l.getvar_max().compareTo(Var_l.getvar_min()) < 0) {
				System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
						+ "index in array : min is bigger than max.");
				System.exit(-1);
			}
		} else {
			if (integer_mflag) {
				Var_g.setvar_min("-" + ziku);
			} else {
				Var_g.setvar_min(ziku);
			}
			scan();
			if (!token.equals("SRANGE")) {
				syntax_err();
			}
			scan();
			integer();
			if (integer_mflag) {
				Var_g.setvar_max("-" + ziku);
			} else {
				Var_g.setvar_max(ziku);
			}
			if (Var_g.getvar_max().compareTo(Var_g.getvar_min()) < 0) {
				System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
						+ "index in array : min is bigger than max.");
				System.exit(-1);
			}
		}
		scan();
		if (!token.equals("SRBRACKET")) {
			syntax_err();
		}
		scan();
		if (!token.equals("SOF")) {
			syntax_err();
		}
		scan();
		normal_st();
		if (!gl_flag) {
			int max = Integer.valueOf(Var_l.getvar_max()).intValue();
			int min = Integer.valueOf(Var_l.getvar_min()).intValue();
			Var_l.setvar_size(max - min + 1);
		} else {
			int max = Integer.valueOf(Var_g.getvar_max()).intValue();
			int min = Integer.valueOf(Var_g.getvar_min()).intValue();
			Var_g.setvar_size(max - min + 1);
		}
		return normal_st();
	}

	static int normal_st() {// 標準型
		if (token.equals("SINTEGER")) {
			return 3;
		} else if (token.equals("SCHAR")) {
			return 1;
		} else if (token.equals("SBOOLEAN")) {
			return 2;
		} else {
			syntax_err();
			return -1;
		}
	}

	private static void subprogramseq_st() throws IOException {// 副プログラム宣言群
		gl_flag = false;
		bw.write("CASL\tSTART\n");
		bw.write("\tJUMP\tMAIN\n");
		bw.write("\tRET\n");
		bw.write("\tEND\n");
		subprogram_st();
		scan();
		if (!token.equals("SSEMICOLON")) {
			syntax_err();
		}
		scan();
		if (token.equals("SPROCEDURE")) {
			do {
				subprogram_st();
				scan();
				if (!token.equals("SSEMICOLON")) {
					syntax_err();
				}
				scan();
			} while (token.equals("SPROCEDURE"));
		}
		gl_flag = true;// 副プログラムの変数宣言完了
	}

	private static void subprogram_st() throws IOException {// 副プログラム宣言
		if (token.equals("SPROCEDURE")) {
			subprogramhead_st();
			String s = procedure_name;
			s = s + "        ";// 8個分の空白を入れる。
			bw.write(";;;;;;" + procedure_name + "\tbegin;;;;;;\n");
			bw.write(s.toUpperCase().substring(0, 7) + "\tSTART\n");
			bw.write("\tPUSH\t0,GR5\n");
			bw.write("\tLD\tGR5,GR8\n");
			scan();
		}
		if (token.equals("SVAR")) {
			scan();
			varseq_st();
			scan();
		}
		bw.write("\tSUBA\tGR8,=" + offset + "\n");
		offset = 0;
		if (token.equals("SBEGIN")) {
			compound_st();
			bw.write("\tLAD\tGR8,1,GR5\n");
			bw.write("\tLD\tGR5,0,GR5\n");
			bw.write("\tRET\n");
			if (const_tmp.size() > 0) {// DC
				bw.write(";;;;;;;;;CONST;;;;;;;;;;\n");
				for (int j = 0; j < const_tmp.size(); j++) {
					bw.write("ST" + j + "\tDC\t" + const_tmp.get(j) + "\n");
				}
			}
			bw.write("\tEND\n");
			scan();
		}
		bcan();
		bw.write(";;;;;;" + procedure_name + "\tend;;;;;;\n");
	}

	private static void subprogramhead_st() throws IOException {// 副プログラム頭部
		if (!token.equals("SPROCEDURE")) {
			syntax_err();
		}
		scan();
		procedure_name = "";
		name();
		tmp3.add(ziku);
		procedure_name = ziku;
		scan();
		if (token.equals("SLPAREN")) {
			instantpara_st();
			scan();
		} else {
			proc_list.add(new proc_list(procedure_name, "", 0, 0));
		}
		if (!token.equals("SSEMICOLON")) {
			syntax_err();
		}
	}

	private static void instantpara_st() {// 仮パラメータ
		if (!token.equals("SLPAREN")) {
			syntax_err();
		}
		scan();
		instantparaseq_st();
		scan();
		if (!token.equals("SRPAREN")) {
			syntax_err();
		}
	}

	private static void instantparaseq_st() {// 仮パラメータの並び
		int type;
		instantnameseq_st();
		scan();
		if (!token.equals("SCOLON")) {
			syntax_err();
		}
		scan();
		type = normal_st();
		int para_counter = tmp2.size() - 1;
		for (int j = 0; j < tmp2.size(); j++) {
			Proc.setpara_offset(2 + para_counter);
			para_counter--;
			proc_list.add(new proc_list(procedure_name, tmp2.get(j), type, Proc
					.getpara_offset()));
		}
		tmp2.clear();
	}

	private static void instantname() {// 仮パラメータ名
		if (!token.equals("SIDENTIFIER")) {
			syntax_err();
		}
	}

	private static void instantnameseq_st() {// 仮パラメータ名の並び
		instantname();
		tmp2.addLast(ziku);
		scan();
		if (token.equals("SCOMMA")) {
			do {
				scan();
				instantname();
				tmp2.addLast(ziku);
				scan();
			} while (token.equals("SCOMMA"));
		}
		bcan();
	}

	private static void st() throws IOException {// 文
		if (token.equals("SIF")) {
			if_st();
		} else if (token.equals("SWHILE")) {
			while_st();
		} else {
			basic_st();
		}
	}

	private static void if_st() throws IOException {// if文
		int type;
		int alab, blab;
		bw.write(";;;;if;;;;\n");
		scan();
		type = exp_st();
		if (type != 2) {
			System.err
					.println("Line"
							+ "\t"
							+ nLine
							+ "\t"
							+ ":"
							+ "\t"
							+ "invalid type is used in conditionnal expression at if statement.");// 13
			System.exit(-1);
		}
		alab = make_label();
		bw.write("\tLAD\tGR2,0\n");
		bw.write("\tCPA\tGR1,GR2\n");
		bw.write("\tJZE\tL" + alab + "\n");
		scan();
		if (!token.equals("STHEN")) {
			syntax_err();
		}
		scan();
		compound_st();
		scan();
		if (token.equals("SELSE")) {
			blab = make_label();
			bw.write("\tJUMP\tL" + blab + "\n");
			bw.write("L" + alab + "\tNOP\n");
			scan();
			compound_st();
			bw.write("L" + blab + "\tNOP\n");
			scan();
		} else {
			bw.write("L" + alab + "\tNOP\n");//
		}
		bcan();
	}

	private static void while_st() throws IOException {// while文
		int type;
		int alab, blab;
		alab = make_label();
		bw.write("L" + alab + "\tNOP\n");
		scan();
		type = exp_st();
		if (type != 2) {
			System.err
					.println("Line"
							+ "\t"
							+ nLine
							+ "\t"
							+ ":"
							+ "\t"
							+ "invalid type is used in conditionnal expression at while statement.");// 13
			System.exit(-1);
		}
		bw.write("\tLAD\tGR2,0\n");
		bw.write("\tCPA\tGR1,GR2\n");
		blab = make_label();
		bw.write("\tJZE\tL" + blab + "\n");
		scan();
		if (!token.equals("SDO")) {
			syntax_err();
		}
		scan();
		st();
		bw.write("\tJUMP\tL" + alab + "\n");
		bw.write("L" + blab + "\tNOP\n");
	}

	private static void basic_st() throws IOException {// 基本文
		if (token.equals("SIDENTIFIER")) {
			cp_ziku = ziku;
			cp_token = token;
			cp_nLine = nLine;
			scan();
			if ((token.equals("SASSIGN")) || (token.equals("SLBRACKET"))) {
				bcan();
				array1.addFirst(cp_ziku);
				array2.addFirst(cp_token);
				array3.addFirst(cp_nLine);
				scan();
				assign_st();
			} else {
				bcan();
				array1.addFirst(cp_ziku);
				array2.addFirst(cp_token);
				array3.addFirst(cp_nLine);
				scan();
				callingpro_st();
			}
		} else if (token.equals("SREADLN")) {
			input_st();
		} else if (token.equals("SWRITELN")) {
			output_st();
		} else {
			compound_st();
		}
	}

	private static void compound_st() throws IOException {// 複合文
		if (!token.equals("SBEGIN")) {
			syntax_err();
		}
		scan();
		stseq();
		scan();
		if (!token.equals("SEND")) {
			syntax_err();
		}
	}

	private static void stseq() throws IOException {// 文の並び
		st();
		scan();
		if (token.equals("SSEMICOLON")) {
			do {
				scan();
				st();
				scan();
			} while (token.equals("SSEMICOLON"));
		}
		bcan();
	}

	static int exp_st() throws IOException {// 式 1:char、2:boolean、3:integer
		int type1, type2;
		int alab, blab;
		String symbol = "";
		type1 = simp_st();
		scan();
		if ((token.equals("SEQUAL")) || (token.equals("SNOTEQUAL"))
				|| (token.equals("SLESS")) || (token.equals("SLESSEQUAL"))
				|| (token.equals("SGREAT")) || (token.equals("SGREATEQUAL"))) {
			symbol = token;
			bw.write("\tPUSH\t0,GR1\n");
			scan();
			type2 = simp_st();
			bw.write("\tPOP\tGR2\n");
			bw.write("\tCPA\tGR2,GR1\n");
			if (type1 != type2) {
				System.err
						.println("Line"
								+ "\t"
								+ nLine
								+ "\t"
								+ ":"
								+ "\t"
								+ "different type operand is used for relation operator.");// 9
				System.exit(-1);
			}
			if (symbol.equals("SEQUAL")) {
				alab = make_label();
				bw.write("\tJNZ\tL" + alab + "\n");
				blab = make_label();
				bw.write("\tLAD\tGR1,1\n");
				bw.write("\tJUMP\tL" + blab + "\n");
				bw.write("L" + alab + "\tLAD\tGR1,0\n");
				bw.write("L" + blab + "\tNOP\n");
			} else if (symbol.equals("SNOTEQUAL")) {
				alab = make_label();
				bw.write("\tJZE\tL" + alab + "\n");
				blab = make_label();
				bw.write("\tLAD\tGR1,1\n");
				bw.write("\tJUMP\tL" + blab + "\n");
				bw.write("L" + alab + "\tLAD\tGR1,0\n");
				bw.write("L" + blab + "\tNOP\n");
			} else if (symbol.equals("SLESS")) {
				alab = make_label();
				bw.write("\tJZE\tL" + alab + "\n");
				bw.write("\tJPL\tL" + alab + "\n");
				blab = make_label();
				bw.write("\tLAD\tGR1,1\n");
				bw.write("\tJUMP\tL" + blab + "\n");
				bw.write("L" + alab + "\tLAD\tGR1,0\n");
				bw.write("L" + blab + "\tNOP\n");
			} else if (symbol.equals("SLESSEQUAL")) {
				alab = make_label();
				bw.write("\tJPL\tL" + alab + "\n");
				blab = make_label();
				bw.write("\tLAD\tGR1,1\n");
				bw.write("\tJUMP\tL" + blab + "\n");
				bw.write("L" + alab + "\tLAD\tGR1,0\n");
				bw.write("L" + blab + "\tNOP\n");
			} else if (symbol.equals("SGREAT")) {
				alab = make_label();
				bw.write("\tJZE\tL" + alab + "\n");
				bw.write("\tJMI\tL" + alab + "\n");
				blab = make_label();
				bw.write("\tLAD\tGR1,1\n");
				bw.write("\tJUMP\tL" + blab + "\n");
				bw.write("L" + alab + "\tLAD\tGR1,0\n");
				bw.write("L" + blab + "\tNOP\n");
			} else if (symbol.equals("SGREATEQUAL")) {
				alab = make_label();
				bw.write("\tJMI\tL" + alab + "\n");
				blab = make_label();
				bw.write("\tLAD\tGR1,1\n");
				bw.write("\tJUMP\tL" + blab + "\n");
				bw.write("L" + alab + "\tLAD\tGR1,0\n");
				bw.write("L" + blab + "\tNOP\n");
			}
			scan();
			bcan();
			return 2;
		} else {
			bcan();
			return type1;
		}
	}

	static int simp_st() throws IOException {// 単純式
		int type1, type2;
		boolean minus_flg = false;
		if ((token.equals("SPLUS")) || (token.equals("SMINUS"))) {
			if (token.equals("SMINUS")) {
				minus_flg = true;
			}
			scan();
		}
		type1 = item_st();
		if (minus_flg) {
			bw.write("\tLAD\tGR2,0\n");
			bw.write("\tSUBA\tGR2,GR1\n");
			bw.write("\tLD\tGR1,GR2\n");
		}
		scan();
		if ((token.equals("SPLUS")) || (token.equals("SMINUS"))) {
			do {
				if (token.equals("SPLUS")) {
					bw.write("\tPUSH\t0,GR1\n");
					scan();
					cp_ziku = ziku;
					cp_token = token;
					cp_nLine = nLine;
					type2 = item_st();
					bw.write("\tPOP\tGR2\n");
					bw.write("\tADDA\tGR1,GR2\n");
					array1.addFirst(cp_ziku);
					array2.addFirst(cp_token);
					array3.addFirst(cp_nLine);
					scan();
					if ((type2 != 3) || (type1 != type2)) {
						System.err
								.println("Line"
										+ "\t"
										+ nLine
										+ "\t"
										+ ":"
										+ "\t"
										+ "type is conflicting between operator and operand.");// 8
						System.exit(-1);
					}
					scan();
				} else if (token.equals("SMINUS")) {
					bw.write("\tPUSH\t0,GR1\n");
					scan();
					cp_ziku = ziku;
					cp_token = token;
					cp_nLine = nLine;
					type2 = item_st();
					bw.write("\tPOP\tGR2\n");
					bw.write("\tSUBA\tGR2,GR1\n");
					bw.write("\tLD\tGR1,GR2\n");
					array1.addFirst(cp_ziku);
					array2.addFirst(cp_token);
					array3.addFirst(cp_nLine);
					scan();
					if ((type2 != 3) || (type1 != type2)) {
						System.err
								.println("Line"
										+ "\t"
										+ nLine
										+ "\t"
										+ ":"
										+ "\t"
										+ "type is conflicting between operator and operand.");// 8
						System.exit(-1);
					}
					scan();
				}
			} while ((token.equals("SPLUS")) || (token.equals("SMINUS")));
			bcan();
			return 3;
		} else if (token.equals("SOR")) {
			do {
				bw.write("\tPUSH\t0,GR1\n");
				scan();
				cp_ziku = ziku;
				cp_token = token;
				cp_nLine = nLine;
				type2 = item_st();
				bw.write("\tPOP\tGR2\n");
				bw.write("\tOR\tGR1,GR2\n");
				array1.addFirst(cp_ziku);
				array2.addFirst(cp_token);
				array3.addFirst(cp_nLine);
				scan();
				if ((type2 != 2) || (type1 != type2)) {
					System.out
							.println("Line"
									+ "\t"
									+ nLine
									+ "\t"
									+ ":"
									+ "\t"
									+ "type of expression is invalid , must be boolean");
					System.exit(-1);
				}
				scan();
			} while ((token.equals("SOR")));
			bcan();
			bw.write(";;;;;simp end;;;;\n");
			return 2;
		} else {
			bcan();
			return type1;
		}

	}

	static int item_st() throws IOException {// 項
		int type1, type2;
		type1 = factor_st();
		scan();
		if ((token.equals("SSTAR")) || (token.equals("SDIVD"))
				|| (token.equals("SMOD"))) {
			do {
				if (token.equals("SSTAR")) {
					bw.write("\tPUSH\t0,GR1\n");
					scan();
					type2 = factor_st();
					if ((type2 != 3) || (type1 != type2)) {
						System.out
								.println("Line"
										+ "\t"
										+ nLine
										+ "\t"
										+ ":"
										+ "\t"
										+ "type of expression is invalid , must be integer");
						System.exit(-1);
					}
					bw.write("\tPOP\tGR2\n");
					bw.write("\tCALL\tMULT\n");
					bw.write("\tLD\tGR1,GR2\n");
					scan();
				} else if (token.equals("SDIVD")) {
					bw.write("\tPUSH\t0,GR1\n");
					scan();
					type2 = factor_st();
					if ((type2 != 3) || (type1 != type2)) {
						System.out
								.println("Line"
										+ "\t"
										+ nLine
										+ "\t"
										+ ":"
										+ "\t"
										+ "type of expression is invalid , must be integer");
						System.exit(-1);
					}
					bw.write("\tPOP\tGR2\n");
					bw.write("\tPUSH\t0,GR1\n");
					bw.write("\tLD\tGR1,GR2\n");
					bw.write("\tPOP\tGR2\n");
					bw.write("\tCALL\tDIV\n");
					bw.write("\tLD\tGR1,GR2\n");
					scan();
				} else if (token.equals("SMOD")) {
					bw.write("\tPUSH\t0,GR1\n");
					scan();
					type2 = factor_st();
					if ((type2 != 3) || (type1 != type2)) {
						System.out
								.println("Line"
										+ "\t"
										+ nLine
										+ "\t"
										+ ":"
										+ "\t"
										+ "type of expression is invalid , must be integer");
						System.exit(-1);
					}
					bw.write("\tPOP\tGR2\n");
					bw.write("\tPUSH\t0,GR1\n");
					bw.write("\tLD\tGR1,GR2\n");
					bw.write("\tPOP\tGR2\n");
					bw.write("\tCALL\tDIV\n");
					scan();
				}
			} while ((token.equals("SSTAR")) || (token.equals("SDIVD"))
					|| (token.equals("SMOD")));
			bcan();
			return 3;
		} else if (token.equals("SAND")) {
			do {
				bw.write("\tPUSH\t0,GR1\n");
				scan();
				type2 = factor_st();
				if ((type2 != 2) || (type1 != type2)) {
					System.out
							.println("Line"
									+ "\t"
									+ nLine
									+ "\t"
									+ ":"
									+ "\t"
									+ "type of expression is invalid , must be boolean");
					System.exit(-1);
				}
				bw.write("\tPOP\tGR2\n");
				bw.write("\tAND\tGR1,GR2\n");
				scan();
			} while (token.equals("SAND"));
			bcan();
			return 2;
		} else {
			bcan();
			return type1;
		}
	}

	static int factor_st() throws IOException {// 因子
		int type1 = 0;
		int type2, type3;
		boolean flg_p = false;
		boolean flg_l = false;
		if (token.equals("SIDENTIFIER")) {
			if (!gl_flag) {// 手続き内
				for (int h = 0; h < proc_list.size(); h++) {
					if (proc_list.get(h).getpara_name().equals(ziku)
							&& (proc_list.get(h).getproc_name()
									.equals(procedure_name))) {
						type1 = para_st();
						bw.write(";;;;;factor para begin;;;;\n");
						bw.write("\tLD\tGR1,"
								+ proc_list.get(h).getpara_offset() + ",GR5\n");// パラメータ
						flg_p = true;
						index_flag1 = false;
						// instant_varname2 = "";
					}
				}
				if (!flg_p) {
					type1 = variable_st();
					bw.write(";;;;;factor variable begin;;;;\n");
					if (index_flag1) {// 式に添字があれば
						for (int f = 0; f < l_var_table.size(); f++) {
							if (l_var_table.get(f).getvar_name()
									.equals(instant_varname2)
									&& (l_var_table.get(f).getproc_name()
											.equals(procedure_name))) {
								bw.write("\tLD\tGR1,"
										+ l_var_table.get(f).getvar_offset()
										+ ",GR5\n");
								flg_l = true;
								index_flag1 = false;
								instant_varname2 = "";
							}
						}
					} else {
						for (int f = 0; f < l_var_table.size(); f++) {
							if ((l_var_table.get(f).getvar_name()
									.equals(instant_varname1)
									&& (l_var_table.get(f).getproc_name()
											.equals(procedure_name)) && (!flg_p))) {
								if (l_var_table.get(f).getarray_type()) {// 配列
									if (!index_flag) {// 添え字がない場合
										bw.write("\tLAD\tGR2,"
												+ l_var_table.get(f)
														.getvar_offset()
												+ ",GR5\n");
										bw.write("\tLAD\tGR1,"
												+ l_var_table.get(f)
														.getvar_size() + "\n");
									} else {// 添え字がある場合
										bw.write("\tLAD\tGR2,"
												+ l_var_table.get(f)
														.getvar_min() + "\n");
										bw.write("\tSUBA\tGR1,GR2\n");
										bw.write("\tLAD\tGR2,"
												+ l_var_table.get(f)
														.getvar_offset() + "\n");
										bw.write("\tADDA\tGR1,GR2\n");
										bw.write("\tADDA\tGR1,GR5\n");
										bw.write("\tLD\tGR1,0,GR1\n");
									}
								} else
									bw.write("\tLD\tGR1,"
											+ l_var_table.get(f)
													.getvar_offset() + ",GR5\n");
								instant_varname1 = "";
								flg_l = true;
							}
						}
					}
					if (!flg_l) {
						if (index_flag1) {// 式に添字があれば
							for (int f = 0; f < g_var_table.size(); f++) {
								if (g_var_table.get(f).getvar_name()
										.equals(instant_varname2)) {
									bw.write("\tLD\tGR1,"
											+ g_var_table.get(f)
													.getvar_offset() + ",GR3\n");
									index_flag1 = false;
									instant_varname2 = "";
								}
							}
						} else {
							for (int f = 0; f < g_var_table.size(); f++) {
								if (g_var_table.get(f).getvar_name()
										.equals(instant_varname1)) {
									if (g_var_table.get(f).getarray_type()) {// 配列
										if (!index_flag) {// 添え字がない場合
											bw.write("\tLAD\tGR2,"
													+ g_var_table.get(f)
															.getvar_offset()
													+ ",GR3\n");
											bw.write("\tLAD\tGR1,"
													+ g_var_table.get(f)
															.getvar_size()
													+ "\n");
										} else {// 添え字がある場合
											bw.write("\tLAD\tGR2,"
													+ g_var_table.get(f)
															.getvar_min()
													+ "\n");
											bw.write("\tSUBA\tGR1,GR2\n");
											bw.write("\tLAD\tGR2,"
													+ g_var_table.get(f)
															.getvar_offset()
													+ "\n");
											bw.write("\tADDA\tGR1,GR2\n");
											bw.write("\tADDA\tGR1,GR3\n");
											bw.write("\tLD\tGR1,0,GR1\n");
										}
									} else
										bw.write("\tLD\tGR1,"
												+ g_var_table.get(f)
														.getvar_offset()
												+ ",GR3\n");
									instant_varname1 = "";
								}
							}
						}
					}
				}
			} else {// 手続き外
				type1 = variable_st();
				bw.write(";;;;;factor variable begin;;;;\n");
				if (index_flag2) {// 式に添字があれば
					for (int f = 0; f < g_var_table.size(); f++) {
						if (g_var_table.get(f).getvar_name()
								.equals(instant_varname3)) {
							bw.write("\tLD\tGR1,"
									+ g_var_table.get(f).getvar_offset()
									+ ",GR3\n");
						}
					}
				} else if ((index_flag1) && (!index_flag2)) {// 式に添字があれば
					for (int f = 0; f < g_var_table.size(); f++) {
						if (g_var_table.get(f).getvar_name()
								.equals(instant_varname2)) {
							if (g_var_table.get(f).getarray_type()) {// 配列
								if (!index_flag) {// 添え字がない場合
									bw.write("\tLD\tGR1,"
											+ g_var_table.get(f)
													.getvar_offset() + ",GR3\n");
								} else {
									bw.write("\tLAD\tGR2,"
											+ g_var_table.get(f).getvar_min()
											+ "\n");
									bw.write("\tSUBA\tGR1,GR2\n");// 添字の中で何番目か
									bw.write("\tLAD\tGR2,"
											+ g_var_table.get(f)
													.getvar_offset() + "\n");
									bw.write("\tADDA\tGR1,GR2\n");
									bw.write("\tADDA\tGR1,GR3\n");
									bw.write("\tLD\tGR1,0,GR1\n");
								}
							} else
								bw.write("\tLD\tGR1,"
										+ g_var_table.get(f).getvar_offset()
										+ ",GR3\n");
						}
					}
				} else {
					for (int f = 0; f < g_var_table.size(); f++) {
						if (g_var_table.get(f).getvar_name()
								.equals(instant_varname1)) {
							if (g_var_table.get(f).getarray_type()) {// 配列
								if (!index_flag) {// 添え字がない場合
									bw.write("\tLAD\tGR2,"
											+ g_var_table.get(f)
													.getvar_offset() + ",GR3\n");
									bw.write("\tLAD\tGR1,"
											+ g_var_table.get(f).getvar_size()
											+ "\n");
								} else {// 添え字がある場合
									bw.write("\tLAD\tGR2,"
											+ g_var_table.get(f).getvar_min()
											+ "\n");
									bw.write("\tSUBA\tGR1,GR2\n");// 添字の中で何番目か
									bw.write("\tLAD\tGR2,"
											+ g_var_table.get(f)
													.getvar_offset() + "\n");
									bw.write("\tADDA\tGR1,GR2\n");
									bw.write("\tADDA\tGR1,GR3\n");
									bw.write("\tLD\tGR1,0,GR1\n");
								}
							} else
								bw.write("\tLD\tGR1,"
										+ g_var_table.get(f).getvar_offset()
										+ ",GR3\n");
						}
					}

				}
			}
			if (flg_p) {
				bw.write(";;;;;factor para end;;;;\n");
			} else {
				bw.write(";;;;;factor variable end;;;;\n");
			}
			return type1;
		} else if (token.equals("SLPAREN")) {
			scan();
			type2 = exp_st();
			scan();
			if (!token.equals("SRPAREN")) {
				syntax_err();
			}
			return type2;
		} else if (token.equals("SNOT")) {
			scan();
			factor_st();
			bw.write("\tLAD\tGR2,1\n");
			bw.write("\tXOR\tGR1,GR2\n");
			return 2;
		} else {
			type3 = const_st();
			return type3;
		}
	}

	static int variable_st() throws IOException {// 変数
		int i_l = 0;
		int i_g = 0;
		int type;
		if (!index_flag2) {
			if (!index_flag1) {
				instant_varname1 = "";
				index_flag = false;
			} else {
				instant_varname2 = "";
			}
		} else {
			instant_varname3 = "";
		}
		varname();
		if (!index_flag2) {
			if (!index_flag1) {
				instant_varname1 = ziku;
			} else {
				instant_varname2 = ziku;
			}
		} else {
			instant_varname3 = ziku;
		}
		boolean flg1 = false;
		boolean flg2 = false;
		for (int j = 0; j < l_var_table.size(); j++) {
			if ((l_var_table.get(j).getvar_name().equals(ziku))
					&& (l_var_table.get(j).getproc_name()
							.equals(procedure_name))) {
				flg1 = true;
				i_l = j;
				break;
			}
		}
		for (int j = 0; j < g_var_table.size(); j++) {
			if (g_var_table.get(j).getvar_name().equals(ziku) && (!flg1)) {
				flg2 = true;
				i_g = j;
				break;
			}
		}
		if (flg1) {
			if (l_var_table.get(i_l).getarray_type() == true) {
				scan();
				if (!token.equals("SLBRACKET") && (output_flag)) {
					char_flg = true;
				} else if (!token.equals("SLBRACKET") && (!output_flag)) {
					System.err.println("Line" + "\t" + nLine + "\t" + ":"
							+ "\t"
							+ "array type variable is used without index.");// 5
					System.exit(-1);
				} else {
					index_flag = true;
				}
				bcan();
			}
		} else if (flg2) {
			if (g_var_table.get(i_g).getarray_type() == true) {
				scan();
				if (!token.equals("SLBRACKET") && (output_flag)) {
					char_flg = true;
				} else if (!token.equals("SLBRACKET") && (!output_flag)) {
					System.err.println("Line" + "\t" + nLine + "\t" + ":"
							+ "\t"
							+ "array type variable is used without index.");// 5
					System.exit(-1);
				} else {
					index_flag = true;
				}
				bcan();
			}
		}
		if ((!flg1) && (!flg2)) {
			System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
					+ "variable name is not defined.");// 2
			System.exit(-1);
		}
		scan();
		if (token.equals("SLBRACKET")) {
			if (index_flag1) {
				index_flag2 = true;
			} else {
				index_flag1 = true;
			}
			if (flg1) {
				if (l_var_table.get(i_l).getarray_type() == false) {
					System.err.println("Line" + "\t" + nLine + "\t" + ":"
							+ "\t" + "variable is not type array.");// 4
					System.exit(-1);
				}
			} else if (flg2) {
				if (g_var_table.get(i_g).getarray_type() == false) {
					System.err.println("Line" + "\t" + nLine + "\t" + ":"
							+ "\t" + "variable is not type array.");// 4
					System.exit(-1);
				}
			}
			scan();
			type = exp_st();
			if (!index_flag2) {
				index_flag1 = false;
			}
			index_flag2 = false;
			if (type != 3) {
				System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
						+ "invalid type is used in index of array.");// 12
				System.exit(-1);
			}
			scan();
			if (!token.equals("SRBRACKET")) {
				syntax_err();
			}
			scan();
		}
		bcan();
		if (flg1) {
			return l_var_table.get(i_l).getvar_type();
		} else {
			return g_var_table.get(i_g).getvar_type();
		}

	}

	static int para_st() throws IOException {// パラメーター
		int i_p = 0;
		instant_paraname = "";
		instantname();
		instant_paraname = ziku;
		boolean flg = false;
		for (int j = 0; j < proc_list.size(); j++) {
			if (proc_list.get(j).getpara_name().equals(ziku)
					&& (proc_list.get(j).getproc_name().equals(procedure_name))) {
				i_p = j;
				flg = true;
			}
		}
		if (!flg) {
			System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
					+ "para name is not defined.");// 2
			System.exit(-1);
		}
		return proc_list.get(i_p).getpara_type();
	}

	private static void assign_st() throws IOException {// 代入文
		bw.write(";;;;;;;begin assign;;;;;;\n");
		int type1 = 0;
		int type2;
		int plab = 0;
		int h2 = 0;
		int f2 = 0;
		int f3 = 0;
		if (!gl_flag) {
			for (int h = 0; h < proc_list.size(); h++) {
				if (proc_list.get(h).getpara_name().equals(ziku)
						&& (proc_list.get(h).getproc_name()
								.equals(procedure_name))) {
					type1 = para_st();
					bw.write(";;;para=\t" + instant_paraname + ";;;;;;\n");
					h2 = h;
					plab = 1;
				}
			}
			if (plab != 1) {
				type1 = variable_st();
				assign_varname = instant_varname1;
				bw.write(";;;var=\t" + assign_varname + ";;;;;;\n");
				for (int f = 0; f < l_var_table.size(); f++) {
					if (l_var_table.get(f).getvar_name().equals(assign_varname)
							&& (l_var_table.get(f).getproc_name()
									.equals(procedure_name))) {
						if (l_var_table.get(f).getarray_type()) {// 配列
							bw.write("\tLAD\tGR2,"
									+ l_var_table.get(f).getvar_min() + "\n");
							bw.write("\tSUBA\tGR1,GR2\n");
							bw.write("\tLAD\tGR2,"
									+ l_var_table.get(f).getvar_offset() + "\n");
							bw.write("\tADDA\tGR1,GR2\n");
							bw.write("\tADDA\tGR1,GR5\n");
							bw.write("\tPUSH\t0,GR1\n");
						}
						f2 = f;
						plab = 2;
					}
				}
				if (plab != 2) {
					for (int f = 0; f < g_var_table.size(); f++) {
						if (g_var_table.get(f).getvar_name()
								.equals(assign_varname)) {
							if (g_var_table.get(f).getarray_type()) {// 配列
								bw.write("\tLAD\tGR2,"
										+ g_var_table.get(f).getvar_min()
										+ "\n");
								bw.write("\tSUBA\tGR1,GR2\n");
								bw.write("\tLAD\tGR2,"
										+ g_var_table.get(f).getvar_offset()
										+ "\n");
								bw.write("\tADDA\tGR1,GR2\n");
								bw.write("\tADDA\tGR1,GR3\n");
								bw.write("\tPUSH\t0,GR1\n");
							}
							f3 = f;
							plab = 3;
						}
					}
				}
			}
		} else {
			type1 = variable_st();
			assign_varname = instant_varname1;
			bw.write(";;;var=\t" + assign_varname + ";;;;;;\n");
			for (int e = 0; e < g_var_table.size(); e++) {
				if (g_var_table.get(e).getvar_name().equals(assign_varname)) {
					if (g_var_table.get(e).getarray_type()) {// 配列
						bw.write("\tLAD\tGR2,"
								+ g_var_table.get(e).getvar_min() + "\n");
						bw.write("\tSUBA\tGR1,GR2\n");
						bw.write("\tLAD\tGR2,"
								+ g_var_table.get(e).getvar_offset() + "\n");
						bw.write("\tADDA\tGR1,GR2\n");
						bw.write("\tADDA\tGR1,GR3\n");
						bw.write("\tPUSH\t0,GR1\n");
					}
					f3 = e;
					plab = 3;
				}
			}
		}
		scan();
		if (!token.equals("SASSIGN")) {
			syntax_err();
		}
		scan();
		type2 = exp_st();
		if (type1 != type2) {
			System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
					+ "type is conflicting in assign statement.");// 10
			System.exit(-1);
		}
		if (plab == 1) {
			bw.write("\tST\tGR1," + proc_list.get(h2).getpara_offset()
					+ ",GR5\n");
		} else if (plab == 2) {
			if (l_var_table.get(f2).getarray_type()) {// 配列
				bw.write("\tPOP\tGR2\n");
				bw.write("\tST\tGR1,0,GR2\n");
			} else
				bw.write("\tST\tGR1," + l_var_table.get(f2).getvar_offset()
						+ ",GR5\n");
		} else if (plab == 3) {
			if (g_var_table.get(f3).getarray_type()) {// 配列
				bw.write("\tPOP\tGR2\n");
				bw.write("\tST\tGR1,0,GR2\n");
			} else
				bw.write("\tST\tGR1," + g_var_table.get(f3).getvar_offset()
						+ ",GR3\n");
		}
		assign_varname = "";
		bw.write(";;;;;;;end assign;;;;;;\n");
	}

	private static void callingpro_st() throws IOException {// 手続き呼出し文
		int counter = 0;
		String instant_procname = ziku;
		instant_procname = instant_procname + "        ";// 8個分の空白を入れる。
		name();
		if (!tmp3.contains(ziku)) {
			System.err.println("Line" + "\t" + nLine + "\t" + ":" + "\t"
					+ "proc name is not defined.");// 7
			System.exit(-1);
		}
		scan();
		if (token.equals("SLPAREN")) {
			scan();
			exp_st();
			bw.write("\tPUSH\t0,GR1\n");// 実引数を積む
			counter++;
			scan();
			if (token.equals("SCOMMA")) {
				do {
					scan();
					exp_st();
					bw.write("\tPUSH\t0,GR1\n");
					counter++;
					scan();
				} while (token.equals("SCOMMA"));
			}
			bcan();
			scan();
			if (!token.equals("SRPAREN")) {
				syntax_err();
			}
			scan();
		}
		bcan();
		bw.write("\tCALL\t" + instant_procname.toUpperCase().substring(0, 7)
				+ "\n");
		for (int i = 0; i < counter; i++) {
			bw.write("\tPOP\tGR0\n");
		}
	}

	private static void input_st() throws IOException {// 入力文
		int k = 0;
		if (token.equals("SREADLN")) {
			scan();
			if (token.equals("SLPAREN")) {
				scan();
				if (gl_flag) {
					variable_st();
					for (int j = 0; j < g_var_table.size(); j++) {
						if (g_var_table.get(j).getvar_name()
								.equals(instant_varname1)) {
							k = j;
							break;
						}
					}
					if (g_var_table.get(k).getvar_type() == 3) {
						bw.write("\tLAD\tGR2,"
								+ g_var_table.get(k).getvar_offset() + ",GR3\n");
						bw.write("\tCALL\tRDINT\n");
					} else if (g_var_table.get(k).getvar_type() == 1) {
						bw.write("\tLAD\tGR2,"
								+ g_var_table.get(k).getvar_offset() + ",GR3\n");
						bw.write("\tCALL\tRDCHAR\n");
					} else if (g_var_table.get(k).getarray_type()) {
						bw.write("\tLAD\tGR1," + Var_g.getvar_offset() + "\n");
						bw.write("\tLAD\tGR2,"
								+ g_var_table.get(k).getvar_offset() + ",GR3\n");
						bw.write("\tCALL\tRDSTRING\n");
					}
				}
				scan();
				if (!token.equals("SRPAREN")) {
					syntax_err();
				}
				scan();
			}
			bcan();
		}
	}

	private static void output_st() throws IOException {// 出力文
		int type1, type2;
		output_flag = true;
		if (token.equals("SWRITELN")) {
			scan();
			if (token.equals("SLPAREN")) {
				scan();
				type1 = exp_st();
				if (type1 == 4) {
					bw.write("\tCALL\tWRTSTR\n");
				} else if (type1 == 3) {
					bw.write("\tLD\tGR2,GR1\n");
					bw.write("\tCALL\tWRTINT\n");
				} else if (type1 == 1) {
					if (char_flg) {
						bw.write("\tCALL\tWRTSTR\n");
					} else {
						bw.write("\tLD\tGR2,GR1\n");
						bw.write("\tCALL\tWRTCH\n");
					}
					char_flg = false;
				}
				scan();
				if (token.equals("SCOMMA")) {
					do {
						scan();
						type2 = exp_st();
						if (type2 == 4) {
							bw.write("\tCALL\tWRTSTR\n");
						} else if (type2 == 3) {
							bw.write("\tLD\tGR2,GR1\n");
							bw.write("\tCALL\tWRTINT\n");
						} else if (type2 == 1) {
							if (char_flg) {
								bw.write("\tCALL\tWRTSTR\n");
							} else {
								bw.write("\tLD\tGR2,GR1\n");
								bw.write("\tCALL\tWRTCH\n");
							}
							char_flg = false;
						}
						scan();
					} while (token.equals("SCOMMA"));
				}
				bcan();
				scan();
				if (!token.equals("SRPAREN")) {
					syntax_err();
				}
				scan();
			}
			bcan();
			bw.write("\tCALL\tWRTLN\n");
			output_flag = false;
		}
	}

	private static void name() {// 名前
		if (!token.equals("SIDENTIFIER")) {
			syntax_err();
		}
	}

	private static void nameseq_st() {// 名前の並び
		name();
		scan();
		if (token.equals("SCOMMA")) {
			do {
				scan();
				name();
				scan();
			} while (token.equals("SCOMMA"));
		}
		bcan();
	}

	static int const_st() throws IOException {// 定数
		if (token.equals("SFALSE")) {
			bw.write("\tLAD\tGR1,0\n");
			return 2;// boolean
		}
		if (token.equals("STRUE")) {
			bw.write("\tLAD\tGR1,1\n");
			return 2;// boolean
		} else if (token.equals("SCONSTANT")) {
			bw.write("\tLAD\tGR1," + ziku + "\n");
			return 3;// integer
		} else if (token.equals("SSTRING")) {
			String[][] constAry = new String[50][1];// 50個も文字列出てこないだろう。
			constAry[const_num][0] = ziku;
			String[] str = ziku.split("'", 3);
			const_tmp.add(ziku);
			if (str[1].length() == 1) {// 文字定数
				bw.write("\tLD\tGR1,ST" + const_num + "\n");//
				const_num++;
				return 1;// char
			} else {// 文字列定数
				bw.write("\tLAD\tGR2,ST" + const_num + "\n");
				bw.write("\tLAD\tGR1," + str[1].length() + "\n");
				const_num++;
				return 4;// string
			}
		} else {
			syntax_err();
			return -1;
		}
	}

	private static void integer() {// 整数
		integer_mflag = false;
		if (token.equals("SPLUS")) {
			scan();
		}
		if (token.equals("SMINUS")) {
			integer_mflag = true;
			;
			scan();
		}
		if (!token.equals("SCONSTANT")) {
			syntax_err();
		}
	}

	public static void main(String[] args) throws IOException {
		FileReader in = new FileReader(args[0]);
		out = new FileWriter(args[1]);
        BufferedReader br = new BufferedReader(in);
        bw = new BufferedWriter(out);
        if(!args[0].endsWith(".ts")){
        	System.err.println("parameter must be .ts.");
        	System.exit(-1);
        }
        if(!args[1].endsWith(".cas")){
        	System.err.println("parameter must be .cas.");
        	System.exit(-1);
        }
        else{
        	while ((line = br.readLine()) != null) 
            {	
        	   int i= 0;
        	   if(line.charAt(i)==('\'')){
        		   String[] strAryed = line.split("'",3); 
        		   ziked = "'"+strAryed[1]+"'";
        		   array1.add(ziked);
        		   ziked ="";
        		   linee = strAryed[2];
        		   String[] strAry = linee.split("\\s",4);
            	   typed = strAry[1];
            	   nLined = strAry[3];
                   array2.add(typed);
                   typed ="";
                   array3.add(nLined);
                   nLined ="";
        	   }else{
               String[] strAry = line.split("\\s",4);
               ziked = strAry[0];
        	   typed = strAry[1];
        	   nLined = strAry[3];
        	   array1.add(ziked);
               ziked ="";
               array2.add(typed);
               typed ="";
               array3.add(nLined);
               nLined ="";
        	   }
	         }
        	scan();
        	if(token.equals("SPROGRAM")){
        		program_st();
        	}
        	else{
        		syntax_err();
        	}
        }
        br.close();
        bw.close();
        in.close();
        out.close();
	}
}
