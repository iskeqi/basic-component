package tech.taoq.system.runner;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.taoq.common.pojo.IResultStatusEnum;
import tech.taoq.system.SystemProperties;
import tech.taoq.system.domain.db.ResultCodeDO;
import tech.taoq.system.mapper.ResultCodeMapper;

import java.util.*;

/**
 * 统计全局响应状态码
 *
 * @author keqi
 */
@Slf4j
@Component
public class ResultCodeRunner implements CommandLineRunner {

    @Autowired(required = false)
    private ResultCodeMapper resultCodeMapper;
    @Autowired
    private SystemProperties systemProperties;

    @Override
    public void run(String... args) {
        try {
            String packageName = IResultStatusEnum.class.getPackage().getName();
            String rootPackageName = packageName.substring(0, packageName.indexOf(".", packageName.indexOf(".") + 1));
            systemProperties.getPackages().add(rootPackageName);

            List<String> list = new ArrayList<>();
            Map<String, String> newCodeMap = new HashMap<>();
            Map<String, ResultCodeDO> oldCodeMap = new HashMap<>();

            // 找到所有实现了 IResultStatusEnum 接口的实现类的反射对象
            Set<Class<? extends IResultStatusEnum>> types = new HashSet<>();
            for (String packageStr : systemProperties.getPackages()) {
                Reflections reflections = new Reflections(packageStr);
                types.addAll(reflections.getSubTypesOf(IResultStatusEnum.class));
            }

            for (Class<? extends IResultStatusEnum> type : types) {
                // 获取反射对象的所有枚举值
                for (IResultStatusEnum constant : type.getEnumConstants()) {
                    String codeAndNameStr = constant.toString();
                    String[] split = codeAndNameStr.split(IResultStatusEnum.DELIMITER);
                    String code = split[0];
                    String codeName = split[1];

                    if (newCodeMap.get(code) != null) {
                        log.error("There is a IResultStatusEnum class {} with the same code", type.getCanonicalName());
                        System.exit(1);
                    }

                    newCodeMap.put(code, codeName);
                    list.add(code);
                }
            }

            if (systemProperties.getResultCode() && resultCodeMapper != null) {
                // 进行增量更新

                List<ResultCodeDO> resultCodeDOList = resultCodeMapper.selectList(null);
                for (ResultCodeDO resultCodeDO : resultCodeDOList) {
                    if (newCodeMap.get(resultCodeDO.getCode()) == null) {
                        // 删除已不存在的响应状态码
                        resultCodeMapper.deleteById(resultCodeDO.getId());
                    }
                    oldCodeMap.put(resultCodeDO.getCode(), resultCodeDO);
                }

                list.sort(String::compareTo);
                for (String code : list) {
                    ResultCodeDO t = oldCodeMap.get(code);
                    String codeName = newCodeMap.get(code);
                    if (t == null) {
                        // 新增新的响应状态码
                        resultCodeMapper.insert(new ResultCodeDO().setCode(code).setCodeName(codeName));
                    } else {
                        if (!Objects.equals(t.getCodeName(), codeName)) {
                            // 修改已修改了描述内容的响应状态码
                            t.setCodeName(codeName);
                            resultCodeMapper.updateById(t);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            log.error("ResultCodeRunner init failure", e);
        }
    }
}
