package ${packageName}.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
* @author
* @Description: 统一分页封装
*/
@Data
public class PageResult<T> extends Page<T> implements IPage<T>{

    private int code;
    private String message;
    private List<T> records;

    public PageResult(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }

    @Override
    public Page<T> setRecords(List<T> records) {
        this.records = records;
        check();
        return this;
    }

    public void check() {
        if(this.getRecords() == null || this.getRecords().size() == 0){
            setMessage("没有数据");
            setCode(201);
        }else {
            setMessage("请求成功");
            setCode(200);
        }
    }
}