package entity;

import java.util.List;

/**分页结果类
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-19 22:00
 */
public class PageResult <T> {
    private  long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
