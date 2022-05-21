package tech.taoq.system.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import tech.taoq.common.pojo.IResultStatusEnum;
import tech.taoq.system.domain.db.ResultCodeDO;
import tech.taoq.system.mapper.ResultCodeMapper;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 统计全局响应状态码
 *
 * @author keqi
 */
@Component
@ConditionalOnProperty(prefix = "taoq.system", value = "result-code")
public class ResultCodeService {

    @Autowired
    private ResultCodeMapper resultCodeMapper;

    @PostConstruct
    public void init() {
        String packageName = IResultStatusEnum.class.getPackage().getName();
        String rootPackageName = packageName.substring(0, packageName.indexOf(".", packageName.indexOf(".") + 1));

        // 找到所有实现了 IResultStatusEnum 接口的实现类的反射对象
        List<String> list = new ArrayList<>();
        Reflections reflections = new Reflections(rootPackageName);
        Set<Class<? extends IResultStatusEnum>> types = reflections.getSubTypesOf(IResultStatusEnum.class);
        for (Class<? extends IResultStatusEnum> type : types) {
            // 获取反射对象的所有枚举值
            for (IResultStatusEnum constant : type.getEnumConstants()) {
                list.add(constant.toString());
            }
        }

        // 进行增量修改
        list.sort(String::compareTo);
        for (String resultEnumObj : list) {
            String[] split = resultEnumObj.split(IResultStatusEnum.DELIMITER);
            String code = split[0];
            String codeName = split[1];
            ResultCodeDO t = resultCodeMapper.selectOne(Wrappers.query(new ResultCodeDO().setCode(code)));
            if (t == null) {
                resultCodeMapper.insert(new ResultCodeDO().setCode(code).setCodeName(codeName));
            } else {
                t.setCodeName(codeName);
                resultCodeMapper.updateById(t);
            }
        }
    }
}
