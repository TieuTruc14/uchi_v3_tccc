package com.vn.osp.task;

import com.vn.osp.modelview.PreventContractList;
import com.vn.osp.modelview.TransactionProperty;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.STPAPIUtil;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by VIETDUNG on 23/05/2017.
 */
public class BackupFromSTPCallable implements Callable {
    private int index;
    private int total;
    private List<TransactionProperty> trans;
    private List<TransactionProperty> errors;
    private static BackupFromSTPCallable instance;

    public BackupFromSTPCallable() {
    }

    public List<TransactionProperty> call() throws Exception {
        while (index < total){
            Boolean result = QueryFactory.addTransactionProperty(trans.get(index));
            if(result == false) errors.add(trans.get(index));
            index++;
        }
        return errors;
    }
    public static synchronized BackupFromSTPCallable getInstance(){
        if (instance == null) {
            instance = new BackupFromSTPCallable();
        }
        return instance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TransactionProperty> getTrans() {
        return trans;
    }

    public void setTrans(List<TransactionProperty> trans) {
        this.trans = trans;
    }

    public List<TransactionProperty> getErrors() {
        return errors;
    }

    public void setErrors(List<TransactionProperty> errors) {
        this.errors = errors;
    }
}
