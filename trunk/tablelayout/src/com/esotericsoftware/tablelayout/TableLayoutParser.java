// line 1 "TableLayoutParser.rl"
// Do not edit this file! Generated by Ragel.

package com.esotericsoftware.tablelayout;

import java.util.ArrayList;

class TableLayoutParser {
	static public void parse (BaseTableLayout table, String input) {
		char[] data = (input + "  ").toCharArray();
		int cs, p = 0, pe = data.length, eof = pe, top = 0;
		int[] stack = new int[4];

		int s = 0;
		String name = null;
		String widgetLayoutString = null;
		String className = null;

		int columnDefaultCount = 0;
		ArrayList<String> values = new ArrayList(4);
		ArrayList<Object> parents = new ArrayList(8);
		Cell cell = null, rowDefaults = null, columnDefaults = null;
		Object parent = table, widget = null;
		RuntimeException parseRuntimeEx = null;
		boolean hasColon = false;

		boolean debug = true;
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
									// line 41 "TableLayoutParser.rl"
								{
									s = p;
								}
									break;
								case 1:
									// line 42 "TableLayoutParser.rl"
								{
									name = new String(data, s, p - s);
									s = p;
								}
									break;
								case 2:
									// line 46 "TableLayoutParser.rl"
								{
									values.add(new String(data, s, p - s));
								}
									break;
								case 3:
									// line 49 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("tableProperty: " + name + " = " + values);
									((BaseTableLayout)parent).setTableProperty(name, values);
									values.clear();
									name = null;
								}
									break;
								case 4:
									// line 55 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("cellDefaultProperty: " + name + " = " + values);
									table.setCellProperty(((BaseTableLayout)parent).cellDefaults, name, values);
									values.clear();
									name = null;
								}
									break;
								case 5:
									// line 61 "TableLayoutParser.rl"
								{
									columnDefaults = ((BaseTableLayout)parent).getColumnDefaults(columnDefaultCount++);
								}
									break;
								case 6:
									// line 64 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("columnDefaultProperty: " + name + " = " + values);
									table.setCellProperty(columnDefaults, name, values);
									values.clear();
									name = null;
								}
									break;
								case 7:
									// line 70 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("startRow");
									rowDefaults = ((BaseTableLayout)parent).startRow();
								}
									break;
								case 8:
									// line 74 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("rowDefaultValue: " + name + " = " + values);
									table.setCellProperty(rowDefaults, name, values);
									values.clear();
									name = null;
								}
									break;
								case 9:
									// line 80 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("cellProperty: " + name + " = " + values);
									table.setCellProperty(cell, name, values);
									values.clear();
									name = null;
								}
									break;
								case 10:
									// line 86 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("widgetLayoutString: " + new String(data, s, p - s).trim());
									widgetLayoutString = new String(data, s, p - s).trim();
								}
									break;
								case 11:
									// line 90 "TableLayoutParser.rl"
								{
									className = new String(data, s, p - s);
								}
									break;
								case 12:
									// line 93 "TableLayoutParser.rl"
								{
									if (debug)
										System.out.println("newWidget, name:" + name + " class:" + className + " widget:" + widget);
									if (widget != null) { // 'label' or ['label'] or [name:'label']
										if (name != null && name.length() > 0) table.register(name, widget);
									} else if (className == null) {
										if (name.length() > 0) {
											if (hasColon) { // [name:]
												widget = table.wrap(null);
												table.register(name, widget);
											} else { // [name]
												widget = table.getWidget(name);
												if (widget == null) {
													// Try the widget name as a class name.
													try {
														widget = table.newWidget(name);
													} catch (RuntimeException ex) {
														throw new IllegalArgumentException("Widget not found with name: " + name);
													}
												}
											}
										} else
											// []
											widget = table.wrap(null);
									} else { // [:class] and [name:class]
										widget = table.newWidget(className);
										className = null;
										if (name.length() > 0) table.register(name, widget);
									}
									name = null;
								}
									break;
								case 13:
									// line 122 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("newLabel: " + new String(data, s, p - s));
									widget = table.wrap(new String(data, s, p - s));
								}
									break;
								case 14:
									// line 126 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("startTable, name:" + name);
									parents.add(parent);
									parent = table.newTableLayout(parent instanceof BaseTableLayout ? (BaseTableLayout)parent : null);
									if (name != null) { // [name:{}]
										table.register(name, ((BaseTableLayout)parent).getTable());
										name = null;
									}
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
											cs = 69;
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 15:
									// line 138 "TableLayoutParser.rl"
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
								case 16:
									// line 146 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("startStack, name:" + name);
									parents.add(parent);
									parent = table.newStack();
									if (name != null) { // [name:<>]
										table.register(name, parent);
										name = null;
									}
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
											cs = 74;
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 17:
									// line 158 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("endStack");
									widget = parent;
									parent = parents.remove(parents.size() - 1);
									{
										cs = stack[--top];
										_goto_targ = 2;
										if (true) continue _goto;
									}
								}
									break;
								case 18:
									// line 164 "TableLayoutParser.rl"
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
											cs = 86;
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 19:
									// line 171 "TableLayoutParser.rl"
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
									// line 177 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("addCell");
									cell = ((BaseTableLayout)parent).addCell(table.wrap(widget));
								}
									break;
								case 21:
									// line 181 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("addWidget");
									table.addChild(parent, table.wrap(widget), widgetLayoutString);
									widgetLayoutString = null;
								}
									break;
								case 22:
									// line 186 "TableLayoutParser.rl"
								{
									if (debug) System.out.println("widgetProperty: " + name + " = " + values);
									table.setProperty(parent, name, values);
									values.clear();
									name = null;
								}
									break;
								case 23:
									// line 203 "TableLayoutParser.rl"
								{
									widget = null;
									hasColon = false;
								}
									break;
								case 24:
									// line 204 "TableLayoutParser.rl"
								{
									hasColon = true;
								}
									break;
								// line 333 "../src/com/esotericsoftware/tablelayout/TableLayoutParser.java"
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

			// line 257 "TableLayoutParser.rl"

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

	// line 353 "../src/com/esotericsoftware/tablelayout/TableLayoutParser.java"
	private static byte[] init__tableLayout_actions_0 () {
		return new byte[] {0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 11, 1, 12, 1, 13, 1, 14, 1, 15, 1, 16,
			1, 17, 1, 18, 1, 19, 1, 20, 1, 21, 1, 22, 1, 23, 2, 0, 2, 2, 0, 11, 2, 0, 13, 2, 1, 3, 2, 1, 4, 2, 1, 6, 2, 1, 8, 2, 1,
			9, 2, 1, 12, 2, 1, 22, 2, 1, 24, 2, 2, 3, 2, 2, 4, 2, 2, 6, 2, 2, 8, 2, 2, 9, 2, 2, 22, 2, 3, 14, 2, 3, 16, 2, 3, 23, 2,
			4, 14, 2, 4, 16, 2, 4, 23, 2, 5, 0, 2, 5, 14, 2, 5, 16, 2, 5, 23, 2, 6, 14, 2, 6, 16, 2, 6, 23, 2, 7, 0, 2, 7, 14, 2, 7,
			16, 2, 7, 23, 2, 8, 14, 2, 8, 16, 2, 8, 23, 2, 9, 14, 2, 9, 15, 2, 9, 16, 2, 9, 18, 2, 9, 23, 2, 10, 21, 2, 11, 12, 2,
			16, 0, 2, 20, 0, 2, 20, 14, 2, 20, 15, 2, 20, 16, 2, 20, 18, 2, 20, 23, 2, 21, 14, 2, 21, 16, 2, 21, 17, 2, 21, 18, 2,
			21, 19, 2, 21, 23, 2, 22, 14, 2, 22, 16, 2, 22, 19, 2, 22, 23, 3, 0, 1, 12, 3, 0, 1, 24, 3, 1, 3, 14, 3, 1, 3, 16, 3, 1,
			3, 23, 3, 1, 4, 14, 3, 1, 4, 16, 3, 1, 4, 23, 3, 1, 6, 14, 3, 1, 6, 16, 3, 1, 6, 23, 3, 1, 8, 14, 3, 1, 8, 16, 3, 1, 8,
			23, 3, 1, 9, 14, 3, 1, 9, 15, 3, 1, 9, 16, 3, 1, 9, 18, 3, 1, 9, 23, 3, 1, 22, 14, 3, 1, 22, 16, 3, 1, 22, 19, 3, 1, 22,
			23, 3, 2, 3, 14, 3, 2, 3, 16, 3, 2, 3, 23, 3, 2, 4, 14, 3, 2, 4, 16, 3, 2, 4, 23, 3, 2, 6, 14, 3, 2, 6, 16, 3, 2, 6, 23,
			3, 2, 8, 14, 3, 2, 8, 16, 3, 2, 8, 23, 3, 2, 9, 14, 3, 2, 9, 15, 3, 2, 9, 16, 3, 2, 9, 18, 3, 2, 9, 23, 3, 2, 22, 14, 3,
			2, 22, 16, 3, 2, 22, 19, 3, 2, 22, 23, 3, 10, 21, 14, 3, 10, 21, 16, 3, 10, 21, 18, 3, 10, 21, 19, 3, 10, 21, 23};
	}

	private static final byte _tableLayout_actions[] = init__tableLayout_actions_0();

	private static short[] init__tableLayout_key_offsets_0 () {
		return new short[] {0, 0, 16, 17, 18, 34, 50, 59, 60, 61, 74, 87, 101, 115, 128, 129, 130, 138, 148, 156, 173, 181, 186,
			188, 196, 203, 211, 212, 213, 217, 234, 251, 264, 265, 266, 277, 287, 295, 315, 326, 335, 351, 367, 380, 381, 382, 392,
			407, 422, 437, 453, 469, 482, 483, 484, 494, 504, 512, 531, 541, 551, 559, 578, 588, 605, 622, 635, 636, 637, 648, 664,
			674, 682, 702, 713, 721, 722, 723, 731, 736, 738, 746, 753, 761, 762, 763, 767, 781, 782, 783, 798, 806, 811, 813, 821,
			828, 836, 837, 838, 842, 857, 872, 887, 900, 901, 902, 911, 921, 929, 947, 956, 959, 959};
	}

	private static final short _tableLayout_key_offsets[] = init__tableLayout_key_offsets_0();

	private static char[] init__tableLayout_trans_keys_0 () {
		return new char[] {32, 39, 42, 45, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 40, 45, 60, 91, 123,
			125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 40, 45, 60, 91, 123, 125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 60,
			91, 123, 125, 9, 13, 45, 45, 32, 39, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 60, 91, 123, 9, 13, 48, 57,
			65, 90, 97, 122, 32, 39, 58, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 58, 60, 91, 123, 9, 13, 48, 57, 65,
			90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 44, 60, 91, 123, 9, 13, 39, 45, 46, 95,
			48, 57, 65, 90, 97, 122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 44, 46, 60, 91, 95, 123, 9, 13, 48, 57, 65, 90,
			97, 122, 32, 39, 44, 60, 91, 123, 9, 13, 32, 58, 93, 9, 13, 58, 93, 32, 39, 58, 60, 93, 123, 9, 13, 32, 39, 58, 93, 123,
			9, 13, 32, 39, 58, 60, 93, 123, 9, 13, 39, 39, 32, 93, 9, 13, 32, 39, 40, 45, 58, 60, 91, 123, 125, 9, 13, 48, 57, 65,
			90, 97, 122, 32, 39, 40, 45, 58, 60, 91, 123, 125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57,
			65, 90, 97, 122, 39, 39, 32, 39, 40, 44, 45, 60, 91, 123, 125, 9, 13, 39, 45, 46, 95, 48, 57, 65, 90, 97, 122, 46, 95,
			48, 57, 65, 90, 97, 122, 32, 37, 39, 40, 44, 45, 46, 60, 91, 95, 123, 125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 40,
			44, 45, 60, 91, 123, 125, 9, 13, 32, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 58, 60, 91, 123, 124, 9, 13, 48, 57,
			65, 90, 97, 122, 32, 39, 45, 58, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57,
			65, 90, 97, 122, 39, 39, 32, 39, 44, 45, 60, 91, 123, 124, 9, 13, 32, 39, 45, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90,
			97, 122, 32, 39, 45, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 60, 91, 123, 124, 9, 13, 48, 57, 65,
			90, 97, 122, 32, 39, 45, 58, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 58, 60, 91, 123, 124, 9, 13,
			48, 57, 65, 90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32, 39, 44, 45, 60, 91, 123, 124,
			9, 13, 39, 45, 46, 95, 48, 57, 65, 90, 97, 122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 44, 45, 46, 60, 91, 95,
			123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 44, 45, 60, 91, 123, 124, 9, 13, 39, 45, 46, 95, 48, 57, 65, 90, 97,
			122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 44, 45, 46, 60, 91, 95, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32,
			39, 44, 45, 60, 91, 123, 124, 9, 13, 32, 39, 42, 45, 58, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 42,
			45, 58, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39,
			32, 39, 42, 44, 45, 60, 91, 123, 124, 9, 13, 32, 39, 42, 45, 60, 91, 123, 124, 9, 13, 48, 57, 65, 90, 97, 122, 39, 45,
			46, 95, 48, 57, 65, 90, 97, 122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39, 42, 44, 45, 46, 60, 91, 95, 123, 124, 9,
			13, 48, 57, 65, 90, 97, 122, 32, 39, 42, 44, 45, 60, 91, 123, 124, 9, 13, 32, 39, 60, 62, 91, 123, 9, 13, 39, 39, 32,
			39, 60, 62, 91, 123, 9, 13, 32, 58, 93, 9, 13, 58, 93, 32, 39, 58, 60, 93, 123, 9, 13, 32, 39, 58, 93, 123, 9, 13, 32,
			39, 58, 60, 93, 123, 9, 13, 39, 39, 32, 93, 9, 13, 32, 39, 41, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32,
			39, 40, 41, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 41, 60, 91, 123, 9, 13, 32, 58, 93, 9, 13, 58, 93, 32,
			39, 58, 60, 93, 123, 9, 13, 32, 39, 58, 93, 123, 9, 13, 32, 39, 58, 60, 93, 123, 9, 13, 39, 39, 32, 93, 9, 13, 32, 39,
			40, 41, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 41, 58, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32,
			39, 41, 58, 60, 91, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 45, 46, 95, 9, 13, 48, 57, 65, 90, 97, 122, 39, 39, 32,
			39, 41, 44, 60, 91, 123, 9, 13, 39, 45, 46, 95, 48, 57, 65, 90, 97, 122, 46, 95, 48, 57, 65, 90, 97, 122, 32, 37, 39,
			41, 44, 46, 60, 91, 95, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 39, 41, 44, 60, 91, 123, 9, 13, 32, 9, 13, 0};
	}

	private static final char _tableLayout_trans_keys[] = init__tableLayout_trans_keys_0();

	private static byte[] init__tableLayout_single_lengths_0 () {
		return new byte[] {0, 8, 1, 1, 8, 8, 7, 1, 1, 5, 5, 6, 6, 5, 1, 1, 6, 4, 2, 9, 6, 3, 2, 6, 5, 6, 1, 1, 2, 9, 9, 5, 1, 1, 9,
			4, 2, 12, 9, 1, 8, 8, 5, 1, 1, 8, 7, 7, 7, 8, 8, 5, 1, 1, 8, 4, 2, 11, 8, 4, 2, 11, 8, 9, 9, 5, 1, 1, 9, 8, 4, 2, 12, 9,
			6, 1, 1, 6, 3, 2, 6, 5, 6, 1, 1, 2, 6, 1, 1, 7, 6, 3, 2, 6, 5, 6, 1, 1, 2, 7, 7, 7, 5, 1, 1, 7, 4, 2, 10, 7, 1, 0, 0};
	}

	private static final byte _tableLayout_single_lengths[] = init__tableLayout_single_lengths_0();

	private static byte[] init__tableLayout_range_lengths_0 () {
		return new byte[] {0, 4, 0, 0, 4, 4, 1, 0, 0, 4, 4, 4, 4, 4, 0, 0, 1, 3, 3, 4, 1, 1, 0, 1, 1, 1, 0, 0, 1, 4, 4, 4, 0, 0, 1,
			3, 3, 4, 1, 4, 4, 4, 4, 0, 0, 1, 4, 4, 4, 4, 4, 4, 0, 0, 1, 3, 3, 4, 1, 3, 3, 4, 1, 4, 4, 4, 0, 0, 1, 4, 3, 3, 4, 1, 1,
			0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 4, 0, 0, 4, 1, 1, 0, 1, 1, 1, 0, 0, 1, 4, 4, 4, 4, 0, 0, 1, 3, 3, 4, 1, 1, 0, 0};
	}

	private static final byte _tableLayout_range_lengths[] = init__tableLayout_range_lengths_0();

	private static short[] init__tableLayout_index_offsets_0 () {
		return new short[] {0, 0, 13, 15, 17, 30, 43, 52, 54, 56, 66, 76, 87, 98, 108, 110, 112, 120, 128, 134, 148, 156, 161, 164,
			172, 179, 187, 189, 191, 195, 209, 223, 233, 235, 237, 248, 256, 262, 279, 290, 296, 309, 322, 332, 334, 336, 346, 358,
			370, 382, 395, 408, 418, 420, 422, 432, 440, 446, 462, 472, 480, 486, 502, 512, 526, 540, 550, 552, 554, 565, 578, 586,
			592, 609, 620, 628, 630, 632, 640, 645, 648, 656, 663, 671, 673, 675, 679, 690, 692, 694, 706, 714, 719, 722, 730, 737,
			745, 747, 749, 753, 765, 777, 789, 799, 801, 803, 812, 820, 826, 841, 850, 853, 854};
	}

	private static final short _tableLayout_index_offsets[] = init__tableLayout_index_offsets_0();

	private static byte[] init__tableLayout_trans_targs_0 () {
		return new byte[] {1, 2, 39, 7, 4, 21, 69, 47, 1, 63, 63, 63, 0, 4, 3, 4, 3, 5, 2, 6, 7, 4, 21, 4, 110, 5, 29, 29, 29, 0,
			5, 2, 6, 7, 4, 21, 4, 110, 5, 29, 29, 29, 0, 6, 2, 7, 4, 21, 4, 110, 6, 0, 8, 0, 9, 0, 10, 2, 4, 21, 4, 10, 11, 11, 11,
			0, 10, 2, 4, 21, 4, 10, 11, 11, 11, 0, 12, 2, 13, 4, 21, 4, 12, 11, 11, 11, 0, 12, 2, 13, 4, 21, 4, 12, 11, 11, 11, 0,
			13, 14, 18, 19, 19, 13, 19, 19, 19, 0, 16, 15, 16, 15, 10, 2, 17, 4, 21, 4, 10, 0, 14, 18, 19, 19, 19, 19, 19, 0, 19,
			19, 19, 19, 19, 0, 10, 20, 2, 17, 19, 4, 21, 19, 4, 10, 19, 19, 19, 0, 10, 2, 17, 4, 21, 4, 10, 0, 21, 23, 4, 21, 22,
			23, 4, 22, 25, 26, 0, 24, 4, 28, 25, 24, 24, 0, 0, 4, 0, 24, 24, 25, 26, 0, 24, 4, 28, 25, 24, 28, 27, 28, 27, 28, 4,
			28, 0, 30, 2, 6, 7, 31, 4, 21, 4, 110, 30, 29, 29, 29, 0, 30, 2, 6, 7, 31, 4, 21, 4, 110, 30, 29, 29, 29, 0, 31, 32, 36,
			37, 37, 31, 37, 37, 37, 0, 34, 33, 34, 33, 5, 2, 6, 35, 7, 4, 21, 4, 110, 5, 0, 32, 36, 37, 37, 37, 37, 37, 0, 37, 37,
			37, 37, 37, 0, 5, 38, 2, 6, 35, 7, 37, 4, 21, 37, 4, 110, 5, 37, 37, 37, 0, 5, 2, 6, 35, 7, 4, 21, 4, 110, 5, 0, 39, 39,
			40, 40, 40, 0, 41, 2, 7, 42, 4, 21, 4, 47, 41, 40, 40, 40, 0, 41, 2, 7, 42, 4, 21, 4, 47, 41, 40, 40, 40, 0, 42, 43, 60,
			61, 61, 42, 61, 61, 61, 0, 45, 44, 45, 44, 46, 2, 59, 7, 4, 21, 4, 47, 46, 0, 46, 2, 7, 4, 21, 4, 47, 46, 40, 40, 40, 0,
			48, 2, 7, 4, 21, 4, 47, 48, 49, 49, 49, 0, 48, 2, 7, 4, 21, 4, 47, 48, 49, 49, 49, 0, 50, 2, 7, 51, 4, 21, 4, 47, 50,
			49, 49, 49, 0, 50, 2, 7, 51, 4, 21, 4, 47, 50, 49, 49, 49, 0, 51, 52, 56, 57, 57, 51, 57, 57, 57, 0, 54, 53, 54, 53, 48,
			2, 55, 7, 4, 21, 4, 47, 48, 0, 52, 56, 57, 57, 57, 57, 57, 0, 57, 57, 57, 57, 57, 0, 48, 58, 2, 55, 7, 57, 4, 21, 57, 4,
			47, 48, 57, 57, 57, 0, 48, 2, 55, 7, 4, 21, 4, 47, 48, 0, 43, 60, 61, 61, 61, 61, 61, 0, 61, 61, 61, 61, 61, 0, 46, 62,
			2, 59, 7, 61, 4, 21, 61, 4, 47, 46, 61, 61, 61, 0, 46, 2, 59, 7, 4, 21, 4, 47, 46, 0, 64, 2, 39, 7, 65, 4, 21, 4, 47,
			64, 63, 63, 63, 0, 64, 2, 39, 7, 65, 4, 21, 4, 47, 64, 63, 63, 63, 0, 65, 66, 71, 72, 72, 65, 72, 72, 72, 0, 68, 67, 68,
			67, 69, 2, 39, 70, 7, 4, 21, 4, 47, 69, 0, 69, 2, 39, 7, 4, 21, 4, 47, 69, 63, 63, 63, 0, 66, 71, 72, 72, 72, 72, 72, 0,
			72, 72, 72, 72, 72, 0, 69, 73, 2, 39, 70, 7, 72, 4, 21, 72, 4, 47, 69, 72, 72, 72, 0, 69, 2, 39, 70, 7, 4, 21, 4, 47,
			69, 0, 74, 75, 77, 111, 78, 77, 74, 0, 77, 76, 77, 76, 74, 75, 77, 111, 78, 77, 74, 0, 78, 80, 77, 78, 79, 80, 77, 79,
			82, 83, 0, 81, 77, 85, 82, 81, 81, 0, 0, 77, 0, 81, 81, 82, 83, 0, 81, 77, 85, 82, 81, 85, 84, 85, 84, 85, 77, 85, 0,
			86, 87, 112, 89, 91, 89, 86, 100, 100, 100, 0, 89, 88, 89, 88, 89, 87, 90, 112, 89, 91, 89, 89, 99, 99, 99, 0, 90, 87,
			112, 89, 91, 89, 90, 0, 91, 93, 89, 91, 92, 93, 89, 92, 95, 96, 0, 94, 89, 98, 95, 94, 94, 0, 0, 89, 0, 94, 94, 95, 96,
			0, 94, 89, 98, 95, 94, 98, 97, 98, 97, 98, 89, 98, 0, 99, 87, 90, 112, 89, 91, 89, 90, 99, 99, 99, 0, 101, 87, 112, 102,
			89, 91, 89, 101, 100, 100, 100, 0, 101, 87, 112, 102, 89, 91, 89, 101, 100, 100, 100, 0, 102, 103, 107, 108, 108, 102,
			108, 108, 108, 0, 105, 104, 105, 104, 86, 87, 112, 106, 89, 91, 89, 86, 0, 103, 107, 108, 108, 108, 108, 108, 0, 108,
			108, 108, 108, 108, 0, 86, 109, 87, 112, 106, 108, 89, 91, 108, 89, 86, 108, 108, 108, 0, 86, 87, 112, 106, 89, 91, 89,
			86, 0, 110, 110, 0, 0, 0, 0};
	}

	private static final byte _tableLayout_trans_targs[] = init__tableLayout_trans_targs_0();

	private static short[] init__tableLayout_trans_actions_0 () {
		return new short[] {0, 0, 0, 0, 31, 45, 0, 0, 0, 1, 1, 1, 0, 53, 1, 25, 0, 39, 39, 194, 39, 191, 197, 185, 188, 39, 182,
			182, 182, 0, 0, 0, 35, 0, 31, 45, 27, 29, 0, 1, 1, 1, 0, 0, 0, 0, 31, 45, 27, 29, 0, 0, 0, 0, 0, 0, 15, 15, 143, 146,
			140, 15, 137, 137, 137, 0, 0, 0, 31, 45, 27, 0, 1, 1, 1, 0, 65, 65, 3, 278, 282, 274, 65, 0, 0, 0, 0, 0, 0, 0, 31, 45,
			27, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 47, 1, 5, 0, 17, 17, 0, 152, 155, 149, 17, 0, 0, 1, 1, 1, 1, 1, 1, 0,
			0, 0, 0, 0, 0, 0, 89, 0, 89, 5, 0, 362, 366, 0, 358, 89, 0, 0, 0, 0, 89, 89, 5, 362, 366, 358, 89, 0, 1, 234, 230, 1, 1,
			77, 71, 0, 1, 0, 0, 179, 23, 27, 1, 1, 21, 0, 0, 176, 0, 21, 0, 50, 0, 0, 179, 176, 27, 50, 1, 53, 1, 25, 0, 0, 23, 0,
			0, 68, 68, 298, 68, 3, 294, 302, 286, 290, 68, 0, 0, 0, 0, 0, 0, 35, 0, 0, 31, 45, 27, 29, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1,
			0, 1, 1, 1, 0, 47, 1, 5, 0, 19, 19, 167, 0, 19, 164, 170, 158, 161, 19, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 92,
			0, 92, 382, 5, 92, 0, 378, 386, 0, 370, 374, 92, 0, 0, 0, 0, 92, 92, 382, 5, 92, 378, 386, 370, 374, 92, 0, 0, 0, 1, 1,
			1, 0, 59, 59, 59, 3, 254, 258, 250, 59, 59, 0, 0, 0, 0, 0, 0, 0, 0, 31, 45, 27, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1,
			1, 1, 0, 47, 1, 5, 0, 9, 9, 0, 9, 110, 113, 107, 9, 9, 0, 0, 0, 0, 31, 45, 27, 0, 0, 1, 1, 1, 0, 11, 11, 11, 122, 125,
			119, 11, 11, 116, 116, 116, 0, 0, 0, 0, 31, 45, 27, 0, 0, 1, 1, 1, 0, 62, 62, 62, 3, 266, 270, 262, 62, 62, 0, 0, 0, 0,
			0, 0, 0, 0, 31, 45, 27, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 47, 1, 5, 0, 13, 13, 0, 13, 131, 134, 128, 13,
			13, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 86, 0, 86, 5, 86, 0, 350, 354, 0, 346, 86, 86, 0, 0, 0, 0, 86, 86, 5,
			86, 350, 354, 346, 86, 86, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 83, 0, 83, 5, 83, 0, 338, 342, 0, 334, 83, 83,
			0, 0, 0, 0, 83, 83, 5, 83, 338, 342, 334, 83, 83, 0, 56, 56, 56, 56, 3, 242, 246, 238, 56, 56, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 31, 45, 27, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 47, 1, 5, 0, 7, 7, 7, 0, 7, 101, 104, 98, 7, 7, 0, 0, 0,
			0, 0, 31, 45, 27, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 80, 0, 80, 80, 5, 80, 0, 326, 330, 0, 322,
			80, 80, 0, 0, 0, 0, 80, 80, 80, 5, 80, 326, 330, 322, 80, 80, 0, 0, 0, 31, 33, 45, 27, 0, 0, 53, 1, 25, 0, 41, 41, 203,
			206, 215, 200, 41, 0, 1, 234, 230, 1, 1, 77, 71, 0, 1, 0, 0, 179, 23, 27, 1, 1, 21, 0, 0, 176, 0, 21, 0, 50, 0, 0, 179,
			176, 27, 50, 1, 53, 1, 25, 0, 0, 23, 0, 0, 0, 0, 37, 31, 45, 27, 0, 1, 1, 1, 0, 53, 1, 25, 0, 0, 41, 209, 212, 203, 215,
			200, 0, 1, 1, 1, 0, 0, 0, 37, 31, 45, 27, 0, 0, 1, 234, 230, 1, 1, 77, 71, 0, 1, 0, 0, 179, 23, 27, 1, 1, 21, 0, 0, 176,
			0, 21, 0, 50, 0, 0, 179, 176, 27, 50, 1, 53, 1, 25, 0, 0, 23, 0, 0, 0, 173, 414, 418, 410, 422, 406, 173, 0, 0, 0, 0,
			74, 74, 314, 3, 310, 318, 306, 74, 0, 0, 0, 0, 0, 0, 37, 0, 31, 45, 27, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 47,
			1, 5, 0, 43, 43, 224, 0, 221, 227, 218, 43, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 95, 0, 95, 398, 5, 0, 394, 402,
			0, 390, 95, 0, 0, 0, 0, 95, 95, 398, 5, 394, 402, 390, 95, 0, 0, 0, 0, 0, 0, 0};
	}

	private static final short _tableLayout_trans_actions[] = init__tableLayout_trans_actions_0();

	static final int tableLayout_start = 1;
	static final int tableLayout_first_final = 110;
	static final int tableLayout_error = 0;

	static final int tableLayout_en_stack = 74;
	static final int tableLayout_en_widgetSection = 86;
	static final int tableLayout_en_main = 1;
	static final int tableLayout_en_main_table = 69;

	// line 272 "TableLayoutParser.rl"
}
