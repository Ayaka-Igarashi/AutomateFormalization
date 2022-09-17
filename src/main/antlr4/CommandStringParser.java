// Generated from antlr4/CommandString.g4 by ANTLR 4.9
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandStringParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EOFTOK=1, LPAREN=2, RPAREN=3, COMMA=4, BAR=5, HYPHEN=6, SINGLEQUO=7, DOUBLEQUO=8, 
		PLUS=9, UNDERBAR=10, WHITE_SPACE_CHAR=11, SYMBOL=12;
	public static final int
		RULE_symbol = 0, RULE_term = 1, RULE_args = 2, RULE_argsterm = 3, RULE_argsnoun = 4, 
		RULE_termlist = 5, RULE_start = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"symbol", "term", "args", "argsterm", "argsnoun", "termlist", "start"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'<EOF>'", "'('", "')'", "','", "'|'", "'-'", "'''", "'\"'", "'+'", 
			"'_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EOFTOK", "LPAREN", "RPAREN", "COMMA", "BAR", "HYPHEN", "SINGLEQUO", 
			"DOUBLEQUO", "PLUS", "UNDERBAR", "WHITE_SPACE_CHAR", "SYMBOL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CommandString.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CommandStringParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SymbolContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(CommandStringParser.SYMBOL, 0); }
		public SymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitSymbol(this);
		}
	}

	public final SymbolContext symbol() throws RecognitionException {
		SymbolContext _localctx = new SymbolContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_symbol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(SYMBOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(CommandStringParser.LPAREN, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CommandStringParser.RPAREN, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			symbol();
			setState(17);
			match(LPAREN);
			setState(18);
			args();
			setState(19);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	 
		public ArgsContext() { }
		public void copyFrom(ArgsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Args_termContext extends ArgsContext {
		public ArgstermContext argsterm() {
			return getRuleContext(ArgstermContext.class,0);
		}
		public Args_termContext(ArgsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterArgs_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitArgs_term(this);
		}
	}
	public static class Args_nounContext extends ArgsContext {
		public ArgsnounContext argsnoun() {
			return getRuleContext(ArgsnounContext.class,0);
		}
		public Args_nounContext(ArgsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterArgs_noun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitArgs_noun(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_args);
		try {
			setState(23);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				_localctx = new Args_nounContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				argsnoun();
				}
				break;
			case 2:
				_localctx = new Args_termContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				argsterm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgstermContext extends ParserRuleContext {
		public ArgstermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argsterm; }
	 
		public ArgstermContext() { }
		public void copyFrom(ArgstermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Argsterm_termContext extends ArgstermContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Argsterm_termContext(ArgstermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterArgsterm_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitArgsterm_term(this);
		}
	}
	public static class Argsterm_listContext extends ArgstermContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CommandStringParser.COMMA, 0); }
		public ArgstermContext argsterm() {
			return getRuleContext(ArgstermContext.class,0);
		}
		public Argsterm_listContext(ArgstermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterArgsterm_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitArgsterm_list(this);
		}
	}

	public final ArgstermContext argsterm() throws RecognitionException {
		ArgstermContext _localctx = new ArgstermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_argsterm);
		try {
			setState(30);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new Argsterm_listContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				term();
				setState(26);
				match(COMMA);
				setState(27);
				argsterm();
				}
				break;
			case 2:
				_localctx = new Argsterm_termContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsnounContext extends ParserRuleContext {
		public ArgsnounContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argsnoun; }
	 
		public ArgsnounContext() { }
		public void copyFrom(ArgsnounContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Argsnoun_symbolContext extends ArgsnounContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public Argsnoun_symbolContext(ArgsnounContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterArgsnoun_symbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitArgsnoun_symbol(this);
		}
	}
	public static class Argsnoun_listContext extends ArgsnounContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CommandStringParser.COMMA, 0); }
		public ArgsnounContext argsnoun() {
			return getRuleContext(ArgsnounContext.class,0);
		}
		public Argsnoun_listContext(ArgsnounContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterArgsnoun_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitArgsnoun_list(this);
		}
	}

	public final ArgsnounContext argsnoun() throws RecognitionException {
		ArgsnounContext _localctx = new ArgsnounContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_argsnoun);
		try {
			setState(37);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new Argsnoun_listContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				symbol();
				setState(33);
				match(COMMA);
				setState(34);
				argsnoun();
				}
				break;
			case 2:
				_localctx = new Argsnoun_symbolContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				symbol();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermlistContext extends ParserRuleContext {
		public TermlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termlist; }
	 
		public TermlistContext() { }
		public void copyFrom(TermlistContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Termlist_BarContext extends TermlistContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode BAR() { return getToken(CommandStringParser.BAR, 0); }
		public TermlistContext termlist() {
			return getRuleContext(TermlistContext.class,0);
		}
		public Termlist_BarContext(TermlistContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterTermlist_Bar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitTermlist_Bar(this);
		}
	}
	public static class Termlist_termContext extends TermlistContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Termlist_termContext(TermlistContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterTermlist_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitTermlist_term(this);
		}
	}

	public final TermlistContext termlist() throws RecognitionException {
		TermlistContext _localctx = new TermlistContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_termlist);
		try {
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new Termlist_BarContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				term();
				setState(40);
				match(BAR);
				setState(41);
				termlist();
				}
				break;
			case 2:
				_localctx = new Termlist_termContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StartContext extends ParserRuleContext {
		public TermlistContext termlist() {
			return getRuleContext(TermlistContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CommandStringParser.EOF, 0); }
		public TerminalNode EOFTOK() { return getToken(CommandStringParser.EOFTOK, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandStringListener ) ((CommandStringListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			termlist();
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOFTOK) {
				{
				setState(47);
				match(EOFTOK);
				}
			}

			setState(50);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16\67\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\5\4\32\n\4\3\5\3\5\3\5\3\5\3\5\5\5!\n\5\3\6\3\6\3\6\3\6\3\6"+
		"\5\6(\n\6\3\7\3\7\3\7\3\7\3\7\5\7/\n\7\3\b\3\b\5\b\63\n\b\3\b\3\b\3\b"+
		"\2\2\t\2\4\6\b\n\f\16\2\2\2\64\2\20\3\2\2\2\4\22\3\2\2\2\6\31\3\2\2\2"+
		"\b \3\2\2\2\n\'\3\2\2\2\f.\3\2\2\2\16\60\3\2\2\2\20\21\7\16\2\2\21\3\3"+
		"\2\2\2\22\23\5\2\2\2\23\24\7\4\2\2\24\25\5\6\4\2\25\26\7\5\2\2\26\5\3"+
		"\2\2\2\27\32\5\n\6\2\30\32\5\b\5\2\31\27\3\2\2\2\31\30\3\2\2\2\32\7\3"+
		"\2\2\2\33\34\5\4\3\2\34\35\7\6\2\2\35\36\5\b\5\2\36!\3\2\2\2\37!\5\4\3"+
		"\2 \33\3\2\2\2 \37\3\2\2\2!\t\3\2\2\2\"#\5\2\2\2#$\7\6\2\2$%\5\n\6\2%"+
		"(\3\2\2\2&(\5\2\2\2\'\"\3\2\2\2\'&\3\2\2\2(\13\3\2\2\2)*\5\4\3\2*+\7\7"+
		"\2\2+,\5\f\7\2,/\3\2\2\2-/\5\4\3\2.)\3\2\2\2.-\3\2\2\2/\r\3\2\2\2\60\62"+
		"\5\f\7\2\61\63\7\3\2\2\62\61\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65"+
		"\7\2\2\3\65\17\3\2\2\2\7\31 \'.\62";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}