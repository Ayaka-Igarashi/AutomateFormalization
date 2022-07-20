grammar CommandString;

EOFTOK : '<EOF>' ;
LPAREN  : '(' ;
RPAREN  : ')' ;
COMMA   : ',' ;
DOT : '.' ;
BAR    : '|' ;
HYPHEN : '-' ;
SINGLEQUO : '\'' ;
DOUBLEQUO : '"' ;
PLUS : '+' ;
UNDERBAR : '_' ; 

WHITE_SPACE_CHAR : [ \t\r\n] -> skip;
SYMBOL : ([a-zA-Z0-9.!?]|HYPHEN|SINGLEQUO|DOUBLEQUO|PLUS|UNDERBAR)+ ;

symbol : SYMBOL ;

term : symbol LPAREN args RPAREN ;

args : argsnoun # args_noun
     | argsterm # args_term ;

argsterm : term COMMA argsterm # argsterm_list
         | term                # argsterm_term ;

argsnoun : symbol COMMA argsnoun # argsnoun_list
         | symbol                # argsnoun_symbol 
         | symbol DOT argsnoun   # argsnoun_dot ;

termlist : term BAR termlist # termlist_Bar
         | term              # termlist_term;

start : termlist EOFTOK? EOF ;
