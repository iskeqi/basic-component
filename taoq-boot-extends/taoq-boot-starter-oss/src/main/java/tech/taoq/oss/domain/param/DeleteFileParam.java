package tech.taoq.oss.domain.param;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DeleteFileParam
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DeleteFileParam {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
