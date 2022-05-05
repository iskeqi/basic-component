package tech.taoq.iot.rcs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 节点
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Node {

	/**
	 * 节点id
	 */
	private String id;

	/**
	 * 节点x坐标
	 */
	private String x;

	/**
	 * 节点y坐标
	 */
	private String y;
}
