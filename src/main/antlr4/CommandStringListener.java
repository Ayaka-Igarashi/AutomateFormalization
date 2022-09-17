// Generated from antlr4/CommandString.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CommandStringParser}.
 */
public interface CommandStringListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CommandStringParser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(CommandStringParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandStringParser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(CommandStringParser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandStringParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(CommandStringParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandStringParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(CommandStringParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code args_noun}
	 * labeled alternative in {@link CommandStringParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs_noun(CommandStringParser.Args_nounContext ctx);
	/**
	 * Exit a parse tree produced by the {@code args_noun}
	 * labeled alternative in {@link CommandStringParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs_noun(CommandStringParser.Args_nounContext ctx);
	/**
	 * Enter a parse tree produced by the {@code args_term}
	 * labeled alternative in {@link CommandStringParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs_term(CommandStringParser.Args_termContext ctx);
	/**
	 * Exit a parse tree produced by the {@code args_term}
	 * labeled alternative in {@link CommandStringParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs_term(CommandStringParser.Args_termContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argsterm_list}
	 * labeled alternative in {@link CommandStringParser#argsterm}.
	 * @param ctx the parse tree
	 */
	void enterArgsterm_list(CommandStringParser.Argsterm_listContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argsterm_list}
	 * labeled alternative in {@link CommandStringParser#argsterm}.
	 * @param ctx the parse tree
	 */
	void exitArgsterm_list(CommandStringParser.Argsterm_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argsterm_term}
	 * labeled alternative in {@link CommandStringParser#argsterm}.
	 * @param ctx the parse tree
	 */
	void enterArgsterm_term(CommandStringParser.Argsterm_termContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argsterm_term}
	 * labeled alternative in {@link CommandStringParser#argsterm}.
	 * @param ctx the parse tree
	 */
	void exitArgsterm_term(CommandStringParser.Argsterm_termContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argsnoun_list}
	 * labeled alternative in {@link CommandStringParser#argsnoun}.
	 * @param ctx the parse tree
	 */
	void enterArgsnoun_list(CommandStringParser.Argsnoun_listContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argsnoun_list}
	 * labeled alternative in {@link CommandStringParser#argsnoun}.
	 * @param ctx the parse tree
	 */
	void exitArgsnoun_list(CommandStringParser.Argsnoun_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argsnoun_symbol}
	 * labeled alternative in {@link CommandStringParser#argsnoun}.
	 * @param ctx the parse tree
	 */
	void enterArgsnoun_symbol(CommandStringParser.Argsnoun_symbolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argsnoun_symbol}
	 * labeled alternative in {@link CommandStringParser#argsnoun}.
	 * @param ctx the parse tree
	 */
	void exitArgsnoun_symbol(CommandStringParser.Argsnoun_symbolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code termlist_Bar}
	 * labeled alternative in {@link CommandStringParser#termlist}.
	 * @param ctx the parse tree
	 */
	void enterTermlist_Bar(CommandStringParser.Termlist_BarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code termlist_Bar}
	 * labeled alternative in {@link CommandStringParser#termlist}.
	 * @param ctx the parse tree
	 */
	void exitTermlist_Bar(CommandStringParser.Termlist_BarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code termlist_term}
	 * labeled alternative in {@link CommandStringParser#termlist}.
	 * @param ctx the parse tree
	 */
	void enterTermlist_term(CommandStringParser.Termlist_termContext ctx);
	/**
	 * Exit a parse tree produced by the {@code termlist_term}
	 * labeled alternative in {@link CommandStringParser#termlist}.
	 * @param ctx the parse tree
	 */
	void exitTermlist_term(CommandStringParser.Termlist_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandStringParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CommandStringParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandStringParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CommandStringParser.StartContext ctx);
}