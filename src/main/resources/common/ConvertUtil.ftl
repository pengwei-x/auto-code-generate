package ${packageName}.common;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author codeGenerate
 */
public class ConvertUtil extends BeanUtil {

    /**
     * Page<DTO>对象转换Page<VO
     * @param record IPage
     * @param clazz clazz
     * @param <T> T
     * @return IPage<clazz>
     */
    public static <T> IPage<T> convert(IPage record, Class<T> clazz) {
        if (record == null || record.getRecords().size() == 0) {
            return record;
        }
        return record.convert(r -> {
            return toBean(r, clazz);
        });
    }

    /**
     * List<DTO>对象转换List<VO>
     * @param list list
     * @param clazz clazz
     * @param <T> T
     * @return List<clazz>
     */
    public static <T> List<T> convert(List list, Class<T> clazz) {
        if (list == null || list.size() == 0) {
            return new ArrayList<T>();
        }
        List<T> l = (List<T>) list.stream().map(r -> {
            T t = toBean(r, clazz);
            return t;
        }).collect(toList());
        return l;
    }
}