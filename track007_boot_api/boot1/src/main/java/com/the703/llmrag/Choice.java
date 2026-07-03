package com.the703.llmrag;

public record Choice(
	int index, Message message, String finish_reason
) {}

//@Value - import lombok.Value;
// ---------
//@Getter
//@FieldDefault(makeFinal=true, level=AccessLevel.PRIVATE) - 모든 필드 private final 변경
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode