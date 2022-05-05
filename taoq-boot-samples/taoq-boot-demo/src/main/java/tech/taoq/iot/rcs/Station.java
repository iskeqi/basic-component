package tech.taoq.iot.rcs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 站点
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Station extends Node {

	/**
	 * 站点类型[WORK:工作站点 PARK:停靠站点 CHARGE:充电站点]
	 */
	private String type;

	/**
	 * 站点名称
	 */
	private String name;

	/**
	 * 站点所在的路径id???一个站点不是处在两个路径之间吗?
	 */
	private String edgeId;

	/**
	 * 站点角度[顺时针,单位:度数]
	 */
	private String yaw;

	/**
	 * 进入点
	 */
	private Point enter;

	/**
	 * 退出点
	 */
	private Point exit;

	public static class Point {

		/**
		 * 站点 x 坐标
		 */
		private String x;

		/**
		 * 站点 y 坐标
		 */
		private String y;

		/**
		 * 站点角度
		 */
		private String yaw;
	}

	public enum Type {
		WORK, PARK, CHARGE
	}
}
