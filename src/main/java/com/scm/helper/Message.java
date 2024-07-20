package com.scm.helper;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String content;

    @Builder.Default // ye annotation default value ko add karega
    private MessageType type =  MessageType.blue;

}
