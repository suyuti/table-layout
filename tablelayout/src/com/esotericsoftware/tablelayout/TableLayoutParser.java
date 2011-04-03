// line 1 "TableLayoutParser.rl"
// Do not edit this file! Generated by Ragel.

package com.esotericsoftware.tablelayout;

import java.util.ArrayList;

class TableLayoutParser {
	static public void parse (TableLayout table, Toolkit toolkit, String input) {
		char[] data = (input + "  ").toCharArray();
		int cs, p = 0, pe = data.length, eof = pe, top = 0;
		int[] stack = new int[4];

		int s = 0;
		String name = null;
		String widgetLayoutString = null;
		String className = null;
		Object label = null;

		int columnDefaultCount = 0;
		ArrayList<String> values = new ArrayList(4);
		ArrayList<Object> parents = new ArrayList(8);
		Cell cell = null, rowDefaults = null, columnDefaults = null;
		Object parent = table, widget = null;
		RuntimeException parseRuntimeEx = null;
		boolean hasColon = false;

		boolean debug = false;
		if (debug) System.out.println();

		try {

			// line 3 "../src/com/esotericsoftware/tablelayout/TableLayoutParser.java"
			{
				cs = tableLayout_start;
				top = 0;
			}

			// line 8 "../src/com/esotericsoftware/tablelayout/TableLayoutParser.java"
			{
				int _klen;
				int _trans = 0;
				int _acts;
				int _nacts;
				int _keys;
				int _goto_targ = 0;

				_goto:
				while (true) {
					switch (_goto_targ) {
					case 0:
						if (p == pe) {
							_goto_targ = 4;
							continue _goto;
						}
						if (cs == 0) {
							_goto_targ = 5;
							continue _goto;
						}
					case 1:
						_match:
						do {
							_keys = _tableLayout_key_offsets[cs];
							_trans = _tableLayout_index_offsets[cs];
							_klen = _tableLayout_single_lengths[cs];
							if (_klen > 0) {
								int _lower = _keys;
								int _mid;
								int _upper = _keys + _klen - 1;
								while (true) {
									if (_upper < _lower) break;

									_mid = _lower + ((_upper - _lower) >> 1);
									if (data[p] < _tableLayout_trans_keys[_mid])
										_upper = _mid - 1;
									else if (data[p] > _tableLayout_trans_keys[_mid])
										_lower = _mid + 1;
									else {
										_trans += (_mid - _keys);
										break _match;
									}
								}
								_keys += _klen;
								_trans += _klen;
							}

							_klen = _tableLayout_range_lengths[cs];
							if (_klen > 0) {
								int _lower = _keys;
								int _mid;
								int _upper = _keys + (_klen << 1) - 2;
								while (true) {
									if (_upper < _lower) break;

									_mid = _lower + (((_upper - _lower) >> 1) & ~1);
									if (data[p] < _tableLayout_trans_keys[_mid])
										_upper = _mid - 2;
									else if (data[p] > _tableLayout_trans_keys[_mid + 1])
										_lower = _mid + 2;
									else {
										_trans += ((_mid - _keys) >> 1);
										break _match;
									}
								}
								_trans += _klen;
							}
						} while (false);

						cs = _tableLayout_trans_targs[_trans];

						if (_tableLayout_trans_actions[_trans] != 0) {
							_acts = _tableLayout_trans_actions[_trans];
							_nacts = (int)_tableLayout_actions[_acts++];
							while (_nacts-- > 0) {
								switch (_tableLayout_actions[_acts++]) {
								case 0:
									// line 42 "TableLayoutParser.rl"
								{
									s = p;
								}
									break;
								case 1:
									// line 43 "TableLayoutParser.rl"
								{
									name = new String(data, s, p - s);
									s = p;
									hasColon = false;
								}
									break;
								case 2:
									// line 48 "TableLayoutParser.rl"
								{
									values.add(new String(data, s, p - s));
								}
									break;
								case 3:
									// line 51 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("tableProperty: " + name + " = " + values);
									toolkit.setTableProperty((TableLayout)parent, name, values);
									values.clear();
								}
									break;
								case 4:
									// line 56 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("cellDefaultProperty: " + name + " = " + values);
									toolkit.setCellProperty(((TableLayout)parent).getCellDefaults(), name, values);
									values.clear();
								}
									break;
								case 5:
									// line 61 "TableLayoutParser.rl"
								{
									columnDefaults = ((TableLayout)parent).getColumnDefaults(columnDefaultCount++);
								}
									break;
								case 6:
									// line 64 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("columnDefaultProperty: " + name + " = " + values);
									toolkit.setCellProperty(columnDefaults, name, values);
									values.clear();
								}
									break;
								case 7:
									// line 69 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("startRow");
									rowDefaults = ((TableLayout)parent).startRow();
								}
									break;
								case 8:
									// line 73 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("rowDefaultValue: " + name + " = " + values);
									toolkit.setCellProperty(rowDefaults, name, values);
									values.clear();
								}
									break;
								case 9:
									// line 78 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("cellProperty: " + name + " = " + values);
									toolkit.setCellProperty(cell, name, values);
									values.clear();
								}
									break;
								case 10:
									// line 83 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("setTitle: " + new String(data, s, p - s));
									if (widget instanceof TableLayout)
										((TableLayout)widget).title = new String(data, s, p - s);
									else
										toolkit.setTitle(widget, new String(data, s, p - s));
								}
									break;
								case 11:
									// line 90 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("widgetLayoutString: " + new String(data, s, p - s).trim());
									widgetLayoutString = new String(data, s, p - s).trim();
								}
									break;
								case 12:
									// line 94 "TableLayoutParser.rl"
								{
									className = new String(data, s, p - s);
								}
									break;
								case 13:
									// line 97 "TableLayoutParser.rl"
								{
									label = toolkit.newLabel(new String(data, s, p - s));
								}
									break;
								case 14:
									// line 100 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("newWidget: " + name + " " + className + " " + label);
									widget = null;
									if (label != null) { // 'label' or ['label'] or [name:'label']
										widget = label;
										label = null;
										if (name.length() > 0) table.setName(name, widget);
									} else if (className == null) {
										if (name.length() > 0) {
											if (hasColon) { // [name:]
												widget = toolkit.newEmptyWidget();
												table.setName(name, widget);
											} else { // [name]
												widget = table.getWidget(name);
												if (widget == null) {
													// Try the widget name as a class name.
													try {
														widget = toolkit.wrap(toolkit.newWidget(name));
													} catch (Exception ex) {
														throw new IllegalArgumentException("Widget not found with name: " + name);
													}
												}
											}
										} // else leave widget null for: []
									} else { // [:class] and [name:class]
										try {
											widget = toolkit.wrap(toolkit.newWidget(className));
										} catch (Exception ex) {
											throw new RuntimeException("Error creating instance of class: " + className, ex);
										}
										className = null;
										if (name.length() > 0) table.setName(name, widget);
									}
								}
									break;
								case 15:
									// line 134 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("newLabel: " + new String(data, s, p - s));
									widget = toolkit.newLabel(new String(data, s, p - s));
								}
									break;
								case 16:
									// line 138 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("startTable");
									parents.add(parent);
									parent = toolkit.newTableLayout();
									cell = null;
									widget = null;
									{
										if (top == stack.length) {
											int[] newStack = new int[stack.length * 2];
											System.arraycopy(stack, 0, newStack, 0, stack.length);
											stack = newStack;
										}
										{
											stack[top++] = cs;
											cs = 82;
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 17:
									// line 146 "TableLayoutParser.rl"
								{
									widget = parent;
									if (!parents.isEmpty()) {
										if (debug) System.out.println("endTable");
										parent = parents.remove(parents.size() - 1);
										{
											cs = stack[--top];
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 18:
									// line 154 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("startWidgetSection");
									parents.add(parent);
									parent = widget;
									widget = null;
									{
										if (top == stack.length) {
											int[] newStack = new int[stack.length * 2];
											System.arraycopy(stack, 0, newStack, 0, stack.length);
											stack = newStack;
										}
										{
											stack[top++] = cs;
											cs = 83;
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 19:
									// line 161 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("endWidgetSection");
									widget = parent;
									parent = parents.remove(parents.size() - 1);
									{
										cs = stack[--top];
										_goto_targ = 2;
										if (true) continue _goto;
									}
								}
									break;
								case 20:
									// line 167 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("addCell");
									if (widget == null)
										cell = ((TableLayout)parent).addCell(toolkit.newEmptyWidget());
									else
										cell = ((TableLayout)parent).addCell(toolkit.wrap(widget));
								}
									break;
								case 21:
									// line 174 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("addWidget");
									toolkit.addChild(parent, toolkit.wrap(widget), widgetLayoutString);
									widgetLayoutString = null;
								}
									break;
								case 22:
									// line 179 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("widgetProperty: " + name + " = " + values);
									try {
										toolkit.setProperty(parent, name, values);
									} catch (RuntimeException ex) {
										throw new RuntimeException("Error setting property: " + name + "\nClass: " + parent.getClass()
											+ "\nValues: " + values, ex);
									}
									values.clear();
								}
									break;
								case 23:
									// line 199 "TableLayoutParser.rl"
								{
									hasColon = true;
								}
									break;
								// line 319 "../src/com/esotericsoftware/tablelayout/TableLayoutParser.java"
								}
							}
						}

					case 2:
						if (cs == 0) {
							_goto_targ = 5;
							continue _goto;
						}
						if (++p != pe) {
							_goto_targ = 1;
							continue _goto;
						}
					case 4:
					case 5:
					}
					break;
				}
			}

			// line 259 "TableLayoutParser.rl"

		} catch (RuntimeException ex) {
			parseRuntimeEx = ex;
		}

		if (p < pe) {
			int lineNumber = 1;
			for (int i = 0; i < p; i++)
				if (data[i] == '\n') lineNumber++;
			throw new IllegalArgumentException("Error parsing layout on line " + lineNumber + " near: "
				+ new String(data, p, pe - p), parseRuntimeEx);
		} else if (top > 0)
			throw new IllegalArgumentException("Error parsing layout (possibly an unmatched brace or quote): " + input,
				parseRuntimeEx);
	}

	// line 339 "../src/com/esotericsoftware/tablelayout/TableLayoutParser.java"
	private static byte[] init__tableLayout_actions_0 () {
		return new byte[] {0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10, 1, 12, 1, 13, 1, 14, 1, 15, 1, 16,
			1, 17, 1, 18, 1, 19, 1, 20, 1, 21, 1, 22, 2, 0, 2, 2, 0, 10, 2, 0, 12, 2, 0, 13, 2, 0, 15, 2, 1, 3, 2, 1, 4, 2, 1, 6, 2,
			1, 8, 2, 1, 9, 2, 1, 14, 2, 1, 22, 2, 1, 23, 2, 2, 3, 2, 2, 4, 2, 2, 6, 2, 2, 8, 2, 2, 9, 2, 2, 22, 2, 3, 16, 2, 4, 16,
			2, 5, 0, 2, 5, 16, 2, 6, 16, 2, 7, 0, 2, 7, 16, 2, 8, 16, 2, 9, 16, 2, 9, 17, 2, 9, 18, 2, 11, 21, 2, 12, 14, 2, 20, 0,
			2, 20, 16, 2, 20, 17, 2, 20, 18, 2, 21, 16, 2, 21, 18, 2, 21, 19, 2, 22, 16, 2, 22, 19, 3, 0, 1, 14, 3, 0, 1, 23, 3, 1,
			3, 16, 3, 1, 4, 16, 3, 1, 6, 16, 3, 1, 8, 16, 3, 1, 9, 16, 3, 1, 9, 17, 3, 1, 9, 18, 3, 1, 22, 16, 3, 1, 22, 19, 3, 2,
			3, 16, 3, 2, 4, 16, 3, 2, 6, 16, 3, 2, 8, 16, 3, 2, 9, 16, 3, 2, 9, 17, 3, 2, 9, 18, 3, 2, 22, 16, 3, 2, 22, 19, 3, 11,
			21, 16, 3, 11, 21, 18, 3, 11, 21, 19};
	}

	private static final byte _tableLayout_actions[] = init__tableLayout_actions_0();

	private static short[] init__tableLayout_key_offsets_0 () {
		return new short[] {0, 0, 16, 17, 18, 34, 50, 58, 59, 60, 72, 84, 97, 110, 123, 124, 125, 132, 142, 150, 166, 173, 178,
			180, 186, 192, 198, 199, 200, 204, 208, 209, 210, 226, 242, 255, 256, 257, 267, 282, 292, 300, 319, 329, 330, 331, 340,
			355, 370, 383, 384, 385, 394, 408, 422, 436, 451, 466, 479, 480, 481, 490, 500, 508, 526, 535, 545, 553, 571, 580, 596,
			612, 625, 626, 627, 637, 652, 662, 670, 689, 699, 700, 701, 717, 730, 731, 732, 747, 754, 759, 761, 767, 773, 779, 780,
			781, 785, 799, 800, 801, 815, 829, 843, 856, 857, 858, 866, 876, 884, 901, 909, 913, 916};
	}

	private static final short _tableLayout_key_offsets[] = init__tableLayout_key_offsets_0();

	private static char[] init__tableLayout_trans_keys_0 () {
		return new char[] {32, 39, 42, 45, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 40, 45, 60, 91, 123,
			125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 40, 45, 60, 91, 123, 125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 91,
			123, 125, 9, 13, 45, 45, 32, 39, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 91, 123, 9, 13, 48, 57, 65, 90, 97,
			122, 32, 39, 58, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 58, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39,
			45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 44, 91, 123, 9, 13, 39, 45, 46, 95, 48, 57, 65, 90, 97, 122,
			46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 44, 46, 91, 95, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 44, 91, 123,
			9, 13, 32, 58, 93, 9, 13, 58, 93, 32, 39, 58, 93, 9, 13, 32, 39, 58, 93, 9, 13, 32, 39, 58, 93, 9, 13, 39, 39, 32, 93,
			9, 13, 32, 60, 9, 13, 62, 62, 32, 39, 40, 45, 58, 91, 123, 125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 40, 45, 58, 91,
			123, 125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 40, 44,
			45, 91, 123, 125, 9, 13, 32, 39, 40, 45, 91, 123, 125, 9, 13, 48, 57, 65, 90, 97, 122, 39, 45, 46, 95, 48, 57, 65, 90,
			97, 122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 40, 44, 45, 46, 91, 95, 123, 125, 9, 13, 48, 57, 65, 90, 97, 122,
			32, 39, 40, 44, 45, 91, 123, 125, 9, 13, 62, 62, 32, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 58, 91, 123, 124, 9,
			13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 58, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 46, 95, 9, 13,
			48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 44, 45, 91, 123, 124, 9, 13, 32, 39, 45, 91, 123, 124, 9, 13, 48, 57, 65, 90,
			97, 122, 32, 39, 45, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97,
			122, 32, 39, 45, 58, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 58, 91, 123, 124, 9, 13, 48, 57, 65, 90,
			97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 44, 45, 91, 123, 124, 9, 13, 39, 45, 46,
			95, 48, 57, 65, 90, 97, 122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 44, 45, 46, 91, 95, 123, 124, 9, 13, 48, 57,
			65, 90, 97, 122, 32, 39, 44, 45, 91, 123, 124, 9, 13, 39, 45, 46, 95, 48, 57, 65, 90, 97, 122, 46, 95, 48, 57, 65, 90,
			97, 122, 32, 37, 39, 44, 45, 46, 91, 95, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 44, 45, 91, 123, 124, 9, 13,
			32, 39, 42, 45, 58, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 42, 45, 58, 91, 123, 124, 9, 13, 48, 57, 65,
			90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 42, 44, 45, 91, 123, 124, 9, 13, 32,
			39, 42, 45, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 39, 45, 46, 95, 48, 57, 65, 90, 97, 122, 46, 95, 48, 57, 65,
			90, 97, 122, 32, 37, 39, 42, 44, 45, 46, 91, 95, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 42, 44, 45, 91, 123,
			124, 9, 13, 62, 62, 32, 39, 42, 45, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 41, 91, 123, 9, 13, 48,
			57, 65, 90, 97, 122, 39, 39, 32, 39, 40, 41, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 41, 91, 123, 9, 13,
			32, 58, 93, 9, 13, 58, 93, 32, 39, 58, 93, 9, 13, 32, 39, 58, 93, 9, 13, 32, 39, 58, 93, 9, 13, 39, 39, 32, 93, 9, 13,
			32, 39, 40, 41, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 62, 62, 32, 39, 40, 41, 91, 123, 9, 13, 48, 57, 65, 90, 97,
			122, 32, 39, 41, 58, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 41, 58, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122,
			32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 41, 44, 91, 123, 9, 13, 39, 45, 46, 95, 48, 57, 65,
			90, 97, 122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 41, 44, 46, 91, 95, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32,
			39, 41, 44, 91, 123, 9, 13, 32, 60, 9, 13, 32, 9, 13, 0};
	}

	private static final char _tableLayout_trans_keys[] = init__tableLayout_trans_keys_0();

	private static byte[] init__tableLayout_single_lengths_0 () {
		return new byte[] {0, 8, 1, 1, 8, 8, 6, 1, 1, 4, 4, 5, 5, 5, 1, 1, 5, 4, 2, 8, 5, 3, 2, 4, 4, 4, 1, 1, 2, 2, 1, 1, 8, 8, 5,
			1, 1, 8, 7, 4, 2, 11, 8, 1, 1, 1, 7, 7, 5, 1, 1, 7, 6, 6, 6, 7, 7, 5, 1, 1, 7, 4, 2, 10, 7, 4, 2, 10, 7, 8, 8, 5, 1, 1,
			8, 7, 4, 2, 11, 8, 1, 1, 8, 5, 1, 1, 7, 5, 3, 2, 4, 4, 4, 1, 1, 2, 6, 1, 1, 6, 6, 6, 5, 1, 1, 6, 4, 2, 9, 6, 2, 1, 0};
	}

	private static final byte _tableLayout_single_lengths[] = init__tableLayout_single_lengths_0();

	private static byte[] init__tableLayout_range_lengths_0 () {
		return new byte[] {0, 4, 0, 0, 4, 4, 1, 0, 0, 4, 4, 4, 4, 4, 0, 0, 1, 3, 3, 4, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 4, 4, 4,
			0, 0, 1, 4, 3, 3, 4, 1, 0, 0, 4, 4, 4, 4, 0, 0, 1, 4, 4, 4, 4, 4, 4, 0, 0, 1, 3, 3, 4, 1, 3, 3, 4, 1, 4, 4, 4, 0, 0, 1,
			4, 3, 3, 4, 1, 0, 0, 4, 4, 0, 0, 4, 1, 1, 0, 1, 1, 1, 0, 0, 1, 4, 0, 0, 4, 4, 4, 4, 0, 0, 1, 3, 3, 4, 1, 1, 1, 0};
	}

	private static final byte _tableLayout_range_lengths[] = init__tableLayout_range_lengths_0();

	private static short[] init__tableLayout_index_offsets_0 () {
		return new short[] {0, 0, 13, 15, 17, 30, 43, 51, 53, 55, 64, 73, 83, 93, 103, 105, 107, 114, 122, 128, 141, 148, 153, 156,
			162, 168, 174, 176, 178, 182, 186, 188, 190, 203, 216, 226, 228, 230, 240, 252, 260, 266, 282, 292, 294, 296, 302, 314,
			326, 336, 338, 340, 349, 360, 371, 382, 394, 406, 416, 418, 420, 429, 437, 443, 458, 467, 475, 481, 496, 505, 518, 531,
			541, 543, 545, 555, 567, 575, 581, 597, 607, 609, 611, 624, 634, 636, 638, 650, 657, 662, 665, 671, 677, 683, 685, 687,
			691, 702, 704, 706, 717, 728, 739, 749, 751, 753, 761, 769, 775, 789, 797, 801, 804};
	}

	private static final short _tableLayout_index_offsets[] = init__tableLayout_index_offsets_0();

	private static byte[] init__tableLayout_trans_targs_0 () {
		return new byte[] {1, 2, 45, 7, 80, 21, 82, 53, 1, 69, 69, 69, 0, 4, 3, 4, 3, 5, 2, 6, 7, 43, 21, 4, 110, 5, 32, 32, 32, 0,
			5, 2, 6, 7, 43, 21, 4, 110, 5, 32, 32, 32, 0, 6, 2, 7, 21, 4, 110, 6, 0, 8, 0, 9, 0, 10, 2, 21, 4, 10, 11, 11, 11, 0,
			10, 2, 21, 4, 10, 11, 11, 11, 0, 12, 2, 13, 21, 4, 12, 11, 11, 11, 0, 12, 2, 13, 21, 4, 12, 11, 11, 11, 0, 13, 14, 18,
			19, 19, 13, 19, 19, 19, 0, 16, 15, 16, 15, 10, 2, 17, 21, 4, 10, 0, 14, 18, 19, 19, 19, 19, 19, 0, 19, 19, 19, 19, 19,
			0, 10, 20, 2, 17, 19, 21, 19, 4, 10, 19, 19, 19, 0, 10, 2, 17, 21, 4, 10, 0, 21, 23, 4, 21, 22, 23, 4, 22, 25, 26, 0, 4,
			25, 24, 24, 0, 0, 4, 24, 24, 25, 26, 0, 4, 25, 24, 28, 27, 28, 27, 28, 4, 28, 0, 29, 30, 29, 0, 111, 31, 111, 31, 33, 2,
			6, 7, 34, 21, 4, 110, 33, 32, 32, 32, 0, 33, 2, 6, 7, 34, 21, 4, 110, 33, 32, 32, 32, 0, 34, 35, 40, 41, 41, 34, 41, 41,
			41, 0, 37, 36, 37, 36, 38, 2, 6, 39, 7, 21, 4, 110, 38, 0, 38, 2, 6, 7, 21, 4, 110, 38, 32, 32, 32, 0, 35, 40, 41, 41,
			41, 41, 41, 0, 41, 41, 41, 41, 41, 0, 38, 42, 2, 6, 39, 7, 41, 21, 41, 4, 110, 38, 41, 41, 41, 0, 38, 2, 6, 39, 7, 21,
			4, 110, 38, 0, 38, 44, 38, 44, 45, 45, 46, 46, 46, 0, 47, 2, 7, 48, 21, 4, 53, 47, 46, 46, 46, 0, 47, 2, 7, 48, 21, 4,
			53, 47, 46, 46, 46, 0, 48, 49, 66, 67, 67, 48, 67, 67, 67, 0, 51, 50, 51, 50, 52, 2, 65, 7, 21, 4, 53, 52, 0, 52, 2, 7,
			21, 4, 53, 52, 46, 46, 46, 0, 54, 2, 7, 21, 4, 53, 54, 55, 55, 55, 0, 54, 2, 7, 21, 4, 53, 54, 55, 55, 55, 0, 56, 2, 7,
			57, 21, 4, 53, 56, 55, 55, 55, 0, 56, 2, 7, 57, 21, 4, 53, 56, 55, 55, 55, 0, 57, 58, 62, 63, 63, 57, 63, 63, 63, 0, 60,
			59, 60, 59, 54, 2, 61, 7, 21, 4, 53, 54, 0, 58, 62, 63, 63, 63, 63, 63, 0, 63, 63, 63, 63, 63, 0, 54, 64, 2, 61, 7, 63,
			21, 63, 4, 53, 54, 63, 63, 63, 0, 54, 2, 61, 7, 21, 4, 53, 54, 0, 49, 66, 67, 67, 67, 67, 67, 0, 67, 67, 67, 67, 67, 0,
			52, 68, 2, 65, 7, 67, 21, 67, 4, 53, 52, 67, 67, 67, 0, 52, 2, 65, 7, 21, 4, 53, 52, 0, 70, 2, 45, 7, 71, 21, 4, 53, 70,
			69, 69, 69, 0, 70, 2, 45, 7, 71, 21, 4, 53, 70, 69, 69, 69, 0, 71, 72, 77, 78, 78, 71, 78, 78, 78, 0, 74, 73, 74, 73,
			75, 2, 45, 76, 7, 21, 4, 53, 75, 0, 75, 2, 45, 7, 21, 4, 53, 75, 69, 69, 69, 0, 72, 77, 78, 78, 78, 78, 78, 0, 78, 78,
			78, 78, 78, 0, 75, 79, 2, 45, 76, 7, 78, 21, 78, 4, 53, 75, 78, 78, 78, 0, 75, 2, 45, 76, 7, 21, 4, 53, 75, 0, 75, 81,
			75, 81, 82, 2, 45, 7, 80, 21, 4, 53, 82, 69, 69, 69, 0, 83, 84, 112, 88, 86, 83, 100, 100, 100, 0, 86, 85, 86, 85, 86,
			84, 87, 112, 97, 88, 86, 86, 96, 96, 96, 0, 87, 84, 112, 88, 86, 87, 0, 88, 90, 86, 88, 89, 90, 86, 89, 92, 93, 0, 86,
			92, 91, 91, 0, 0, 86, 91, 91, 92, 93, 0, 86, 92, 91, 95, 94, 95, 94, 95, 86, 95, 0, 96, 84, 87, 112, 88, 86, 87, 96, 96,
			96, 0, 99, 98, 99, 98, 99, 84, 87, 112, 88, 86, 99, 96, 96, 96, 0, 101, 84, 112, 102, 88, 86, 101, 100, 100, 100, 0,
			101, 84, 112, 102, 88, 86, 101, 100, 100, 100, 0, 102, 103, 107, 108, 108, 102, 108, 108, 108, 0, 105, 104, 105, 104,
			83, 84, 112, 106, 88, 86, 83, 0, 103, 107, 108, 108, 108, 108, 108, 0, 108, 108, 108, 108, 108, 0, 83, 109, 84, 112,
			106, 108, 88, 108, 86, 83, 108, 108, 108, 0, 83, 84, 112, 106, 88, 86, 83, 0, 29, 30, 29, 0, 111, 111, 0, 0, 0};
	}

	private static final byte _tableLayout_trans_targs[] = init__tableLayout_trans_targs_0();

	private static short[] init__tableLayout_trans_actions_0 () {
		return new short[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 57, 1, 29, 0, 39, 39, 150, 39, 39, 39, 144, 147, 39, 141, 141,
			141, 0, 0, 0, 35, 0, 0, 0, 31, 33, 0, 1, 1, 1, 0, 0, 0, 0, 0, 31, 33, 0, 0, 0, 0, 0, 0, 15, 15, 15, 120, 15, 117, 117,
			117, 0, 0, 0, 0, 31, 0, 1, 1, 1, 0, 69, 69, 3, 69, 188, 69, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0,
			1, 1, 1, 0, 45, 1, 5, 0, 17, 17, 0, 17, 123, 17, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 93, 0, 93, 5, 0, 93, 0,
			224, 93, 0, 0, 0, 0, 93, 93, 5, 93, 224, 93, 0, 1, 172, 168, 1, 1, 81, 75, 0, 1, 0, 0, 27, 1, 1, 23, 0, 0, 138, 23, 0,
			51, 0, 0, 138, 51, 1, 54, 1, 25, 0, 0, 27, 0, 0, 0, 0, 0, 0, 48, 1, 21, 0, 72, 72, 200, 72, 3, 72, 192, 196, 72, 0, 0,
			0, 0, 0, 0, 35, 0, 0, 0, 31, 33, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 45, 1, 5, 0, 19, 19, 132, 0, 19, 19, 126,
			129, 19, 0, 0, 0, 35, 0, 0, 31, 33, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 96, 0, 96, 236, 5, 96, 0,
			96, 0, 228, 232, 96, 0, 0, 0, 0, 96, 96, 236, 5, 96, 96, 228, 232, 96, 0, 48, 1, 21, 0, 0, 0, 1, 1, 1, 0, 63, 63, 63, 3,
			63, 180, 63, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 45, 1, 5, 0, 9, 9, 0, 9,
			9, 105, 9, 9, 0, 0, 0, 0, 0, 31, 0, 0, 1, 1, 1, 0, 11, 11, 11, 11, 111, 11, 11, 108, 108, 108, 0, 0, 0, 0, 0, 31, 0, 0,
			1, 1, 1, 0, 66, 66, 66, 3, 66, 184, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1,
			0, 45, 1, 5, 0, 13, 13, 0, 13, 13, 114, 13, 13, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 90, 0, 90, 5, 90, 0, 90, 0,
			220, 90, 90, 0, 0, 0, 0, 90, 90, 5, 90, 90, 220, 90, 90, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 87, 0, 87, 5, 87,
			0, 87, 0, 216, 87, 87, 0, 0, 0, 0, 87, 87, 5, 87, 87, 216, 87, 87, 0, 60, 60, 60, 60, 3, 60, 176, 60, 60, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 31, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 45, 1, 5, 0, 7, 7, 7, 0, 7, 7, 102, 7, 7, 0, 0, 0, 0,
			0, 0, 31, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 84, 0, 84, 84, 5, 84, 0, 84, 0, 212, 84, 84, 0, 0,
			0, 0, 84, 84, 84, 5, 84, 84, 212, 84, 84, 0, 48, 1, 21, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 1, 1, 1, 0, 0, 0, 37, 0, 31, 0,
			1, 1, 1, 0, 57, 1, 29, 0, 0, 41, 156, 159, 0, 41, 153, 0, 1, 1, 1, 0, 0, 0, 37, 0, 31, 0, 0, 1, 172, 168, 1, 1, 81, 75,
			0, 1, 0, 0, 27, 1, 1, 23, 0, 0, 138, 23, 0, 51, 0, 0, 138, 51, 1, 54, 1, 25, 0, 0, 27, 0, 0, 0, 135, 252, 256, 135, 248,
			135, 0, 0, 0, 0, 48, 1, 21, 0, 0, 41, 156, 159, 41, 153, 0, 1, 1, 1, 0, 78, 78, 208, 3, 78, 204, 78, 0, 0, 0, 0, 0, 0,
			37, 0, 0, 31, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 45, 1, 5, 0, 43, 43, 165, 0, 43, 162, 43, 0, 0, 1, 1, 1, 1,
			1, 1, 0, 0, 0, 0, 0, 0, 0, 99, 0, 99, 244, 5, 0, 99, 0, 240, 99, 0, 0, 0, 0, 99, 99, 244, 5, 99, 240, 99, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0};
	}

	private static final short _tableLayout_trans_actions[] = init__tableLayout_trans_actions_0();

	static final int tableLayout_start = 1;
	static final int tableLayout_first_final = 110;
	static final int tableLayout_error = 0;

	static final int tableLayout_en_widgetSection = 83;
	static final int tableLayout_en_main = 1;
	static final int tableLayout_en_main_table = 82;

	// line 274 "TableLayoutParser.rl"
}
