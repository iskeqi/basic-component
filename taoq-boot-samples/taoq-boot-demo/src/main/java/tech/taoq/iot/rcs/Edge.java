package tech.taoq.iot.rcs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 路径
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Edge {

	/**
	 * 路径id
	 */
	private String id;

	/**
	 * 路径类型[LINE:直线路径 CIRCLE:圆弧路径 BESSEL:贝塞尔曲线]
	 */
	private String type;

	/**
	 * 路径起点
	 */
	private Node start;

	/**
	 * 路径终点
	 */
	private Node end;

	/**
	 * 圆弧的圆心x坐标/贝塞尔曲线靠近起点的控制点的x坐标
	 */
	private String sx;

	/**
	 * 圆弧的圆心y坐标/贝塞尔曲线靠近起点的控制点的y坐标
	 */
	private String sy;

	/**
	 * 贝塞尔曲线靠近起点的控制点的x坐标
	 */
	private String ex;

	/**
	 * 贝塞尔曲线靠近起点的控制点的y坐标
	 */
	private String ey;
}
