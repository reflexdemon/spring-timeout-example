package io.vpv.timeout.timeout.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TimeoutMessage {
    private Long timeout;
    private String message;
}
