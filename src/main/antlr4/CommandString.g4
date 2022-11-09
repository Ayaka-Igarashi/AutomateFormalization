grammar CommandString;

EOFTOK : '<EOF>' ;
LPAREN  : '(' ;
RPAREN  : ')' ;
LPARENB  : '[' ;
RPARENB  : ']' ;
COMMA   : ',' ;
DOT : '.' ;
BAR    : '|' ;
HYPHEN : '-' ;
SINGLEQUO : '\'' ;
DOUBLEQUO : '"' ;
PLUS : '+' ;
UNDERBAR : '_' ; 

WHITE_SPACE_CHAR : [ \t\r\n] -> skip;
SYMBOL : ([a-zA-Z0-9!?]|HYPHEN|SINGLEQUO|DOUBLEQUO|PLUS|UNDERBAR)+ ;
//SYMBOL : [a-z]+;
IDX : (LPARENB([0-9]|UNDERBAR)RPARENB) ;

symbol : SYMBOL ;

noun : symbol IDX            # noun_symbol
     | symbol IDX DOT symbol # noun_dot ;

term : symbol LPAREN args RPAREN ;

args : argsnoun # args_noun
     | argsterm # args_term ;

argsterm : term COMMA argsterm # argsterm_list
         | term                # argsterm_term ;

argsnoun : noun COMMA argsnoun # argsnoun_list
         | noun                # argsnoun_symbol ;

termlist : term BAR termlist # termlist_Bar
         | term              # termlist_term;

start : termlist EOFTOK? EOF ;
