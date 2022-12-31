name: Data_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [38, 38] (U+0026 AMPERSAND (&amp;))

<span style="color: blue; ">Set the return state to the Data_state</span>

<span style="color: blue; "> =>  set_to ( return_state [_] , Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(return_state) = EVal(Data_state)</span>

<span style="color: blue; ">Switch to the Character_reference_state</span>

<span style="color: blue; "> =>  switch_to ( Character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Character_reference_state)</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Tag_open_state</span>

<span style="color: blue; "> =>  switch_to ( Tag_open_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Tag_open_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: RCDATA_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [38, 38] (U+0026 AMPERSAND (&amp;))

<span style="color: blue; ">Set the return state to the RCDATA_state</span>

<span style="color: blue; "> =>  set_to ( return_state [_] , RCDATA_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(return_state) = EVal(RCDATA_state)</span>

<span style="color: blue; ">Switch to the Character_reference_state</span>

<span style="color: blue; "> =>  switch_to ( Character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Character_reference_state)</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the RCDATA_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( RCDATA_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RCDATA_less_than_sign_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: RAWTEXT_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the RAWTEXT_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( RAWTEXT_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RAWTEXT_less_than_sign_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Script_data_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Script_data_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_less_than_sign_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: PLAINTEXT_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Tag_open_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [33, 33] (U+0021 EXCLAMATION MARK (!))

<span style="color: blue; ">Switch to the Markup_declaration_open_state</span>

<span style="color: blue; "> =>  switch_to ( Markup_declaration_open_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Markup_declaration_open_state)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Switch to the End_tag_open_state</span>

<span style="color: blue; "> =>  switch_to ( End_tag_open_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(End_tag_open_state)</span>

char: [65, 90], [97, 122] (ASCII alpha)

<span style="color: blue; ">Create a new start tag token [0] and set a new start tag token [0] tag name to the empty string</span>

<span style="color: blue; "> =>  create ( start_tag_token [0] ) | set_to ( start_tag_token [0] . name , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(start_tag_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LVar(last_start_tag_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Tag_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Tag_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Tag_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [63, 63] (U+003F QUESTION MARK (?))

<span style="color: blue; ">This is an unexpected-question-mark-instead-of-tag-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-question-mark-instead-of-tag-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-question-mark-instead-of-tag-name_parse_error))</span>

<span style="color: blue; ">Create a comment token whose data is the empty string</span>

<span style="color: blue; "> =>  create ( comment_token [_] ) | set_to ( comment_token [_] . data , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(comment_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Bogus_comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-before-tag-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-before-tag-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-before-tag-name_parse_error))</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token and an end-of-file token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is an invalid-first-character-of-tag-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( invalid-first-character-of-tag-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(invalid-first-character-of-tag-name_parse_error))</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">Reconsume in the Data_state</span>

<span style="color: blue; "> =>  reconsume_in ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: End_tag_open_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [65, 90], [97, 122] (ASCII alpha)

<span style="color: blue; ">Create a new end tag token [0] , set a new end tag token [0] tag name to the empty string</span>

<span style="color: blue; "> =>  create ( end_tag_token [0] ) | set_to ( end_tag_token [0] . name , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(end_tag_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Tag_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Tag_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Tag_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is a missing-end-tag-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-end-tag-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-end-tag-name_parse_error))</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-before-tag-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-before-tag-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-before-tag-name_parse_error))</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token , a U+002F SOLIDUS character token and an end-of-file token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) | emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is an invalid-first-character-of-tag-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( invalid-first-character-of-tag-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(invalid-first-character-of-tag-name_parse_error))</span>

<span style="color: blue; ">Create a comment token whose data is the empty string</span>

<span style="color: blue; "> =>  create ( comment_token [_] ) | set_to ( comment_token [_] . data , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(comment_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Bogus_comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Tag_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Switch to the Self_closing_start_tag_state</span>

<span style="color: blue; "> =>  switch_to ( Self_closing_start_tag_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Self_closing_start_tag_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current tag token</span>

<span style="color: blue; "> =>  emit ( current_tag_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] . lowercase_version , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt2(EVal(current_input_character),A2Var(lowercase_version)))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-tag_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(current_input_character))</span>





name: RCDATA_less_than_sign_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Set the temporary buffer to the empty string</span>

<span style="color: blue; "> =>  set_to ( temporary_buffer [_] , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = EVal(empty_string)</span>

<span style="color: blue; ">Switch to the RCDATA_end_tag_open_state</span>

<span style="color: blue; "> =>  switch_to ( RCDATA_end_tag_open_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RCDATA_end_tag_open_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">Reconsume in the RCDATA_state</span>

<span style="color: blue; "> =>  reconsume_in ( RCDATA_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RCDATA_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: RCDATA_end_tag_open_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [65, 90], [97, 122] (ASCII alpha)

<span style="color: blue; ">Create a new end tag token [0] , set a new end tag token [0] tag name to the empty string</span>

<span style="color: blue; "> =>  create ( end_tag_token [0] ) | set_to ( end_tag_token [0] . name , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(end_tag_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the RCDATA_end_tag_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( RCDATA_end_tag_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RCDATA_end_tag_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token and a U+002F SOLIDUS character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">Reconsume in the RCDATA_state</span>

<span style="color: blue; "> =>  reconsume_in ( RCDATA_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RCDATA_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: RCDATA_end_tag_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Self_closing_start_tag_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Self_closing_start_tag_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Self_closing_start_tag_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">If the current end tag token [0] is an appropriate end tag token , then switch to the Data_state and emit the current end tag token [0] . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Data_state [_] ) , emit ( current_tag_token [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Data_state)</span>

<span style="color: blue; "> LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] . lowercase_version , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt2(EVal(current_input_character),A2Var(lowercase_version)))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [97, 122] (ASCII lower alpha)

<span style="color: blue; ">Append the current input character [0] to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(current_input_character))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token , a U+002F SOLIDUS character token , and a character token for each of the characters in the temporary buffer [0] ( in the order the characters in the temporary buffer [0] were added to the temporary buffer [1] </span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) | emit ( temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(temporary_buffer))</span>

<span style="color: blue; ">Reconsume in the RCDATA_state</span>

<span style="color: blue; "> =>  reconsume_in ( RCDATA_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RCDATA_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: RAWTEXT_less_than_sign_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Set the temporary buffer to the empty string</span>

<span style="color: blue; "> =>  set_to ( temporary_buffer [_] , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = EVal(empty_string)</span>

<span style="color: blue; ">Switch to the RAWTEXT_end_tag_open_state</span>

<span style="color: blue; "> =>  switch_to ( RAWTEXT_end_tag_open_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RAWTEXT_end_tag_open_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">Reconsume in the RAWTEXT_state</span>

<span style="color: blue; "> =>  reconsume_in ( RAWTEXT_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RAWTEXT_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: RAWTEXT_end_tag_open_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [65, 90], [97, 122] (ASCII alpha)

<span style="color: blue; ">Create a new end tag token [0] , set a new end tag token [0] tag name to the empty string</span>

<span style="color: blue; "> =>  create ( end_tag_token [0] ) | set_to ( end_tag_token [0] . name , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(end_tag_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the RAWTEXT_end_tag_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( RAWTEXT_end_tag_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RAWTEXT_end_tag_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token and a U+002F SOLIDUS character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">Reconsume in the RAWTEXT_state</span>

<span style="color: blue; "> =>  reconsume_in ( RAWTEXT_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RAWTEXT_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: RAWTEXT_end_tag_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Self_closing_start_tag_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Self_closing_start_tag_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Self_closing_start_tag_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">If the current end tag token [0] is an appropriate end tag token , then switch to the Data_state and emit the current end tag token [0] . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Data_state [_] ) , emit ( current_tag_token [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Data_state)</span>

<span style="color: blue; "> LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] . lowercase_version , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt2(EVal(current_input_character),A2Var(lowercase_version)))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [97, 122] (ASCII lower alpha)

<span style="color: blue; ">Append the current input character [0] to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(current_input_character))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token , a U+002F SOLIDUS character token , and a character token for each of the characters in the temporary buffer [0] ( in the order the characters in the temporary buffer [0] were added to the temporary buffer [1] </span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) | emit ( temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(temporary_buffer))</span>

<span style="color: blue; ">Reconsume in the RAWTEXT_state</span>

<span style="color: blue; "> =>  reconsume_in ( RAWTEXT_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(RAWTEXT_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_less_than_sign_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Set the temporary buffer to the empty string</span>

<span style="color: blue; "> =>  set_to ( temporary_buffer [_] , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = EVal(empty_string)</span>

<span style="color: blue; ">Switch to the Script_data_end_tag_open_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_end_tag_open_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_end_tag_open_state)</span>

char: [33, 33] (U+0021 EXCLAMATION MARK (!))

<span style="color: blue; ">Switch to the Script_data_escape_start_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escape_start_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escape_start_state)</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token and a U+0021 EXCLAMATION MARK character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+0021_EXCLAMATION_MARK_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+0021_EXCLAMATION_MARK_character_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">Reconsume in the Script_data_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_end_tag_open_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [65, 90], [97, 122] (ASCII alpha)

<span style="color: blue; ">Create a new end tag token [0] , set a new end tag token [0] tag name to the empty string</span>

<span style="color: blue; "> =>  create ( end_tag_token [0] ) | set_to ( end_tag_token [0] . name , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(end_tag_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Script_data_end_tag_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_end_tag_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_end_tag_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token and a U+002F SOLIDUS character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">Reconsume in the Script_data_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_end_tag_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Self_closing_start_tag_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Self_closing_start_tag_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Self_closing_start_tag_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">If the current end tag token [0] is an appropriate end tag token , then switch to the Data_state and emit the current end tag token [0] . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Data_state [_] ) , emit ( current_tag_token [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Data_state)</span>

<span style="color: blue; "> LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] . lowercase_version , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt2(EVal(current_input_character),A2Var(lowercase_version)))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [97, 122] (ASCII lower alpha)

<span style="color: blue; ">Append the current input character [0] to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(current_input_character))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token , a U+002F SOLIDUS character token , and a character token for each of the characters in the temporary buffer [0] ( in the order the characters in the temporary buffer [0] were added to the temporary buffer [1] </span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) | emit ( temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(temporary_buffer))</span>

<span style="color: blue; ">Reconsume in the Script_data_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_escape_start_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Script_data_escape_start_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escape_start_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escape_start_dash_state)</span>

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Script_data_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_escape_start_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Script_data_escaped_dash_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_dash_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_dash_dash_state)</span>

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Script_data_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_escaped_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Script_data_escaped_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_dash_state)</span>

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Script_data_escaped_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_less_than_sign_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-script-html-comment-like-text parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-script-html-comment-like-text_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-script-html-comment-like-text_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Script_data_escaped_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Script_data_escaped_dash_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_dash_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_dash_dash_state)</span>

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Script_data_escaped_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_less_than_sign_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-script-html-comment-like-text parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-script-html-comment-like-text_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-script-html-comment-like-text_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Script_data_escaped_dash_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Script_data_escaped_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_less_than_sign_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Script_data_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_state)</span>

<span style="color: blue; ">Emit a U+003E GREATER-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003E_GREATER-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003E_GREATER-THAN_SIGN_character_token))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-script-html-comment-like-text parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-script-html-comment-like-text_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-script-html-comment-like-text_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Script_data_escaped_less_than_sign_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Set the temporary buffer to the empty string</span>

<span style="color: blue; "> =>  set_to ( temporary_buffer [_] , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = EVal(empty_string)</span>

<span style="color: blue; ">Switch to the Script_data_escaped_end_tag_open_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_escaped_end_tag_open_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_end_tag_open_state)</span>

char: [65, 90], [97, 122] (ASCII alpha)

<span style="color: blue; ">Set the temporary buffer to the empty string</span>

<span style="color: blue; "> =>  set_to ( temporary_buffer [_] , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = EVal(empty_string)</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">Reconsume in the Script_data_double_escape_start_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_double_escape_start_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escape_start_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">Reconsume in the Script_data_escaped_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_escaped_end_tag_open_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [65, 90], [97, 122] (ASCII alpha)

<span style="color: blue; ">Create a new end tag token [0] , set a new end tag token [0] tag name to the empty string</span>

<span style="color: blue; "> =>  create ( end_tag_token [0] ) | set_to ( end_tag_token [0] . name , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(end_tag_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Script_data_escaped_end_tag_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_escaped_end_tag_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_end_tag_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token and a U+002F SOLIDUS character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">Reconsume in the Script_data_escaped_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_escaped_end_tag_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Before_attribute_name_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Before_attribute_name_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">If the current end tag token is an appropriate end tag token , then switch to the Self_closing_start_tag_state . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Self_closing_start_tag_state [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Self_closing_start_tag_state)</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">If the current end tag token [0] is an appropriate end tag token , then switch to the Data_state and emit the current end tag token [0] . Otherwise , treat it as per the anything else entry below</span>

<span style="color: blue; "> =>  if_then ( the_current_end_tag_token_is_an_appropriate_end_tag_token , switch_to ( Data_state [_] ) , emit ( current_tag_token [_] ) )  | otherwise ( treat_it_as_per_the_"_anything_else_"_entry_below ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If And(Exist(EVal(last_start_tag_token)),Equal(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt1(EVal(last_start_tag_token),Att1(A1Var(name),ANone)))) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Data_state)</span>

<span style="color: blue; "> LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; "> else  LVar(treat_flag) = EVal(True)</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] . lowercase_version , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt2(EVal(current_input_character),A2Var(lowercase_version)))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [97, 122] (ASCII lower alpha)

<span style="color: blue; ">Append the current input character [0] to the current tag token 's tag name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , current_tag_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(current_input_character))</span>

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token , a U+002F SOLIDUS character token , and a character token for each of the characters in the temporary buffer [0]   ( in the order the characters in the temporary buffer [0] were added to the temporary buffer [1] </span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( U+002F_SOLIDUS_character_token [_] ) | emit ( temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(temporary_buffer))</span>

<span style="color: blue; ">Reconsume in the Script_data_escaped_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_double_escape_start_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_double_escaped_state . Otherwise , switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_double_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_double_escaped_state . Otherwise , switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_double_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_double_escaped_state . Otherwise , switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_double_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_double_escaped_state . Otherwise , switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_double_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_double_escaped_state . Otherwise , switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_double_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_double_escaped_state . Otherwise , switch to the Script_data_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_double_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [65, 90] (ASCII upper alpha)

Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the temporary buffer

 =>  append_to ( lowercase_version [0] , temporary_buffer [_] ) 

==>

LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(x0))

<span style="color: blue; ">Emit the current input character [0] as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [97, 122] (ASCII lower alpha)

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

<span style="color: blue; ">Emit the current input character [0] as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Script_data_escaped_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_double_escaped_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Script_data_double_escaped_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_dash_state)</span>

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Script_data_double_escaped_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_less_than_sign_state)</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-script-html-comment-like-text parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-script-html-comment-like-text_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-script-html-comment-like-text_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Script_data_double_escaped_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Script_data_double_escaped_dash_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_dash_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_dash_dash_state)</span>

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Script_data_double_escaped_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_less_than_sign_state)</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-script-html-comment-like-text parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-script-html-comment-like-text_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-script-html-comment-like-text_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Script_data_double_escaped_dash_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Emit a U+002D HYPHEN-MINUS character token</span>

<span style="color: blue; "> =>  emit ( U+002D_HYPHEN-MINUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Switch to the Script_data_double_escaped_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_less_than_sign_state)</span>

<span style="color: blue; ">Emit a U+003C LESS-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003C_LESS-THAN_SIGN_character_token))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Script_data_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_state)</span>

<span style="color: blue; ">Emit a U+003E GREATER-THAN SIGN character token</span>

<span style="color: blue; "> =>  emit ( U+003E_GREATER-THAN_SIGN_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+003E_GREATER-THAN_SIGN_character_token))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit a U+FFFD REPLACEMENT CHARACTER character token</span>

<span style="color: blue; "> =>  emit ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-script-html-comment-like-text parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-script-html-comment-like-text_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-script-html-comment-like-text_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: Script_data_double_escaped_less_than_sign_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Set the temporary buffer to the empty string</span>

<span style="color: blue; "> =>  set_to ( temporary_buffer [_] , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = EVal(empty_string)</span>

<span style="color: blue; ">Switch to the Script_data_double_escape_end_state</span>

<span style="color: blue; "> =>  switch_to ( Script_data_double_escape_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escape_end_state)</span>

<span style="color: blue; ">Emit a U+002F SOLIDUS character token</span>

<span style="color: blue; "> =>  emit ( U+002F_SOLIDUS_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+002F_SOLIDUS_character_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_double_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Script_data_double_escape_end_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_escaped_state . Otherwise , switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_double_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_escaped_state . Otherwise , switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_double_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_escaped_state . Otherwise , switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_double_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_escaped_state . Otherwise , switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_double_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_escaped_state . Otherwise , switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_double_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">If the temporary buffer is the string script , then switch to the Script_data_escaped_state . Otherwise , switch to the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  if_then ( is ( temporary_buffer [_] , string_"script" [_] ) , switch_to ( Script_data_escaped_state [_] ) )  | otherwise ( switch_to ( Script_data_double_escaped_state [_] ) ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(temporary_buffer),EVal(string_"script")) :</span>

<span style="color: blue; "> then  LVar(state) = EVal(Script_data_escaped_state)</span>

<span style="color: blue; "> else  LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [65, 90] (ASCII upper alpha)

Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the temporary buffer

 =>  append_to ( lowercase_version [0] , temporary_buffer [_] ) 

==>

LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(x0))

<span style="color: blue; ">Emit the current input character [0] as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [97, 122] (ASCII lower alpha)

<span style="color: blue; ">Append the current input character [0] to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [0] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

<span style="color: blue; ">Emit the current input character [0] as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Script_data_double_escaped_state</span>

<span style="color: blue; "> =>  reconsume_in ( Script_data_double_escaped_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Script_data_double_escaped_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Before_attribute_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [61, 61] (U+003D EQUALS SIGN (=))

<span style="color: blue; ">This is an unexpected-equals-sign-before-attribute-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-equals-sign-before-attribute-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-equals-sign-before-attribute-name_parse_error))</span>

<span style="color: blue; ">Start a new attribute [0] in the current tag token</span>

<span style="color: blue; "> =>  start_a_new_attribute_in_the_current_tag_token</span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),ANone)),EVal(attribute))</span>

<span style="color: blue; ">Set a new attribute [0] name to the current input character , and a new attribute [0] value to the empty string</span>

<span style="color: blue; "> =>  set_to ( attribute [0] . name , current_input_character [_] ) | set_to ( attribute [0] . value , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))) = EVal(current_input_character)</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = EVal(empty_string)</span>

<span style="color: blue; ">Switch to the Attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Attribute_name_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Start a new attribute in the current tag token</span>

<span style="color: blue; "> =>  start_a_new_attribute_in_the_current_tag_token</span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),ANone)),EVal(attribute))</span>

<span style="color: blue; ">Set that attribute name and value to the empty string</span>

<span style="color: blue; "> =>  set_to ( attribute [_] . name , empty_string [_] ) | set_to ( attribute [_] . value , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))) = EVal(empty_string)</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Attribute_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Reconsume in the After_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( After_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [61, 61] (U+003D EQUALS SIGN (=))

<span style="color: blue; ">Switch to the Before_attribute_value_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_value_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_value_state)</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the current attribute 's name</span>

<span style="color: blue; "> =>  append_to ( lowercase_version [_] , attribute [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))),EVal(lowercase_version))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current attribute 's name</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , attribute [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">This [0] is an unexpected-character-in-attribute-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-attribute-name_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-attribute-name_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">This [0] is an unexpected-character-in-attribute-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-attribute-name_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-attribute-name_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">This [0] is an unexpected-character-in-attribute-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-attribute-name_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-attribute-name_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current attribute 's name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , attribute [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))),EVal(current_input_character))</span>





name: After_attribute_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Switch to the Self_closing_start_tag_state</span>

<span style="color: blue; "> =>  switch_to ( Self_closing_start_tag_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Self_closing_start_tag_state)</span>

char: [61, 61] (U+003D EQUALS SIGN (=))

<span style="color: blue; ">Switch to the Before_attribute_value_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_value_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_value_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current tag token</span>

<span style="color: blue; "> =>  emit ( current_tag_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-tag_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Start a new attribute in the current tag token</span>

<span style="color: blue; "> =>  start_a_new_attribute_in_the_current_tag_token</span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),ANone)),EVal(attribute))</span>

<span style="color: blue; ">Set that attribute name and value to the empty string</span>

<span style="color: blue; "> =>  set_to ( attribute [_] . name , empty_string [_] ) | set_to ( attribute [_] . value , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(name),ANone)))) = EVal(empty_string)</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = EVal(empty_string)</span>

<span style="color: blue; ">Reconsume in the Attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Before_attribute_value_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">Switch to the Attribute_value_double_quoted_state</span>

<span style="color: blue; "> =>  switch_to ( Attribute_value_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Attribute_value_double_quoted_state)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">Switch to the Attribute_value_single_quoted_state</span>

<span style="color: blue; "> =>  switch_to ( Attribute_value_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Attribute_value_single_quoted_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is a missing-attribute-value parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-attribute-value_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-attribute-value_parse_error))</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current tag token</span>

<span style="color: blue; "> =>  emit ( current_tag_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Attribute_value_unquoted_state</span>

<span style="color: blue; "> =>  reconsume_in ( Attribute_value_unquoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Attribute_value_unquoted_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Attribute_value_double_quoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">Switch to the After_attribute_value_quoted_state</span>

<span style="color: blue; "> =>  switch_to ( After_attribute_value_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_value_quoted_state)</span>

char: [38, 38] (U+0026 AMPERSAND (&amp;))

<span style="color: blue; ">Set the return state to the Attribute_value_double_quoted_state</span>

<span style="color: blue; "> =>  set_to ( return_state [_] , Attribute_value_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(return_state) = EVal(Attribute_value_double_quoted_state)</span>

<span style="color: blue; ">Switch to the Character_reference_state</span>

<span style="color: blue; "> =>  switch_to ( Character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Character_reference_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current attribute 's value</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , attribute [_] . value ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-tag_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current attribute 's value</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , attribute [_] . value ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(current_input_character))</span>





name: Attribute_value_single_quoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">Switch to the After_attribute_value_quoted_state</span>

<span style="color: blue; "> =>  switch_to ( After_attribute_value_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_attribute_value_quoted_state)</span>

char: [38, 38] (U+0026 AMPERSAND (&amp;))

<span style="color: blue; ">Set the return state to the Attribute_value_single_quoted_state</span>

<span style="color: blue; "> =>  set_to ( return_state [_] , Attribute_value_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(return_state) = EVal(Attribute_value_single_quoted_state)</span>

<span style="color: blue; ">Switch to the Character_reference_state</span>

<span style="color: blue; "> =>  switch_to ( Character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Character_reference_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current attribute 's value</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , attribute [_] . value ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-tag_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current attribute 's value</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , attribute [_] . value ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(current_input_character))</span>





name: Attribute_value_unquoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [38, 38] (U+0026 AMPERSAND (&amp;))

<span style="color: red; ">Set the return state to the Attribute_value_unquoted_state</span>

<span style="color: red; "> =>  set_to ( return_state [_] , Attribute_value_unquoted_state [_] ) </span>

<span style="color: red; ">==></span>

<span style="color: red; ">LVar(return_state) = EVal(Attribute_value_unquoted_state)</span>

<span style="color: blue; ">Switch to the Character_reference_state</span>

<span style="color: blue; "> =>  switch_to ( Character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Character_reference_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current tag token</span>

<span style="color: blue; "> =>  emit ( current_tag_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current attribute 's value</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , attribute [_] . value ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">This [0] is an unexpected-character-in-unquoted-attribute-value parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-unquoted-attribute-value_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-unquoted-attribute-value_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">This [0] is an unexpected-character-in-unquoted-attribute-value parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-unquoted-attribute-value_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-unquoted-attribute-value_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">This [0] is an unexpected-character-in-unquoted-attribute-value parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-unquoted-attribute-value_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-unquoted-attribute-value_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [61, 61] (U+003D EQUALS SIGN (=))

<span style="color: blue; ">This [0] is an unexpected-character-in-unquoted-attribute-value parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-unquoted-attribute-value_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-unquoted-attribute-value_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [96, 96] (U+0060 GRAVE ACCENT (`))

<span style="color: blue; ">This [0] is an unexpected-character-in-unquoted-attribute-value parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-in-unquoted-attribute-value_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-in-unquoted-attribute-value_parse_error))</span>

<span style="color: blue; ">Treat This [0] as per the anything else entry below</span>

<span style="color: blue; "> =>  treat_it_as_per_the_"_anything_else_"_entry_below </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(treat_flag) = EVal(True)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-tag_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current attribute 's value</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , attribute [_] . value ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(current_input_character))</span>





name: After_attribute_value_quoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the Before_attribute_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

char: [47, 47] (U+002F SOLIDUS (/))

<span style="color: blue; ">Switch to the Self_closing_start_tag_state</span>

<span style="color: blue; "> =>  switch_to ( Self_closing_start_tag_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Self_closing_start_tag_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current tag token</span>

<span style="color: blue; "> =>  emit ( current_tag_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-tag_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-whitespace-between-attributes parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-between-attributes_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-between-attributes_parse_error))</span>

<span style="color: blue; ">Reconsume in the Before_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Self_closing_start_tag_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Set the self-closing flag of the current tag token [0]</span>

<span style="color: blue; "> =>  set_to ( current_tag_token [_] . self-closing_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(self-closing_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current tag token [0]</span>

<span style="color: blue; "> =>  emit ( current_tag_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-tag_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is an unexpected-solidus-in-tag parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-solidus-in-tag_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-solidus-in-tag_parse_error))</span>

<span style="color: blue; ">Reconsume in the Before_attribute_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Before_attribute_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_attribute_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Bogus_comment_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Emit the comment</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(current_input_character))</span>





name: Comment_start_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Comment_start_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_start_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_start_dash_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is an abrupt-closing-of-empty-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( abrupt-closing-of-empty-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(abrupt-closing-of-empty-comment_parse_error))</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_start_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Comment_end_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is an abrupt-closing-of-empty-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( abrupt-closing-of-empty-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(abrupt-closing-of-empty-comment_parse_error))</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-comment_parse_error))</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append a U+002D HYPHEN-MINUS character (-) to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">Reconsume in the Comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Append the current input character to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(current_input_character))</span>

<span style="color: blue; ">Switch to the Comment_less_than_sign_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_less_than_sign_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_less_than_sign_state)</span>

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Comment_end_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_end_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_dash_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-comment_parse_error))</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(current_input_character))</span>





name: Comment_less_than_sign_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [33, 33] (U+0021 EXCLAMATION MARK (!))

<span style="color: blue; ">Append the current input character to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(current_input_character))</span>

<span style="color: blue; ">Switch to the Comment_less_than_sign_bang_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_less_than_sign_bang_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_less_than_sign_bang_state)</span>

char: [60, 60] (U+003C LESS-THAN SIGN (&lt;))

<span style="color: blue; ">Append the current input character to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(current_input_character))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_less_than_sign_bang_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Comment_less_than_sign_bang_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_less_than_sign_bang_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_less_than_sign_bang_dash_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_less_than_sign_bang_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Comment_less_than_sign_bang_dash_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_less_than_sign_bang_dash_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_less_than_sign_bang_dash_dash_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Comment_end_dash_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_end_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_dash_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_less_than_sign_bang_dash_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Reconsume in the Comment_end_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Reconsume in the Comment_end_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a nested-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( nested-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(nested-comment_parse_error))</span>

<span style="color: blue; ">Reconsume in the Comment_end_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_end_dash_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Switch to the Comment_end_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_state)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-comment_parse_error))</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append a U+002D HYPHEN-MINUS character (-) to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">Reconsume in the Comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_end_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [33, 33] (U+0021 EXCLAMATION MARK (!))

<span style="color: blue; ">Switch to the Comment_end_bang_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_end_bang_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_bang_state)</span>

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Append a U+002D HYPHEN-MINUS character (-) to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-comment_parse_error))</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append two U+002D HYPHEN-MINUS characters (-) to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) | append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">Reconsume in the Comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Comment_end_bang_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [45, 45] (U+002D HYPHEN-MINUS (-))

<span style="color: blue; ">Append two U+002D HYPHEN-MINUS characters (-) and a U+0021 EXCLAMATION MARK character ( -EXC- ) to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) | append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) | append_to ( U+0021_EXCLAMATION_MARK_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+0021_EXCLAMATION_MARK_character_token))</span>

<span style="color: blue; ">Switch to the Comment_end_dash_state</span>

<span style="color: blue; "> =>  switch_to ( Comment_end_dash_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_end_dash_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is an incorrectly-closed-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( incorrectly-closed-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(incorrectly-closed-comment_parse_error))</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-comment parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-comment_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-comment_parse_error))</span>

<span style="color: blue; ">Emit the comment token</span>

<span style="color: blue; "> =>  emit ( comment_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append two U+002D HYPHEN-MINUS characters (-) and a U+0021 EXCLAMATION MARK character ( ! ) to the comment token 's data</span>

<span style="color: blue; "> =>  append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) | append_to ( U+002D_HYPHEN-MINUS_character_token [_] , comment_token [_] . data ) | append_to ( U+0021_EXCLAMATION_MARK_character_token [_] , comment_token [_] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+002D_HYPHEN-MINUS_character_token))</span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(data),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(data),ANone)),EVal(U+0021_EXCLAMATION_MARK_character_token))</span>

<span style="color: blue; ">Reconsume in the Comment_state</span>

<span style="color: blue; "> =>  reconsume_in ( Comment_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Comment_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: DOCTYPE_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the Before_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_name_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the Before_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_name_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the Before_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_name_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the Before_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_name_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Reconsume in the Before_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Before_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Create a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  create ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(DOCTYPE_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">Set a new DOCTYPE token [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-whitespace-before-doctype-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-before-doctype-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-before-doctype-name_parse_error))</span>

<span style="color: blue; ">Reconsume in the Before_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  reconsume_in ( Before_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_name_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Before_DOCTYPE_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Create a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  create ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(DOCTYPE_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">Set a new DOCTYPE token [0] name to the lowercase version of the current input character [1] ( add 0x0020 to the current input character [1] code point </span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . name , current_input_character [1] . lowercase_version ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EAtt2(EVal(current_input_character),A2Var(lowercase_version))</span>

<span style="color: blue; ">Switch to the DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_name_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Create a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  create ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(DOCTYPE_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">Set a new DOCTYPE token [0] name to a U+FFFD REPLACEMENT CHARACTER character</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . name , U+FFFD_REPLACEMENT_CHARACTER_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token)</span>

<span style="color: blue; ">Switch to the DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_name_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is a missing-doctype-name parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-doctype-name_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-doctype-name_parse_error))</span>

<span style="color: blue; ">Create a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  create ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(DOCTYPE_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">Set a new DOCTYPE token [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Create a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  create ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(DOCTYPE_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">Set a new DOCTYPE token [0]   force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Create a new DOCTYPE token [0]</span>

<span style="color: blue; "> =>  create ( DOCTYPE_token [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(x0) = create EVal(DOCTYPE_token)</span>

<span style="color: blue; ">LVar(current_token) <- EVal(x0)</span>

<span style="color: blue; ">Set a new DOCTYPE token [0] name to the current input character</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . name , current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(name),ANone)) = EVal(current_input_character)</span>

<span style="color: blue; ">Switch to the DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_name_state)</span>





name: DOCTYPE_name_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the After_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_name_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the After_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_name_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the After_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_name_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the After_DOCTYPE_name_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_name_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_name_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current DOCTYPE token</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [65, 90] (ASCII upper alpha)

<span style="color: blue; ">Append the lowercase version of the current input character [0] ( add 0x0020 to the current input character [0] code point ) to the current DOCTYPE token 's name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] . lowercase_version , DOCTYPE_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EAtt2(EVal(current_input_character),A2Var(lowercase_version)))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current DOCTYPE token 's name</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , DOCTYPE_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current DOCTYPE token 's name</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , DOCTYPE_token [_] . name ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(name),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(name),ANone)),EVal(current_input_character))</span>





name: After_DOCTYPE_public_keyword_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the Before_DOCTYPE_public_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_public_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_public_identifier_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the Before_DOCTYPE_public_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_public_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_public_identifier_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the Before_DOCTYPE_public_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_public_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_public_identifier_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the Before_DOCTYPE_public_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_public_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_public_identifier_state)</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">This is a missing-whitespace-after-doctype-public-keyword parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-after-doctype-public-keyword_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-after-doctype-public-keyword_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's public identifier to the empty string ( not missing ) , then switch to the DOCTYPE_public_identifier_double_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . public_identifier , empty_string [_] ) | switch_to ( DOCTYPE_public_identifier_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_public_identifier_double_quoted_state)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">This is a missing-whitespace-after-doctype-public-keyword parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-after-doctype-public-keyword_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-after-doctype-public-keyword_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's public identifier to the empty string ( not missing ) , then switch to the DOCTYPE_public_identifier_single_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . public_identifier , empty_string [_] ) | switch_to ( DOCTYPE_public_identifier_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_public_identifier_single_quoted_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is a missing-doctype-public-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-doctype-public-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-doctype-public-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-quote-before-doctype-public-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-quote-before-doctype-public-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-quote-before-doctype-public-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Reconsume in the Bogus_DOCTYPE_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_DOCTYPE_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_DOCTYPE_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Before_DOCTYPE_public_identifier_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">Set the DOCTYPE token 's public identifier to the empty string ( not missing ) , then switch to the DOCTYPE_public_identifier_double_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . public_identifier , empty_string [_] ) | switch_to ( DOCTYPE_public_identifier_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_public_identifier_double_quoted_state)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">Set the DOCTYPE token 's public identifier to the empty string ( not missing ) , then switch to the DOCTYPE_public_identifier_single_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . public_identifier , empty_string [_] ) | switch_to ( DOCTYPE_public_identifier_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_public_identifier_single_quoted_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is a missing-doctype-public-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-doctype-public-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-doctype-public-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-quote-before-doctype-public-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-quote-before-doctype-public-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-quote-before-doctype-public-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Reconsume in the Bogus_DOCTYPE_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_DOCTYPE_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_DOCTYPE_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: DOCTYPE_public_identifier_double_quoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">Switch to the After_DOCTYPE_public_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_public_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_public_identifier_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current DOCTYPE token 's public identifier</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , DOCTYPE_token [_] . public_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(public_identifier),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is an abrupt-doctype-public-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( abrupt-doctype-public-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(abrupt-doctype-public-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current DOCTYPE token 's public identifier</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , DOCTYPE_token [_] . public_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(public_identifier),ANone)),EVal(current_input_character))</span>





name: DOCTYPE_public_identifier_single_quoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">Switch to the After_DOCTYPE_public_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_public_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_public_identifier_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current DOCTYPE token 's public identifier</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , DOCTYPE_token [_] . public_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(public_identifier),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is an abrupt-doctype-public-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( abrupt-doctype-public-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(abrupt-doctype-public-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current DOCTYPE token 's public identifier</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , DOCTYPE_token [_] . public_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(public_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(public_identifier),ANone)),EVal(current_input_character))</span>





name: After_DOCTYPE_public_identifier_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the Between_DOCTYPE_public_and_system_identifiers_state</span>

<span style="color: blue; "> =>  switch_to ( Between_DOCTYPE_public_and_system_identifiers_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Between_DOCTYPE_public_and_system_identifiers_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the Between_DOCTYPE_public_and_system_identifiers_state</span>

<span style="color: blue; "> =>  switch_to ( Between_DOCTYPE_public_and_system_identifiers_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Between_DOCTYPE_public_and_system_identifiers_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the Between_DOCTYPE_public_and_system_identifiers_state</span>

<span style="color: blue; "> =>  switch_to ( Between_DOCTYPE_public_and_system_identifiers_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Between_DOCTYPE_public_and_system_identifiers_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the Between_DOCTYPE_public_and_system_identifiers_state</span>

<span style="color: blue; "> =>  switch_to ( Between_DOCTYPE_public_and_system_identifiers_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Between_DOCTYPE_public_and_system_identifiers_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current DOCTYPE token</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">This is a missing-whitespace-between-doctype-public-and-system-identifiers parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-between-doctype-public-and-system-identifiers_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-between-doctype-public-and-system-identifiers_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_double_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_double_quoted_state)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">This is a missing-whitespace-between-doctype-public-and-system-identifiers parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-between-doctype-public-and-system-identifiers_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-between-doctype-public-and-system-identifiers_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_single_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_single_quoted_state)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-quote-before-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-quote-before-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-quote-before-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Reconsume in the Bogus_DOCTYPE_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_DOCTYPE_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_DOCTYPE_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Between_DOCTYPE_public_and_system_identifiers_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current DOCTYPE token</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_double_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_double_quoted_state)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_single_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_single_quoted_state)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-quote-before-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-quote-before-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-quote-before-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Reconsume in the Bogus_DOCTYPE_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_DOCTYPE_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_DOCTYPE_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: After_DOCTYPE_system_keyword_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Switch to the Before_DOCTYPE_system_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_system_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_system_identifier_state)</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Switch to the Before_DOCTYPE_system_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_system_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_system_identifier_state)</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Switch to the Before_DOCTYPE_system_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_system_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_system_identifier_state)</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Switch to the Before_DOCTYPE_system_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( Before_DOCTYPE_system_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Before_DOCTYPE_system_identifier_state)</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">This is a missing-whitespace-after-doctype-system-keyword parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-after-doctype-system-keyword_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-after-doctype-system-keyword_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_double_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_double_quoted_state)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">This is a missing-whitespace-after-doctype-system-keyword parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-whitespace-after-doctype-system-keyword_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-whitespace-after-doctype-system-keyword_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_single_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_single_quoted_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is a missing-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-quote-before-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-quote-before-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-quote-before-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Reconsume in the Bogus_DOCTYPE_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_DOCTYPE_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_DOCTYPE_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Before_DOCTYPE_system_identifier_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_double_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_double_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_double_quoted_state)</span>

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">Set the DOCTYPE token 's system identifier to the empty string ( not missing ) , then switch to the DOCTYPE_system_identifier_single_quoted_state</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . system_identifier , empty_string [_] ) | switch_to ( DOCTYPE_system_identifier_single_quoted_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = EVal(empty_string)</span>

<span style="color: blue; ">LVar(state) = EVal(DOCTYPE_system_identifier_single_quoted_state)</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is a missing-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-quote-before-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-quote-before-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-quote-before-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [_] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Reconsume in the Bogus_DOCTYPE_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_DOCTYPE_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_DOCTYPE_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: DOCTYPE_system_identifier_double_quoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [34, 34] (U+0022 QUOTATION MARK ("))

<span style="color: blue; ">Switch to the After_DOCTYPE_system_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_system_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_system_identifier_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current DOCTYPE token 's system identifier</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , DOCTYPE_token [_] . system_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(system_identifier),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is an abrupt-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( abrupt-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(abrupt-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current DOCTYPE token 's system identifier</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , DOCTYPE_token [_] . system_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(system_identifier),ANone)),EVal(current_input_character))</span>





name: DOCTYPE_system_identifier_single_quoted_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [39, 39] (U+0027 APOSTROPHE ('))

<span style="color: blue; ">Switch to the After_DOCTYPE_system_identifier_state</span>

<span style="color: blue; "> =>  switch_to ( After_DOCTYPE_system_identifier_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(After_DOCTYPE_system_identifier_state)</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Append a U+FFFD REPLACEMENT CHARACTER character to the current DOCTYPE token 's system identifier</span>

<span style="color: blue; "> =>  append_to ( U+FFFD_REPLACEMENT_CHARACTER_character_token [_] , DOCTYPE_token [_] . system_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(system_identifier),ANone)),EVal(U+FFFD_REPLACEMENT_CHARACTER_character_token))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">This is an abrupt-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( abrupt-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(abrupt-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Append the current input character to the current DOCTYPE token 's system identifier</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , DOCTYPE_token [_] . system_identifier ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(current_token),Att1(A1Var(system_identifier),ANone)) = ECons(EAtt1(EVal(current_token),Att1(A1Var(system_identifier),ANone)),EVal(current_input_character))</span>





name: After_DOCTYPE_system_identifier_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [9, 9] (U+0009 CHARACTER TABULATION (tab))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [10, 10] (U+000A LINE FEED (LF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [12, 12] (U+000C FORM FEED (FF))

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [32, 32] (U+0020 SPACE)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the current DOCTYPE token</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-doctype parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-doctype_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-doctype_parse_error))</span>

<span style="color: blue; ">Set the DOCTYPE token 's [0] force-quirks flag to on</span>

<span style="color: blue; "> =>  set_to ( DOCTYPE_token [0] . force-quirks_flag , on [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LAtt(LVar(x0),Att1(A1Var(force-quirks_flag),ANone)) = EVal(on)</span>

<span style="color: blue; ">Emit the DOCTYPE token 's [0]</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [0] . data ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(x0))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is an unexpected-character-after-doctype-system-identifier parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-character-after-doctype-system-identifier_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-character-after-doctype-system-identifier_parse_error))</span>

<span style="color: blue; ">Reconsume [0] in the Bogus_DOCTYPE_state</span>

<span style="color: blue; "> =>  reconsume_in ( Bogus_DOCTYPE_state [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Bogus_DOCTYPE_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

<span style="color: blue; "></span>

<span style="color: blue; "> =>  </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>





name: Bogus_DOCTYPE_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [62, 62] (U+003E GREATER-THAN SIGN (&gt;))

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

<span style="color: blue; ">Emit the DOCTYPE token</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

char: [0, 0] (U+0000 NULL)

<span style="color: blue; ">This [0] is an unexpected-null-character parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unexpected-null-character_parse_error [0] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unexpected-null-character_parse_error))</span>

<span style="color: blue; ">Ignore This [0]</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">Emit the DOCTYPE token</span>

<span style="color: blue; "> =>  emit ( DOCTYPE_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_token))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Ignore the character</span>

<span style="color: blue; "> =>  ignore_the_character </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">Skip</span>





name: CDATA_section_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [93, 93] (U+005D RIGHT SQUARE BRACKET (]))

<span style="color: blue; ">Switch to the CDATA_section_bracket_state</span>

<span style="color: blue; "> =>  switch_to ( CDATA_section_bracket_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(CDATA_section_bracket_state)</span>

char: [-1, -1] (EOF)

<span style="color: blue; ">This is an eof-in-cdata parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( eof-in-cdata_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(eof-in-cdata_parse_error))</span>

<span style="color: blue; ">Emit an end-of-file token</span>

<span style="color: blue; "> =>  emit ( end-of-file_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(end-of-file_token))</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit the current input character as a character token</span>

<span style="color: blue; "> =>  emit ( current_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>





name: CDATA_section_bracket_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [93, 93] (U+005D RIGHT SQUARE BRACKET (]))

<span style="color: blue; ">Switch to the CDATA_section_end_state</span>

<span style="color: blue; "> =>  switch_to ( CDATA_section_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(CDATA_section_end_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit a U+005D RIGHT SQUARE BRACKET character token</span>

<span style="color: blue; "> =>  emit ( U+005D_RIGHT_SQUARE_BRACKET_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+005D_RIGHT_SQUARE_BRACKET_character_token))</span>

<span style="color: blue; ">Reconsume in the CDATA_section_state</span>

<span style="color: blue; "> =>  reconsume_in ( CDATA_section_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(CDATA_section_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: CDATA_section_end_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [93, 93] (U+005D RIGHT SQUARE BRACKET (]))

<span style="color: blue; ">Emit a U+005D RIGHT SQUARE BRACKET character token</span>

<span style="color: blue; "> =>  emit ( U+005D_RIGHT_SQUARE_BRACKET_character_token [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+005D_RIGHT_SQUARE_BRACKET_character_token))</span>

char: [62, 62] (U+003E GREATER-THAN SIGN character)

<span style="color: blue; ">Switch to the Data_state</span>

<span style="color: blue; "> =>  switch_to ( Data_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Data_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Emit two U+005D RIGHT SQUARE BRACKET character tokens</span>

<span style="color: blue; "> =>  emit ( U+005D_RIGHT_SQUARE_BRACKET_character_token [_] ) | emit ( U+005D_RIGHT_SQUARE_BRACKET_character_token [_] )</span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+005D_RIGHT_SQUARE_BRACKET_character_token))</span>

<span style="color: blue; ">LVar(output_tokens) = ECons(EVal(output_tokens),EVal(U+005D_RIGHT_SQUARE_BRACKET_character_token))</span>

<span style="color: blue; ">Reconsume in the CDATA_section_state</span>

<span style="color: blue; "> =>  reconsume_in ( CDATA_section_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(CDATA_section_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Character_reference_state

prev:

<span style="color: blue; ">Set the temporary buffer [0] to the empty string</span>

<span style="color: blue; "> =>  set_to ( temporary_buffer [0] , empty_string [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = EVal(empty_string)</span>

<span style="color: blue; ">Append a U+0026 AMPERSAND ( & amp ;) character to the temporary buffer [0]</span>

<span style="color: blue; "> =>  append_to ( U+0026_AMPERSAND_character [_] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(U+0026_AMPERSAND_character))</span>

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [48, 57], [65, 90], [97, 122] (ASCII alphanumeric)

<span style="color: blue; ">Reconsume in the Named_character_reference_state</span>

<span style="color: blue; "> =>  reconsume_in ( Named_character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Named_character_reference_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [35, 35] (U+0023 NUMBER SIGN (#))

<span style="color: blue; ">Append the current input character to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

<span style="color: blue; ">Switch to the Numeric_character_reference_state</span>

<span style="color: blue; "> =>  switch_to ( Numeric_character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Numeric_character_reference_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: red; ">Flush code points consumed as a character reference</span>

<span style="color: red; "> =>  flush_code_points_consumed_as_a_character_reference </span>

<span style="color: red; ">==></span>

<span style="color: red; ">If Or(Equal(EVal(return_state),EVal(Attribute_value_double_quoted_state)),Or(Equal(EVal(return_state),EVal(Attribute_value_single_quoted_state)),Equal(EVal(return_state),EVal(Attribute_value_unquoted_state)))) :</span>

<span style="color: red; "> then  LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(temporary_buffer))</span>

<span style="color: red; "> else  LVar(output_tokens) = ECons(EVal(output_tokens),EVal(temporary_buffer))</span>

<span style="color: blue; ">Reconsume in the return state</span>

<span style="color: blue; "> =>  reconsume_in ( return_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(return_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Ambiguous_ampersand_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [48, 57], [65, 90], [97, 122] (ASCII alphanumeric)

<span style="color: red; ">If the character reference was consumed as part of an attribute [0] , then append the current input character [1] to an attribute [0] value . Otherwise , emit the current input character [1] as a character token</span>

<span style="color: red; "> =>  if_then ( the_character_reference_was_consumed_as_part_of_an_attribute , append_to ( current_input_character [1] , attribute [0] . value ) )  | otherwise ( emit ( current_input_character [1] ) ) </span>

<span style="color: red; ">==></span>

<span style="color: red; ">If Or(Equal(EVal(return_state),EVal(Attribute_value_double_quoted_state)),Or(Equal(EVal(return_state),EVal(Attribute_value_single_quoted_state)),Equal(EVal(return_state),EVal(Attribute_value_unquoted_state)))) :</span>

<span style="color: red; "> then  LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(current_input_character))</span>

<span style="color: red; "> else  LVar(output_tokens) = ECons(EVal(output_tokens),EVal(current_input_character))</span>

char: [59, 59] (U+003B SEMICOLON (;))

<span style="color: blue; ">This is an unknown-named-character-reference parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( unknown-named-character-reference_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(unknown-named-character-reference_parse_error))</span>

<span style="color: blue; ">Reconsume in the return state</span>

<span style="color: blue; "> =>  reconsume_in ( return_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(return_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the return state</span>

<span style="color: blue; "> =>  reconsume_in ( return_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(return_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Numeric_character_reference_state

prev:

<span style="color: blue; ">Set the character reference code to zero ( 0).Consume the next input character </span>

<span style="color: blue; "> =>  set_to ( character_reference_code [_] , 0 [_] ) | consume ( next_input_character [_] )</span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EVal(0)</span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [120, 120] (U+0078 LATIN SMALL LETTER X)

<span style="color: blue; ">Append the current input character to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

<span style="color: blue; ">Switch to the Hexadecimal_character_reference_start_state</span>

<span style="color: blue; "> =>  switch_to ( Hexadecimal_character_reference_start_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Hexadecimal_character_reference_start_state)</span>

char: [88, 88] (U+0058 LATIN CAPITAL LETTER X)

<span style="color: blue; ">Append the current input character to the temporary buffer</span>

<span style="color: blue; "> =>  append_to ( current_input_character [_] , temporary_buffer [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(temporary_buffer) = ECons(EVal(temporary_buffer),EVal(current_input_character))</span>

<span style="color: blue; ">Switch to the Hexadecimal_character_reference_start_state</span>

<span style="color: blue; "> =>  switch_to ( Hexadecimal_character_reference_start_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Hexadecimal_character_reference_start_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">Reconsume in the Decimal_character_reference_start_state</span>

<span style="color: blue; "> =>  reconsume_in ( Decimal_character_reference_start_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Decimal_character_reference_start_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Hexadecimal_character_reference_start_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [48, 57], [65, 70], [97, 102] (ASCII hex digit)

<span style="color: blue; ">Reconsume in the Hexadecimal_character_reference_state</span>

<span style="color: blue; "> =>  reconsume_in ( Hexadecimal_character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Hexadecimal_character_reference_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is an absence-of-digits-in-numeric-character-reference parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( absence-of-digits-in-numeric-character-reference_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(absence-of-digits-in-numeric-character-reference_parse_error))</span>

<span style="color: red; ">Flush code points consumed as a character reference</span>

<span style="color: red; "> =>  flush_code_points_consumed_as_a_character_reference </span>

<span style="color: red; ">==></span>

<span style="color: red; ">If Or(Equal(EVal(return_state),EVal(Attribute_value_double_quoted_state)),Or(Equal(EVal(return_state),EVal(Attribute_value_single_quoted_state)),Equal(EVal(return_state),EVal(Attribute_value_unquoted_state)))) :</span>

<span style="color: red; "> then  LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(temporary_buffer))</span>

<span style="color: red; "> else  LVar(output_tokens) = ECons(EVal(output_tokens),EVal(temporary_buffer))</span>

<span style="color: blue; ">Reconsume in the return state</span>

<span style="color: blue; "> =>  reconsume_in ( return_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(return_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Decimal_character_reference_start_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [48, 57] (ASCII digit)

<span style="color: blue; ">Reconsume in the Decimal_character_reference_state</span>

<span style="color: blue; "> =>  reconsume_in ( Decimal_character_reference_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Decimal_character_reference_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is an absence-of-digits-in-numeric-character-reference parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( absence-of-digits-in-numeric-character-reference_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(absence-of-digits-in-numeric-character-reference_parse_error))</span>

<span style="color: red; ">Flush code points consumed as a character reference</span>

<span style="color: red; "> =>  flush_code_points_consumed_as_a_character_reference </span>

<span style="color: red; ">==></span>

<span style="color: red; ">If Or(Equal(EVal(return_state),EVal(Attribute_value_double_quoted_state)),Or(Equal(EVal(return_state),EVal(Attribute_value_single_quoted_state)),Equal(EVal(return_state),EVal(Attribute_value_unquoted_state)))) :</span>

<span style="color: red; "> then  LAtt(LVar(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))) = ECons(EAtt1(EVal(current_token),Att1(A1Var(attributes),Att1(LastElem,Att1(A1Var(value),ANone)))),EVal(temporary_buffer))</span>

<span style="color: red; "> else  LVar(output_tokens) = ECons(EVal(output_tokens),EVal(temporary_buffer))</span>

<span style="color: blue; ">Reconsume in the return state</span>

<span style="color: blue; "> =>  reconsume_in ( return_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(return_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Hexadecimal_character_reference_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [48, 57] (ASCII digit)

<span style="color: blue; ">Multiply the character reference code [1] by 16</span>

<span style="color: blue; "> =>  multiply_the_character_reference_code_by ( 16 [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EMul(EVal(character_reference_code),EVal(16))</span>

<span style="color: blue; ">Add a numeric version of the current input character [0] ( subtract 0x0030 from the current input character [0] code point ) to the character reference code [1]</span>

<span style="color: blue; "> =>  add_to_the_character_reference_code ( current_input_character [0] . numeric_version ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EPlus(EVal(character_reference_code),EAtt2(EVal(current_input_character),A2Var(numeric_version)))</span>

char: [48, 57], [65, 70] (ASCII upper hex digit)

<span style="color: blue; ">Multiply the character reference code [1] by 16</span>

<span style="color: blue; "> =>  multiply_the_character_reference_code_by ( 16 [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EMul(EVal(character_reference_code),EVal(16))</span>

<span style="color: blue; ">Add a numeric version of the current input character [0] as a hexadecimal digit ( subtract 0x0037 from the current input character [0] code point ) to the character reference code [1]</span>

<span style="color: blue; "> =>  add_to_the_character_reference_code ( current_input_character [0] . numeric_version ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EPlus(EVal(character_reference_code),EAtt2(EVal(current_input_character),A2Var(numeric_version)))</span>

char: [48, 57], [97, 102] (ASCII lower hex digit)

<span style="color: blue; ">Multiply the character reference code [1] by 16</span>

<span style="color: blue; "> =>  multiply_the_character_reference_code_by ( 16 [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EMul(EVal(character_reference_code),EVal(16))</span>

<span style="color: blue; ">Add a numeric version of the current input character [0] as a hexadecimal digit ( subtract 0x0057 from the current input character [0] code point ) to the character reference code [1]</span>

<span style="color: blue; "> =>  add_to_the_character_reference_code ( current_input_character [0] . numeric_version ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EPlus(EVal(character_reference_code),EAtt2(EVal(current_input_character),A2Var(numeric_version)))</span>

char: [59, 59] (U+003B SEMICOLON)

<span style="color: blue; ">Switch to the Numeric_character_reference_end_state</span>

<span style="color: blue; "> =>  switch_to ( Numeric_character_reference_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Numeric_character_reference_end_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-semicolon-after-character-reference parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-semicolon-after-character-reference_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-semicolon-after-character-reference_parse_error))</span>

<span style="color: blue; ">Reconsume in the Numeric_character_reference_end_state</span>

<span style="color: blue; "> =>  reconsume_in ( Numeric_character_reference_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Numeric_character_reference_end_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>





name: Decimal_character_reference_state

prev:

<span style="color: blue; ">Consume the next input character </span>

<span style="color: blue; "> =>  consume ( next_input_character [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">If Equal(EVal(reconsume_flag),EVal(False)) :</span>

<span style="color: blue; "> then  If Equal(EVal(input_index),ELength(EVal(input))) :</span>

<span style="color: blue; ">  then   LVar(current_input_character) = EVal(eof)</span>

<span style="color: blue; ">  else   LVar(current_input_character) = EIdxof(EVal(input),EVal(input_index))</span>

<span style="color: blue; ">  LVar(input_index) = EPlus(EVal(input_index),ELength(EVal(next_input_character)))</span>

<span style="color: blue; "> else  LVar(reconsume_flag) = EVal(False)</span>

trans:

char: [48, 57] (ASCII digit)

<span style="color: blue; ">Multiply the character reference code [1] by 10</span>

<span style="color: blue; "> =>  multiply_the_character_reference_code_by ( 10 [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EMul(EVal(character_reference_code),EVal(10))</span>

<span style="color: blue; ">Add a numeric version of the current input character [0] ( subtract 0x0030 from the current input character [0] code point ) to the character reference code [1]</span>

<span style="color: blue; "> =>  add_to_the_character_reference_code ( current_input_character [0] . numeric_version ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(character_reference_code) = EPlus(EVal(character_reference_code),EAtt2(EVal(current_input_character),A2Var(numeric_version)))</span>

char: [59, 59] (U+003B SEMICOLON)

<span style="color: blue; ">Switch to the Numeric_character_reference_end_state</span>

<span style="color: blue; "> =>  switch_to ( Numeric_character_reference_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Numeric_character_reference_end_state)</span>

char: [-1, 65535] (Anything else)

<span style="color: blue; ">This is a missing-semicolon-after-character-reference parse error</span>

<span style="color: blue; "> =>  this_is_parse_error ( missing-semicolon-after-character-reference_parse_error [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(error_list) = ECons(EVal(error_list),EVal(missing-semicolon-after-character-reference_parse_error))</span>

<span style="color: blue; ">Reconsume in the Numeric_character_reference_end_state</span>

<span style="color: blue; "> =>  reconsume_in ( Numeric_character_reference_end_state [_] ) </span>

<span style="color: blue; ">==></span>

<span style="color: blue; ">LVar(state) = EVal(Numeric_character_reference_end_state)</span>

<span style="color: blue; ">LVar(reconsume_flag) = EVal(True)</span>






