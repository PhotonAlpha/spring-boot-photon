/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/03/10
 */
package com.ethan.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WSOutPutMessage {
    private String header;
    private String body;
    private String title;
    private Boolean success;
    private String userId;
}
