
// line 1 "TableLayoutTokenizer.rl"
// Do not edit this file! Generated by Ragel.

package com.esotericsoftware.tablelayout.swing;

import java.util.HashSet;

public class TableLayoutTokenizer extends RagelTokenMaker {
	static public final int PLAIN = 1;
	static public final int STRUCTURE = 2;
	static public final int SYMBOL = 3;
	static public final int NAME = 4;
	static public final int STRING = 5;
	static public final int BRACKET = 6;
	static public final int PROPERTY = 7;
	static public final int KEYWORD = 8;
	static public final int VALUE = 9;
	static public final int CONSTANT = 10;

	protected void parse (int initialTokenType) {
		
// line 3 "../src/com/esotericsoftware/tablelayout/swing/TableLayoutTokenizer.java"
	{
	cs = syntax_start;
	}

// line 7 "../src/com/esotericsoftware/tablelayout/swing/TableLayoutTokenizer.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _syntax_key_offsets[cs];
	_trans = _syntax_index_offsets[cs];
	_klen = _syntax_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _syntax_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _syntax_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _syntax_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _syntax_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _syntax_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _syntax_indicies[_trans];
	cs = _syntax_trans_targs[_trans];

	if ( _syntax_trans_actions[_trans] != 0 ) {
		_acts = _syntax_trans_actions[_trans];
		_nacts = (int) _syntax_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _syntax_actions[_acts++] )
			{
	case 0:
// line 23 "TableLayoutTokenizer.rl"
	{ s = p; }
	break;
	case 1:
// line 24 "TableLayoutTokenizer.rl"
	{ addToken(PLAIN); }
	break;
	case 2:
// line 25 "TableLayoutTokenizer.rl"
	{ addToken(STRUCTURE); }
	break;
	case 3:
// line 27 "TableLayoutTokenizer.rl"
	{ addToken(SYMBOL); }
	break;
	case 4:
// line 28 "TableLayoutTokenizer.rl"
	{ addCharToken(SYMBOL); }
	break;
	case 5:
// line 29 "TableLayoutTokenizer.rl"
	{ addToken(NAME); }
	break;
	case 6:
// line 30 "TableLayoutTokenizer.rl"
	{ addCharToken(BRACKET); }
	break;
	case 7:
// line 31 "TableLayoutTokenizer.rl"
	{ addToken(STRING); }
	break;
	case 8:
// line 32 "TableLayoutTokenizer.rl"
	{ addCharToken(STRING); }
	break;
	case 9:
// line 33 "TableLayoutTokenizer.rl"
	{ addToken(keywords, KEYWORD, PROPERTY); }
	break;
	case 10:
// line 34 "TableLayoutTokenizer.rl"
	{
			addToken(constants, CONSTANT, VALUE);
			try {
				Integer.parseInt(currentToken.getLexeme());
				currentToken.type = CONSTANT;
			} catch (NumberFormatException ignored) {}
		}
	break;
// line 137 "../src/com/esotericsoftware/tablelayout/swing/TableLayoutTokenizer.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	int __acts = _syntax_eof_actions[cs];
	int __nacts = (int) _syntax_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _syntax_actions[__acts++] ) {
	case 0:
// line 23 "TableLayoutTokenizer.rl"
	{ s = p; }
	break;
	case 1:
// line 24 "TableLayoutTokenizer.rl"
	{ addToken(PLAIN); }
	break;
	case 2:
// line 25 "TableLayoutTokenizer.rl"
	{ addToken(STRUCTURE); }
	break;
	case 3:
// line 27 "TableLayoutTokenizer.rl"
	{ addToken(SYMBOL); }
	break;
	case 5:
// line 29 "TableLayoutTokenizer.rl"
	{ addToken(NAME); }
	break;
	case 7:
// line 31 "TableLayoutTokenizer.rl"
	{ addToken(STRING); }
	break;
	case 9:
// line 33 "TableLayoutTokenizer.rl"
	{ addToken(keywords, KEYWORD, PROPERTY); }
	break;
	case 10:
// line 34 "TableLayoutTokenizer.rl"
	{
			addToken(constants, CONSTANT, VALUE);
			try {
				Integer.parseInt(currentToken.getLexeme());
				currentToken.type = CONSTANT;
			} catch (NumberFormatException ignored) {}
		}
	break;
// line 196 "../src/com/esotericsoftware/tablelayout/swing/TableLayoutTokenizer.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 67 "TableLayoutTokenizer.rl"

	}
	
// line 206 "../src/com/esotericsoftware/tablelayout/swing/TableLayoutTokenizer.java"
private static byte[] init__syntax_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    6,    1,    7,    1,    8,    1,   10,    2,    0,    1,    2,    0,
	    7,    2,    1,    0,    2,    1,    4,    2,    1,    6,    2,    1,
	    8,    2,    2,    0,    2,    2,    6,    2,    2,    8,    2,    3,
	    0,    2,    3,    6,    2,    3,    8,    2,    7,    4,    2,    7,
	    8,    2,    7,   10,    2,    8,    7,    2,    9,    0,    2,   10,
	    0,    2,   10,    4,    2,   10,    6,    2,   10,    7,    2,   10,
	    8,    3,    0,    1,    6,    3,    0,    1,    8,    3,    0,    5,
	    1,    3,    0,    7,    4,    3,    0,    7,    8,    3,    5,    0,
	    1,    3,    7,   10,    4,    3,    9,    0,    1,    3,   10,    7,
	    4,    4,    0,    5,    1,    4,    4,    0,    5,    1,    6,    4,
	    5,    0,    1,    4,    4,    5,    0,    1,    6,    4,    9,    0,
	    1,    4,    4,    9,    0,    1,    6,    4,    9,    0,    1,    8
	};
}

private static final byte _syntax_actions[] = init__syntax_actions_0();


private static short[] init__syntax_key_offsets_0()
{
	return new short [] {
	    0,    0,   10,   18,   37,   56,   57,   58,   77,   99,  121,  145,
	  169,  171,  173,  193,  195,  197,  208,  218,  229,  231,  253,  273,
	  292,  294,  296,  303,  307,  314,  325,  335,  346
	};
}

private static final short _syntax_key_offsets[] = init__syntax_key_offsets_0();


private static char[] init__syntax_trans_keys_0()
{
	return new char [] {
	   39,   45,   46,   95,   48,   57,   65,   90,   97,  122,   46,   95,
	   48,   57,   65,   90,   97,  122,   39,   58,   60,   62,   91,   93,
	  124,   40,   42,   44,   46,   48,   57,   65,   90,   97,  122,  123,
	  125,   39,   58,   60,   62,   91,   93,  124,   40,   42,   44,   46,
	   48,   57,   65,   90,   97,  122,  123,  125,   39,   39,   39,   58,
	   60,   62,   91,   93,  124,   40,   42,   44,   46,   48,   57,   65,
	   90,   97,  122,  123,  125,   32,   39,   58,   60,   62,   91,   93,
	  124,    9,   13,   40,   42,   44,   46,   48,   57,   65,   90,   97,
	  122,  123,  125,   32,   39,   58,   60,   62,   91,   93,  124,    9,
	   13,   40,   42,   44,   46,   48,   57,   65,   90,   97,  122,  123,
	  125,   32,   39,   44,   45,   46,   58,   60,   62,   91,   93,   95,
	  124,    9,   13,   40,   42,   48,   57,   65,   90,   97,  122,  123,
	  125,   32,   39,   44,   45,   46,   58,   60,   62,   91,   93,   95,
	  124,    9,   13,   40,   42,   48,   57,   65,   90,   97,  122,  123,
	  125,   39,   44,   39,   44,   39,   44,   58,   60,   62,   91,   93,
	  124,   40,   42,   45,   46,   48,   57,   65,   90,   97,  122,  123,
	  125,   39,   44,   39,   44,   39,   44,   45,   46,   95,   48,   57,
	   65,   90,   97,  122,   39,   44,   46,   95,   48,   57,   65,   90,
	   97,  122,   37,   39,   44,   46,   95,   48,   57,   65,   90,   97,
	  122,   39,   44,   37,   39,   44,   45,   46,   58,   60,   62,   91,
	   93,   95,  124,   40,   42,   48,   57,   65,   90,   97,  122,  123,
	  125,   39,   44,   58,   60,   62,   91,   93,  124,   40,   42,   45,
	   46,   48,   57,   65,   90,   97,  122,  123,  125,   39,   58,   60,
	   62,   91,   93,  124,   40,   42,   44,   46,   48,   57,   65,   90,
	   97,  122,  123,  125,   58,   93,   58,   93,   32,   39,   58,   93,
	  123,    9,   13,   39,   58,   93,  123,   32,   39,   58,   93,  123,
	    9,   13,   39,   44,   45,   46,   95,   48,   57,   65,   90,   97,
	  122,   39,   44,   46,   95,   48,   57,   65,   90,   97,  122,   37,
	   39,   44,   46,   95,   48,   57,   65,   90,   97,  122,   39,   44,
	    0
	};
}

private static final char _syntax_trans_keys[] = init__syntax_trans_keys_0();


private static byte[] init__syntax_single_lengths_0()
{
	return new byte [] {
	    0,    4,    2,    7,    7,    1,    1,    7,    8,    8,   12,   12,
	    2,    2,    8,    2,    2,    5,    4,    5,    2,   12,    8,    7,
	    2,    2,    5,    4,    5,    5,    4,    5,    2
	};
}

private static final byte _syntax_single_lengths[] = init__syntax_single_lengths_0();


private static byte[] init__syntax_range_lengths_0()
{
	return new byte [] {
	    0,    3,    3,    6,    6,    0,    0,    6,    7,    7,    6,    6,
	    0,    0,    6,    0,    0,    3,    3,    3,    0,    5,    6,    6,
	    0,    0,    1,    0,    1,    3,    3,    3,    0
	};
}

private static final byte _syntax_range_lengths[] = init__syntax_range_lengths_0();


private static short[] init__syntax_index_offsets_0()
{
	return new short [] {
	    0,    0,    8,   14,   28,   42,   44,   46,   60,   76,   92,  111,
	  130,  133,  136,  151,  154,  157,  166,  174,  183,  186,  204,  219,
	  233,  236,  239,  246,  251,  258,  267,  275,  284
	};
}

private static final short _syntax_index_offsets[] = init__syntax_index_offsets_0();


private static byte[] init__syntax_indicies_0()
{
	return new byte [] {
	    0,    2,    3,    3,    3,    3,    3,    1,    4,    4,    4,    4,
	    4,    1,    6,    7,    9,    9,   10,   11,    7,    7,    7,    8,
	    8,    8,    9,    5,   13,   14,   16,   16,   17,   18,   14,   14,
	   14,   15,   15,   15,   16,   12,   20,   19,   22,   21,   24,   25,
	   27,   27,   28,   29,   25,   25,   25,   26,   26,   26,   27,   23,
	   31,   32,   35,   36,   36,   37,   38,   33,   31,   33,   33,   34,
	   34,   34,   36,   30,   40,   13,   41,   16,   16,   17,   18,   14,
	   40,   14,   14,   15,   15,   15,   16,   39,   43,   44,   45,   46,
	   47,   45,   48,   48,   49,   50,   47,   45,   43,   45,   47,   47,
	   47,   48,   42,   51,   52,   14,   53,   54,   14,   16,   16,   17,
	   18,   54,   14,   51,   14,   54,   54,   54,   16,   39,   56,   57,
	   55,   59,   60,   58,    6,   61,    7,    9,    9,   10,   11,    7,
	    7,    7,    8,    8,    8,    9,    5,   56,   63,   62,   59,   65,
	   64,   66,   65,   67,   68,   68,   68,   68,   68,   64,   59,   65,
	   69,   69,   69,   69,   69,   64,   70,   59,   71,   69,   69,   69,
	   69,   69,   64,   59,   71,   64,   73,   74,   76,   75,    4,   75,
	   77,   77,   78,   79,    4,   75,   75,    4,    4,    4,   77,   72,
	   74,   76,   75,   77,   77,   78,   79,   75,   75,   75,   80,   80,
	   80,   77,   72,   82,   83,   85,   85,   86,   87,   83,   83,   83,
	   84,   84,   84,   85,   81,   89,   90,   88,   92,   93,   91,   95,
	   96,   45,   50,   48,   95,   94,   13,   14,   18,   16,   97,   99,
	   13,   14,   18,   16,   99,   98,  100,   60,  101,  102,  102,  102,
	  102,  102,   58,   59,   60,  103,  103,  103,  103,  103,   58,  104,
	   59,  105,  103,  103,  103,  103,  103,   58,   59,  105,   58,    0
	};
}

private static final byte _syntax_indicies[] = init__syntax_indicies_0();


private static byte[] init__syntax_trans_targs_0()
{
	return new byte [] {
	   15,    0,    2,   21,   21,    4,    5,    7,    8,   23,   24,    3,
	    4,    5,    7,    8,   23,   24,    3,    6,    3,    6,    3,    4,
	    5,    7,    8,   23,   24,    3,    4,    9,    5,    7,    8,   10,
	   23,   24,    3,    4,    9,   10,    4,   11,   12,    7,    2,   21,
	   23,   24,    3,   11,   12,    2,   21,   13,   14,   29,   13,   14,
	   29,    1,   16,   17,   16,   17,   15,   18,   19,   19,   20,   17,
	    4,   22,    5,    7,    1,   23,   24,    3,    8,    4,    5,    7,
	    8,   23,   24,    3,   25,   26,    3,   25,   26,    3,   27,   28,
	    5,   27,   27,   28,   15,   30,   31,   31,   32,   29
	};
}

private static final byte _syntax_trans_targs[] = init__syntax_trans_targs_0();


private static short[] init__syntax_trans_actions_0()
{
	return new short [] {
	   15,    0,    1,    1,    0,    1,   15,    1,    1,    1,   11,   11,
	    0,   34,   25,   25,   25,   31,   31,    1,  101,    0,   58,   46,
	   52,    0,   46,   46,   49,   49,  113,   67,  151,  113,    0,  141,
	  113,  146,  146,   25,    0,   28,   19,    1,   89,   19,   19,   19,
	   19,   85,   85,    0,   34,   25,   25,    1,  101,   97,    0,   58,
	   55,    9,    1,   97,    0,   55,   64,    1,    1,    0,    0,  117,
	   70,    0,   82,   70,   73,   70,   76,   76,   70,   37,   43,   37,
	   37,    0,   40,   40,    1,  121,  126,    0,  131,  136,   19,    1,
	   89,    0,   25,    0,   58,    1,    1,    0,    0,  109
	};
}

private static final short _syntax_trans_actions[] = init__syntax_trans_actions_0();


private static short[] init__syntax_eof_actions_0()
{
	return new short [] {
	    0,    0,    0,    0,    3,   22,   13,    7,  113,    3,   19,    3,
	   22,   13,    0,   22,   13,   13,   13,   79,   79,   17,   17,    5,
	   93,  105,   19,    3,    3,   13,   13,   61,   61
	};
}

private static final short _syntax_eof_actions[] = init__syntax_eof_actions_0();


static final int syntax_start = 3;
static final int syntax_first_final = 3;
static final int syntax_error = 0;

static final int syntax_en_main = 3;


// line 70 "TableLayoutTokenizer.rl"

	private HashSet<String> keywords = new HashSet();
	{
		keywords.add("debug");
		keywords.add("size");
		keywords.add("width");
		keywords.add("height");
		keywords.add("expand");
		keywords.add("fill");
		keywords.add("align");
		keywords.add("colspan");
		keywords.add("uniform");
		keywords.add("padding");
		keywords.add("paddingTop");
		keywords.add("paddingLeft");
		keywords.add("paddingBottom");
		keywords.add("paddingRight");
		keywords.add("spacing");
		keywords.add("spacingTop");
		keywords.add("spacingLeft");
		keywords.add("spacingBottom");
		keywords.add("spacingRight");
		keywords.add("ignore");
		keywords.add("w");
		keywords.add("h");
		keywords.add("pad");
		keywords.add("padTop");
		keywords.add("padLeft");
		keywords.add("padBottom");
		keywords.add("padRight");
		keywords.add("space");
		keywords.add("spaceTop");
		keywords.add("spaceLeft");
		keywords.add("spaceBottom");
		keywords.add("spaceRight");
	}

	private HashSet<String> constants = new HashSet();
	{
		constants.add("x");
		constants.add("y");
		constants.add("top");
		constants.add("bottom");
		constants.add("left");
		constants.add("right");
		constants.add("center");
		constants.add("min");
		constants.add("pref");
		constants.add("max");
		constants.add("cell");
		constants.add("table");
		constants.add("widget");
	}

	public boolean getCurlyBracesDenoteCodeBlocks () {
		return true;
	}
}
